package com.b07group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DataModels.Product;

import java.util.ArrayList;
import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder> {
    private List<Product> productList;
    private List<Product> originalList;

    public CartProductAdapter(List<Product> productList) {
        this.productList = productList;
        this.originalList = new ArrayList<>(productList);
    }

    public void CartfilterList(List<Product> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new CartProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productIdTextView.setText(product.getName());
        holder.priceTextView.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class CartProductViewHolder extends RecyclerView.ViewHolder {
        TextView productIdTextView;
        TextView priceTextView;

        public CartProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productIdTextView = itemView.findViewById(R.id.tv_product_id);
            priceTextView = itemView.findViewById(R.id.tv_product_price);
        }
    }
}
