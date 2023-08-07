package com.b07group4.owner_orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.b07group4.DataModels.Product;
import com.b07group4.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Product> {

    public OrderAdapter(Context context, int resource) {
        super(context, resource);
    }


    
}