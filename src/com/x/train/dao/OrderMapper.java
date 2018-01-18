package com.x.train.dao;

import com.x.train.bean.Order;

import java.util.ArrayList;
import java.util.Map;

public interface OrderMapper {
	public int insert(Order order);
	
	public ArrayList<Order> selectOrder(Map<String, Object> pageMap);
	
	public ArrayList<Order> selectOrderByUserId(int userId);
	
	public int deleteOrderById(int orderId);
	
	public Order selectOrderByOrderId(int orderId);
	
	public int updateOrderById(Order order);
}