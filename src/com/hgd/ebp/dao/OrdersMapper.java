package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.Order;

public interface OrdersMapper {
	 public int queryMaxCount(Map<String, Object> map);
	 public List<Order> queryByPage(Map<String, Object> map);
	 public void insertOrders(Map<String, Object> map);
	 public int selectMaxOid();
	 public void insertOrderlist(Map<String, Object> map);
	 public double selectBalance(Integer uid);
	 public void updateBalance(Map<String, Object> map);
	 public Order getOrder(int maxoid);

}
