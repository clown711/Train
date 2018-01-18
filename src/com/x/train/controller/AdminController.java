package com.x.train.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x.train.bean.Admin;
import com.x.train.bean.PageBean;
import com.x.train.serviceInterface.AdminServiceInterface;
import com.x.train.util.FormatJSON;


@Controller
public class AdminController {
	
	private AdminServiceInterface adminService;
	private FormatJSON formatJSON;

	public AdminServiceInterface getAdminService() {
		return adminService;
	}
	
	@Resource
	public void setAdminService(AdminServiceInterface adminService) {
		this.adminService = adminService;
	}
	
	public FormatJSON getFormatJSON() {
		return formatJSON;
	}
	
	@Resource
	public void setFormatJSON(FormatJSON formatJSON) {
		this.formatJSON = formatJSON;
	}
	
	/**管理员增加
	 * @param admin
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminAdminAdd")
	public Map<String,Object> adminAdminAdd(Admin admin,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>(); 
		try {
			Admin currentAdmin = (Admin)request.getSession().getAttribute("admin");
			adminService.insert(admin,currentAdmin);
			resultMap.put("result","1");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result","0");
			resultMap.put("errorInfo",e.getMessage());
			// TODO: handle exception
		}
		return resultMap;
	}
	
	/**删除管理员
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminAdminDelete")
	public Map<String,Object> adminAdminDelete(String adminId,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>(); 
		try {
			Admin currentAdmin = (Admin)request.getSession().getAttribute("admin");
			adminService.deleteAdminById(Integer.parseInt(adminId),currentAdmin);
			resultMap.put("result","1");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result","0");
			resultMap.put("errorInfo",e.getMessage());
			// TODO: handle exception
		}
		return resultMap;
	}
	/**管理员录登录
	 * @param admin
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/loginAdmin")
	public Map<String, Object> loginAdmin(Admin admin,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			request.getSession().setAttribute("admin",adminService.selectOneAdminByAcount(admin));
			resultMap.put("result", "1");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", "0");
			resultMap.put("errorInfo",e.getMessage());
		}
		return resultMap;
		
	} 
	
	/**得到所有管理员
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminAdminSelect")
	public Map<String,Object> adminAdminSelect(PageBean pageBean){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> pageMap = new HashMap<>();
		
		try {
			pageMap.put("showNumber", pageBean.getLimit());
			pageMap.put("startNumber",(pageBean.getPage()-1)*pageBean.getLimit());
			resultMap.put("data", formatJSON.AdminToJSON(adminService.selectAdmin(pageMap)));
			pageBean.setTotalCount(adminService.getAdminCountNumber());//总条数
			if(pageBean.getTotalCount()<pageBean.getLimit()){
				pageBean.setTotalPage(1);
			}
			else if(pageBean.getTotalCount() % pageBean.getLimit() != 0){
				pageBean.setTotalPage(pageBean.getTotalCount() / pageBean.getLimit()+1);
			}
			else if(pageBean.getTotalCount() % pageBean.getLimit() == 0){
				pageBean.setTotalPage(pageBean.getTotalCount() / pageBean.getLimit());
			}
			else {
				pageBean.setTotalPage(0);
			}//总页数
			resultMap.put("pageInfo", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return resultMap;
	}
	
	/**修改管理员状态
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminAdminStatus")
	public Map<String,Object> adminAdminStatus(Admin admin,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>(); 
		try {
			Admin currentAdmin = (Admin)request.getSession().getAttribute("admin");
			adminService.updateAdminStatus(admin,currentAdmin);
			resultMap.put("result","1");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result","0");
			resultMap.put("errorInfo",e.getMessage());
			// TODO: handle exception
		}
		return resultMap;
	}
	
	/**管理员退出
	 * @param request
	 * @return
	 */
	@RequestMapping("admin/adminLogout")
	public String adminLogout(HttpServletRequest request){
				request.getSession().removeAttribute("admin");
				 return "redirect:../admin"; 
	}
	
}
