package br.edu.unoesc.crud.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.crud.Repository.PedidoRepository;
import br.edu.unoesc.crud.model.Pedido;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public void salvar(Pedido pedido) {
		pedido.setData(LocalDate.now().toString());
		pedidoRepository.saveAndFlush(pedido);
	}

	public void excluir(Long id, Pedido pedido) {
		pedido.setProduto(null);
		pedidoRepository.deleteById(id);
	}
}
