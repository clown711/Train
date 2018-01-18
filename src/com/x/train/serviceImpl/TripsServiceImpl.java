package com.x.train.serviceImpl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.x.train.bean.Trips;
import com.x.train.dao.TripsMapper;
import com.x.train.serviceInterface.TripsServiceInterface;

@Service
public class TripsServiceImpl implements TripsServiceInterface {
	
	private TripsMapper tripsMapper;

	public TripsMapper getTripsMapper() {
		return tripsMapper;
	}
	
	@Resource
	public void setTripsMapper(TripsMapper tripsMapper) {
		this.tripsMapper = tripsMapper;
	}

	@Override
	public int insertTrips(Trips trips) throws Exception {
		// TODO Auto-generated method stub
		return tripsMapper.insert(trips);
	}

	@Override
	public ArrayList<Trips> selectTrips(Map<String, Object> pagemap) throws Exception {
		// TODO Auto-generated method stub
		return tripsMapper.selectTrips(pagemap);
	}

	@Override
	public int getTripsCountNumber() throws Exception {
		// TODO Auto-generated method stub
		return tripsMapper.getTripsCountNumber();
	}

	@Override
	public int deleteTripsById(int tripsId) throws Exception {
		// TODO Auto-generated method stub
		return tripsMapper.deleteTripsById(tripsId);
	}

	@Override
	public int updateTripsById(Trips trips) throws Exception {
		// TODO Auto-generated method stub
		return tripsMapper.updateTripsById(trips);
	}

	@Override
	public ArrayList<Trips> selectTripsBySearchMap(Map<String, Object> searchMap) throws Exception {
		// TODO Auto-generated method stub
		return tripsMapper.selectTripsBySearchMap(searchMap);
	}
	
}
