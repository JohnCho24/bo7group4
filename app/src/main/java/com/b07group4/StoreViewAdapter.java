package com.b07group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DataModels.Owner;

import java.util.List;

public class StoreViewAdapter extends RecyclerView.Adapter<StoreViewAdapter.StoreViewHolder> {

    private List<Owner> storeList;
    private OnItemClickListener listener;

    public StoreViewAdapter(List<Owner> storeList) {
        this.storeList = storeList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_layout, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder v, int position) {
        Owner store = storeList.get(position);
        v.getStoreName().setText(store.getStoreName());
        v.getStoreSize().setText(store.getProducts().size() + " Products");
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        private final TextView storeName, storeSize;
        private final Button discover;

        public StoreViewHolder(@NonNull View view) {
            super(view);
            storeName = view.findViewById(R.id.storeName);
            storeSize = view.findViewById(R.id.storeSize);
            discover = view.findViewById(R.id.discoverBtn);

            discover.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }

        public TextView getStoreName() {
            return storeName;
        }

        public TextView getStoreSize() {
            return storeSize;
        }
    }
}
