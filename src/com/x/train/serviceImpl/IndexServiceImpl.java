package com.x.train.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.x.train.bean.Order;
import com.x.train.bean.Ticket;
import com.x.train.bean.Trips;
import com.x.train.dao.OrderMapper;
import com.x.train.dao.TicketMapper;
import com.x.train.dao.TripsMapper;
import com.x.train.serviceInterface.IndexServiceInterface;

@Service
public class IndexServiceImpl implements IndexServiceInterface {
	
	private TripsMapper tripsMapper;
	
	private TicketMapper ticketMapper;
	
	private OrderMapper orderMapper;
	
	
	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	
	@Resource
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	public TicketMapper getTicketMapper() {
		return ticketMapper;
	}
	
	@Resource
	public void setTicketMapper(TicketMapper ticketMapper) {
		this.ticketMapper = ticketMapper;
	}

	public TripsMapper getTripsMapper() {
		return tripsMapper;
	}
	
	@Resource
	public void setTripsMapper(TripsMapper tripsMapper) {
		this.tripsMapper = tripsMapper;
	}

	@Override
	public Trips selectTripsById(int tripsId){
		// TODO Auto-generated method stub
		return tripsMapper.selectTripsById(tripsId);
	}

	@Override
	public Ticket selectTicketById(int ticketId) throws Exception {
		// TODO Auto-generated method stub
		return ticketMapper.selectTicketById(ticketId);
	}

	@Override
	public ArrayList<Trips> selectTicketTimeByOrderId(int orderId) throws Exception {
		// TODO Auto-generated method stub
		Order order = orderMapper.selectOrderByOrderId(orderId);
		Date date = new Date();
		ArrayList<Trips> tripses = null;
		if(order.getTrips().getTripsBegintime().after(date)){
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("tripsDestination",order.getTrips().getTripsDestination());
			searchMap.put("tripsOrigin",order.getTrips().getTripsOrigin());
			searchMap.put("tripsBegintime",order.getTrips().getTripsBegintime());
			searchMap.put("ticketSeattype",order.getTicketSeattype());
			tripses = tripsMapper.selectTripsChangeInfo(searchMap);
		}
		return tripses;
	}

}
