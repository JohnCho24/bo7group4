package com.b07group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DBHandler.CartManager;
import com.b07group4.DataModels.Product;

import java.util.ArrayList;
import java.util.List;
public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder> {

    private List<Product> myCart;

    public CartProductAdapter(List<Product> myCart) {
        this.myCart = myCart;
    }

    public void CartfilterList(List<Product> filteredList) {
        myCart = filteredList;
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
        Product product = myCart.get(position);
        holder.productIdTextView.setText(product.getName());
        holder.priceTextView.setText(String.valueOf(product.getPrice()));

        holder.deleteButton.setOnClickListener(v -> {
            CartManager.getInstance().Remove(product);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }

    public static class CartProductViewHolder extends RecyclerView.ViewHolder {
        TextView productIdTextView;
        TextView priceTextView;
        Button deleteButton;

        public CartProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productIdTextView = itemView.findViewById(R.id.tv_product_id);
            priceTextView = itemView.findViewById(R.id.tv_product_price);
            deleteButton = itemView.findViewById(R.id.removeFromCart);
        }
    }
}

