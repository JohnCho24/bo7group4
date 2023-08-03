package com.b07group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DataModels.Store;

import java.util.List;

public class StoreViewAdapter extends RecyclerView.Adapter<StoreViewAdapter.StoreViewHolder> {

    private List<Store> storeList;
    private OnItemClickListener listener;

    public StoreViewAdapter(List<Store> storeList) {
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
        Store store = storeList.get(position);
        v.textViewStoreName.setText(store.getStoreName());
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStoreName;

        public StoreViewHolder(@NonNull View view) {
            super(view);
            textViewStoreName = view.findViewById(R.id.textViewStoreName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
