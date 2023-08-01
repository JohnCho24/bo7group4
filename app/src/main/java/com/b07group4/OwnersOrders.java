package com.b07group4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.b07group4.DataModels.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OwnersOrders extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_orders);

        // Listview
        ListView resultsListView = (ListView) findViewById(R.id.listViewOrders);

        HashMap<String, String> nameAddresses = new HashMap<>();
        nameAddresses.put("anotherOrder", "Completed");
        nameAddresses.put("newOrder1", "In progress");
//        nameAddresses.put("Rich Homie Quan", "111 Everything Gold Way");
//        nameAddresses.put("Donna", "789 Escort St");
//        nameAddresses.put("Bartholomew", "332 Dunkin St");
//        nameAddresses.put("Eden", "421 Angelic Blvd");
//        nameAddresses.put("wer", "421 Angelic Blvd");
//        nameAddresses.put("rfs", "421 Angelic Blvd");
//        nameAddresses.put("vfdg", "421 Angelic Blvd");

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item_order,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.textViewOrderId, R.id.textViewStatus});


        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        resultsListView.setAdapter(adapter);

    }
}
