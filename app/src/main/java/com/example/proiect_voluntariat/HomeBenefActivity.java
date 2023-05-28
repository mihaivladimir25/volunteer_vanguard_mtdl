package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

public class HomeBenefActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cardView1, cardView2, cardView3;
    private Button deployButton;
    private LinearLayout listContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);

        deployButton = findViewById(R.id.deployButton);

        deployButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cardView1) {
            Intent intent1 = new Intent(HomeBenefActivity.this, Card1Activity.class);
            startActivity(intent1);
        } else if (view.getId() == R.id.cardView2) {
            Intent intent2 = new Intent(HomeBenefActivity.this, Card2Activity.class);
            startActivity(intent2);
        } else if (view.getId() == R.id.cardView3) {
            Intent intent3 = new Intent(HomeBenefActivity.this, Card3Activity.class);
            startActivity(intent3);
        }
    }
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, deployButton);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_benef, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_item1) {
                    startActivity(new Intent(HomeBenefActivity.this, HomeBenefActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item2) {
                    Intent intent = new Intent(HomeBenefActivity.this, PrintBenefActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item3) {
                    startActivity(new Intent(HomeBenefActivity.this, SearchEventActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item4) {
                    startActivity(new Intent(HomeBenefActivity.this, ManageEventsActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item5) {
                    startActivity(new Intent(HomeBenefActivity.this, LoginActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }
}