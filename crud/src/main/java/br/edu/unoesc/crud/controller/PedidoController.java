package br.edu.unoesc.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.unoesc.crud.Repository.PedidoRepository;
import br.edu.unoesc.crud.model.Pedido;
import br.edu.unoesc.crud.service.PedidoService;


@RequestMapping("/pedido")
@Controller
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private PedidoRepository pedidoRepository;

	// Carregar a pagina de cadastro de pedido
	@RequestMapping(path = { "/cadastro", "/", "" })
	public String cadastro() {
		return "pedido/cadastro";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("pedido", pedidoRepository.findAll());
		return "pedido/listar";
	}

	// Metodo para salvar o pedido, chama esse no botao "salvar" e redireciona pra
		// tela de listagem
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public String salvar(Pedido pedido, Model model) {
		pedidoService.salvar(pedido);
		model.addAttribute("pedido", pedidoRepository.findAll());
		return "pedido/listar";
	}

	// Metodo para excluir o pedido
	@RequestMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, Pedido pedido) {
		pedidoService.excluir(id, pedido);
		return "redirect:/pedido/listar";
	}

	@RequestMapping(path = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Optional<Pedido> p = pedidoRepository.findById(id);
		model.addAttribute("pedido", p.get());
		return "pedido/editar";
	}

	@RequestMapping(path = "/salvarEditado", method = RequestMethod.POST)
	public String editando(Pedido pedido, Model model) {
		pedidoRepository.saveAndFlush(pedido);
		return "redirect:/pedido/listar";
	}
	
	
}
