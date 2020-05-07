package br.edu.unoesc.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.unoesc.crud.Repository.ClienteRepository;
import br.edu.unoesc.crud.Repository.UsuarioRepository;
import br.edu.unoesc.crud.model.Cliente;
import br.edu.unoesc.crud.service.ClienteService;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	// Carregar a pagina de cadastro de locatarios
	@RequestMapping(path = { "/cadastro", "/", "" })
	public String cadastro() {
		return "clientes/cadastro";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "clientes/listar";
	}

	// Metodo para salvar o cliente, chama esse no botao "salvar" e redireciona pra
	// tela de listagem
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public String salvar(Cliente cliente, Model model) {
		clienteService.salvar(cliente);
		model.addAttribute("clientes", clienteRepository.findAll());
		return "clientes/listar";
	}

	// Metodo para excluir o cliente
	@RequestMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, Cliente cliente) {
		clienteService.excluir(id, cliente);
		return "redirect:/clientes/listar";
	}

	@RequestMapping(path = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Optional<Cliente> c = clienteRepository.findById(id);
		model.addAttribute("clientes", c.get());
		return "clientes/editar";
	}

	@RequestMapping(path = "/salvarEditado", method = RequestMethod.POST)
	public String editando(Cliente cliente, Model model) {
		clienteRepository.saveAndFlush(cliente);
		return "redirect:/clientes/listar";
	}

}
