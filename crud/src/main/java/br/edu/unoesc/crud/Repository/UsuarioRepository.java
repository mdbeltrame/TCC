package br.edu.unoesc.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unoesc.crud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLogin(String login);
	
}	
