package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edPassword, edConfirm, edName, edAddress, edSpecialization, edInterests, edSkills;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirm = findViewById(R.id.editTextRegConfPassword);
        edName = findViewById(R.id.editTextRegName);
        edAddress = findViewById(R.id.editTextRegAddress);
        edSpecialization = findViewById(R.id.editTextRegSpecialization);
        edInterests = findViewById(R.id.editTextRegInterests);
        edSkills = findViewById(R.id.editTextRegSkills);
        btn = findViewById(R.id.buttonRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String name = edName.getText().toString();
                String address = edAddress.getText().toString();
                String specialization = edSpecialization.getText().toString();
                String interests = edInterests.getText().toString();
                String skills = edSkills.getText().toString();
                Database db = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
                if (username.length() == 0 || password.length() == 0 || name.length() == 0 || address.length() == 0 || specialization.length() == 0 || interests.length() == 0 || skills.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                } else if (username.length() != 0 || password.length() != 0 || address.length() != 0 || specialization.length() != 0 || interests.length() != 0 || skills.length() != 0) {
                    Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
//                    db.register(username, password);
                    db.register(username, password, name, address, specialization, interests, skills);
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}