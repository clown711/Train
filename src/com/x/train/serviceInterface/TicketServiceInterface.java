package com.x.train.serviceInterface;

import java.util.ArrayList;
import java.util.Map;

import com.x.train.bean.Ticket;
import com.x.train.bean.Trips;

public interface TicketServiceInterface {
		
	public int insert(Ticket ticket)  throws Exception;
	
	public ArrayList<Ticket> selectTicket(Map<String, Object> pagemap) throws Exception;
	
	public int getTicketCountNumber() throws Exception;
	
	public int deleteTicketById(int ticketId) throws Exception;
	
	public Ticket selectTicketById(int ticketId) throws Exception;
	
	public int updateTicketById(Ticket ticket) throws Exception;
	
	public Ticket selectTicketByTripsIdAndType(Map<String, Object> map) throws Exception;
	
}
