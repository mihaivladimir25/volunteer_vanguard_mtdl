package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editAddress;
    private EditText editSpecialization;
    private EditText editInterests;
    private EditText editSkills;
    private Button btnSave;
    private Button deployButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editSpecialization = findViewById(R.id.editSpecialization);
        editInterests = findViewById(R.id.editInterests);
        editSkills = findViewById(R.id.editSkills);
        btnSave = findViewById(R.id.btnSave);

        deployButton = findViewById(R.id.deployButton);

        deployButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editName.getText().toString();
                String address = editAddress.getText().toString();
                String specialization = editSpecialization.getText().toString();
                String interests = editInterests.getText().toString();
                String skills = editSkills.getText().toString();

                Database database = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
                SQLiteDatabase db = database.getWritableDatabase();
                String currentUserName = getIntent().getStringExtra("username");

                String updateQuery = "UPDATE users SET name=?, address=?, specialization=?, interests=?, skills=? WHERE username=?";
                db.execSQL(updateQuery, new String[]{name, address, specialization, interests, skills, currentUserName});

                Toast.makeText(getApplicationContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("username", currentUserName);
                startActivity(intent);
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
                    startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item2) {
                    Intent intent = new Intent(ProfileActivity.this, PrintActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item3) {
                    Intent intent = new Intent(ProfileActivity.this, SearchEventActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item4) {
                    Intent intent = new Intent(ProfileActivity.this, ShopActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item5) {
                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

}