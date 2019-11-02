package br.edu.unoesc.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.crud.Repository.ClienteRepository;
import br.edu.unoesc.crud.model.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void salvar(Cliente cliente) {
		clienteRepository.saveAndFlush(cliente);
	}
	
	public void excluir(Long id, Cliente cliente) {
		clienteRepository.deleteById(id);
	}
}
