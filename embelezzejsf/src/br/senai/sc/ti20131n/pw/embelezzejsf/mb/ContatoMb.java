package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ContatoMb {

	private String nome; 
	private String email; 
	private String mensagem;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String enviar(){

		
		return "";
	}
	
}
