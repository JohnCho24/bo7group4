package com.b07group4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.b07group4.DataModels.Order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class OwnersOrders extends AppCompatActivity {

    private ListView ordersListView;
    private ArrayAdapter<Order> ordersAdapter;
    private DatabaseReference ordersReference;
    private List<Order> orderList;

    private String STORE = "gucci";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_orders);

        ordersListView = findViewById(R.id.listViewOrders);
        orderList = new ArrayList<>();
        ordersAdapter = new OrderListAdapter(this, orderList);
        ordersListView.setAdapter(ordersAdapter);

        ordersReference = FirebaseDatabase.getInstance().getReference("Orders");

        ordersReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Order order = snapshot.getValue(Order.class);
                if (order != null && STORE.equals(order.getStoreId())) {
                    orderList.add(order);
                    ordersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Custom ArrayAdapter for displaying Order items in the ListView
    private class OrderListAdapter extends ArrayAdapter<Order> {
        private LayoutInflater inflater;

        public OrderListAdapter(Context context, List<Order> orderList) {
            super(context, 0, orderList);
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = inflater.inflate(R.layout.list_item_order, parent, false);
            }

            Order currentOrder = getItem(position);

            TextView orderIdTextView = listItemView.findViewById(R.id.textViewOrderId);
            TextView orderStatusTextView = listItemView.findViewById(R.id.textViewStatus);
            TextView shopperIdTextView = listItemView.findViewById(R.id.textViewShopperId);

            orderIdTextView.setText("Order ID: " + currentOrder.getOrderId());
            orderStatusTextView.setText("Status: " + currentOrder.getOrderStatus());
            shopperIdTextView.setText("Shopper ID: " + currentOrder.getShopperId());

            return listItemView;
        }
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }
}


