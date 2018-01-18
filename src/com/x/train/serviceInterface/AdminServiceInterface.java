package com.x.train.serviceInterface;

import java.util.ArrayList;
import java.util.Map;

import com.x.train.bean.Admin;

public interface AdminServiceInterface {
	int insert(Admin admin, Admin currentAdmin)throws Exception;

	Admin selectOneAdminByAcount(Admin admin)throws Exception;
    
    ArrayList<Admin> selectAdmin(Map<String, Object> pagemap)throws Exception;
    
    int deleteAdminById(int adminId, Admin currentAdmin)throws Exception;
    
    int getAdminCountNumber()throws Exception;
    
    int updateAdminStatus(Admin admin, Admin currentAdmin)throws Exception;
}
