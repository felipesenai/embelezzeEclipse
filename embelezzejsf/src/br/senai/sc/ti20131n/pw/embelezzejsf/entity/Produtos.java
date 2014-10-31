package br.senai.sc.ti20131n.pw.embelezzejsf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produtos {
	
	@Id
	@GeneratedValue
	private Long ID = null;
	private String marcaProduto;
	private Double precoProduto;
	private String nomeProduto;
	private String imagem;
	
	
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long ID) {
		this.ID = ID;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getMarcaProduto() {
		return marcaProduto;
	}
	public void setMarcaProduto(String marcaProduto) {
		this.marcaProduto = marcaProduto;
	}
	public Double getPrecoProduto() {
		return precoProduto;
	}
	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}
	
	
}
