package com.hgd.ebp.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.Book;
import com.hgd.ebp.exception.BookException;
import com.hgd.ebp.exception.CommentException;
import com.hgd.ebp.filter.UserLoginFilter;
import com.hgd.ebp.service.BookService;
import com.hgd.ebp.state.BookQueryState;
import com.hgd.ebp.domain.CommentBean;

@Controller
public class BooksController{
	@Resource
	private BookService bookSvc;
	
	@RequestMapping(value="/ListBooksCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session,String keyWord){     //处理搜索筛选
		session.removeAttribute("BookQueryState");
	    
		Map<String, String> errorMap = new HashMap<String, String>();
		try{
			 if(keyWord == null || "".equals(keyWord)){
				 errorMap.put("wrong", "请输入查询条件");
			 }	 
		}
		catch(Exception e){
			e.printStackTrace();
			errorMap.put("GLOBAL", "发生非预期错误，请联系系统管理员");
			model.addAttribute("errorMap", errorMap);
	        return "QueryBooks";
		}
		if(errorMap.size()!=0){
			model.addAttribute("errorMap", errorMap);
			return "QueryBooks";
		}
		
		BookQueryState state = new BookQueryState(0,keyWord);
		List<Book> list = null;
		try{
			int lastPage = bookSvc.getLastPage(state);
			state.setLastPage(lastPage);
			
		    list = bookSvc.getQueryBooks(state);
			session.setAttribute("BookQueryState", state);
			model.addAttribute("lastPage", lastPage);
		}
		catch(Exception e){
			e.printStackTrace();
			errorMap.put("GLOBAL", "发生非预期错误，请联系系统管理员");
			model.addAttribute("errorMap", errorMap);
			
		}
		model.addAttribute("QueryBookList",list);
        
        return "QueryBooks";
    }
	
	@RequestMapping(value="/ListBooksCtrl", method=RequestMethod.GET)
	public String listAllBooks(Model model, HttpSession session, String page){ //处理翻页
		BookQueryState state = null;

		if (page == null) {
			page = "0";
			session.removeAttribute("BookQueryState");
			state = new BookQueryState();
		} else {
			state = (BookQueryState)
					session.getAttribute("BookQueryState");
			if (state == null) {
				state = new BookQueryState();
			}
		}
		
		List<Book> list = null;
		try {
			
			int lastPage = bookSvc.getLastPage(state);
			state.setLastPage(lastPage);
			list = bookSvc.getBooksByPage(state, page);
			session.setAttribute("BookQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<Book>();
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系系统管理员");
			model.addAttribute("errorMap", errorMap);
		}
		model.addAttribute("QueryBookList",list);
        return "QueryBooks";
	
	}
	
	@RequestMapping(value="/BookInfoCtrl", method=RequestMethod.GET)	
	public String getBook(Model model, HttpSession session,Integer bid) {
		if(bid != null)
			session.removeAttribute("getBook");
		Book book = (Book)session.getAttribute("getBook");
		List<CommentBean> list = new ArrayList<CommentBean>();
		if(book == null){
			book = bookSvc.getBook(bid);
			list = bookSvc.getComments(bid);
		}
		else{
			list = bookSvc.getComments(book.getBid());
		}
		session.setAttribute("getBook",book);
		if(list == null)
			list = new ArrayList<>();
		model.addAttribute("GetComments",list);
		return "detailInfo";
	}
	
	
	@RequestMapping(value="/user/SendCommentCtrl", method=RequestMethod.POST)
	public String sendComment(@Valid @ModelAttribute("commentbean")CommentBean commentbean,
			Errors errors,HttpSession session){
			if (errors.hasFieldErrors()) return "detailInfo";
			try { 
					Integer uid = (Integer)(session.getAttribute(UserLoginFilter.ATTR_ADMINUSERID)); 
					Book book = (Book) (session.getAttribute("getBook"));
					Integer bid = book.getBid();
					if(uid == null)
						return "user/UserLogin";
					if(bid == null)
					{
						return "detailInfo";
					}
					commentbean.setUid(uid);
					commentbean.setBid(bid);
				    bookSvc.sendComment(commentbean);
				} catch (Exception e) {
					e.printStackTrace();
					errors.reject("", (e instanceof CommentException) ? 
							e.getMessage() : "发生非预期错误，请联系管理员");
					return "detailInfo";
				}
				
			return "redirect:/BookInfoCtrl";
		}
	
		
	@RequestMapping(value="/GetCommentsCtrl", method=RequestMethod.GET)	
	public String getComments(Model model, HttpSession session)	{
	    Book book = (Book) (session.getAttribute("getBook"));
		Integer bid = book.getBid();
		List<CommentBean> list = bookSvc.getComments(bid);
		if(list == null)
			list = new ArrayList<>();
		model.addAttribute("GetComments",list);
		return "";
	}
	
	@RequestMapping(value="/admin/BooksCtrl", method=RequestMethod.GET)
	public String listAll(Model model,HttpSession session, String page) {
		BookQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("BookQueryState");
			state = new BookQueryState();
		} else {
			state = (BookQueryState)
					session.getAttribute("BookQueryState");
			if (state == null) {
				state = new BookQueryState();
			}
		}
		List<Book> list = null;
		try {
			int lastPage = bookSvc.getLastPage(state);
			state.setLastPage(lastPage);
			list = bookSvc.getBooksByPage(state, page);
			session.setAttribute("BookQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (BookException e) {
			e.printStackTrace();
			list = new ArrayList<Book>();
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
		}
		model.addAttribute("booksList", list); 
        return "admin/BooksMng";
	}

	@RequestMapping(value="/admin/BooksCtrl", method=RequestMethod.POST)
	public String listTimeBy(Model model, HttpSession session,String beginTime,String endTime,String keyWord) {
		session.removeAttribute("BookQueryState");
		Map<String,String> errorMap = new HashMap<String,String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateBegin=null;
		Date dateEnd=null;
		try{
			if (!(beginTime == null || "".equals(beginTime)))
				dateBegin = (Date) dateFormat.parse(beginTime);
			if (!(endTime == null || "".equals(endTime)))
				dateEnd = (Date) dateFormat.parse(endTime);
			if(dateBegin != null && dateEnd != null){
				if(dateBegin.getTime()>dateEnd.getTime()){
					errorMap.put("GLOBAL","起始日期应小于终止日期,请确认后重新输入");
					model.addAttribute("errorMap", errorMap);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errorMap", errorMap);
			return "admin/BooksMng";
		}
		BookQueryState state = new BookQueryState(0, beginTime, endTime,keyWord);
		List<Book> list = null;
		try{
			int lastPage = bookSvc.getLastPage(state);
			state.setLastPage(lastPage);
			list = bookSvc.getQueryBooks(state);
			session.setAttribute("BookQueryState", state);
			model.addAttribute("lastPage", lastPage);
		}
		catch(Exception e){
			e.printStackTrace();
			list = new ArrayList<Book>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
		}
		model.addAttribute("booksList", list); 
		return "admin/BooksMng";
	}

	private String manageListRefresh(Model model, HttpSession session) {
		BookQueryState state = (BookQueryState)
				session.getAttribute("BookQueryState");
		if (state == null) {
			state = new BookQueryState();
		}
		List<Book> list = null;
		try {
			int lastPage = bookSvc.getLastPage(state);
			state.setLastPage(lastPage);
			list = bookSvc.getQueryBooks(state);
			session.setAttribute("BookQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<Book>();
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
		}
		model.addAttribute("booksList", list); 
        return "admin/BooksMng";
	}

	@RequestMapping(value="/admin/AddBookCtrl", method=RequestMethod.POST)
    public String add(@Valid @ModelAttribute("book")Book book,
   		 Errors errors,Model model) {  
		if (errors.hasFieldErrors()) return "admin/AddBook";
		
		try {
			Book bookInsert = new Book(book.getBname(),book.getImage(),book.getAuthor(),book.getDescp(),
										book.getPrice(),book.getPublisher(),book.getPublishTime(),book.getType());
			bookSvc.addBook(bookInsert);
			model.addAttribute("bookInsert",bookInsert);
		} catch (Exception e) {
			e.printStackTrace();
			errors.reject("", "发生非预期错误，请联系管理员");
			return "admin/AddBook";
		}
		return "admin/AddBookSucc";
	}

	
	@RequestMapping(value="/admin/UpdateBookCtrl")
	public String preUpdate(Model model, HttpSession session, int bid) {
		try {
			Book bookget = bookSvc.getBook(bid);
			model.addAttribute("book", bookget);
			
		} catch (Exception e) {
			e.printStackTrace();
			return manageListRefresh(model, session);
		}
		return "admin/UpdateBook";
	}

	@RequestMapping(value="/admin/UpdateBookCtrl", method=RequestMethod.POST)
    public String update(@Valid @ModelAttribute("book")Book book,
   		 Errors errors, Model model, HttpSession session) {
    	if (errors.hasFieldErrors()) return "admin/BooksMng";
    	try {
			bookSvc.updateBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manageListRefresh(model, session);
	}

	
}


