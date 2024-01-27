package com.juice.service;

import java.util.List;

import com.juice.entity.Order;

public interface OrderService {
public  Order placeOrder(Order o);
public  boolean  cancleOrder(String orderId);
public List<Order> showMyOrderHistory(String  customerId);
public Order showOrderById(String orderid);


}
