package com.hgd.ebp.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.hgd.ebp.exception.AdminException;
import com.hgd.ebp.exception.EBPexception;
import com.hgd.ebp.filter.AdminLoginFilter;
import com.hgd.ebp.service.AdminService;
import com.hgd.ebp.state.UserQueryState;
import com.hgd.ebp.domain.*;

@Controller
public class AdminController{

	private final String UPLOAD_PATH = "bookImages/";
	@Resource
	private AdminService adminSvc;
	
	@RequestMapping(value="/admin/AdminLogin", method=RequestMethod.POST)
	public String Login(@Valid @ModelAttribute("admin")Admin admin,
			Errors errors,HttpSession session)
	{
		if (errors.hasFieldErrors()) return "admin/AdminLogin";
		try{
			admin=adminSvc.adminLogin(admin);
		}catch(Exception e){
			 e.printStackTrace();
			 errors.reject("", (e instanceof AdminException) ? 
						e.getMessage() : "发生非预期错误，请联系管理员");
		     return "admin/AdminLogin";
		}
		session.setAttribute(AdminLoginFilter.ATTR_ADMINUSER, admin);
		
		return "redirect:/admin/BooksCtrl";
		
	}

	@RequestMapping(value="/admin/UsermangeCtrl", method=RequestMethod.GET)
	public String listUsers(Model model, HttpSession session, String page)
			throws ServletException, IOException {
		UserQueryState state = null;
		if(page == null){
			//表示点击查询按钮产生或点击菜单栏链接
			page = "0";
			session.removeAttribute("UserQueryState");
			state = new UserQueryState();
		}else {
			//获取当前用户页面的状态
			state = (UserQueryState)
					session.getAttribute("UserQueryState");
			if (state == null) {
				state = new UserQueryState();
				state.setStatus(0);
			}
		}
		
		List<User> userList = null;
		try {
			int lastPage = adminSvc.getLastPage(state);
			state.setLastPage(lastPage);
			userList = adminSvc.getUserByPage(state, page);
			session.setAttribute("UserQueryState", state);
			model.addAttribute("lastPage", lastPage);
			
		} catch (EBPexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			userList = new ArrayList<User>();
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errorMap", errorMap);
		}
		model.addAttribute("userList", userList);
		return "/admin/UserMng";
	}
	
	@RequestMapping(value="/admin/keyTimeCtrl", method=RequestMethod.POST)
	public String listUserByTime(Model model, HttpSession session, String beginTime, String endTime)
			throws ServletException, IOException {
		Map<String, String> errorMap = new HashMap<String,String>();
		
		
		session.removeAttribute("UserQueryState");
		
		if("".equals(beginTime) && "".equals(endTime))
			errorMap.put("empty", "时间项不能全为空");
		
		try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if(!"".equals(beginTime) && !"".equals(endTime)){
	            Date dateStart = df.parse(beginTime);
	            Date dateEnd = df.parse(endTime);
	            if(dateStart.getTime()>dateEnd.getTime())
	            	errorMap.put("timeRange", "日期范围有误~");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMap.put("GLOBAL", "发生非预期的错误~");
        }
		
		if(errorMap.size()!=0){
			model.addAttribute("errorMap", errorMap);
			return "/admin/UserMng";
		}
		endTime +=" 23:59:59";
		UserQueryState state = new UserQueryState(beginTime,endTime);
		try{
			state.setStatus(1);//根据时间信息查询
			int lastPage = adminSvc.getLastPage(state);
			List<User> userList = adminSvc.getUsers(state);
			session.setAttribute("UserQueryState", state);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("userList", userList);
		}catch (EBPexception e){
			e.printStackTrace();
			if(errorMap.get("GLOBAL")==null)
				errorMap.put("GLOBAL", "发生非预期的错误~");
			model.addAttribute("errorMap", errorMap);
			
			return "/admin/UserMng";
		}
		
		return "/admin/UserMng";
	}
	
	@RequestMapping(value="/admin/keyUserinforCtrl", method=RequestMethod.POST)
	public String listUserByInfo(Model model, HttpSession session, String name,String idCard, String telNo )
			throws ServletException, IOException {
		Map<String, String> errorMap = new HashMap<String,String>();
		session.removeAttribute("UserQueryState");
		UserQueryState state = new UserQueryState(name, idCard, telNo);
		try{
				if("".equals(telNo) && "".equals(idCard) && "".equals(name)){
					errorMap.put("emptyError", "未填信息");
				}
				if(!"".equals(telNo)&& telNo.length()!=11)
						errorMap.put("lengthError", "电话号码应为11位");
				if(!"".equals(idCard) && idCard.length()!=18)
					if(errorMap.get("lengthError")==null)
						errorMap.put("lengthError", "身份证号码应为18位");
				if(!"".equals(name) && name.length()>50)
					if(errorMap.get("lengthError")==null)
						errorMap.put("lengthError", "输入长度为8~20个字符");
		}catch (Exception e){
			e.printStackTrace();
			errorMap.put("emptyError", "有未填信息");
		}
		if(errorMap.size()!=0){
			model.addAttribute("errorMap", errorMap);
			return "/admin/UserMng";
		}
		
		try{
			state.setStatus(2);//当前为用户信息查找
			int lastPage = adminSvc.getLastPage(state);
			state.setLastPage(lastPage);
			
			List<User> userList = adminSvc.getUsers(state);
			session.setAttribute("UserQueryState", state);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("userList", userList);
		}catch (EBPexception e){
			e.printStackTrace();
				errorMap.put("GLOBAL", "发生非预期的错误~");
			model.addAttribute("errorMap", errorMap);
			return "/admin/UserMng";
		}
		
		return "/admin/UserMng";
	}
	
	@RequestMapping(value="/admin/changeStatusCtrl")
	public String changeStatus(Model model, HttpSession session,String uid, String status)
			throws ServletException, IOException {
		Map<String, String> errorMap = new HashMap<String,String>();
		try{
			int userid = Integer.parseInt(uid);
			int idstatus = Integer.parseInt(status);
			adminSvc.changeStatus(userid, idstatus);
			if(idstatus == 1)
				model.addAttribute("msg",0);
			else
				model.addAttribute("msg", 1);
			//errorMap.put("statusError", "更改失败错误~");
		}catch (Exception e){
			e.printStackTrace();
			if(e instanceof EBPexception){
				errorMap.put("statusError", "更改失败错误~");
			}else{
				errorMap.put("GLOBAL", "发生非预期的错误~");
			}
		}
		
		if(errorMap.size()!=0){
			model.addAttribute("errorMap", errorMap);
			return backCurPage(model,session);
		}
		//return backCurPage(model,session);
		return "/admin/AjaxInfor";
	}
	
	private String backCurPage(Model model, HttpSession session){
		Map<String, String> errorMap = new HashMap<String,String>();
		UserQueryState state;
		state = (UserQueryState)session.getAttribute("UserQueryState");
		try{			
			List<User> userList = adminSvc.getCurPageSelect(state);
			model.addAttribute("userList", userList);
		}catch (EBPexception e){
			e.printStackTrace();
			errorMap.put("GLOBAL", "发生非预期的错误~");
			model.addAttribute("errorMap", errorMap);
			return "/admin/UserMng";
		}
		
		return "/admin/UserMng";
	}
	
	@RequestMapping(value="/admin/BookImageUploadCtrl", method=RequestMethod.POST)
	public String imageUpload(Model model,MultipartFile image,HttpServletRequest request){
		
		if(image == null){
			model.addAttribute("msg2", "null");
			return "/admin/AdminAjaxBody";
		}
		
		if (image.getOriginalFilename().equals("")){
			model.addAttribute("msg2", "nulltoo");
			return "/admin/AdminAjaxBody";
		} 

		//设置上传文件的保存目录
		String path = request.getServletContext().getRealPath(".");
		path += "/"+ UPLOAD_PATH;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        
        String filename = image.getOriginalFilename();
        String newFilename= System.currentTimeMillis() + "-" + filename;
        try {
            //将上传的文件保存到指定位置
        	image.transferTo(new File(path, newFilename));
            model.addAttribute("msg2","/"+UPLOAD_PATH + newFilename);
        } catch (Exception e) {
            e.printStackTrace();
            return "/admin/AdminAjaxBody";
        } 
        return "/admin/AdminAjaxBody";
	}

}
