package com.x.train.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x.train.bean.PageBean;
import com.x.train.bean.Ticket;
import com.x.train.serviceInterface.TicketServiceInterface;
import com.x.train.util.FormatJSON;

@Controller
public class TicketController {
	
	private TicketServiceInterface ticketService;
	
	private FormatJSON formatJSON;
	
	public TicketServiceInterface getTicketService() {
		return ticketService;
	}
	
	@Resource
	public void setTicketService(TicketServiceInterface ticketService) {
		this.ticketService = ticketService;
	}

	public FormatJSON getFormatJSON() {
		return formatJSON;
	}
	
	@Resource
	public void setFormatJSON(FormatJSON formatJSON) {
		this.formatJSON = formatJSON;
	}

	
	/**管理员添加车票
	 * @param ticket
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminTicketAdd")
	public Map<String, Object> adminTicketAdd(Ticket ticket){
		Map<String, Object> resultMap = new HashMap<>();
		try {
			ticketService.insert(ticket);
			resultMap.put("result","1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("result","0");
			resultMap.put("errorInfo",e.getMessage());
		}
		return resultMap;
	}
	

	/**管理员查询所有车票信息
	 * @param pageBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminTicketSelect")
	public Map<String,Object> adminTripsSelect(PageBean pageBean){
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> pageMap = new HashMap<>();
		
		try {
			pageMap.put("showNumber", pageBean.getLimit());
			pageMap.put("startNumber",(pageBean.getPage()-1)*pageBean.getLimit());
			resultMap.put("data", formatJSON.ticketToJSON(ticketService.selectTicket(pageMap)));
			pageBean.setTotalCount(ticketService.getTicketCountNumber());//总条数
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
			// TODO: handle exception
		}
	
		return resultMap;
	}
	
	/**管理员删除车票信息
	 * @param ticketId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin/adminTicketDelete")
	public Map<String,Object> adminTicketDelete(String ticketId,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>(); 
		try {
			ticketService.deleteTicketById(Integer.parseInt(ticketId));
			resultMap.put("result","1");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result","0");
			// TODO: handle exception
		}
		return resultMap;
	}
	
	/**管理员修改车票信息
	 * @param ticket
	 * @return
	 */ 
	@ResponseBody
	@RequestMapping("admin/adminTicketEdit")
	public Map<String, Object> adminTicketEdit(Ticket ticket){
		Map<String,Object> resultMap = new HashMap<>(); 
		try {
			ticketService.updateTicketById(ticket);
			resultMap.put("result","1");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result","0");
			// TODO: handle exception
		}
		return resultMap;
	}
}
