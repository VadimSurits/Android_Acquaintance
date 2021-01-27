package com.example.android_acquaintance;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.android_acquaintance.NoteFragment.CURRENT_NOTE;

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
        initList(view);
    }

    private void initList(View view) {
        notes = new Note[]{
                new Note(0,
                        getString(R.string.first_note_title),
                        getString(R.string.first_note_content),
                        Calendar.getInstance(),
                        Color.BLUE),
                new Note(1,
                        getString(R.string.second_note_title),
                        getString(R.string.second_note_content),
                        Calendar.getInstance(),
                        Color.GRAY),
                new Note(2,
                        getString(R.string.third_note_title),
                        getString(R.string.third_note_content),
                        Calendar.getInstance(),
                        Color.YELLOW),
        };

        LinearLayout linearView = (LinearLayout) view;
        for (Note n : notes) {
            Context context = getContext();
            if (context != null) {
                LinearLayout innerLayout = new LinearLayout(context);
                innerLayout.setOrientation(LinearLayout.VERTICAL);
                innerLayout.setBackgroundColor(n.getColor());
                TextView tv1 = new TextView(context);
                TextView tv2 = new TextView(context);
                TextView tv3 = new TextView(context);
                tv1.setText(String.format("%s - id:%s", n.getTitle(), n.getId()));
                tv1.setTextSize(25);
                tv2.setText(n.getContent());
                tv2.setTextSize(20);
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy",
                        Locale.getDefault());
                tv3.setText(formatter.format(n.getCreationDate().getTime()));
                tv3.setTextSize(15);
                innerLayout.addView(tv1);
                innerLayout.addView(tv2);
                innerLayout.addView(tv3);
                linearView.addView(innerLayout);
                innerLayout.setOnClickListener(v -> {
                    currentNote = n;
                    showNote(currentNote);
                });
            }
        }
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
        Intent intent = new Intent(getActivity(), NoteActivity.class);
        intent.putExtra(NoteFragment.CURRENT_NOTE, currentNote);
        startActivity(intent);
    }
}