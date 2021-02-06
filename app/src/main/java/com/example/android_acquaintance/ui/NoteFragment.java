package com.example.android_acquaintance.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android_acquaintance.Note;
import com.example.android_acquaintance.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteFragment extends Fragment {

    public static final String CURRENT_NOTE = "currentNote";
    private Note note;
    private Note changedNote;

    public static NoteFragment newInstance(Note note) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(CURRENT_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        TextInputEditText titleText = view.findViewById(R.id.note_title);
        TextInputEditText contentText = view.findViewById(R.id.note_content);
        TextView dateOfCreationText = view.findViewById(R.id.note_date_of_creation);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy",
                Locale.getDefault());
        dateOfCreationText.setText(String.format("%s: %s", "Дата создания",
                formatter.format(note.getCreationDate().getTime())));
        titleText.setText(note.getTitle());
        contentText.setText(note.getContent());
        view.setBackgroundColor(note.getColor());
        setHasOptionsMenu(true);
        MaterialButton saveButton = view.findViewById(R.id.save_changes_button);
        saveButton.setOnClickListener(v -> {
            changedNote = new Note(titleText.getText().toString(),
                    contentText.getText().toString(),
                    note.getCreationDate(),
                    note.getColor());
            //как коррректно передать changedNote в качестве выбранной Note в RecyclerView?
            // (чтобы независимо от режима экрана при нажатии на эту кнопку changedNote
            // сохранялась и отображалась в ListOfNotesFragment).
        });
        return view;
    }
}