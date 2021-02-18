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

import com.example.android_acquaintance.DeleteDialogFragment;
import com.example.android_acquaintance.MainActivity;
import com.example.android_acquaintance.Navigation;
import com.example.android_acquaintance.OnDeleteDialogListener;
import com.example.android_acquaintance.R;
import com.example.android_acquaintance.data.Note;
import com.example.android_acquaintance.data.NotesSourceFirebase;
import com.example.android_acquaintance.data.NotesSourceInterface;
import com.example.android_acquaintance.data.NotesSourceResponse;
import com.example.android_acquaintance.observe.Observer;
import com.example.android_acquaintance.observe.Publisher;

import java.util.Objects;

public class ListOfNotesFragment extends Fragment {

    private NotesSourceInterface data;
    private NotesAdapter adapter;
    private RecyclerView recyclerView;
    private Navigation navigation;
    private Publisher publisher;
    private boolean moveToFirstPosition;

    public static ListOfNotesFragment newInstance() {
        return new ListOfNotesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_notes, container, false);
        recyclerView = view.findViewById(R.id.notes_recycler_view);
        data = new NotesSourceFirebase().init(new NotesSourceResponse() {
            @Override
            public void initialized(NotesSourceInterface notesData) {
                adapter.notifyDataSetChanged();
            }
        });
        initRecyclerView(recyclerView, data);
        setHasOptionsMenu(true);
        adapter.setDataSource(data);
        return view;
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

        adapter = new NotesAdapter(this);
        recyclerView.setAdapter(adapter);
        //декоратор
        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(Objects.requireNonNull
                (ContextCompat.getDrawable(getContext(), R.drawable.separator)));
        recyclerView.addItemDecoration(itemDecoration);

        if (moveToFirstPosition && data.size() > 0) {
            recyclerView.scrollToPosition(0);
            moveToFirstPosition = false;
        }
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
            //здесь добавим диалоговое окно
            DeleteDialogFragment deleteDlgFragment = new DeleteDialogFragment();
            deleteDlgFragment.setCancelable(false);
            deleteDlgFragment.setOnDialogListener(new OnDeleteDialogListener() {
                @Override
                public void onDelete() {
                    data.deleteNote(position);
                    adapter.notifyItemRemoved(position);
                    deleteDlgFragment.dismiss();
                }

                @Override
                public void onCancelDelete() {
                    deleteDlgFragment.dismiss();
                }
            });
            deleteDlgFragment.show(requireActivity().getSupportFragmentManager(),
                    "DeleteFragmentTag");
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem search = menu.findItem(R.id.menu_search);
        MenuItem sort = menu.findItem(R.id.menu_sort);
        MenuItem addNote = menu.findItem(R.id.menu_add_note);
        MenuItem send = menu.findItem(R.id.menu_send);
        MenuItem addPhoto = menu.findItem(R.id.menu_add_photo);
        search.setVisible(true);
        sort.setVisible(true);
        send.setVisible(false);
        addPhoto.setVisible(false);
        addNote.setOnMenuItemClickListener(item -> {
            navigation.addFragment(NoteFragment.newInstance(), true);
            publisher.subscribe(new Observer() {
                @Override
                public void updateNote(Note note) {
                    data.addNote(note);
                    adapter.notifyItemInserted(data.size() - 1);
                    moveToFirstPosition = true;
                }
            });
            return true;
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}