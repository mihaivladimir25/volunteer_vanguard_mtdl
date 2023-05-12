package com.example.proiect_voluntariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private List<Product> productList;
    private ProductAdapter productAdapter;
    private int pointsBalance;
    private Button deployButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop);
        deployButton = findViewById(R.id.deployButton);

        deployButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });

        TextView pointsBalanceTextView = findViewById(R.id.points_balance_text_view);
        pointsBalanceTextView.setText(getString(R.string.points_balance, pointsBalance));

        Database database = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
        SQLiteDatabase db = database.getReadableDatabase();
        String currentUserName = getIntent().getStringExtra("username");
        Cursor cursor = db.rawQuery("SELECT points FROM users WHERE username=?", new String[] {currentUserName});
        if (cursor.moveToFirst()) {
            pointsBalance = cursor.getInt(cursor.getColumnIndex("points"));
            pointsBalanceTextView.setText(getString(R.string.points_balance, pointsBalance));
        }
        cursor.close();
        productList = database.getAllProducts();

        ListView listView = findViewById(R.id.product_list_view);
        productAdapter = new ProductAdapter(this, productList);
        listView.setAdapter(productAdapter);

        database.close();
    }

    public void onProductBuyButtonClick(Product product) {
        if (pointsBalance >= product.getPrice()) {
            pointsBalance -= product.getPrice();
            Database database = new Database(getApplicationContext(), "proiect_voluntariat", null, 1);
            SQLiteDatabase db = database.getWritableDatabase();
            String currentUserName = getIntent().getStringExtra("username");
            db.execSQL("UPDATE users SET points=? WHERE username=?", new Object[] {pointsBalance, currentUserName});
            database.close();
            Toast.makeText(this, "Product purchased successfully", Toast.LENGTH_SHORT).show();
            TextView pointsBalanceTextView = findViewById(R.id.points_balance_text_view);
            pointsBalanceTextView.setText(getString(R.string.points_balance, pointsBalance));
        } else {
            Toast.makeText(this, "Insufficient points to purchase this product", Toast.LENGTH_SHORT).show();
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
                    startActivity(new Intent(ShopActivity.this, HomeActivity.class));
                    return true;
                } else if (itemId == R.id.menu_item2) {
                    Intent intent = new Intent(ShopActivity.this, PrintActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item3) {
                    Intent intent = new Intent(ShopActivity.this, SearchEventActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item4) {
                    Intent intent = new Intent(ShopActivity.this, ShopActivity.class);
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item5) {
                    startActivity(new Intent(ShopActivity.this, LoginActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

}
