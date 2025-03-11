package org.example;

class Revenue {
    String date, orderId, customer, paymentMethod;
    double totalPrice;

    public Revenue(String date, String orderId, String customer, double totalPrice, String paymentMethod) {
        this.date = date;
        this.orderId = orderId;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }
}
