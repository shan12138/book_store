package com.hgd.ebp.service;

import com.hgd.ebp.dao.OrdersMapper;
import com.hgd.ebp.domain.Order;
import com.hgd.ebp.domain.ShoppingBook;
import com.hgd.ebp.exception.OrdersException;
import com.hgd.ebp.state.OrderQueryState;
import com.hgd.ebp.util.mapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class CopyOfOrdersService {
	@Resource
	private OrdersMapper oDAO;
	
	public int getLastPage(OrderQueryState state) throws OrdersException {
		String endTime = state.getEndtime() + " 23:59:59";
		Map<String, Object> map = new HashMap<>();
		map.put("name", state.getName());
		map.put("idcard", state.getIdcard());
		map.put("oid", state.getOid());
		map.put("beginTime", state.getBegtime());
		map.put("endTime", endTime);
		int count = oDAO.queryMaxCount(map);
		int maxPage = (count + mapUtil.MAX_PAGE_LINES - 1) / mapUtil.MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
	}
	
	public List<Order> getOrdersByPage(OrderQueryState state, 
			String page) throws OrdersException {
		int curPage = state.getCurPage();
		switch (page) {
			case "0":
				curPage = 0;
				break;
			case "prev":
				if (curPage > 0) curPage--;
				break;
			case "next":
				if (curPage < state.getLastPage()) curPage++;
				break;
			default:
				curPage = state.getLastPage();
				break;
		}
		state.setCurPage(curPage);
		String endTime = state.getEndtime() + " 23:59:59";
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", state.getCurPage() * mapUtil.MAX_PAGE_LINES);
		map.put("rowCount", mapUtil.MAX_PAGE_LINES);
		map.put("name", state.getName());
		map.put("idcard", state.getIdcard());
		map.put("oid", state.getOid());
		map.put("beginTime", state.getBegtime());
		map.put("endTime", endTime);
		List<Order> list = oDAO.queryByPage(map);
        return  list;
	}
	
	public List<Order> getOrders(OrderQueryState state) throws OrdersException {
		String endTime = state.getEndtime() + " 23:59:59";
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", state.getCurPage() * mapUtil.MAX_PAGE_LINES);
		map.put("rowCount", mapUtil.MAX_PAGE_LINES);
		map.put("name", state.getName());
		map.put("idcard", state.getIdcard());
		map.put("oid", state.getOid());
		map.put("beginTime", state.getBegtime());
		map.put("endTime", endTime);
		List<Order> list = oDAO.queryByPage(map);
        return list;
	}
	
	public void addOrders(double AmountToPrcie,
			List<ShoppingBook> list, Integer uid)throws OrdersException {
		 	Map<String, Object> map = new HashMap<>();
			map.put("AmountToPrcie", AmountToPrcie);
			map.put("uid", uid);
		    oDAO.insertOrders(map);
		    int maxOid = oDAO.selectMaxOid();
		    map.put("maxOid",maxOid);
		    for(int i = 0; i<list.size();i++){
		    	ShoppingBook book = list.get(i);
		    	map.put("book", book);
		    	oDAO.insertOrderlist(map);
		   }
	}

	public double queryBalance(Integer uid) throws OrdersException {
		
		return oDAO.selectBalance(uid);
	}
	
	public void updateBalance(int uid,double balance) throws OrdersException{
		Map<String, Object> map = new HashMap<>();
		map.put("balance", balance);
		map.put("uid", uid);
		oDAO.updateBalance(map);
	}

	public Order getOrder() throws OrdersException {
		 int maxoid = oDAO.selectMaxOid();
		Order order = oDAO.getOrder(maxoid);
		return order;
	} 

}