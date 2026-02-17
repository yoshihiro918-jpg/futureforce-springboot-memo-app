package com.lesson.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;


@Controller
@RequestMapping("/admin") 
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/signup")
    public String showSignup(Model model) {
		model.addAttribute("admin", new Admin());
        return "admin/signup"; 
    }
	
	@GetMapping("/signin")
    public String showSignin() {
        return "admin/signin"; 
    }
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute Admin admin) {
	    String encodedPassword = passwordEncoder.encode(admin.getPassword());
	    admin.setPassword(encodedPassword);
	    
	    adminRepository.save(admin);
	    
	    return "redirect:/admin/signin";
	}


}
