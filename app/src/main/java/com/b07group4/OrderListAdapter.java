package com.b07group4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.b07group4.DataModels.Order; // Import the Order class from the correct package


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orderList;

    public OrderListAdapter(Context context, List<Order> orderList) {
        super(context, 0, orderList);
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        }

        // Get the current order
        Order currentOrder = orderList.get(position);

        // Update the views in the list item with order details
        TextView orderIdTextView = listItemView.findViewById(R.id.textViewOrderId);
        TextView shopperIdTextView = listItemView.findViewById(R.id.textViewShopperId);
        TextView statusTextView = listItemView.findViewById(R.id.textViewStatus);
        //TextView totalAmountTextView = listItemView.findViewById(R.id.textViewTotalAmount);

        orderIdTextView.setText("Order ID: " + currentOrder.getOrderId());
        shopperIdTextView.setText("Shopper ID: " + currentOrder.getShopperId());
        statusTextView.setText("Status: " + currentOrder.getStatus());
        //totalAmountTextView.setText("Total Amount: " + currentOrder.getTotalAmount());

        return listItemView;
    }
}
