package com.hgd.ebp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.BookMapper;
import com.hgd.ebp.domain.Book;
import com.hgd.ebp.exception.BookException;
import com.hgd.ebp.state.BookQueryState;
import com.hgd.ebp.util.mapUtil;
import com.hgd.ebp.domain.CommentBean;

@Service
@Scope("singleton")
public class BookService {
	@Resource
	private BookMapper bDAO;
	
	public int getLastPage(	BookQueryState state) throws BookException{
		Map<String, Object> map = new HashMap<>();
		map.put("keyWord",state.getKeyWord());
		map.put("beginTime", state.getBegintime());
		map.put("endTime", state.getEndtime());
		int count = bDAO.queryMaxCount(map);
		int maxPage = (count + mapUtil.MAX_PAGE_LINES - 1) / mapUtil.MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
}
	
	public List<Book> getQueryBooks(BookQueryState state) throws BookException {
		String keyWord = state.getKeyWord();
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", state.getCurPage() * mapUtil.MAX_PAGE_LINES);
		map.put("rowCount", mapUtil.MAX_PAGE_LINES);
		map.put("keyWord",keyWord);
		map.put("beginTime", state.getBegintime());
		map.put("endTime", state.getEndtime());
		List<Book> list = bDAO.queryByPage(map);
        return list;
	}
	
	
	public List<Book> getBooksByPage(BookQueryState state, String page) throws BookException {
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
		String keyWord = state.getKeyWord();
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", state.getCurPage() * mapUtil.MAX_PAGE_LINES);
		map.put("rowCount", mapUtil.MAX_PAGE_LINES);
		map.put("keyWord", keyWord);
		map.put("beginTime", state.getBegintime());
		map.put("endTime", state.getEndtime());
		List<Book> list = bDAO.queryByPage(map);
        return list;
	}

	public void sendComment(CommentBean commentbean) {
		bDAO.sendComment(commentbean);
	}
	
	public List<CommentBean> getComments(int bid) {
		List<CommentBean> list = bDAO.getComments(bid);
		return list;
	}

	public Book getBook(int bid) {
		Book book = bDAO.selectBook(bid);
		if(book == null) 
			book = new Book();
		return book;
	}

	public double getPrice(int bid) {
		double price = bDAO.selectPrice(bid);
		return price;
	}

	public String getBname(int bid) {
		String bname = bDAO.selectBname(bid);
		return bname;
	}
	
	public void addBook(Book book) throws BookException {
		bDAO.insert(book);
	}
	
	public void updateBook(Book book) throws BookException {
		 bDAO.update(book);
	}
}
