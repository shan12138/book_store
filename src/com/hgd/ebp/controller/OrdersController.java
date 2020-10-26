package com.hgd.ebp.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.hgd.ebp.domain.Order;
import com.hgd.ebp.exception.OrdersException;
import com.hgd.ebp.service.OrdersService;
import com.hgd.ebp.state.OrderQueryState;

@Controller
@RequestMapping(value="/admin")
public class OrdersController {
	
	@Resource
    private OrdersService orderSvc;
	
	@RequestMapping(value="/OrdersCtrl", method=RequestMethod.GET)
	public String listOrders(Model model,HttpSession session, String page)
	{
		OrderQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("OrderQueryState");
			state = new OrderQueryState();
		} else {
			state = new OrderQueryState();
			state = (OrderQueryState)
					session.getAttribute("OrderQueryState");
		}
		List<Order> orderList = null;
		try {
			int lastPage = orderSvc.getLastPage(state);
			state.setLastPage(lastPage);
			orderList = orderSvc.getOrdersByPage(state, page);
			session.setAttribute("OrderQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (OrdersException e) {
			e.printStackTrace();
			orderList = new ArrayList<Order>();
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
		}
		model.addAttribute("orderList", orderList); 
        return "admin/OrdersMng";
	}
	
	@RequestMapping(value="/OrdersTimeCtrl", method=RequestMethod.POST)
	public String listOrderByTime(Model model, HttpSession session,String beginTime,String endTime) 
			throws ServletException, IOException {
		session.removeAttribute("OrderQueryState");
		Map<String,String> errorMap = new HashMap<String,String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1=null;
		Date dt2=null;
		try{
			dt1 = dateFormat.parse(beginTime);
			dt2 = dateFormat.parse(endTime);
			if(dt1 != null && dt2 != null&&(dt1.getTime()>dt2.getTime())){
				errorMap.put("GLOBAL","起始日期应小于终止日期,请确认后重新输入");
				model.addAttribute("errorMap",errorMap);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			errorMap.put("GLOBAL", "请输入正确的起始日期或终止日期");
			model.addAttribute("errorMap", errorMap);
			return "admin/OrdersMng";
		}
		OrderQueryState state = new OrderQueryState(0, beginTime, endTime);
		List<Order> orderList = null;
		try{
			int lastPage = orderSvc.getLastPage(state);
			state.setLastPage(lastPage);
			orderList = orderSvc.getOrders(state);
			session.setAttribute("OrderQueryState", state);
			model.addAttribute("lastPage", lastPage);
		}
		catch(Exception e){
			e.printStackTrace();
			orderList = new ArrayList<Order>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
		}
		model.addAttribute("orderList", orderList); 
		return "admin/OrdersMng";
	}
	
	@RequestMapping(value="/OrdersInfoCtrl", method=RequestMethod.POST)
	public String listOrderByInfo(Model model, HttpSession session,String name,String oid,String idCard) 
			throws ServletException, IOException {
		session.removeAttribute("OrderQueryState");
		Map<String,String> errorMap = new HashMap<String,String>();
		Integer oids = 0;
		if(oid != null && !"".equals(oid)){
			try{
				oids = Integer.parseInt(oid);
			}
			catch(Exception e){
				e.printStackTrace();
				errorMap.put("oid", "请输入正确的订单编号");
				model.addAttribute("errorMap", errorMap);
				return "admin/OrdersMng";
			}
		}
		if(!"".equals(idCard) && idCard.length()!=18){
			errorMap.put("lengthError", "身份证号码应为18位");
			model.addAttribute("errorMap", errorMap);
			return "admin/OrdersMng";
		}
		OrderQueryState state = new OrderQueryState(0, name, oids,idCard);
		List<Order> orderList = null;
		try{
			int lastPage = orderSvc.getLastPage(state);
			state.setLastPage(lastPage);
			orderList = orderSvc.getOrders(state);
			session.setAttribute("OrderQueryState", state);
			model.addAttribute("lastPage", lastPage);
		}
		catch(Exception e){
			e.printStackTrace();
			orderList = new ArrayList<Order>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
			return "admin/OrdersMng";
		}
		model.addAttribute("orderList", orderList); 
		return "admin/OrdersMng";
	}
}