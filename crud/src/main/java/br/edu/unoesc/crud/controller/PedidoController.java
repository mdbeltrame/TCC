package br.edu.unoesc.crud.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.edu.unoesc.crud.Repository.PedidoRepository;
import br.edu.unoesc.crud.Repository.ProdutoRepository;
import br.edu.unoesc.crud.Repository.UsuarioRepository;
import br.edu.unoesc.crud.model.Pedido;
import br.edu.unoesc.crud.model.Produto;
import br.edu.unoesc.crud.model.Usuario;
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
	@Autowired
	private UsuarioRepository usuarioRepository;
	@RequestMapping(path = { "/cadastro" })
	public String cadastro(Pedido pedido, Model model) {
		model.addAttribute("pedido", new Pedido());
		model.addAttribute("produtos", produtoRepository.findAll());
		return "pedido/adicionarProduto";
	}

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

	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public String salvar(Produto produto, Produto produtoNovo, Pedido pedido, Model model, Principal principal) {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		List<Usuario> usuarioLogado = new ArrayList<Usuario>();
		usuarioLogado.add(usuario);
		if (pedido.getId() != null) {
			if(pedidoRepository.getOne(pedido.getId()).getProduto().size() >= 1) {
				Pedido pedidoComLista = pedidoRepository.findById(pedido.getId()).get();
				pedidoComLista.addProduto(produto);
				pedidoComLista.calculaValorTotal();
				pedidoComLista.setData(LocalDate.now().toString());
				pedidoComLista.setUsuario(usuarioLogado);
				pedidoRepository.save(pedidoComLista);
			} else {
				pedido.setData(LocalDate.now().toString());
				pedido.calculaValorTotal();
				pedido.setUsuario(usuarioLogado);
				pedidoRepository.saveAndFlush(pedido);
			}
		} else {
			pedido.setData(LocalDate.now().toString());
			pedido.calculaValorTotal();
			pedido.setUsuario(usuarioLogado);
			pedidoRepository.saveAndFlush(pedido);
		}
		model.addAttribute("pedido", pedidoRepository.getOne(pedido.getId()));
		return "redirect:/pedido/editar/"+pedido.getId();
	

	}

	// Metodo para excluir o pedido
	@RequestMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, Pedido pedido) {
		pedidoService.excluir(id, pedido);
		return "redirect:/pedido/listar";
	}
	
	// Metodo para excluir o pedido
		@RequestMapping(path = "/excluirProduto/{id}/{idProduto}")
		public String excluirProduto(@PathVariable(value = "id") Long id, @PathVariable(value = "idProduto") Long idProduto) {
			Pedido pedido = pedidoRepository.findById(id).get();
			pedido.removeProduto(produtoRepository.findById(idProduto).get());
			pedidoRepository.saveAndFlush(pedido);
			return "redirect:/pedido/editar/"+pedido.getId();
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
		return "redirect:/pedido/editar/"+pedido.getId();
	}

}
