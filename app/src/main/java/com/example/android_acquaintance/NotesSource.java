package com.example.android_acquaintance;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class NotesSource implements NotesSourceInterface {
    private ArrayList<Note> notes;
    private Resources resources;
    private int counter = 0;

    public NotesSource(Resources resources) {
        this.resources = resources;
        notes = new ArrayList<>();
    }

    public NotesSource init() {
        Note[] notesArray = new Note[]{
                new Note(resources.getString(R.string.first_note_title),
                        resources.getString(R.string.first_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.second_note_title),
                        resources.getString(R.string.second_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.third_note_title),
                        resources.getString(R.string.third_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.fourth_note_title),
                        resources.getString(R.string.fourth_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.fifth_note_title),
                        resources.getString(R.string.fifth_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.sixth_note_title),
                        resources.getString(R.string.sixth_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.seventh_note_title),
                        resources.getString(R.string.seventh_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.eighth_note_title),
                        resources.getString(R.string.eighth_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.ninth_note_title),
                        resources.getString(R.string.ninth_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.tenth_note_title),
                        resources.getString(R.string.tenth_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.eleventh_note_title),
                        resources.getString(R.string.eleventh_note_content),
                        Calendar.getInstance(), getColor()),
                new Note(resources.getString(R.string.twelfth_note_title),
                        resources.getString(R.string.twelfth_note_content),
                        Calendar.getInstance(), getColor())
        };
        Collections.addAll(notes, notesArray);
        return this;
    }

    @Override
    public Note getNote(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void deleteNote(int position) {
        notes.remove(position);
    }

    @Override
    public void changeNote(int position, Note note) {
        notes.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    public int getColor() {
        int[] colors = resources.getIntArray(R.array.colors);
        int color = colors[counter];
        if (counter < colors.length - 1) {
            counter++;
        } else counter = 0;
        return color;
    }
}
