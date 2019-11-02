package br.edu.unoesc.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.unoesc.crud.Repository.ProdutoRepository;
import br.edu.unoesc.crud.model.Produto;
import br.edu.unoesc.crud.service.ProdutoService;

@RequestMapping("/produto")
@Controller
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	// Carregar a pagina de cadastro de produtos
	@RequestMapping(path = { "/cadastro", "/", "" })
	public String cadastro() {
		return "produto/cadastro";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("produto", produtoRepository.findAll());
		return "produto/listar";
	}

	// Metodo para salvar o produto, chama esse no botao "salvar" e redireciona pra
	// tela de listagem
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public String salvar(Produto produto, Model model) {
		produtoService.salvar(produto);
		model.addAttribute("produto", produtoRepository.findAll());
		return "produto/listar";
	}

	// Metodo para excluir o produto
	@RequestMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, Produto produto) {
		produtoService.excluir(id, produto);
		return "redirect:/produto/listar";
	}

	@RequestMapping(path = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Optional<Produto> p = produtoRepository.findById(id);
		model.addAttribute("produto", p.get());
		return "produto/editar";
	}

	@RequestMapping(path = "/salvarEditado", method = RequestMethod.POST)
	public String editando(Produto produto, Model model) {
		produtoRepository.saveAndFlush(produto);
		return "redirect:/produto/listar";
	}

}
