package br.edu.unoesc.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.crud.Repository.ProdutoRepository;
import br.edu.unoesc.crud.model.Produto;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	public void salvar(Produto produto) {
		produtoRepository.saveAndFlush(produto);
	}

	public void excluir(Long id, Produto produto) {
		produtoRepository.deleteById(id);
	}
}
