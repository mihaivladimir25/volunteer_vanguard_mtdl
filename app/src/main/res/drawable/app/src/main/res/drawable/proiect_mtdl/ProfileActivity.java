package com.example.proiect_mtdl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editAddress;
    private EditText editSpecialization;
    private EditText editInterests;
    private EditText editSkills;
    private Button btnSave;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inițializarea obiectelor de interfață
        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editSpecialization = findViewById(R.id.editSpecialization);
        editInterests = findViewById(R.id.editInterests);
        editSkills = findViewById(R.id.editSkills);
        btnSave = findViewById(R.id.btnSave);

        // Inițializarea obiectului de bază de date
        database = new Database(getApplicationContext(), "your_database_name", null, 1);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
            }
        });

        ProfileData profileData = database.getProfileData();
        if(profileData != null) {
            editName.setText(profileData.getName());
            editAddress.setText(profileData.getAddress());
            editSpecialization.setText(profileData.getSpecialization());
            editInterests.setText(profileData.getInterests());
            editSkills.setText(profileData.getSkills());
        }

    }

    private void saveProfileData() {
        // Extrage valorile introduse de utilizator din câmpurile de editare
        String name = editName.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String specialization = editSpecialization.getText().toString().trim();
        String interests = editInterests.getText().toString().trim();
        String skills = editSkills.getText().toString().trim();

        // Verifică dacă toate câmpurile sunt completate
        if (!name.isEmpty() && !address.isEmpty() && !specialization.isEmpty() && !interests.isEmpty() && !skills.isEmpty()) {
            // Salvează datele în baza de date utilizând obiectul Database
            database.updateProfile(name, address, specialization, interests, skills);
            Toast.makeText(this, "Data registered successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            intent.putExtra("message", "Data registered successfully");
            startActivity(intent);
            finish();

            // Afisează un mesaj de succes sau alte acțiuni dorite

        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}