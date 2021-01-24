package com.example.android_acquaintance;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ListOfNotesFragment extends Fragment {

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
        LinearLayout linearLayout = (LinearLayout) view;
        Note[] notes = {
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

        for (Note n : notes) {
            TextView tv1 = new TextView(getContext());
            TextView tv2 = new TextView(getContext());
            TextView tv3 = new TextView(getContext());
            tv1.setText(String.format("%s - id:%s", n.getTitle(), n.getId()));
            tv1.setTextSize(25);
            tv1.setBackgroundColor(n.getColor());
            tv2.setText(n.getContent());
            tv2.setTextSize(20);
            tv2.setBackgroundColor(n.getColor());
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy",
                    Locale.getDefault());
            tv3.setText(formatter.format(n.getCreationDate().getTime()));
            tv3.setTextSize(15);
            tv3.setBackgroundColor(n.getColor());
            linearLayout.addView(tv1);
            linearLayout.addView(tv2);
            linearLayout.addView(tv3);
        }
    }
}