package br.edu.unoesc.crud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2441171712296281534L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
	private Long id;

	@OneToMany(cascade = {CascadeType.ALL, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Produto> produto;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Usuario> usuario;
	private long quantidade;
	private String data;
	private String observacao;
	
	public void addProduto(Produto p) {
		if (produto == null) {
			produto = new ArrayList<Produto>();
		}
		produto.add(p);
	}
	
	public Pedido() {
	}

	public Pedido(Long id, List<Produto> produto, List<Usuario> usuario, long quantidade, String data,
			String observacao) {
		super();
		this.id = id;
		this.produto = produto;
		this.usuario = usuario;
		this.quantidade = quantidade;
		this.data = data;
		this.observacao = observacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
