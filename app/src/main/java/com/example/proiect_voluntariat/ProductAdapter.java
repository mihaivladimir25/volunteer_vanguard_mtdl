package com.example.proiect_voluntariat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.product_image);
        TextView nameTextView = convertView.findViewById(R.id.product_name);
        TextView priceTextView = convertView.findViewById(R.id.product_price);
        Button buyButton = convertView.findViewById(R.id.buy_button);

        Product product = productList.get(position);
        byte[] image = product.getImage();
        if (image != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.placeholder_image);
        }

        nameTextView.setText(product.getName());
        priceTextView.setText(String.format("%.2f points", product.getPrice()));

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ShopActivity)context).onProductBuyButtonClick(product);
            }
        });

        return convertView;
    }
}