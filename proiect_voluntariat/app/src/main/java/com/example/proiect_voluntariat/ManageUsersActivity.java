package com.example.proiect_voluntariat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ManageUsersActivity extends AppCompatActivity {

    private ListView userListView;
    private Button buttonAddUser;
    private Button buttonAddBeneficiary;
    private Button buttonDeleteUser;

    private List<String> userList;
    private ArrayAdapter<String> userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        userListView = findViewById(R.id.userListView);
        buttonAddUser = findViewById(R.id.buttonAddUser);
        buttonAddBeneficiary = findViewById(R.id.buttonAddBeneficiary);
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);

        userList = new ArrayList<>();

        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        userListView.setAdapter(userAdapter);

        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageUsersActivity.this);
                builder.setTitle("Add User");

                LinearLayout layout = new LinearLayout(ManageUsersActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText etUsername = new EditText(ManageUsersActivity.this);
                etUsername.setHint("Username");
                layout.addView(etUsername);

                final EditText etName = new EditText(ManageUsersActivity.this);
                etName.setHint("Name");

                final EditText etPassword = new EditText(ManageUsersActivity.this);
                etPassword.setHint("Password");
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                layout.addView(etPassword);

                builder.setView(layout);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = etUsername.getText().toString().trim();
                        String name = etName.getText().toString().trim();
                        String password = etPassword.getText().toString();

                        if (!username.isEmpty() && !name.isEmpty() && !password.isEmpty()) {
                            addUser(username, name, password);
                        } else {
                            Toast.makeText(ManageUsersActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        buttonAddBeneficiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageUsersActivity.this);
                builder.setTitle("Add Beneficiary");

                LinearLayout layout = new LinearLayout(ManageUsersActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText etUsername = new EditText(ManageUsersActivity.this);
                etUsername.setHint("Username");
                layout.addView(etUsername);

                final EditText etName = new EditText(ManageUsersActivity.this);
                etName.setHint("Name");
                layout.addView(etName);

                final EditText etAddress = new EditText(ManageUsersActivity.this);
                etAddress.setHint("Address");
                layout.addView(etAddress);

                final EditText etField = new EditText(ManageUsersActivity.this);
                etField.setHint("Field");
                layout.addView(etField);

                final EditText etDescription = new EditText(ManageUsersActivity.this);
                etDescription.setHint("Description");
                layout.addView(etDescription);

                final EditText etPassword = new EditText(ManageUsersActivity.this);
                etPassword.setHint("Password");
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                layout.addView(etPassword);

                builder.setView(layout);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = etUsername.getText().toString().trim();
                        String password = etPassword.getText().toString();
                        String name = etName.getText().toString().trim();
                        String address = etAddress.getText().toString().trim();
                        String field = etField.getText().toString().trim();
                        String description = etDescription.getText().toString().trim();

                        if (!username.isEmpty() && !password.isEmpty()) {
                            addBeneficiary(username, password, name, address, field, description);
                        } else {
                            Toast.makeText(ManageUsersActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageUsersActivity.this);
                builder.setTitle("Delete User");
                final EditText input = new EditText(ManageUsersActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = input.getText().toString().trim();
                        if (!username.isEmpty()) {
                            deleteUser(username);
                        } else {
                            Toast.makeText(ManageUsersActivity.this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    private void deleteUser(String username) {
        Database database = new Database(ManageUsersActivity.this, "proiect_voluntariat", null, 1);
        database.deleteUser(username);
        refreshUserList();
        Toast.makeText(ManageUsersActivity.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
    }

    private void addUser(String username, String name, String password) {
        Database database = new Database(ManageUsersActivity.this, "proiect_voluntariat", null, 1);
        database.addUser(username, password);
        refreshUserList();
        Toast.makeText(ManageUsersActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
    }

    private void addBeneficiary(String username, String password, String name, String address, String field, String description) {
        Database database = new Database(ManageUsersActivity.this, "proiect_voluntariat", null, 1);
        database.addBeneficiary(username, password, name, address, field, description);
        refreshUserList();
        Toast.makeText(ManageUsersActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
    }

    private void refreshUserList() {
        userList.clear();
        userAdapter.notifyDataSetChanged();
    }
}