package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventNameTextView;
    private TextView eventDescriptionTextView;
    private TextView eventPointsTextView;
    private Button applyButton;
    private Button finishButton;
    private Button deployButton;
    private String eventName;
    private int eventPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventNameTextView = findViewById(R.id.event_name_text_view);
        eventDescriptionTextView = findViewById(R.id.event_description_text_view);
        eventPointsTextView = findViewById(R.id.event_points_text_view);
        applyButton = findViewById(R.id.apply_button);
        finishButton = findViewById(R.id.finish_confirmation_button);
        deployButton = findViewById(R.id.deployButton);

        deployButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });

        Intent intent = getIntent();
        eventName = intent.getStringExtra("name");

        Database db = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
        Cursor cursor = db.query("events",
                new String[]{"name", "description", "points"},
                "name = ?",
                new String[]{eventName},
                null, null, null);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            eventPoints = cursor.getInt(cursor.getColumnIndex("points"));

            eventNameTextView.setText(name);
            eventDescriptionTextView.setText(description);
            eventPointsTextView.setText(String.valueOf(eventPoints) + " points");
        }

        finishButton.setVisibility(View.GONE);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyButton.setEnabled(false);
                finishButton.setVisibility(View.VISIBLE);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
                SQLiteDatabase db = database.getWritableDatabase();
                String currentUserName = getIntent().getStringExtra("username");
                String username = currentUserName;
                Cursor userCursor = db.query("users",
                        new String[]{"points"},
                        "username = ?",
                        new String[]{username},
                        null, null, null);
                int userPoints = 0;
                if (userCursor.moveToFirst()) {
                    userPoints = userCursor.getInt(userCursor.getColumnIndex("points"));
                }

                int newPoints = userPoints + eventPoints;
                ContentValues contentValues = new ContentValues();
                contentValues.put("points", newPoints);
                db.update("users", contentValues, "username = ?", new String[]{username});

                String message = "Congratulations! You have earned " + eventPoints + " points.";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                finish();
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
                    startActivity(new Intent(EventDetailsActivity.this, HomeActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item2) {
                    Intent intent = new Intent(EventDetailsActivity.this, PrintActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item3) {
                    Intent intent = new Intent(EventDetailsActivity.this, SearchEventActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item4) {
                    Intent intent = new Intent(EventDetailsActivity.this, ShopActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item5) {
                    startActivity(new Intent(EventDetailsActivity.this, LoginActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }
}
