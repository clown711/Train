package com.x.train.serviceInterface;

import java.util.ArrayList;
import java.util.Map;

import com.x.train.bean.Trips;

public interface TripsServiceInterface {
	
	public int insertTrips(Trips trips) throws Exception;
	
	public ArrayList<Trips> selectTrips(Map<String, Object> pagemap)throws Exception;
	
	public int getTripsCountNumber()throws Exception;
	
	public int deleteTripsById(int tripsId)throws Exception;
	
	public int updateTripsById(Trips trips)throws Exception;
	
	public ArrayList<Trips> selectTripsBySearchMap(Map<String, Object> searchMap)throws Exception;
	
}
