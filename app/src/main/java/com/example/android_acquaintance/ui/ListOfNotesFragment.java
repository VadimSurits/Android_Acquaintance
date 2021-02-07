package com.example.android_acquaintance.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_acquaintance.MainActivity;
import com.example.android_acquaintance.Navigation;
import com.example.android_acquaintance.Note;
import com.example.android_acquaintance.NotesSource;
import com.example.android_acquaintance.NotesSourceInterface;
import com.example.android_acquaintance.R;
import com.example.android_acquaintance.observe.Observer;
import com.example.android_acquaintance.observe.Publisher;

import java.util.Objects;

import static com.example.android_acquaintance.ui.NoteFragment.CURRENT_DATA;
import static com.example.android_acquaintance.ui.NoteFragment.CURRENT_NOTE;

public class ListOfNotesFragment extends Fragment {

    private Note currentNote;
    private NotesSource data;
    private NotesAdapter adapter;
    private RecyclerView recyclerView;
    private Navigation navigation;
    private Publisher publisher;
    private boolean moveToLastPosition;

    public static ListOfNotesFragment newInstance() {
        return new ListOfNotesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (data == null) {
            data = new NotesSource(getResources()).init();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.notes_recycler_view);
        initRecyclerView(recyclerView, data);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        navigation = activity.getNavigation();
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }

    private void initRecyclerView(RecyclerView recyclerView, NotesSourceInterface data) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if (moveToLastPosition) {
            recyclerView.smoothScrollToPosition(data.size() - 1);
            moveToLastPosition = false;
        }

        adapter = new NotesAdapter(data, this);
        NotesSourceInterface finalData = data;
        adapter.setOnItemClickListener((position, note) -> {
            navigation.addFragment(NoteFragment.newInstance(finalData.getNote(position)),
                    true);
            publisher.subscribe(new Observer() {
                @Override
                public void updateNote(Note note) {
                    finalData.changeNote(position, note);
                    adapter.notifyItemChanged(position);
                }
            });
        });

        recyclerView.setAdapter(adapter);
        //декоратор
        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(Objects.requireNonNull
                (ContextCompat.getDrawable(getContext(), R.drawable.separator)));
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNote);
        outState.putParcelable(CURRENT_DATA, data);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            data = savedInstanceState.getParcelable(CURRENT_DATA);
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            currentNote = data.getNote(0);
        }
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v,
                                    @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        if (item.getItemId() == R.id.menu_delete_note) {
            data.deleteNote(position);
            adapter.notifyItemRemoved(position);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem addNote = menu.findItem(R.id.menu_add_note);
        addNote.setOnMenuItemClickListener(item -> {
            navigation.addFragment(NoteFragment.newInstance(), true);
            publisher.subscribe(new Observer() {
                @Override
                public void updateNote(Note note) {
                    data.addNote(note);
                    adapter.notifyItemInserted(data.size() - 1);
                    moveToLastPosition = true;
                }
            });
            return true;
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}