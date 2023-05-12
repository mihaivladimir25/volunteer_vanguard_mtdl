package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrintBenefActivity extends AppCompatActivity {

    private TextView textData;

    private Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_benef);

        textData = findViewById(R.id.textViewData);
        btnChange = findViewById(R.id.btnChange);
        Database database = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);

        SQLiteDatabase db = database.getReadableDatabase();
        String currentUserName = getIntent().getStringExtra("username");
        if (currentUserName == null) {
            Log.e("PrintBenefActivity", "currentUserName is null");
            return;
        }else{

        }
        Cursor cursor = db.rawQuery("SELECT * FROM benef WHERE username = ?", new String[]{currentUserName});
        if (cursor == null) {
            Log.e("PrintBenefActivity", "Cursor is null");
            return;
        }

        StringBuilder benefData = new StringBuilder();
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String field = cursor.getString(cursor.getColumnIndex("field"));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            benefData.append("Name: ").append(name).append("\n");
            benefData.append("Address: ").append(address).append("\n");
            benefData.append("Field: ").append(field).append("\n");
            benefData.append("Description: ").append(description).append("\n\n");

        }

        cursor.close();
        db.close();

        textData.setText(benefData.toString());

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserName = getIntent().getStringExtra("username");
                Toast.makeText(getApplicationContext(), "Change Profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PrintBenefActivity.this, ProfileBenefActivity.class);
                intent.putExtra("username", currentUserName);
                startActivity(intent);
            }
        });

    }
}