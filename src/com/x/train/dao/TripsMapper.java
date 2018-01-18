package com.x.train.dao;

import com.x.train.bean.Trips;

import java.util.ArrayList;
import java.util.Map;


public interface TripsMapper {
	public int insert(Trips trips);
	
	public ArrayList<Trips> selectTrips(Map<String, Object> pagemap);
	
	public int getTripsCountNumber();
	
	public int deleteTripsById(int tripsId);
	
	public Trips selectTripsById(int tripsId);
	
	public int updateTripsById(Trips trips);
	
	public ArrayList<Trips> selectTripsBySearchMap(Map<String, Object> searchMap);
	
	public ArrayList<Trips> selectTripsChangeInfo(Map<String, Object> searchMap);
}