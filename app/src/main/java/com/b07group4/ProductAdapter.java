package com.b07group4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.b07group4.DataModels.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> productList) {
        super(context, 0, productList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the product at the specified position
        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_product, parent, false);
        }

        // Set the product name in the custom layout
        TextView textViewName = convertView.findViewById(R.id.textViewProductName);
        textViewName.setText(product.getName());

        // If you have other views in the custom layout, set their values here

        return convertView;
    }
}