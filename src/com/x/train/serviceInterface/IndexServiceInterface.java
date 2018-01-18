package com.x.train.serviceInterface;

import java.util.ArrayList;
import java.util.List;

import com.x.train.bean.Ticket;
import com.x.train.bean.Trips;

public interface IndexServiceInterface {
	public Trips selectTripsById(int tripsId) throws Exception;
	
	public Ticket selectTicketById(int ticketId) throws Exception;
	
	public ArrayList<Trips> selectTicketTimeByOrderId(int orderId) throws Exception;
}
