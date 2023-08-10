package com.b07group4.shopper_orders;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.b07group4.DataModels.Order;
import com.b07group4.DataModels.Product;
import com.b07group4.DBHandler.OrderManager;
import com.b07group4.R;

import java.util.HashMap;
import java.util.List;

public class OrderAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Order> orders;
    private HashMap<String, List<Product>> products;
    private String username, storeOwner;


    private List<String> orderId;
    private List<String> orderStatus;

    TextView statusTv;

    OrderManager om;

    private HashMap<String, List<String>> itemIds;

    interface ClickTheButt {
        void onClick(String orderId);
    }

    private ClickTheButt onClick;

    public OrderAdapter(Context context, String username, @Nullable List<Order> orders, @Nullable HashMap<String, List<Product>> products, String storeOwner) {
        this.username = username;
        this.context = context;
        this.orders = orders;
        this.products = products;
        this.storeOwner = storeOwner;
    }

    @Override
    public int getGroupCount() {
        return this.orders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        List<Product> ps = this.products.get(this.orders.get(i).getId());
        if (ps == null)
            return 0;

        return ps.size();
    }

    @Override
    public Object getGroup(int i) {
        return this.orders.get(i).getId();
    }

    @Override
    public Object getChild(int i, int i1) {
        return products.get(orders.get(i).getId()).get(i1);
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
        Order o = orders.get(i);


        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_header, null);
        }

        TextView h1 = view.findViewById(R.id.orderHeader1);
        TextView h2 = view.findViewById(R.id.orderHeader2);

        h1.setText("Order ID: " + o.getId());
        h2.setText("Order Status: " + o.getSubStoreOrders().get(storeOwner).getOrderStatus());

        // Button stuff
        Button button = (Button) view.findViewById(R.id.statusButton);

        button.setFocusable(false);

        // Button to change order status
        button.setOnClickListener(v -> {
            onClick.onClick(o.getId());
        });
        return view;
    }


    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Product p = (Product) getChild(i, i1);


        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_items, null);
        }

        TextView h1 = view.findViewById(R.id.itemIdTV);
        h1.setText("Item Name: " + p.getName());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    public void setOnClick(ClickTheButt onClick) {
        this.onClick = onClick;
    }
}
