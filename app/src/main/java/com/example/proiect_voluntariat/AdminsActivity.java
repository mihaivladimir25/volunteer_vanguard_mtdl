package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AdminsActivity extends AppCompatActivity {

    Button btnManageUsers;
    Button btnManageProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins);

        btnManageUsers = findViewById(R.id.btnManageUsers);
        btnManageProducts = findViewById(R.id.btnManageProducts);

        btnManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdminsActivity.this, ManageUsersActivity.class);
                startActivity(intent1);
            }
        });

        btnManageProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminsActivity.this, ManageProductsActivity.class);
                startActivity(intent2);
            }
        });
    }
}