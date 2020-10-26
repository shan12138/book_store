package com.hgd.ebp.service;

import com.hgd.ebp.dao.AdminMapper;
import com.hgd.ebp.domain.*;
import com.hgd.ebp.exception.AdminException;
import com.hgd.ebp.exception.EBPexception;
import com.hgd.ebp.state.UserQueryState;
import com.hgd.ebp.util.mapUtil;
import com.hgd.ebp.vi.UserCount;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class AdminService {
	
	@Resource
	private AdminMapper aDAO;
	private List<UserCount> list;
	public AdminService(){}
	
	public Admin adminLogin(Admin admin) throws AdminException{
		List<Admin> list =aDAO.adminLogin(admin);
		if (list.size() == 0) {
        	throw new AdminException("用户名或密码错");
        }
        
        if (list.size() > 1) {
        	throw new AdminException("用户重复，请联系管理员");
        }
        
        return list.get(0);
	}

	public List<User> getUsers(UserQueryState state) throws EBPexception{
		List<User> userList = new ArrayList<User>();
		Map<String, Object> map = new HashMap<>();
		map.put("startTime", state.getStartTime());
		map.put("endTime", state.getEndTime());
		map.put("name", state.getName());
		map.put("idCard", state.getIdCard());
		map.put("telNo", state.getTelNo());
		map.put("startRow", state.getCurPage() * mapUtil.MAX_PAGE_LINES);
		map.put("rowCount", mapUtil.MAX_PAGE_LINES);
		userList = aDAO.queryByPage(map);
		return userList;
	}
	
	public int getLastPage(UserQueryState state) throws EBPexception{
		setList(new ArrayList<UserCount>());
		int count= aDAO.getMaxCount(state);
		int maxPage = (count+mapUtil.MAX_PAGE_LINES-1) / mapUtil.MAX_PAGE_LINES;
		int lastPage = maxPage > 0 ? maxPage-1 : 0;
		return lastPage;
	} 

	public List<User> getUserByPage(UserQueryState state, String page)throws EBPexception {
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
		map.put("startTime", state.getStartTime());
		map.put("endTime", state.getEndTime());
		map.put("name", state.getName());
		map.put("idCard", state.getIdCard());
		map.put("telNo", state.getTelNo());
		map.put("startRow", state.getCurPage() * mapUtil.MAX_PAGE_LINES);
		map.put("rowCount", mapUtil.MAX_PAGE_LINES);
		List<User> list = aDAO.queryByPage(map);
        return list;
	}
	
	public void changeStatus(int uid, int status)throws EBPexception {
		status = (status ==1 ? 0 : 1);
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("status", status);
		aDAO.UpdateStatus(map);
	}
	
	public List<User> getCurPageSelect(UserQueryState state)throws EBPexception{
		Map<String, Object> map = new HashMap<>();
		map.put("state", state);
		map.put("startRow", state.getCurPage() * mapUtil.MAX_PAGE_LINES);
		map.put("rowCount", mapUtil.MAX_PAGE_LINES);
		List<User> list = aDAO.queryByPage(map);
        return list;
	}

	public List<UserCount> getList() {
		return list;
	}

	public void setList(List<UserCount> list) {
		this.list = list;
	}
}
