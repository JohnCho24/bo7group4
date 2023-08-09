package com.b07group4.owner_orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.b07group4.R;

import java.util.HashMap;
import java.util.List;

public class OrderAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> orderId;
    private List<String> orderStatus;

    private HashMap<String, List<String>> itemIds;

    public OrderAdapter(Context context, @Nullable List<String> orderId, @Nullable List<String> orderStatus, @Nullable HashMap<String, List<String>> itemIds) {
        this.context = context;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.itemIds = itemIds;
    }

    @Override
    public int getGroupCount() {
        return this.orderStatus.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.itemIds.get(this.orderStatus.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.orderStatus.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.itemIds.get(this.orderStatus.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String orderIdValue = orderId.get(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_header, null);
        }

        TextView h1 = view.findViewById(R.id.orderHeader1);
        TextView h2 = view.findViewById(R.id.orderHeader2);

        h1.setText("Order ID: " + orderIdValue);
        h2.setText("Order Status: " + orderStatus.get(i));

        return view;
    }


    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String title1 = (String) getChild(i, i1);


        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_items, null);
        }

        TextView h1 = view.findViewById(R.id.itemIdTV);
        h1.setText("Item ID: " + title1);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
