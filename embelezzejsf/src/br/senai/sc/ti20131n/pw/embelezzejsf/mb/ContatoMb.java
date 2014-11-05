package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import javax.faces.bean.ManagedBean;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.senai.sc.ti20131n.pw.embelezzejsf.util.EmailUtil;

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
		String mensagemText = "Nome: " + nome +
							  "\nEmail: " + email +
							  "\nMensagem: " + mensagem;
		
		try {
			EmailUtil.enviarEmail(email, "Contato via site.", mensagemText);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
		return "";
	}
	
}
