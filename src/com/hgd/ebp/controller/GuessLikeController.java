package com.hgd.ebp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import com.hgd.ebp.domain.Book;
import com.hgd.ebp.filter.UserLoginFilter;
import com.hgd.ebp.service.GuessLikeService;

@Controller
public class GuessLikeController {
	@Resource
	private GuessLikeService guessSvc;
	
	@RequestMapping(value="/user/GuessLikeCtrl", method=RequestMethod.POST)
	public String guessLike(Model model,HttpSession session){
		Integer uid = (Integer)(session.getAttribute(UserLoginFilter.ATTR_ADMINUSERID));
		@SuppressWarnings("unchecked")
		List<Book> list =  (List<Book>)session.getAttribute("guessLikeList");
		if(list==null){
			list = new ArrayList<>();
		}
		List<Integer> classList = guessSvc.guessByClass(uid);
		for(int i = 0;i < classList.size();i++){
			int bid = classList.get(i);
			Book book = guessSvc.getBook(bid);
			list.add(book);
		}
		
		String gender = guessSvc.getGender(uid);
		List<Integer> genderList = guessSvc.guessByGender(gender);
		for(int i = 0;i <genderList.size();i++){
			int bid = genderList.get(i);
			Book book = guessSvc.getBook(bid);
			list.add(book);
		}
		model.addAttribute("guessLikeList", list);
		return "/index";
	}
	

}
