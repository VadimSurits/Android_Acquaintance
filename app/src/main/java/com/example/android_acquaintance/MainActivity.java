package com.example.android_acquaintance;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_acquaintance.ui.ListOfNotesFragment;
import com.example.android_acquaintance.ui.NoteFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean isLandscape;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment fragment = getVisibleFragment(fragmentManager);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initStartFragment();
        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initStartFragment() {
        //создание нового фрагмента при создании или пересоздании активити
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListOfNotesFragment listFragment = new ListOfNotesFragment();
        //проверка ориентации и открытого в данный момент фрагмента для того, чтобы при необходимости
        //убрать с экрана лишний созданный стартовый фрагмент в ландшафтной ориентации
        Fragment fragment = fragmentManager.findFragmentById(R.id.list_of_notes_fragment_container);
        if (isLandscape && fragment instanceof NoteFragment) {
            fragmentManager.popBackStack();
        } else {
            //обязательно используем replace, а не add, иначе в той же ситуации ListOfNotesFragment
            //будет накладываться на пересозданный при повороте экрана такой же фрагмент
            fragmentTransaction.replace(R.id.list_of_notes_fragment_container, listFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchText = (SearchView) search.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        MenuItem sort = menu.findItem(R.id.menu_sort);
        sort.setOnMenuItemClickListener(item -> {
            Toast.makeText(MainActivity.this, R.string.menu_sort, Toast.LENGTH_SHORT).show();
            return true;
        });
//        MenuItem addNote = menu.findItem(R.id.menu_add_note);
//        addNote.setOnMenuItemClickListener(item -> {
//            Toast.makeText(MainActivity.this, R.string.menu_add_note, Toast.LENGTH_SHORT).show();
//            return true;
//        });
        MenuItem send = menu.findItem(R.id.menu_send);
        send.setOnMenuItemClickListener(item -> {
            Toast.makeText(MainActivity.this, R.string.menu_send, Toast.LENGTH_SHORT).show();
            return true;
        });
        MenuItem addPhoto = menu.findItem(R.id.menu_add_photo);
        addPhoto.setOnMenuItemClickListener(item -> {
            Toast.makeText(MainActivity.this, R.string.menu_add_photo, Toast.LENGTH_SHORT).show();
            return true;
        });
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = getVisibleFragment(fragmentManager);
        if (!isLandscape && fragment instanceof NoteFragment) {
            search.setVisible(false);
//            addNote.setVisible(false);
            sort.setVisible(false);
            send.setVisible(true);
            addPhoto.setVisible(true);
        } else if (!isLandscape && fragment instanceof ListOfNotesFragment) {
            search.setVisible(true);
//            addNote.setVisible(true);
            sort.setVisible(true);
            send.setVisible(false);
            addPhoto.setVisible(false);
        } else {
            search.setVisible(true);
//            addNote.setVisible(true);
            sort.setVisible(true);
            send.setVisible(true);
            addPhoto.setVisible(true);
        }
        return true;
    }

    private Fragment getVisibleFragment(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        int countFragments = fragments.size();
        for (int i = countFragments - 1; i >= 0; i--) {
            Fragment fragment = fragments.get(i);
            if (fragment.isVisible())
                return fragment;
        }
        return null;
    }
}