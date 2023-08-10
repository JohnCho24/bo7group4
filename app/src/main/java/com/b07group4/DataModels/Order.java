package com.b07group4.DataModels;

import java.util.HashMap;
import java.util.Map;

public class Order {

    public enum OrderStatus {
        IN_CART,
        IN_PROCESS,
        DONE
    };

        private String id;
        private String shopperId;
        private final Map<String, SubStoreOrder> subStoreOrders;

        public Order() {
            subStoreOrders = new HashMap<>();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopperId() {
            return shopperId;
        }

        public void setShopperId(String shopperId) {
            this.shopperId = shopperId;
        }

        public Map<String, SubStoreOrder> getSubStoreOrders() {
            return subStoreOrders;
        }

        public void setSubStoreOrder(String storeName, SubStoreOrder subStoreOrder) {
            subStoreOrders.put(storeName, subStoreOrder);
        }
}
