package com.x.train.bean;

import java.math.BigDecimal;

public class Order {
    private Integer orderId;

    private String orderNumber;

    private Integer userId;

    private Integer tripsId;

    private String orderStatus;

    private Integer orderQuantity;

    private BigDecimal orderPrice;
    
    private Trips trips;
    
    private String ticketSeattype;
    
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTripsId() {
        return tripsId;
    }

    public void setTripsId(Integer tripsId) {
        this.tripsId = tripsId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}