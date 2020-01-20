package com.PFCbalancer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PFCbalancer.model.FormLoginData;
import com.PFCbalancer.model.User;

import com.PFCbalancer.dao.UserDaoInterface;

@Controller
public class LoginController {
	
	@Autowired
    private UserDaoInterface userDao;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/login")
	public String getLogin(@ModelAttribute FormLoginData formLoginData, Model model) {
		return "login";
	}
	
	@PostMapping("/login")
	public String PostLogin(@ModelAttribute FormLoginData formLoginData, RedirectAttributes attributes, Model model) {
		
		User result = new User();
		result = userDao.selectOne(formLoginData.getNickname(), formLoginData.getPassword());
		
		if(result.getNickname() == null) {
			String msg = "nicknameまたはpasswordが一致しませんでした。";
			model.addAttribute("msg", msg);
			return "login";
		}
		
		session.setAttribute("loginUser", result);
//		attributes.addFlashAttribute("loginUser", result);
		
		return "redirect:/";
	}


}
