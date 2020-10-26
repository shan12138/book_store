package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.Book;
import com.hgd.ebp.domain.CommentBean;


public interface BookMapper {
	public int queryMaxCount(Map<String, Object> map);
	public List<Book> queryByPage (Map<String, Object> map);
	public void sendComment(CommentBean commentbean);
	public List<CommentBean> getComments(int bid);
	public Book selectBook(int bid);
	public double selectPrice(int bid);
	public String selectBname(int bid);
	public void insert(Book book);
	public void update(Book book);
}
