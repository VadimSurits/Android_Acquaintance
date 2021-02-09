package com.example.android_acquaintance;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.android_acquaintance.observe.Publisher;
import com.example.android_acquaintance.ui.ListOfNotesFragment;
import com.example.android_acquaintance.ui.NoteFragment;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment fragment = getVisibleFragment(fragmentManager);

    private Navigation navigation;
    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = new Navigation(getSupportFragmentManager());
        initToolbar();
        getNavigation().addFragment(ListOfNotesFragment.newInstance(), false);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public Publisher getPublisher() {
        return publisher;
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
        if (fragment instanceof NoteFragment) {
            search.setVisible(false);
            sort.setVisible(false);
            send.setVisible(true);
            addPhoto.setVisible(true);
        } else if (fragment instanceof ListOfNotesFragment) {
            search.setVisible(true);
            sort.setVisible(true);
            send.setVisible(false);
            addPhoto.setVisible(false);
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