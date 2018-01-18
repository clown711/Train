package com.x.train.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.x.train.bean.Ticket;
import com.x.train.bean.Trips;
import com.x.train.bean.User;
import com.x.train.dao.TicketMapper;
import com.x.train.serviceInterface.TicketServiceInterface;

@Service
public class TicketServiceImpl implements TicketServiceInterface{
	
	private TicketMapper ticketMapper;
	
	public TicketMapper getTicketMapper() {
		return ticketMapper;
	}
	
	@Resource
	public void setTicketMapper(TicketMapper ticketMapper) {
		this.ticketMapper = ticketMapper;
	}

	@Override
	public int insert(Ticket ticket) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("ticketSeattype",ticket.getTicketSeattype());
		map.put("tripsId",ticket.getTripsId());
		Ticket oldTicket = ticketMapper.selectTicketByTripsIdAndType(map);
		if(oldTicket!=null){
			throw new Exception("'"+oldTicket.getTicketSeattype()+"'类型，已存在，请勿重复添加");
		}
		return ticketMapper.insert(ticket);
	}

	@Override
	public ArrayList<Ticket> selectTicket(Map<String, Object> pagemap) {
		// TODO Auto-generated method stub
		return ticketMapper.selectTicket(pagemap);
	}

	@Override
	public int getTicketCountNumber() {
		// TODO Auto-generated method stub
		return ticketMapper.getTicketCountNumber();
	}

	@Override
	public int deleteTicketById(int ticketId) {
		// TODO Auto-generated method stub
		return ticketMapper.deleteTicketById(ticketId);
	}

	@Override
	public Ticket selectTicketById(int ticketId) {
		// TODO Auto-generated method stub
		return ticketMapper.selectTicketById(ticketId);
	}

	@Override
	public int updateTicketById(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketMapper.updateTicketById(ticket);
	}

	@Override
	public Ticket selectTicketByTripsIdAndType(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return ticketMapper.selectTicketByTripsIdAndType(map);
	}


}
