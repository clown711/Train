package com.x.train.dao;

import com.x.train.bean.Ticket;
import java.util.ArrayList;
import java.util.Map;


public interface TicketMapper {
	public int insert(Ticket ticket);
	
	public ArrayList<Ticket> selectTicket(Map<String, Object> pagemap);
	
	public int getTicketCountNumber();
	
	public int deleteTicketById(int ticketId);
	
	public Ticket selectTicketById(int ticketId);
	
	public int updateTicketById(Ticket ticket);
	
	public Ticket selectTicketByTripsIdAndType(Map<String, Object> map);
}