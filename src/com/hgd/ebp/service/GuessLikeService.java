package com.hgd.ebp.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.GuessLikeDao;
import com.hgd.ebp.domain.Book;

@Service
@Scope("singleton")
public class GuessLikeService {
	
	@Resource
	private  GuessLikeDao gDAO = null;
	

	public GuessLikeService() {
		gDAO = new GuessLikeDao();
	}

	public List<Integer> guessByClass(Integer uid) {
		List<Integer> list = gDAO.selectOnClass(uid);
		return list;
	}

	public List<Integer> guessByGender(String gender) {
		List<Integer> list = gDAO.selectOnGender(gender);
		return list;
	}

	public String getGender(Integer uid) {
		String sex = gDAO.selectGender(uid);
		return sex;
	}

	public Book getBook(int bid) {
		Book book = gDAO.selectBook(bid);
		return book;
	}
	
	public void toClose(){
		gDAO.close();
	}
}
