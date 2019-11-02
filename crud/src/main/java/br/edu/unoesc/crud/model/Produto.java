package br.edu.unoesc.crud.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	private String grupo;

	private String unidade;

	private long quantidade;
	
	private String valor;
	

	public Produto() {

	}

	
	
	public Produto(long id, String nome, String grupo, String unidade, long quantidade, String valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.grupo = grupo;
		this.unidade = unidade;
		this.quantidade = quantidade;
		this.valor = valor;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public String getUnidade() {
		return unidade;
	}


	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}


	public long getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
