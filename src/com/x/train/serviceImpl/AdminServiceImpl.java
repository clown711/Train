package com.x.train.serviceImpl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.x.train.bean.Admin;
import com.x.train.dao.AdminMapper;
import com.x.train.serviceInterface.AdminServiceInterface;
import com.x.train.util.EncodeUtil;

@Service
public class AdminServiceImpl implements AdminServiceInterface {
	
	private AdminMapper adminMapper;
	private EncodeUtil encodeUtil;
	
	public EncodeUtil getEncodeUtil() {
		return encodeUtil;
	}
	
	@Resource
	public void setEncodeUtil(EncodeUtil encodeUtil) {
		this.encodeUtil = encodeUtil;
	}

	public AdminMapper getAdminMapper() {
		return adminMapper;
	}
	
	@Resource
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	@Override
	public int insert(Admin admin,Admin currentAdmin) throws Exception {
		// TODO Auto-generated method stub
		admin.setAdminStatus(1);
		admin.setAdminPassword(encodeUtil.md5Encode(admin.getAdminAccount(), admin.getAdminPassword()));
		
		if(adminMapper.selectOneAdminByAcount(admin.getAdminAccount()).size()>0){
			throw new Exception("账号重复");
		}
		
		if(!currentAdmin.getAdminPower().equals("2")){
			throw new Exception("权限不足");
		}
		
		return adminMapper.insert(admin);
	}

	@Override
	public Admin selectOneAdminByAcount(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Admin> admins = adminMapper.selectOneAdminByAcount(admin.getAdminAccount());
		
		admin.setAdminPassword(encodeUtil.md5Encode(admin.getAdminAccount(), admin.getAdminPassword()));
		
		if(admins.size()==0)
			
			throw new Exception("账号不存在");
		
		if(admins.get(0).getAdminStatus() == 0)
			
			throw new Exception("账号被锁定");
		
		if(!admins.get(0).getAdminAccount().equals(admin.getAdminAccount()))
			
			throw new Exception("账号或密码错误");
		
		if(!admins.get(0).getAdminPassword().equals(admin.getAdminPassword()))
			
			throw new Exception("账号或密码错误");
		
		return admins.get(0);
	}

	
	@Override
	public ArrayList<Admin> selectAdmin(Map<String, Object> pagemap) throws Exception {
		// TODO Auto-generated method stub
		
		return adminMapper.selectAdmin(pagemap);
	}

	@Override
	public int deleteAdminById(int adminId,Admin currentAdmin) throws Exception {
		if(!currentAdmin.getAdminPower().equals("2")){
			throw new Exception("权限不足");
		}
		return adminMapper.deleteAdminById(adminId);
	}

	@Override
	public int getAdminCountNumber() throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.getAdminCountNumber();
	}

	@Override
	public int updateAdminStatus(Admin admin,Admin currentAdmin) throws Exception {
				// TODO Auto-generated method stub
				if(!currentAdmin.getAdminPower().equals("2")){
					throw new Exception("权限不足");
				}
				return adminMapper.updateAdminStatus(admin);
	}

}
