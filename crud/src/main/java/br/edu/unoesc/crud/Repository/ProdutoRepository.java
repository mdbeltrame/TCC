package br.edu.unoesc.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unoesc.crud.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
