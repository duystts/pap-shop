package org.example;

public class Order {
    String orderId, orderDate, customer, product, status;
    int quantity;
    double totalPrice;

    public Order(String orderId, String orderDate, String customer, String product, int quantity, double totalPrice, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
