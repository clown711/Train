package com.x.train.serviceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.x.train.bean.Order;

public interface OrderServiceInterface {
	
	public int insert(Order order) throws Exception;
	
	public ArrayList<Order> selectOrder(Map<String, Object> pageMap)throws Exception;
	
	public ArrayList<Order> selectOrderByUserId(int userId)throws Exception;
	
	public int deleteOrderById(int orderId)throws Exception;
	
	public int updateOrderChange(int orderId, int tripsId)throws Exception;
	
	public int updateOrderChange(Order order) throws Exception;
	
}
