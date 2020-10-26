package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.domain.User;
import com.hgd.ebp.state.UserQueryState;

public interface AdminMapper {
	
	public List<Admin> adminLogin(Admin admin);
	public int getMaxCount(UserQueryState state);
	public void UpdateStatus(Map<String, Object> map);
	public List<User> queryByPage(Map<String, Object> map);
}
