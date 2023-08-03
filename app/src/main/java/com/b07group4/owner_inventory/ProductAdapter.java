package com.b07group4.owner_inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DataModels.Product;
import com.b07group4.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> products;

    public ProductAdapter(List<Product> l) {
        products = l;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, brand, price, info;
        private final Button editBtn, deleteBtn;
        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            brand = view.findViewById(R.id.brand);
            price = view.findViewById(R.id.price);
            info = view.findViewById(R.id.info);
            editBtn = view.findViewById(R.id.editBtn);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }

        public TextView getName() {
            return name;
        }

        public TextView getBrand() {
            return brand;
        }

        public TextView getPrice() {
            return price;
        }

        public TextView getInfo() {
            return info;
        }

        public Button getEditBtn() {
            return editBtn;
        }

        public Button getDeleteBtn() {
            return deleteBtn;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inventory_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getName().setText(products.get(position).getName());
        holder.getBrand().setText(products.get(position).getBrand());
        holder.getPrice().setText(String.valueOf(products.get(position).getPrice()));
        holder.getInfo().setText(products.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}