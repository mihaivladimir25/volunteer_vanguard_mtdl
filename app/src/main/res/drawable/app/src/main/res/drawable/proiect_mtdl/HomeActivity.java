package com.example.proiect_mtdl;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardView1, cardView2, cardView3;
    private Button deployButton;
    private LinearLayout listContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);

        deployButton = findViewById(R.id.deployButton);
        listContainer = findViewById(R.id.listContainer);

        deployButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardView1) {
            // Handle click for card 1
            Intent intent1 = new Intent(HomeActivity.this, Card1Activity.class);
            startActivity(intent1);
        } else if (v.getId() == R.id.cardView2) {
            // Handle click for card 2
            Intent intent2 = new Intent(HomeActivity.this, Card2Activity.class);
            startActivity(intent2);
        } else if (v.getId() == R.id.cardView3) {
            // Handle click for card 3
            Intent intent3 = new Intent(HomeActivity.this, Card3Activity.class);
            startActivity(intent3);
        }
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, deployButton);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_item1) {
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item2) {
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item3) {
                    startActivity(new Intent(HomeActivity.this, OrganizerActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item4) {
//                    startActivity(new Intent(HomeActivity.this, LoginAdminActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item5) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

}