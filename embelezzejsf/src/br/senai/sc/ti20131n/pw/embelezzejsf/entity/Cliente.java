package br.senai.sc.ti20131n.pw.embelezzejsf.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.senai.sc.ti20131n.pw.embelezzejsf.modelo.Evento;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue
	private Long ID;
	@Column(length=30, nullable=false)
	private String Nome;
	@Column(length=14, nullable=false,unique=true)
	private String CPF;
	private String rua;
	private Integer numero;
	private String bairro;
	private String CEP;
	private String cidade;
	private String UF;
	private String email;
	private String telefone;
	private String celular;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cliente")
	private List<Evento> listaEvento;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		this.ID = iD;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String CEP) {
		this.CEP = CEP;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String UF) {
		this.UF = UF;
	}
	public String getTelefone() {
		return telefone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
}