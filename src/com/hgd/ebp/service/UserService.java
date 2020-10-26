package com.hgd.ebp.service;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.*;
import com.hgd.ebp.domain.*;
import com.hgd.ebp.exception.UserException;
import com.hgd.ebp.state.UserOrdersQueryState;
import com.hgd.ebp.util.mapUtil;
import com.hgd.ebp.vi.RenewUserBean;
import com.hgd.ebp.vi.UserBean;

@Service
@Scope("singleton")
public class UserService {
	@Resource
	private  UserMapper uDAO;
	
	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int userlogin(UserBean userbean) throws UserException{
		List<Integer> list=uDAO.userlogin(userbean);
		if (list.size() == 0) {
        	throw new UserException("用户名或密码错");
        }
        if (list.size() > 1) {
        	throw new UserException("用户重复，请联系管理员");
        }
        else {
        	User user = uDAO.selectUserById(list.get(0));
        	if(user.getStatus()==0)
        		throw new UserException("当前处于禁用状态");
		}
        return list.get(0);
	}
	
	public void register(User user)throws UserException{
		uDAO.userregister(user);
	}
	
	public int getLastPage(UserOrdersQueryState state){
		int count = uDAO.queryMaxCount(state);
		int maxPage = (count + mapUtil.MAX_ORDER_LINES - 1) / mapUtil.MAX_ORDER_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
		
	}
	
	public List<Order> getUserOrdersByPage(UserOrdersQueryState state,String page){
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
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", state.getCurPage() * mapUtil.MAX_ORDER_LINES);
		map.put("rowCount", mapUtil.MAX_ORDER_LINES);
		map.put("uid", state.getUid());
		
		List<Order> list = uDAO.queryByPage(map);
        return list;
		
	}
	
	public void changeUserInfor(RenewUserBean rubean) throws UserException {
		// TODO Auto-generated method stub
		if(rubean.getPassword() != "")
			uDAO.updateUserInfor(rubean);
		else
			uDAO.updateUserInforNoPassword(rubean);
	}
	
	public void chargeUserAccount(int uid,double balance)throws UserException{
		
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("balance", balance);
		
		 uDAO.updateUserAccount(map);
		
	}
	
	public User getUserById(int uid)throws UserException {
		User user = new User();
		user = uDAO.selectUserById(uid);
		return user;
	}
	
	public List<UserOrders> getList(int oid) {
		List<UserOrders> list = uDAO.selectByOid(oid);
		return list;
	}
}
