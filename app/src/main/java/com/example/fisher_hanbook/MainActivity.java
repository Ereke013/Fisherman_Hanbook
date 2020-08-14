package com.example.fisher_hanbook;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

import com.example.fisher_hanbook.settings.Settings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView list;
    private String[] array;
    private ArrayAdapter<String> adapter;
    private int category_index;

    //    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listView);
        array = getResources().getStringArray(R.array.fish_array);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array)));
        list.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Text_Content_Activity.class);
                intent.putExtra("category", category_index);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.fish);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent i = new Intent(MainActivity.this, Settings.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.id_fish) {
            toolbar.setTitle(R.string.fish);
            array = getResources().getStringArray(R.array.fish_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_index = 0;
            fillArray(R.string.fish, R.array.fish_array, 0);
            Toast.makeText(this, "Fish page is pressed", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_na) {
//            toolbar.setTitle(R.string.naj);
//            array = getResources().getStringArray(R.array.na_array);
//            adapter.clear();
//            adapter.addAll(array);
//            adapter.notifyDataSetChanged();
//            category_index = 1;
            fillArray(R.string.naj, R.array.na_array, 1);
            Toast.makeText(this, "Nazhivka page is pressed", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_pri) {
            fillArray(R.string.prikormka, R.array.pri_array, 2);
            Toast.makeText(this, "Prikormka page is pressed", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_sna) {
            fillArray(R.string.sna, R.array.sna_array, 3);
            Toast.makeText(this, "Snasti page is pressed", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_history) {
            fillArray(R.string.history, R.array.history_array, 4);
            Toast.makeText(this, "History page is pressed", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_advice) {
            fillArray(R.string.advice, R.array.advice_array, 5);
            Toast.makeText(this, "Advice page is pressed", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void fillArray(int title, int arrayList, int index) {
        toolbar.setTitle(title);
        array = getResources().getStringArray(arrayList);
        adapter.clear();
        adapter.addAll(array);
        adapter.notifyDataSetChanged();
        category_index = index;
    }
}
