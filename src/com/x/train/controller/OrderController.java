package com.x.train.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x.train.bean.Order;
import com.x.train.bean.PageBean;
import com.x.train.bean.Ticket;
import com.x.train.bean.Trips;
import com.x.train.bean.User;
import com.x.train.serviceInterface.OrderServiceInterface;
import com.x.train.serviceInterface.TicketServiceInterface;
import com.x.train.util.FormatJSON;

@Controller
public class OrderController {
	
		private TicketServiceInterface ticketService;
		private OrderServiceInterface orderService;
		private FormatJSON formatJSON;
		
		public FormatJSON getFormatJSON() {
			return formatJSON;
		}
		
		@Resource
		public void setFormatJSON(FormatJSON formatJSON) {
			this.formatJSON = formatJSON;
		}

		public OrderServiceInterface getOrderService() {
			return orderService;
		}
		
		@Resource
		public void setOrderService(OrderServiceInterface orderService) {
			this.orderService = orderService;
		}

		public TicketServiceInterface getTicketService() {
			return ticketService;
		}
		
		@Resource
		public void setTicketService(TicketServiceInterface ticketService) {
			this.ticketService = ticketService;
		}

		
		/**用户购买车票
		 * @param tripsId
		 * @param ticketSeattype
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping("user/userOrderAdd")
		public synchronized Map<String, Object> userOrderAdd(int tripsId,String ticketSeattype,HttpServletRequest request){
			Map<String,Object> resultMap = new HashMap<>();
			
			User currentUser = (User) request.getSession().getAttribute("user");
			if(currentUser != null){
				try {
					Map<String,Object> map = new HashMap<>();
					map.put("tripsId", tripsId);
					map.put("ticketSeattype", ticketSeattype);
					Ticket ticket = ticketService.selectTicketByTripsIdAndType(map);
					if(ticket==null){
						resultMap.put("result","0");
						resultMap.put("errorInfo","没有此类型的车票");
					}
					else if(ticket.getTicketNumber()<=0){
						resultMap.put("result","0");
						resultMap.put("errorInfo","票已售完，请重新选择");
					}
					else if(ticket.getTicketNumber()>0){
						ticket.setTicketNumber(ticket.getTicketNumber()-1);
						ticketService.updateTicketById(ticket);
						
						Order order = new Order();
						order.setOrderPrice(ticket.getTicketPrice());
						order.setOrderQuantity(1);
						order.setTripsId(tripsId);
						order.setUserId(currentUser.getUserId());
						order.setOrderStatus("未完成");
						order.setTicketSeattype(ticket.getTicketSeattype());
						orderService.insert(order);
						resultMap.put("result","1");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					resultMap.put("result","0");
					resultMap.put("errorInfo","系统出错");
				}
				
			}
			else{
				resultMap.put("result","0");
				resultMap.put("errorInfo","请先登录");
			}
			
			return resultMap;
		}
		
		
		/**用户查询订单
		 * @param request
		 * @return
		 */
		@RequestMapping("user/userOrderSelect")
		public String userOrderSelect(HttpServletRequest request){
			try {
				User currentUser = (User)request.getSession().getAttribute("user"); 
				if(currentUser == null){
					return "front/index";
				}
				request.setAttribute("orders",orderService.selectOrderByUserId(currentUser.getUserId()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "front/order";
		} 
		
		
		/**用户改签
		 * @param orderId
		 * @param tripsId
		 * @return
		 */
		@ResponseBody
		@RequestMapping("user/userOrderChange")
		public synchronized Map<String,Object> userOrderChange(int orderId,int tripsId){
			Map<String,Object> resultMap = new HashMap<>();
			try {
				orderService.updateOrderChange(orderId, tripsId);
				resultMap.put("result","1");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("result","0");
				resultMap.put("errorInfo",e.getMessage());
			}
			return resultMap;
		}
		
		
		/**用户退票
		 * @param orderId
		 * @param tripsId
		 * @return
		 */
		@ResponseBody
		@RequestMapping("user/userOrderDelete")
		public synchronized Map<String,Object> userOrderDelete(int orderId){
			Map<String,Object> resultMap = new HashMap<>();
			try {
				orderService.deleteOrderById(orderId);
				resultMap.put("result","1");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("result","0");
				resultMap.put("errorInfo",e.getMessage());
			}
			return resultMap;
		}
		
		
		/**用户取票
		 * @param orderId
		 * @return
		 */
		@ResponseBody
		@RequestMapping("user/userOrderConfirm")
		public synchronized Map<String,Object> userOrderConfirm(Order order){
			Map<String,Object> resultMap = new HashMap<>();
			try {
				order.setOrderStatus("已完成");
				orderService.updateOrderChange(order);
				resultMap.put("result","1");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("result","0");
				resultMap.put("errorInfo",e.getMessage());
			}
			return resultMap;
		}
		
		/**管理员查询所有订单
		 * @param pageBean
		 * @return
		 */
		@ResponseBody
		@RequestMapping("admin/adminOrderSelect")
		public Map<String,Object> adminOrderSelect(PageBean pageBean){
			Map<String, Object> resultMap = new HashMap<>();
			Map<String, Object> pageMap = new HashMap<>();
			
			try {
				pageMap.put("showNumber", pageBean.getLimit());
				pageMap.put("startNumber",(pageBean.getPage()-1)*pageBean.getLimit());
				resultMap.put("data", formatJSON.orderToJSON(orderService.selectOrder(pageMap)));
				pageBean.setTotalCount(ticketService.getTicketCountNumber());//总条数
				if(pageBean.getTotalCount()<pageBean.getLimit()){
					pageBean.setTotalPage(1);
				} else if (pageBean.getTotalCount() % pageBean.getLimit() != 0) {
					pageBean.setTotalPage(pageBean.getTotalCount() / pageBean.getLimit()+1);
				} else //总页数
					pageBean.setTotalPage(pageBean.getTotalCount() % pageBean.getLimit() == 0 ? pageBean.getTotalCount() / pageBean.getLimit() : 0);
				resultMap.put("pageInfo", pageBean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			return resultMap;
		}
}
