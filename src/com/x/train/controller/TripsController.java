package com.x.train.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x.train.bean.PageBean;
import com.x.train.bean.Ticket;
import com.x.train.bean.Trips;
import com.x.train.serviceInterface.TripsServiceInterface;
import com.x.train.util.FormatJSON;


@Controller
public class TripsController {
		
		private TripsServiceInterface tripsService;
		private FormatJSON formatJSON;
		
		public FormatJSON getFormatJSON() {
			return formatJSON;
		}
		
		@Resource
		public void setFormatJSON(FormatJSON formatJSON) {
			this.formatJSON = formatJSON;
		}

		public TripsServiceInterface getTripsService() {
			return tripsService;
		}
		
		@Resource
		public void setTripsService(TripsServiceInterface tripsService) {
			this.tripsService = tripsService;
		}

		/**管理员添加车次
		 * @param trips
		 * @return
		 */
		@ResponseBody
		@RequestMapping("admin/adminTripsAdd")
		public Map<String, Object> adminTripsAdd(Trips trips){
			Map<String, Object> resultMap = new HashMap<>();
			try {
				tripsService.insertTrips(trips);
				resultMap.put("result","1");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("result","0");
				resultMap.put("errorInfo",e.getMessage());
			}
			return resultMap;
		}
		
		/**管理员得到所有车次信息
		 * @return
		 */
		@ResponseBody
		@RequestMapping("admin/adminTripsSelect")
		public Map<String,Object> adminTripsSelect(PageBean pageBean,String flag){
			Map<String, Object> resultMap = new HashMap<>();
			Map<String, Object> pageMap = new HashMap<>();
			
			try {
				if(flag==null){
					pageMap.put("showNumber", pageBean.getLimit());
					pageMap.put("startNumber",(pageBean.getPage()-1)*pageBean.getLimit());
					resultMap.put("data", formatJSON.tripsToJSON(tripsService.selectTrips(pageMap)));
					pageBean.setTotalCount(tripsService.getTripsCountNumber());//总条数
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
				}
				else if(flag!=null&&(flag.equals("ticket_add"))){
					pageMap.put("showNumber", null);
					pageMap.put("startNumber",null);
					resultMap.put("result","1");
					resultMap.put("content", formatJSON.tripsToJSON(tripsService.selectTrips(pageMap)));
				}
				else{
					resultMap.put("result","0");
				}
			
			}catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result","0");
			}
			return resultMap;
		}
		
		/*管理员删除车次信息
		 * @param tripsId
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping("admin/adminTripsDelete")
		public Map<String,Object> adminTripsDelete(String tripsId,HttpServletRequest request){
			Map<String,Object> resultMap = new HashMap<>(); 
			try {
				tripsService.deleteTripsById(Integer.parseInt(tripsId));
				resultMap.put("result","1");
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result","0");
				// TODO: handle exception
			}
			return resultMap;
		}
		
		/**管理员修改车次信息
		 * @param trips
		 * @return
		 */
		@ResponseBody
		@RequestMapping("admin/adminTripsEdit")
		public Map<String, Object> adminTripsEdit(Trips trips){
			Map<String,Object> resultMap = new HashMap<>(); 
			try {
				tripsService.updateTripsById(trips);
				resultMap.put("result","1");
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result","0");
				// TODO: handle exception
			}
			return resultMap;
		}
		
		/**用户查询车次
		 * @param tripsOrigin
		 * @param tripsDestination
		 * @param tripsBegintime
		 * @param request
		 * @return
		 */
		@RequestMapping(value="user/userTripsSearch",method=RequestMethod.POST)
		public String userTripsSearch(	String tripsOrigin,
										String tripsDestination,
										@DateTimeFormat(pattern="yyyy-MM-dd")
										Date tripsBegintime,
										HttpServletRequest request
									){
			
			
			try {
				Map<String, Object> searchMap = new HashMap<>();
				searchMap.put("tripsOrigin",tripsOrigin);
				searchMap.put("tripsDestination",tripsDestination);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				searchMap.put("tripsBegintime",simpleDateFormat.format(tripsBegintime));
				ArrayList<Trips> tripses = tripsService.selectTripsBySearchMap(searchMap);
				
				
				String[] seatTypes = {"软座","硬座","无座","软卧","硬卧"};
				for(int i=0;i<tripses.size();i++){
						ArrayList<Ticket> newTickets = new ArrayList<>();
						for(int j=0;j<seatTypes.length;j++){
							Ticket ticket = new Ticket();
							ticket.setTicketSeattype(seatTypes[i]);
							newTickets.add(ticket);
						}
						
						ArrayList<Ticket> tickets = (ArrayList<Ticket>) tripses.get(i).getTickets();
						int n= tickets.size();
						for(int j=0;j<seatTypes.length;j++){
							for(int k=0;k<n;k++){
								if(tickets.get(k).getTicketSeattype().equals(seatTypes[j])){
									newTickets.remove(j);
									newTickets.add(j,tickets.get(k));
									break;
								}
								
							}
							
						}
						
						tripses.get(i).setTickets(newTickets);
				}
				
				request.setAttribute("tripses",tripses);
				request.setAttribute("tripsOrigin", tripsOrigin);
				request.setAttribute("tripsDestination", tripsDestination);
				request.setAttribute("tripsBegintime", simpleDateFormat.format(tripsBegintime));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "front/serach";
		}
		
}
