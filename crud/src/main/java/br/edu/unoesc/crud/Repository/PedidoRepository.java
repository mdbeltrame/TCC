package br.edu.unoesc.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unoesc.crud.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
