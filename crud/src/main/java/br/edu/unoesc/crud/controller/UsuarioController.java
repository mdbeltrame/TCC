package br.edu.unoesc.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.unoesc.crud.Repository.UsuarioRepository;
import br.edu.unoesc.crud.model.Usuario;
import br.edu.unoesc.crud.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	//Carregar a pagina de cadastro de usuarios
	@RequestMapping(path = { "/cadastro", "/", "" })
	public String cadastro() {
		return "usuarios/cadastro";
	}
	
	//Metodo para salvar o usuario, chama esse no botao "salvar" e redireciona pra tela de login
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public String salvar(Model model, Usuario usuario){
		usuarioService.salvar(usuario);
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "redirect:/login";
	}
	
	//Metodo para excluir o usuario
	@RequestMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, Usuario usuario) {
		usuarioService.excluir(id, usuario);
		return "redirect:/index/index";
	}
	
	//Metodo para listar
	@RequestMapping(path = {"/listar"})
	public String mostrar(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "usuarios/mostrar";
	}
	
}
