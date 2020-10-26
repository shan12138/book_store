package com.hgd.ebp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hgd.ebp.domain.Book;
import com.hgd.ebp.domain.Order;
import com.hgd.ebp.domain.ShoppingBook;
import com.hgd.ebp.exception.OrdersException;
import com.hgd.ebp.filter.UserLoginFilter;
import com.hgd.ebp.service.BookService;
import com.hgd.ebp.service.OrdersService;
import com.hgd.ebp.state.BookQueryState;

@Controller
public class ShoppingCartController {
	@Resource
	private BookService bookSvc;
	@Resource
	private OrdersService orderSvc;
	@RequestMapping(value="/user/AddShoppingCartCtrl", method=RequestMethod.GET)
	@ResponseBody
	public String addShoppingCart(Model model, HttpSession session) {
			@SuppressWarnings("unchecked")
			List<Book> list =  (List<Book>)session.getAttribute("shoppingList");
			Book book=(Book)session.getAttribute("getBook");
			if(list==null){
				list = new ArrayList<>();
				book.setQuantity(1);
				list.add(book);
			}
				
			else	
					{
						boolean exeist = false;
						for(int i =0;i<list.size();i++){
							if(book.getBid()==list.get(i).getBid()){
							exeist = true;
							int quantity = list.get(i).getQuantity();
							quantity++;
							list.get(i).setQuantity(quantity);
							break;
							}
					
						}
						 if(!exeist){
							    book.setQuantity(1);
							    list.add(book);
							}
				 }
				
			session.setAttribute("shoppingList", list);
			return "succ";
	}
	
	
	

	@RequestMapping(value="/user/DeleteShoppingCartCtrl", method=RequestMethod.GET)
	public String deleteShoppingCart(Model model, HttpSession session,int bid){
		@SuppressWarnings({ "unchecked"})
		List<Book> list =  (List<Book>) session.getAttribute("shoppingList");
		if(list==null){
			list = new ArrayList<>();
		}
		
		for(int i =0;i<list.size();i++){
			Book book = list.get(i);
			if(book.getBid()==bid){
				list.remove(i);
			}
		}
		
		return  "/user/ShoppingCart";
	}
	
	
	@RequestMapping(value="/user/SendListCtrl", method=RequestMethod.POST)
	@ResponseBody
	public String getShopingCart(String lists,Model model ,HttpSession session){
		
		if(lists!=null){
			String[] count = lists.split(";");
			List<ShoppingBook> checkList = new ArrayList<>();
			for(int i = 0; i<count.length;i++){
				String[] each = count[i].split(",");
				int bid = Integer.parseInt(each[0]);
				int quantity = Integer.parseInt(each[1]);
				ShoppingBook book = new ShoppingBook(bid,quantity);
				checkList.add(book);
			}
			
			
			Integer uid = (Integer)(session.getAttribute(UserLoginFilter.ATTR_ADMINUSERID));
			Map<String, String> errorMap = new HashMap<String, String>();
			double AmountToPrcie = 0;
			int AmountToNum =0;
			for(int i = 0; i<checkList.size();i++){
				int bid = checkList.get(i).getBid();
				int quantity = checkList.get(i).getQuantity();
				double price = bookSvc.getPrice(bid);
				String bname = bookSvc.getBname(bid);
				double amount = price * quantity;
				checkList.get(i).setBid(bid);
				checkList.get(i).setAmount(amount);
				checkList.get(i).setBname(bname);
				checkList.get(i).setPrice(price);
				AmountToPrcie += amount;
				AmountToNum  += quantity;
			}
			
			double balance = 0;
			try {
				balance = orderSvc.queryBalance(uid);
			} catch (OrdersException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				if(AmountToPrcie==0){
					errorMap.put("error", "当前购物车无加入");
					model.addAttribute("errorMap", errorMap);
					return "/user/ShoppingCart";
				}
				if(balance < AmountToPrcie){
					errorMap.put("error", "余额不足，请先充值");
					model.addAttribute("errorMap", errorMap);
					return "/user/ShoppingCart";
		}
				
			  orderSvc.updateBalance(uid,balance-AmountToPrcie);
				
		   	}
		catch(Exception e){
				e.printStackTrace();
				errorMap.put("GLOBAL", "发生非预期错误，请联系系统管理员");
				model.addAttribute("errorMap", errorMap);
				return "/user/ShoppingCart";
			} 
			if(errorMap.size()!=0){
				model.addAttribute("errorMap", errorMap);
				return "/user/ShoppingCart";
			}
			
			try {
				orderSvc.addOrders(AmountToPrcie,checkList,uid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Order order =  (Order)session.getAttribute("order");
			if(order==null){
				order = new Order();
			}
			
			try {
				order = orderSvc.getOrder();
			} catch (OrdersException e) {
				e.printStackTrace();
			}
			session.setAttribute("order", order);	
			
			session.setAttribute("checkList", checkList);
			@SuppressWarnings("unchecked")
			List<Book> shoppingList = (List<Book>) session.getAttribute("shoppingList");
			List <Integer>removeList = new ArrayList<Integer>(); 
			for(int i = 0; i < checkList.size();i++){
				for(int j = 0; j<shoppingList.size();j++){
					 if(checkList.get(i).getBid() == ((Book) shoppingList.get(j)).getBid()){
					   removeList.add(j);
					   break;
					}
				}
			}
			for(int j=removeList.size()-1;j>=0;j--){
				int num = removeList.get(j);
				shoppingList.remove(num);
			}
			
			
			
			List<ShoppingBook> list_copy = new ArrayList<ShoppingBook>();
			for(int i = 0;i<checkList.size();i++){
				list_copy.add(checkList.get(i));
			}
			session.setAttribute("checkList", list_copy);
			session.setAttribute("shoppingList", shoppingList);
			session.setAttribute("AmountToPrice",AmountToPrcie);
			session.setAttribute("AmountToNum",AmountToNum);
			checkList.clear();
			return "succ";
		}
		else
			return "fall";
	}

	
	@SuppressWarnings("unused")
	private String manageListRefresh(Model model, HttpSession session) {
		BookQueryState state = (BookQueryState)
				session.getAttribute("BookQueryState");
		if (state == null) {
			state = new BookQueryState();
		}
		
		List<Book> list = null;
		try {
			int lastPage = bookSvc.getLastPage(state);
			state.setLastPage(lastPage);
			
			list = bookSvc.getQueryBooks(state);
			session.setAttribute("BookQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<Book>();
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
		}

		model.addAttribute("QueryBookList", list); 
        return "QueryBooks";
	}
	
}
