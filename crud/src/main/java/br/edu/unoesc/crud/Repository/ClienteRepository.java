package br.edu.unoesc.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unoesc.crud.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
