package br.edu.unoesc.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.unoesc.crud.service.LoginService;

@Controller
@RequestMapping(path = {"/login"})
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(path = {"/entrar", "", "/" }, method = RequestMethod.GET)
	public String entrar() {
		return"login/entrar";
	}
	
	@RequestMapping(path = {"/login"}, method = RequestMethod.GET)
	public String loginPage( @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model) {
		String errorMessge = null;
		if (error != null) {
			errorMessge = "Usuario ou senha incorreta!!";
		}
		if (logout != null) {
			errorMessge = "Logout feito com sucesso!!";
		}
		model.addAttribute("errorMessage", errorMessge);
		return "index/index";
	}

}
