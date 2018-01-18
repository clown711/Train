package com.x.train.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.x.train.serviceInterface.IndexServiceInterface;
import com.x.train.serviceInterface.OrderServiceInterface;



@Controller
@RequestMapping("")
public class IndexController {
	
		private IndexServiceInterface indexService;
		private OrderServiceInterface orderService;

		public OrderServiceInterface getOrderService() {
			return orderService;
		}

		public void setOrderService(OrderServiceInterface orderService) {
			this.orderService = orderService;
		}

		public IndexServiceInterface getIndexService() {
			return indexService;
		}
		
		@Resource
		public void setIndexService(IndexServiceInterface indexService) {
			this.indexService = indexService;
		}

		/**跳转管理员首页
		 * @return
		 */
		@RequestMapping("/admin")
		public String toAdminIndex(){
			
			return "admin/index";
			
		}
		
		/**跳转管理员登录界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminLogin")
		public String toAdminLogin(){
			
			return "admin/login";
			
		}
		
		/**跳转管理员增加界面
		 * @return
		 */
		@RequestMapping("admin/toAdminAdd")
		public String toAdminAdd(){
			
			return "admin/admin-add";
			
		}
		
		/**跳转管理员增加界面
		 * @return
		 */
		@RequestMapping("admin/toAdminManage")
		public String toAdminManage(){
			
			return "admin/admin-manage";
			
		}
		
		/**跳转车次添加界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminTripsAdd")
		public String toAdminTripsAdd(){
			
			return "admin/trips-add";
			
		}
		
		/**跳转车次管理界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminTripsManage")
		public String toAdminTripsManage(){
			
			return "admin/trips-manage";
			
		}
		
		/**跳转车次信息修改界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminTripsEdit")
		public String toAdminTripsEdit(int tripsId,HttpServletRequest request){
			try {
				request.setAttribute("trips",indexService.selectTripsById(tripsId));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "admin/trips-add";
			
		}
		
		/**跳转车票添加界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminTicketAdd")
		public String toAdminTicketAdd(){
			
			return "admin/ticket-add";
			
		}
		
		/**跳转车票管理界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminTicketManage")
		public String toAdminTicketManage(){
			
			return "admin/ticket-manage";
			
		}
		
		/**跳转用户管理界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminUserManage")
		public String toAdminUserManage(){
			
			return "admin/user-manage";
			
		}
		
		/**跳转车票信息修改界面
		 * @param ticketId
		 * @param request
		 * @return
		 */
		@RequestMapping("/admin/toAdminTicketEdit")
		public String toAdminTicketEdit(int ticketId,HttpServletRequest request){
			try {
				request.setAttribute("ticket",indexService.selectTicketById(ticketId));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "admin/ticket-add";
		}
		
		/**跳转订单管理界面
		 * @return
		 */
		@RequestMapping("/admin/toAdminOrderManage")
		public String toAdminOrderManage(){
			
			return "admin/order-manage";
			
		}
		
		/**跳转网站首页
		 * @return
		 */
		@RequestMapping("/front")
		public String toFrontIndex(){
			
			return "front/index";
			
		}
		
		/**跳转火车票改签页面
		 * @return
		 */
		@RequestMapping("/user/toUserOrderChange")
		public String toUserOrderChange(int orderId,String ticketSeattype,HttpServletRequest request){
			try {
				request.setAttribute("orderId",orderId);
				request.setAttribute("xiwei",ticketSeattype);
				request.setAttribute("tripses",indexService.selectTicketTimeByOrderId(orderId));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "front/gaiqian";
		}
		
}
