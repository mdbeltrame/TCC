package br.edu.unoesc.crud.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.unoesc.crud.Repository.UsuarioRepository;
import br.edu.unoesc.crud.model.Roles;
import br.edu.unoesc.crud.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public void salvar(Usuario usuario) {
		List<Roles> roles = new ArrayList<Roles>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		if (usuarios.isEmpty()) {
			roles.add(Roles.ADMIN);
			usuario.setRoles(roles);
			usuario.criptografarSenha();
			usuarioRepository.saveAndFlush(usuario);
		} else {
			roles.add(Roles.USER);
			usuario.setRoles(roles);
			usuario.criptografarSenha();
			usuarioRepository.saveAndFlush(usuario);
		}
	}

	public void excluir(Long id, Usuario usuario) {
		List<Roles> roles = new ArrayList<Roles>(usuario.getRoles());
		roles.clear();
		usuario.setRoles(roles);
		usuarioRepository.deleteById(id);
	}

}
