package com.x.train.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;



public class Trips {
    private Integer tripsId;

    private String tripsName;

    private String tripsOrigin;

    private String tripsDestination;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  
    private Date tripsBegintime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  
	private Date tripsEndtime;
    
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Date getTripsBegintime() {
		return tripsBegintime;
	}

	public void setTripsBegintime(Date tripsBegintime) {
		this.tripsBegintime = tripsBegintime;
	}

	public Date getTripsEndtime() {
		return tripsEndtime;
	}

	public void setTripsEndtime(Date tripsEndtime) {
		this.tripsEndtime = tripsEndtime;
	}
	
    public Integer getTripsId() {
        return tripsId;
    }

    public void setTripsId(Integer tripsId) {
        this.tripsId = tripsId;
    }

    public String getTripsName() {
        return tripsName;
    }

    public void setTripsName(String tripsName) {
        this.tripsName = tripsName == null ? null : tripsName.trim();
    }

    public String getTripsOrigin() {
        return tripsOrigin;
    }

    public void setTripsOrigin(String tripsOrigin) {
        this.tripsOrigin = tripsOrigin == null ? null : tripsOrigin.trim();
    }

    public String getTripsDestination() {
        return tripsDestination;
    }

    public void setTripsDestination(String tripsDestination) {
        this.tripsDestination = tripsDestination == null ? null : tripsDestination.trim();
    }

}