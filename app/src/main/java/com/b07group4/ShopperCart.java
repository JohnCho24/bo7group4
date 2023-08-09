package com.b07group4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DBHandler.CartManager;
import com.b07group4.DataModels.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class ShopperCart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartProductAdapter productAdapter;
    private static List<Product> myCart;
    private static double totalProductPrice = 0.0;
    private static TextView tvTotalPrice;
    private String currentUser;
    CartManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_cart);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        currentUser = preferences.getString("currentUser", null);
        Log.d("DBG",currentUser);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvTotalPrice = findViewById(R.id.tv_total_price);
        cm = CartManager.getInstance();
        myCart = cm.getCart();
        if (!myCart.isEmpty()) {
            Log.d("DBG", myCart.get(0).getName());
        }
        productAdapter = new CartProductAdapter(myCart);
        recyclerView.setAdapter(productAdapter);

        updateTotalPriceUI();
    }

    public static void updateTotalPriceUI() {
        totalProductPrice = 0;
        for (Product product : myCart) {
            totalProductPrice += product.getPrice();
        }
        tvTotalPrice.setText(String.format(Locale.getDefault(), "%.2f", totalProductPrice));
    }
    public void clickCheckout(View view){
        try {
            CartManager.getInstance().Checkout(currentUser);
            Toast.makeText(ShopperCart.this, "Order submitted successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("DBG", "Exception in clickCheckout: " + e.getMessage());
        }

        finish();
    }
    public void onClickBack(View view){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        return true;
    }

    private void filter(String query) {
        List<Product> filteredList = new ArrayList<>();
        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(myCart);
        } else {
            for (Product product : myCart) {
                if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(product);
                }
            }
        }
        productAdapter.CartfilterList(filteredList);
    }
}
