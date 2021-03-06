package com.PFCbalancer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.PFCbalancer.dao.UserDaoInterface;
import com.PFCbalancer.service.UserService;
import com.PFCbalancer.model.FormLoginData;
import com.PFCbalancer.model.User;

@Controller
public class LoginController {

	@Autowired
    UserDaoInterface userDao;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String getLogin(@ModelAttribute FormLoginData formLoginData, Model model) {
		return "login";
	}
	
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute FormLoginData formLoginData, Model model) {
		return "signup";
	}
	
	@GetMapping("/logout")
	public String PostLogout(Model model) {
		// Sessionクリア
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/login")
	public String PostLogin(@ModelAttribute FormLoginData formLoginData, Model model) {

		User result = new User();
		result = userDao.selectOne(formLoginData.getNickname(), formLoginData.getPassword());

		if(result.getNickname() == null) {
			String msg = "nicknameまたはpasswordが一致しませんでした。";
			model.addAttribute("msg", msg);
			return "login";
		}
		session.setAttribute("loginUser", result);
		return "redirect:/";
	}
	
	@PostMapping("/signup")
	public String PostSignup(@ModelAttribute FormLoginData formLoginData, BindingResult bindingResult, Model model) {
				
		User user = new User();
		user.setEmail(formLoginData.getEmail());
		user.setNickname(formLoginData.getNickname());
		user.setPassword(formLoginData.getPassword());
		
		boolean result = userService.insertOne(user);
		
		if(result == true) {
			System.out.println("insert成功。");
			return "redirect:/login";
		} else {
			System.out.println("insert失敗。");
			return "redirect:/signup";
		}
		
	}

	


}
