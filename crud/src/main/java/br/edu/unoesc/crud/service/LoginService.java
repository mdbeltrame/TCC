package br.edu.unoesc.crud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public String validaLogin(String error, String logout) {
		String errorMessge = null;
		if (error != null) {
			return errorMessge = "Usuario ou senha incorreta!!";
			
		}
		if (logout != null) {
			return errorMessge = "Logout feito com sucesso!!";
		}
		return errorMessge;
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}
}
