package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.*;
import com.hgd.ebp.state.*;
import com.hgd.ebp.vi.RenewUserBean;
import com.hgd.ebp.vi.UserBean;

public interface UserMapper {
	public int queryMaxCount(UserOrdersQueryState state);
	public void updateUserInfor(RenewUserBean rubean);
	public List<Order> queryByPage(Map<String, Object> map);
	public void updateUserAccount(Map<String, Object> map);
	public User selectUserById(int uid);
	public List<Integer> userlogin(UserBean userbean);
	public void userregister(User user);
	public List<UserOrders> selectByOid(int oid);
	public void updateUserInforNoPassword(RenewUserBean rubean);
}
