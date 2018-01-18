package com.x.train.dao;

import java.util.ArrayList;
import java.util.Map;

import com.x.train.bean.Admin;


public interface AdminMapper {
	int insert(Admin admin);

    ArrayList<Admin> selectOneAdminByAcount(String adminAcount);
    
    ArrayList<Admin> selectAdmin(Map<String, Object> pagemap);
    
    int deleteAdminById(int adminId);
    
    int getAdminCountNumber();
    
    int updateAdminStatus(Admin admin);
}