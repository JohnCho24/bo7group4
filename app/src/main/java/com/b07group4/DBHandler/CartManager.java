package com.b07group4.DBHandler;

import android.text.TextUtils;
import android.util.Log;

import com.b07group4.DataModels.Order;
import com.b07group4.DataModels.Product;
import com.b07group4.DataModels.SubStoreOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManager {
    private static CartManager instance_;

    List<Product> myCart;

    private CartManager() {
        myCart = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance_ == null)
            instance_ = new CartManager();

        Log.d("DBG", "cart manager called");
        return instance_;
    }

    public void Add(Product p) {
        myCart.add(p);
    }

    public void Remove(Product p) {
        myCart.remove(p);
    }

    public void ClearCart(){
        myCart.clear();
    }
    public List<Product> getCart(){
        return myCart;
    }

    public void Checkout(String shopperId) {
        //Log.d("DBG", "Checkout method started");

        OrderManager orderManager = OrderManager.getInstance();
        Map<String, List<String>> subStoreOrders = new HashMap<>();
        Order order = new Order();
        order.setShopperId(shopperId);

        for (Product product : myCart) {
            String ownerId = product.getOwner_id();
            //Log.d("DBG", "Product owner ID: " + ownerId);

            if (!subStoreOrders.containsKey(ownerId)) {
                subStoreOrders.put(ownerId, new ArrayList<>());
            }
            subStoreOrders.get(ownerId).add(product.getId());
        }

        for (String storeName : subStoreOrders.keySet()) {
            List<String> productIds = subStoreOrders.get(storeName);

            SubStoreOrder subStoreOrder = new SubStoreOrder();
            String productListString = TextUtils.join(", ", productIds);
            subStoreOrder.setOrderStatus(Order.OrderStatus.IN_PROCESS);
            subStoreOrder.setProductIdList(productListString);
            order.setSubStoreOrder(storeName, subStoreOrder);
        }

        orderManager.Create(order, data -> {
            if (data != null) {
                String orderId = data.getId();
                Log.d("DBG", "Order created successfully. Order ID: " + orderId);

                CartManager.getInstance().ClearCart();
            } else {
                Log.d("DBG", "Failed to create order.");
            }
        });

        //Log.d("DBG", "Checkout method completed");

    }
}
