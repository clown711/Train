package com.x.train.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x.train.bean.PageBean;
import com.x.train.bean.User;
import com.x.train.serviceInterface.UserServiceInterface;
import com.x.train.util.FormatJSON;


@Controller
public class UserController {
	
	private UserServiceInterface userService;
	private FormatJSON formatJSON;
	
	public FormatJSON getFormatJSON() {
		return formatJSON;
	}
	
	@Resource
	public void setFormatJSON(FormatJSON formatJSON) {
		this.formatJSON = formatJSON;
	}

	public UserServiceInterface getUserService() {
		return userService;
	}
	
	@Resource
	public void setUserService(UserServiceInterface userService) {
		this.userService = userService;
	}

	/**用户注册
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("user/userRegister")
	public Map<String, Object> userRegister(User user){
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int n = userService.insert(user);
			if(n>0){
				resultMap.put("result","1");
			}
			else{
				resultMap.put("result","0");
				resultMap.put("errorInfo","未知错误");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("result","0");
			resultMap.put("errorInfo",e.getMessage());
		}
		return resultMap;
	}
	
	/**用户登录
	 * @param user
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("user/userLogin")
	public Map<String,Object> userLogin(User user,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		try {
			user = userService.selectUserByUsername(user);
			user.setUserPassword("没有密码，啦啦啦");
			request.getSession().setAttribute("user",user);
			resultMap.put("result","1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("result","0");
			resultMap.put("errorInfo",e.getMessage());
		}
		return resultMap;
	}
	
	/**得到所有会员
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminUserSelect")
	public Map<String,Object> adminUserSelect(PageBean pageBean){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> pageMap = new HashMap<>();
		
		try {
			pageMap.put("showNumber", pageBean.getLimit());
			pageMap.put("startNumber",(pageBean.getPage()-1)*pageBean.getLimit());
			resultMap.put("data", formatJSON.UserToJSON(userService.selectUser(pageMap)));
			pageBean.setTotalCount(userService.getUserCountNumber());//总条数
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
	
	/**删除会员
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminUserDelete")
	public Map<String,Object> adminUserDelete(String userId){
		Map<String,Object> resultMap = new HashMap<>(); 
		try {
			userService.deleteUserById(Integer.parseInt(userId));
			resultMap.put("result","1");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result","0");
			// TODO: handle exception
		}
		return resultMap;
	}
	
	
	/**用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping("user/userLogOut")
	public String userLogout(HttpServletRequest request){
				request.getSession().removeAttribute("user");
				 return "redirect:../front"; 
	}
	
}
