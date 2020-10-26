package com.hgd.ebp.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.hadoop.hive.ql.parse.HiveParser_IdentifiersParser.stringLiteralSequence_return;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;












import com.hgd.ebp.service.GuessLikeService;
import com.hgd.ebp.service.UserService;
import com.hgd.ebp.state.UserOrdersQueryState;
import com.hgd.ebp.vi.ChargeBean;
import com.hgd.ebp.vi.RenewUserBean;
import com.hgd.ebp.vi.UserBean;
import com.hgd.ebp.domain.*;
import com.hgd.ebp.exception.*;
import com.hgd.ebp.filter.UserLoginFilter;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {
	
	public static final String ATTR_ADMINUSERID = "UserID";
	private final String UPLOAD_PATH = "uploadImages/";
	@Resource
	private UserService userSvc;
	@Resource
	private GuessLikeService guessSvc;
	
	@RequestMapping(value="/user/UserLogin", method=RequestMethod.POST)
	public String Login(@Valid @ModelAttribute("userbean")UserBean userbean,
			Errors errors,HttpSession session,Model model){
		if (errors.hasFieldErrors()){
			session.setAttribute("msg",false);
			return "index";
		}
		int userId;
		User user = null;
		try{
			userId=userSvc.userlogin(userbean);
			user = userSvc.getUserById(userId);
		}catch(Exception e){
			e.printStackTrace();
			errors.reject("", (e instanceof UserException) ? 
					e.getMessage() : "������Ԥ�ڴ�������ϵ����Ա");
			session.setAttribute("msg",false);
        	return "index";
		}
		
		session.setAttribute("images",user.getImages());
		session.setAttribute(UserLoginFilter.ATTR_ADMINUSERID, userId);
		session.setAttribute("msg",true);
		
		return "redirect:../";
		
	}
	
	@RequestMapping(value="/user/GuessLike", method=RequestMethod.POST)
	@ResponseBody
	public String GuessLike(HttpSession session,Model model){
			User user=null;
			Integer userId = 0;
			try {
				userId = (Integer)session.getAttribute(ATTR_ADMINUSERID);
				if(userId == null){
					return "fall";
					}else{
					Integer i = (Integer)session.getAttribute("guessLike");
					if(i == null)
						session.setAttribute("guessLike", 1);
					else
						return "fall";
					}
				user = userSvc.getUserById(userId);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			@SuppressWarnings("unchecked")
			List<Book> list =  (List<Book>)session.getAttribute("guessLikeList");
			if(list==null){
				list = new ArrayList<>();
			}
			List<Integer> classList = guessSvc.guessByClass(userId);
			for(int i = 0;i < classList.size();i++){
				int bid = classList.get(i);
				Book book = guessSvc.getBook(bid);
				list.add(book);
			}
			
			String gender = user.getGender() == "��"? "1":"0";
			List<Integer> genderList = guessSvc.guessByGender(gender);
			
			for(int i = 0;i <genderList.size();i++){
				
				int bid = genderList.get(i);
				Book book = guessSvc.getBook(bid);
				list.add(book);
			}
			
			guessSvc.toClose();
			session.setAttribute("guessLikeList", list);
			
			
		return "succ";
		
	}
	
	@RequestMapping(value="/Register", method=RequestMethod.POST)
	public String Register(@Valid @ModelAttribute("user")User user,
			Errors errors,HttpSession session,Model model){
		if(!user.getPassword().equals(user.getPasswordagain())){
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("GLOBAL", "�����������벻һ��");
			model.addAttribute("errorMap", errorMap);
			return "index";
		}
		if (errors.hasFieldErrors()) {
			return "index";
		}
		try {
				userSvc.register(user);
			} catch (Exception e) {
				e.printStackTrace();
				errors.reject("", (e instanceof UserException) ? 
						e.getMessage() : "������Ԥ�ڴ�������ϵ����Ա");
				return "index";
			}
		session.setAttribute("msg",true);
		return "redirect:index.jsp";
	}
	
	@RequestMapping(value="/user/UserQuit", method=RequestMethod.GET)
	public String Quit(@Valid @ModelAttribute("userbean")UserBean userbean,
			Errors errors,HttpSession session,Model model){
		session.setAttribute("msg",false);
		session.removeAttribute(UserLoginFilter.ATTR_ADMINUSERID);
		return "index";
		
	}
	
	@RequestMapping(value="/user/getOrders")
	   public String getOrders(Model model,HttpSession session,String page){
		   UserOrdersQueryState state = null;
			if (page == null) {
				page = "0";
				session.removeAttribute("UserOrdersQueryState");
				state = new UserOrdersQueryState();
			} else {
				state = (UserOrdersQueryState)
						session.getAttribute("UserOrdersQueryState");
				if (state == null) {
					state = new UserOrdersQueryState();
				}
			}
			state.setUid((int)session.getAttribute(UserLoginFilter.ATTR_ADMINUSERID));
			List<Order> list = null;
			try {
				int lastPage = userSvc.getLastPage(state);
				state.setLastPage(lastPage);
				
				list = userSvc.getUserOrdersByPage(state, page);
				session.setAttribute("UserOrdersQueryState", state);
				model.addAttribute("lastPage", lastPage);
			} catch (Exception e) {
				e.printStackTrace();
				list = new ArrayList<Order>();
				Map<String, String> errorMap = new HashMap<String, String>();
				errorMap.put("GLOBAL", "������Ԥ�ڴ�������ϵ����Ա");
				model.addAttribute("errorMap", errorMap);
			}
			
			HashMap<Order, List<UserOrders>> orderMap = new HashMap<>();
			for(int i = 0; i<list.size();i++)
			{
				int oid = list.get(i).getOid();
				List<UserOrders> orderlist = userSvc.getList(oid);
				orderMap.put(list.get(i),orderlist);
			}
			model.addAttribute("orderMap", orderMap);

			//����ϲ��//
			
	       return "user/UserBooks";
	       
	       
	   }
   
 //��ȡ��Ա��Ϣҳ�ĵ�ǰ�û���Ϣ
 	@RequestMapping(value="/user/UserInforCtrl")
 	public String UserInforController(Model model, HttpSession session){
 		Integer uid = (Integer)(session.getAttribute(UserLoginFilter.ATTR_ADMINUSERID));
 		User user = null;
 		RenewUserBean renewUserbean=new RenewUserBean();
 		if (uid == null)return "/user/UserLogin";
 		
 		try {
 				user = userSvc.getUserById(uid);
 				renewUserbean.setBean(user);
 		} catch (UserException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		model.addAttribute("RenewUserBean",renewUserbean);
 		return "/user/UserInfor";
 	}
 	
 	//�޸ı���
 	@RequestMapping(value="/user/ChangeUserInforCtrl", method=RequestMethod.POST)
 	public String ChangeUserInfor(@Valid @ModelAttribute("RenewUserBean")RenewUserBean renewUserbean,Errors errors,
 			 HttpSession session)
 			throws ServletException, IOException {
 		if(errors.hasFieldErrors())return "/user/UserInfor";
 		session.removeAttribute("msg");
 		Integer uid = (Integer)(session.getAttribute(UserLoginFilter.ATTR_ADMINUSERID));
 		if (uid == null){
 			session.setAttribute("msg", false);
 			return "/index";
 		}
 		boolean status = true;
 		if(!"".equals(renewUserbean.getPassword()) && "".equals(renewUserbean.getPasswordagain())){
 			errors.reject("password","�������벻ƥ��");
				return "/user/UserInfor";
			}
 			
		if("".equals(renewUserbean.getPassword()) && !"".equals(renewUserbean.getPasswordagain())){
			errors.reject("passwordagain","�������벻ƥ��");
			return "/user/UserInfor";
		}
 		
 		if(!"".equals(renewUserbean.getPassword()) && !"".equals(renewUserbean.getPasswordagain()))
				status = false;
 		//����
 		try {
 			
 			renewUserbean.setUid(uid);
 			 userSvc.changeUserInfor(renewUserbean);
 		} catch (UserException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			//�쳣
 			errors.reject("","������Ԥ�ڵĴ���");
 		}
 		System.out.println("1111111111111111111111111111111"+status);
 		session.setAttribute("msg", status);
 		//���ص���ҳ��
 		return "/index";
 	}
 	
 	//����
 	@RequestMapping(value="/user/ChangeUserInforCtrl")
 	public String RUserInfor(Model model, HttpSession session)
 			throws ServletException, IOException {
 		
 		//�ѳ����û�����balance��status�����Ķ����
 		//ȥ��request�е��û�
 		Integer uid = (Integer)(session.getAttribute(UserLoginFilter.ATTR_ADMINUSERID));
 		if(uid == null)return "/user/UserLogin";
 		User user = null;User currentUser = new User();
 		try {
 			user = userSvc.getUserById(uid);
 			//����������Ϣ
 			currentUser.setUid(uid);
 			currentUser.setUserName(user.getUserName());
 			currentUser.setBalance(user.getBalance());
 			currentUser.setImages(user.getImages());
 			currentUser.setGender("");
 		} catch (UserException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			
 		}
 		System.out.println("111111111111111111111");
 		//save�µ�User
 		model.addAttribute("RenewUserBean", currentUser);
 		return "/user/AjaxBody";
 	}
 	
 	@RequestMapping(value="/user/UserAccount")
 	public String UserAccount(HttpSession session,Model model)
 	{
 		Integer uid = (Integer)session.getAttribute("UserID");
 		if(uid == null)return "/user/UserLogin";
 		ChargeBean chargeBean = new ChargeBean();
 		User user = null;
 		try{
 			user = userSvc.getUserById(uid);
 			chargeBean.setUname(user.getUname());
 			chargeBean.setSurplusmoney(user.getBalance());
 		}catch (Exception e) {
 			e.printStackTrace();
 		}
 		session.setAttribute("ChargeBean", chargeBean);
 		
 		return "/user/UserAccount";
 	}
 	@RequestMapping(value="/user/UserAccount", method=RequestMethod.POST)
 	public String ChargeUserAccount(String styles,Double chargemoney,HttpSession session,Model model)
 	{
 		Integer uid = (Integer)session.getAttribute("UserID");
 		double totalBalance = 0.0;ChargeBean chargeBean = new ChargeBean();
 		if(uid == null)return "/user/UserLogin";
 		Map<String,String>errorMap = new HashMap<String,String>();
 		if(chargemoney==null || !(chargemoney>=100&&chargemoney<=2000))
 			errorMap.put("money","��ֵ�����100~2000Ԫ֮��");
 		if(errorMap.size()!=0){
 			model.addAttribute("errorMap",errorMap);
 			return "/user/UserAccount";
 		}
 			
 		try{
 			User user = userSvc.getUserById(uid);
 			totalBalance = user.getBalance()+chargemoney;
 			chargeBean.setSurplusmoney(totalBalance);
 			chargeBean.setUname(user.getUname());
 			chargeBean.setChargemoney(chargemoney);
 			chargeBean.setChargestyle(styles);
 			userSvc.chargeUserAccount(uid,totalBalance);
 		}catch (Exception e) {
 			e.printStackTrace();
 			errorMap.put("GOLBAL","������Ԥ�ڵĴ���~");
 		}
 		model.addAttribute("ChargeBean", chargeBean);
 		session.removeAttribute("ChargeBean");
 		model.addAttribute("errorMap",errorMap);
 		return "/user/AccountRechargeSucc";
 	}
 	
 	
 	@RequestMapping(value="/user/ImageUploadCtrl", method=RequestMethod.POST)
 	@ResponseBody
	public String imageUpload(Model model,MultipartFile images,HttpServletRequest request){
		
		if(images == null){
			//model.addAttribute("msg", "null");
			return "fall";
		}
		
		if (images.getOriginalFilename().equals("")){
			//model.addAttribute("msg", "nulltoo");
			return "fall";
		} 

		//�����ϴ��ļ��ı���Ŀ¼
		String path = request.getServletContext().getRealPath(".");
		path += "/"+ UPLOAD_PATH;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        
        String filename = images.getOriginalFilename();
        String newFilename= System.currentTimeMillis() + "-" + filename;
        try {
            //���ϴ����ļ����浽ָ��λ��
        	images.transferTo(new File(path, newFilename));
        	System.out.println(newFilename+"+++++++++++++++++++++++++++++++++++++++++++++");
           // model.addAttribute("msg","/"+UPLOAD_PATH + newFilename);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "fall";
        } 
        return "/"+UPLOAD_PATH + newFilename;
	}
}
