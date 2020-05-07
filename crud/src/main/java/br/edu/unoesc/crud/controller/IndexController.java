package br.edu.unoesc.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.unoesc.crud.Repository.PedidoRepository;
import br.edu.unoesc.crud.Repository.UsuarioRepository;

@Controller
@RequestMapping({ "/index", "/", "" })
public class IndexController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping({ "/index", "", "/" })
	public String index(Model model) {

		model.addAttribute("contausuario", usuarioRepository.count());
		model.addAttribute("contapedido", pedidoRepository.count());
		

		return "index/index";
	}
	
	

}
