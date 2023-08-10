package com.b07group4.shopper_orders;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.b07group4.DBHandler.OrderManager;
import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Order;
import com.b07group4.DataModels.Product;
import com.b07group4.DataModels.SubStoreOrder;
import com.b07group4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopperOrders extends AppCompatActivity {
    private ProductManager pm;
    private OrderManager om;
    private String username;

    OrderAdapter myAdapter;
    ExpandableListView expandableListView;
    private List<Order> orders;
    private HashMap<String, List<Product>> itemIdHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_orders);
        pm = ProductManager.getInstance();
        om = OrderManager.getInstance();

        username = getIntent().getStringExtra("SHOPPER_NAME");

        expandableListView = findViewById(R.id.expandableListView);


        itemIdHash = new HashMap<>();
        orders = new ArrayList<>();
        myAdapter = new OrderAdapter(this, username, orders, itemIdHash);
        expandableListView.setAdapter(myAdapter);

        showList();
    }

    public void showList(){

        orders.clear();
        itemIdHash.clear();

        pm.GetAll(allProducts -> {
            om.GetAll(list -> {
                for (Order o : list) {
                    String userid = o.getShopperId();
                    if (userid.equals(username)) {
                        orders.add(o);
                        itemIdHash.put(o.getId(), new ArrayList<>());

                        Map<String, SubStoreOrder> mapp = o.getSubStoreOrders();
                        o.getSubStoreOrders().forEach((x, sso) -> {
                            String[] ids = sso != null ? sso.getProductIdList().split(",\\s*") : new String[]{};

                            for (String id : ids) {
                                for (Product pp : allProducts) {
                                    if (pp.getId().equals(id)) {
                                        itemIdHash.get(o.getId()).add(pp);
                                        break;
                                    }
                                }
                            }
                        });
                    }
                }
                myAdapter.notifyDataSetChanged();
            });
        });
    }




    // Back button
    public void onClickBack(View view){
        finish();
    }
}
