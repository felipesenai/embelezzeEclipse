package br.senai.sc.ti20131n.pw.embelezzejsf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Servico {

	@Id
	@GeneratedValue
	private Long ID;
	private String nomeServico;
	private Double precoServico;
	private String descricaoServico;
	private String imagemServico;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public Double getPrecoServico() {
		return precoServico;
	}
	public void setPrecoServico(Double precoServico) {
		this.precoServico = precoServico;
	}
	public String getDescricaoServico() {
		return descricaoServico;
	}
	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}
	public String getImagemServico() {
		return imagemServico;
	}
	public void setImagemServico(String imagemServico) {
		this.imagemServico = imagemServico;
	}
	
	
}