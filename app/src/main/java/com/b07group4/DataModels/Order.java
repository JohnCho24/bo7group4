package com.b07group4.DataModels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    public enum OrderStatus {
        IN_CART,
        IN_PROCESS,
        DONE
    };

        private String orderId;
        private String shopperId;
        private Map<String, SubStoreOrder> subStoreOrders;

        public Order() {
            subStoreOrders = new HashMap<>();
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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
