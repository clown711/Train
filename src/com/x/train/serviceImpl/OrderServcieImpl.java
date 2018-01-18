package com.x.train.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.x.train.bean.Order;
import com.x.train.bean.Ticket;
import com.x.train.dao.OrderMapper;
import com.x.train.dao.TicketMapper;
import com.x.train.serviceInterface.OrderServiceInterface;

@Service
public class OrderServcieImpl implements OrderServiceInterface{
	
	private OrderMapper orderMapper;
	private TicketMapper ticketMapper;

	public TicketMapper getTicketMapper() {
		return ticketMapper;
	}
	
	@Resource
	public void setTicketMapper(TicketMapper ticketMapper) {
		this.ticketMapper = ticketMapper;
	}

	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	
	@Resource
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	@Override
	public int insert(Order order) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.insert(order);
	}

	@Override
	public ArrayList<Order> selectOrder(Map<String, Object> pageMap) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.selectOrder(pageMap);
	}

	@Override
	public ArrayList<Order> selectOrderByUserId(int userId) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderByUserId(userId);
	}

	@Override
	public int deleteOrderById(int orderId) throws Exception {
		// TODO Auto-generated method stub
		Order order = orderMapper.selectOrderByOrderId(orderId);
		Map<String,Object> ticketMap = new HashMap<>();
		ticketMap.put("ticketSeattype",order.getTicketSeattype());
		ticketMap.put("tripsId",order.getTripsId());
		Ticket ticket = ticketMapper.selectTicketByTripsIdAndType(ticketMap);
		ticket.setTicketNumber(ticket.getTicketNumber()+1);
		ticketMapper.updateTicketById(ticket);
		return orderMapper.deleteOrderById(orderId);
	}

	@Override
	public int updateOrderChange(int orderId,int tripsId) throws Exception {
		// TODO Auto-generated method stub
		int n = 0;
		Order order = orderMapper.selectOrderByOrderId(orderId);
		Map<String,Object> ticketMap = new HashMap<>();
		ticketMap.put("ticketSeattype",order.getTicketSeattype());
		ticketMap.put("tripsId",tripsId);
		Ticket ticket =  ticketMapper.selectTicketByTripsIdAndType(ticketMap);
		if(ticket == null){
			throw new  Exception("改签失败，错误代码'10-1'");
		}
		else if(ticket.getTicketNumber()<=0){
			throw new  Exception("改签失败，票已售完");
		}
		else if(ticket.getTicketNumber()>0){
			ticket.setTicketNumber(ticket.getTicketNumber()-1);
			ticketMapper.updateTicketById(ticket);
			order.setTripsId(tripsId);
			n=orderMapper.updateOrderById(order);
		}
		return n;
	}

	@Override
	public int updateOrderChange(Order order) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderById(order);
	}

}
