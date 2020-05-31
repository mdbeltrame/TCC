package br.edu.unoesc.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.unoesc.crud.Repository.PedidoRepository;
import br.edu.unoesc.crud.Repository.ProdutoRepository;
import br.edu.unoesc.crud.model.Pedido;
import br.edu.unoesc.crud.model.Produto;
import br.edu.unoesc.crud.service.PedidoService;

@RequestMapping("/pedido")
@Controller
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;

	// Carregar a pagina de cadastro de pedido
	@RequestMapping(path = { "/cadastro" })
	public String cadastro(Pedido pedido, Model model) {
		model.addAttribute("pedido", new Pedido());
		model.addAttribute("produtos", produtoRepository.findAll());
		return "pedido/adicionarProduto";
	}

	// Carregar a pagina de cadastro de pedido
	@RequestMapping(path = { "/cadastro/{id}" })
	public String adicionar(Model model, @PathVariable(value = "id") Long id) {
		model.addAttribute("produtos", produtoRepository.findAll());
		model.addAttribute("pedido", pedidoRepository.getOne(id));
		return "pedido/adicionarProduto";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("pedido", pedidoRepository.findAll());
		return "pedido/listar";
	}

	// Metodo para salvar o pedido, chama esse no botao "salvar" e redireciona pra
	// tela de listagem
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public String salvar(Produto produto, Pedido pedido, Model model, ModelMap modelMap) {
		List<Produto> produtos = pedido.getProduto();
		produtos.add(produto);
		pedido.setProduto(produtos);
		pedidoRepository.save(pedido);
		model.addAttribute("pedido", pedidoRepository.getOne(pedido.getId()));
		modelMap.addAttribute("pedido", pedidoRepository.getOne(pedido.getId()));
		 return "redirect:/pedido/editar/"+pedido.getId();

	}

	// Metodo para excluir o pedido
	@RequestMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, Pedido pedido) {
		pedidoService.excluir(id, pedido);
		return "redirect:/pedido/adicionarProduto";
	}

	@RequestMapping(path = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Optional<Pedido> p = pedidoRepository.findById(id);
		model.addAttribute("produtos", produtoRepository.findAll());
		model.addAttribute("pedido", p.get());
		return "pedido/editar";
	}

	@RequestMapping(path = "/salvarEditado", method = RequestMethod.POST)
	public String editando(Pedido pedido, Model model) {
		pedidoRepository.saveAndFlush(pedido);
		return "redirect:/pedido/listar";
	}

}
