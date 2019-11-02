package br.edu.unoesc.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.unoesc.crud.service.LoginService;

@Controller
public class HeaderController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) { 
		loginService.logout(request, response);
		return "redirect:/login?logout=true";
	}
	
}
