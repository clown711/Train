package com.x.train.bean;

import java.math.BigDecimal;

public class Ticket {
    private Integer ticketId;

    private Integer tripsId;

    private Integer ticketNumber;

    private BigDecimal ticketPrice;
    
    private String ticketSeattype;
    
    private Trips trips;

    public Trips getTrips() {
		return trips;
	}

	public void setTrips(Trips trips) {
		this.trips = trips;
	}

	public String getTicketSeattype() {
		return ticketSeattype;
	}

	public void setTicketSeattype(String ticketSeattype) {
		this.ticketSeattype = ticketSeattype;
	}

	public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTripsId() {
        return tripsId;
    }

    public void setTripsId(Integer tripsId) {
        this.tripsId = tripsId;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}