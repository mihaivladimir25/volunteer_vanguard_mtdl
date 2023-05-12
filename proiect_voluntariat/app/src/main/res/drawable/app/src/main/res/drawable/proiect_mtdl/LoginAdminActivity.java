//package com.example.proiect_mtdl;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class LoginAdminActivity extends AppCompatActivity {
//
//    EditText edUsername, edPassword;
//    Button btn;
//    TextView tv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_admin);
//        edUsername = findViewById(R.id.editTextLoginUsername);
//        edPassword = findViewById(R.id.editTextLoginPassword);
//        btn = findViewById(R.id.buttonLogin);
//        tv = findViewById(R.id.textRegister);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username = edUsername.getText().toString();
//                String password = edPassword.getText().toString();
//                Database db = new Database(getApplicationContext(), "proiect_mtdl", null, 1);
//                if (username.length() == 0 || password.length() == 0) {
//                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (db.login_admin(username, password) == 1) {
//                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(LoginAdminActivity.this, AdminActivity.class));
//                        finish(); // Add this line to remove LoginAdminActivity from the back stack
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginAdminActivity.this, RegisterAdminActivity.class));
//
//            }
//        });
//    }
//}