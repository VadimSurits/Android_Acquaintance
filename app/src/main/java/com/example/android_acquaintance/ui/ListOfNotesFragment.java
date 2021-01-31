package com.example.android_acquaintance.ui;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_acquaintance.Note;
import com.example.android_acquaintance.R;

import java.util.Calendar;

import static com.example.android_acquaintance.ui.NoteFragment.CURRENT_NOTE;


public class ListOfNotesFragment extends Fragment {

    private boolean isLandscape;
    private Note[] notes;
    private Note currentNote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNotes();
        RecyclerView recyclerView = view.findViewById(R.id.notes_recycler_view);
        initRecyclerView(recyclerView, notes);
    }

    private void initNotes() {
        notes = new Note[]{
                new Note(getString(R.string.first_note_title),
                        getString(R.string.first_note_content),
                        Calendar.getInstance(),
                        Color.MAGENTA),
                new Note(getString(R.string.second_note_title),
                        getString(R.string.second_note_content),
                        Calendar.getInstance(),
                        Color.GRAY),
                new Note(getString(R.string.third_note_title),
                        getString(R.string.third_note_content),
                        Calendar.getInstance(),
                        Color.YELLOW),
                new Note(getString(R.string.fourth_note_title),
                        getString(R.string.fourth_note_content),
                        Calendar.getInstance(),
                        Color.CYAN),
                new Note(getString(R.string.fifth_note_title),
                        getString(R.string.fifth_note_content),
                        Calendar.getInstance(),
                        Color.BLUE),
                new Note(getString(R.string.sixth_note_title),
                        getString(R.string.sixth_note_content),
                        Calendar.getInstance(),
                        Color.DKGRAY),
                new Note(getString(R.string.seventh_note_title),
                        getString(R.string.seventh_note_content),
                        Calendar.getInstance(),
                        Color.GREEN),
                new Note(getString(R.string.eighth_note_title),
                        getString(R.string.eighth_note_content),
                        Calendar.getInstance(),
                        Color.RED),
                new Note(getString(R.string.ninth_note_title),
                        getString(R.string.ninth_note_content),
                        Calendar.getInstance(),
                        Color.WHITE),
                new Note(getString(R.string.tenth_note_title),
                        getString(R.string.tenth_note_content),
                        Calendar.getInstance(),
                        Color.MAGENTA),
                new Note(getString(R.string.eleventh_note_title),
                        getString(R.string.eleventh_note_content),
                        Calendar.getInstance(),
                        Color.GRAY),
                new Note(getString(R.string.twelfth_note_title),
                        getString(R.string.twelfth_note_content),
                        Calendar.getInstance(),
                        Color.YELLOW),
        };
    }

    private void initRecyclerView(RecyclerView recyclerView, Note[] notes) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NotesAdapter adapter = new NotesAdapter(notes);
        adapter.setOnItemClickListener((position, note) -> {
            currentNote = note;
            showNote(currentNote);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            currentNote = notes[0];
        }
        if (isLandscape) {
            showLandNote(currentNote);
        }
    }

    private void showNote(Note currentNote) {
        if (isLandscape) {
            showLandNote(currentNote);
        } else {
            showPortNote(currentNote);
        }
    }

    private void showLandNote(Note currentNote) {
        NoteFragment fragment = NoteFragment.newInstance(currentNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.note_layout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNote(Note currentNote) {
        NoteFragment fragment = NoteFragment.newInstance(currentNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("list_fragment");
        fragmentTransaction.replace(R.id.list_of_notes_fragment_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}