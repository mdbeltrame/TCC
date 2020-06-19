package br.edu.unoesc.crud.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
	private Long id;

	@OneToMany
	@JoinTable(name = "pedido_produto",  joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "pedido_id"))
	private List<Produto> produto;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Usuario> usuario;
	private Integer quantidade;
	private String data;
	private String observacao;
	private BigDecimal valorTotal;
	
	public void addProduto(Produto p) {
		if (this.produto == null) {
			produto = new ArrayList<Produto>();
		}
		produto.add(p);
	}
	
	public void removeProduto(Produto p) {
		if (this.produto != null && !this.produto.isEmpty()) {
			this.produto.remove(p);
		}
	}
	
	public Pedido() {
		super();

	}
	
	public void calculaValorTotal() {
		if (this.produto != null && !this.produto.isEmpty()) {
			for (Produto p : produto) {
				this.valorTotal = getValorTotal().add(p.getValor());
			}
		}
	}

	public Pedido(Long id, List<Produto> produto, List<Usuario> usuario, Integer quantidade, String data,
			String observacao, BigDecimal valorTotal) {
		super();
		this.id = id;
		this.produto = produto;
		this.usuario = usuario;
		this.quantidade = quantidade;
		this.data = data;
		this.observacao = observacao;
		this.valorTotal = valorTotal;
	}

	public Pedido(List<Produto> produto, List<Usuario> usuario, Integer quantidade, String data,
			String observacao, BigDecimal valorTotal) {
		super();
		this.produto = produto;
		this.usuario = usuario;
		this.quantidade = quantidade;
		this.data = data;
		this.observacao = observacao;
		this.valorTotal = valorTotal;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal != null ? valorTotal : BigDecimal.ZERO;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
