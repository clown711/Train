package com.x.train.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.x.train.bean.Admin;
import com.x.train.bean.Order;
import com.x.train.bean.Ticket;
import com.x.train.bean.Trips;
import com.x.train.bean.User;

@Component
public class FormatJSON {
	public JSONArray tripsToJSON(List<Trips> tripses){
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Trips trips : tripses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tripsId",trips.getTripsId());
            jsonObject.put("tripsName",trips.getTripsName());
            jsonObject.put("tripsOrigin",trips.getTripsOrigin());
            jsonObject.put("tripsDestination",trips.getTripsDestination());
            if(trips.getTripsBegintime()!=null){
            	 jsonObject.put("tripsBegintime",simpleDateFormat.format(trips.getTripsBegintime()));
            }
            else{
            	 jsonObject.put("tripsBegintime",null);
            }
            if(trips.getTripsEndtime()!=null){
            	jsonObject.put("tripsEndtime",simpleDateFormat.format(trips.getTripsEndtime()));
            }
            else{
            	jsonObject.put("tripsEndtime",null);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
	
	public JSONArray ticketToJSON(List<Ticket> tickets){
        JSONArray jsonArray = new JSONArray();
        for (Ticket ticket : tickets) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ticketId",ticket.getTicketId());
            jsonObject.put("tripsName",ticket.getTrips().getTripsName());
            jsonObject.put("ticketNumber",ticket.getTicketNumber());
            jsonObject.put("ticketPrice",ticket.getTicketPrice());
            jsonObject.put("ticketSeattype",ticket.getTicketSeattype());
            jsonObject.put("tripsId",ticket.getTripsId());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
	
	public JSONArray orderToJSON(List<Order> orders){
        JSONArray jsonArray = new JSONArray();
        for (Order order : orders) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderId",order.getOrderId());
            jsonObject.put("tripsName",order.getTrips().getTripsName());
            jsonObject.put("ticketSeattype",order.getTicketSeattype());
            jsonObject.put("orderPrice",order.getOrderPrice());
            jsonObject.put("orderQuantity","1");
            jsonObject.put("orderStatus",order.getOrderStatus());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
	
	public JSONArray UserToJSON(List<User> users){
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        for (User user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId",user.getUserId());
            jsonObject.put("userNickname",user.getUserNickname());
            jsonObject.put("userAccount",user.getUserAccount());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
	
	public JSONArray AdminToJSON(List<Admin> admins){
        JSONArray jsonArray = new JSONArray();
        for (Admin admin : admins) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("adminId",admin.getAdminId());
            jsonObject.put("adminAccount",admin.getAdminAccount());
            if(admin.getAdminPower().equals("2")){
            	 jsonObject.put("adminPower","超级管理员");
            }
            else{
            	 jsonObject.put("adminPower","普通管理员");
            }
            if(admin.getAdminStatus() == 1){
            	 jsonObject.put("adminStatus","启用");
	        }
	        else{
	        	 jsonObject.put("adminStatus","禁用");
	        }
           
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
