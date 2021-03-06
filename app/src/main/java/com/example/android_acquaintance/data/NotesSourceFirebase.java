package com.example.android_acquaintance.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotesSourceFirebase implements NotesSourceInterface {

    private static final String NOTES_COLLECTION = "notes";
    private static final String TAG = "[NotesSourceFirebase]";

    private FirebaseFirestore store = FirebaseFirestore.getInstance();
    private CollectionReference collection = store.collection(NOTES_COLLECTION);
    private List<Note> notes = new ArrayList<>();

    @Override
    public NotesSourceInterface init(final NotesSourceResponse notesSourceResponse) {
        collection.orderBy(NoteMapping.Fields.DATE, Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            notes = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> doc = document.getData();
                                String id = document.getId();
                                Note note = NoteMapping.toNote(id, doc);
                                notes.add(note);
                            }
                            Log.d(TAG, "success " + notes.size() + " qnt");
                            notesSourceResponse.initialized(NotesSourceFirebase.this);
                        } else {
                            Log.d(TAG, "get failed with " + task.getException());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "get failed with " + e);
                    }
                });
        return this;
    }

    @Override
    public Note getNote(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        if (notes == null) {
            return 0;
        }
        return notes.size();
    }

    @Override
    public void deleteNote(int position) {
        collection.document(notes.get(position).getId()).delete();
        notes.remove(position);
    }

    @Override
    public void changeNote(int position, Note note) {
        String id = note.getId();
        collection.document(id).set(NoteMapping.toDocument(note));
        notes.set(position, note);
    }

    @Override
    public void addNote(final Note note) {
        collection.add(NoteMapping.toDocument(note)).addOnSuccessListener
                (new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        note.setId(documentReference.getId());
                    }
                });
    }
}
