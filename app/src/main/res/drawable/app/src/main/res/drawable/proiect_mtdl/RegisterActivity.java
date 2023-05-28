package com.example.proiect_mtdl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername,edEmail, edName ,edPassword, edConf;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConf = findViewById(R.id.editTextRegConfPassword);
        edName = findViewById(R.id.editTextRegEmail);
        edEmail = findViewById(R.id.editTextRegEmail);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String conf = edConf.getText().toString();
                String email = edEmail.getText().toString();
                String name = edName.getText().toString();
                Database db = new Database(getApplicationContext(), "proiect_mtdl", null, 1);
                if(username.length()==0 || password.length()==0 || conf.length() == 0 || email.length()==0 || name.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(conf)==0){
                        db.register(username, name, email, password);
                        Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Password and Confirm password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}