package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText edUsername;
    Button btnResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edUsername = findViewById(R.id.editTextForgotUsername);
        btnResetPassword = findViewById(R.id.buttonForgotPassword);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                } else {
                    resetPassword(username);
                }
            }
        });
    }

    private void resetPassword(String username) {
        Database db = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
        String newPassword = generateNewPassword(); // Generate a new password
        boolean success = db.updatePassword(username, newPassword);

        if (success) {
            Toast.makeText(ForgotPasswordActivity.this, "Password reset requested for username: " + username, Toast.LENGTH_SHORT).show();
            Toast.makeText(ForgotPasswordActivity.this, "New password: " + newPassword, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ForgotPasswordActivity.this, "Failed to reset password. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateNewPassword() {
        return "parola123";
    }
}