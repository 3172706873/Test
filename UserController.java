package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import model.User;
import service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	HttpServletRequest request;

	@ResponseBody
	@RequestMapping(value="register", produces="application/json;charset=utf-8")
	public String register(User user, String vCode) {
		return userService.register(user, vCode);
	}
	
	@ResponseBody
	@RequestMapping(value="login", produces="application/json;charset=utf-8")//以UTF-8编码返回JSON值
	public String login(User user) {
		return userService.login(user);
	}
	
	@ResponseBody
	@RequestMapping(value="loginByVCode", produces="application/json;charset=utf-8")
	public String loginByVCode(String phoneOrEmail, String vCode) {
		return userService.loginByVcode(phoneOrEmail, vCode);
	}
	
	@ResponseBody
	@RequestMapping(value="sendVCode")
	public boolean sendVCode(String content, String type, String action) {
		return userService.sendVCode(content, type, action);
	}
	
	@ResponseBody
	@RequestMapping(value="verifyVCode")
	public boolean verifyVCode(String vCode) {
		return userService.verifyVCode(vCode);
	}
	
	@ResponseBody
	@RequestMapping(value="updateEmail")
	public boolean updateEmail(User user, String vCode) {
		return userService.updateEmail(user, vCode);
	}
	
	@ResponseBody
	@RequestMapping(value="updatePassword")
	public boolean updatePassword(User user, String vCode) {
		return userService.updatePassword(user, vCode);
	}
	
	@ResponseBody
	@RequestMapping(value="updateUser")
	public boolean updateUser(String phoneNumber, String content, String type) {//type:修改哪种信息
		return userService.updateUser(phoneNumber, content, type);
	}
	
	@ResponseBody
	@RequestMapping(value="updateHeadImg", produces="application/json;charset=utf-8")
	public String updateHeadImg(String phoneNumber, MultipartFile headImg) {
		return userService.updateHeadImg(headImg, phoneNumber);
	}
	
	@ResponseBody
	@RequestMapping(value="logout")
	public void logout() {
		request.getSession().invalidate();
	}
	
	@ResponseBody
	@RequestMapping(value="getSessionId")
	public void getSessionId() {
		System.out.println("a request from android;" + userService.getIp());
		request.getSession().setAttribute("test", "test");
	}

	@ResponseBody
	@RequestMapping(value="getHighRateUsers", produces="application/json;charset=utf-8")
	public String getHighRateUsers(String phoneNumber) {
		return userService.getHighRateUsers(phoneNumber);
	}
}
