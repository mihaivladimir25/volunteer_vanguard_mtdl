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

public class ProfileBenefActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editAddress;
    private EditText editField;
    private EditText editDescription;
    private Button btnSave;
    private Button deployButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_benef);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editField = findViewById(R.id.editField);
        editDescription = findViewById(R.id.editDescription);
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
            public void onClick(View view) {
                String name = editName.getText().toString();
                String address = editAddress.getText().toString();
                String field = editField.getText().toString();
                String description = editDescription.getText().toString();

                Database database = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
                SQLiteDatabase db = database.getWritableDatabase();
                String currentUserName = getIntent().getStringExtra("username");

                String updateQuery = "UPDATE benef SET name=?, address=?, field=?, description=? WHERE username=?";
                db.execSQL(updateQuery, new String[]{name, address, field, description, currentUserName});

                Toast.makeText(getApplicationContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeBenefActivity.class);
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
                    startActivity(new Intent(ProfileBenefActivity.this, HomeBenefActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item2) {
                    Intent intent = new Intent(ProfileBenefActivity.this, PrintActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item3) {
                    Intent intent = new Intent(ProfileBenefActivity.this, SearchEventActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item4) {
                    Intent intent = new Intent(ProfileBenefActivity.this, ShopActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item5) {
                    startActivity(new Intent(ProfileBenefActivity.this, LoginActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

}