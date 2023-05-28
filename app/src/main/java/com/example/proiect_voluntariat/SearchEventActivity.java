package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchEventActivity extends AppCompatActivity {
    private EditText searchBar;
    private ListView listView;
    private List<String> searchResults;
    private Button deployButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_event);

        searchBar = findViewById(R.id.search_bar);
        listView = findViewById(R.id.list_view);
        searchResults = new ArrayList<>();
        deployButton = findViewById(R.id.deployButton);

        deployButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });

        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String searchQuery = searchBar.getText().toString().trim();
                    searchRecords(searchQuery);
                    return true;
                }
                return false;
            }
        });
    }

    private void searchRecords(String searchQuery) {
        searchResults.clear();
        Database db = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
        Cursor cursor = db.query("events",
                new String[]{"name", "description", "points"},
                "name LIKE ? OR description LIKE ?",
                new String[]{"%" + searchQuery + "%", "%" + searchQuery + "%"},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                int points = cursor.getInt(cursor.getColumnIndex("points"));
                searchResults.add(name + "\n" + description + "\n" + points + " points");
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, searchResults);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemName = searchResults.get(position).split("\n")[0];

                Intent intent = new Intent(SearchEventActivity.this, EventDetailsActivity.class);
                String username = getIntent().getStringExtra("username");
                intent.putExtra("name", selectedItemName);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, deployButton);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_item1) {
                    startActivity(new Intent(SearchEventActivity.this, HomeActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item2) {
                    Intent intent = new Intent(SearchEventActivity.this, PrintActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item3) {
                    Intent intent = new Intent(SearchEventActivity.this, SearchEventActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item4) {
                    Intent intent = new Intent(SearchEventActivity.this, ShopActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item5) {
                    startActivity(new Intent(SearchEventActivity.this, LoginActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

}