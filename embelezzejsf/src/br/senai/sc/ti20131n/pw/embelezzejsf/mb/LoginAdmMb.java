package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.embelezzejsf.entity.LoginAdm;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

@SessionScoped
@ManagedBean
public class LoginAdmMb {

	private LoginAdm admLogado;
	private LoginAdm admForm;

	@PostConstruct
	private void init() {
		admForm = new LoginAdm();
	}

	private EntityManager getEntityManager() {
		return Util.getEntityManager();
	}

	public String autenticar() {
		Query query = getEntityManager().createQuery(
				" SELECT a FROM LoginAdm a WHERE a.login = ?", LoginAdm.class);
		query.setParameter(1, getAdmForm().getLogin());

		try {
			LoginAdm loginBanco = (LoginAdm) query.getSingleResult();
			if (loginBanco.getLogin().equalsIgnoreCase(admForm.getLogin())
					&& loginBanco.getSenha().equalsIgnoreCase(
							admForm.getSenha())) {
				admLogado = loginBanco;
				return "/index?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Usuário ou senha inválidos!"));
				return "";
			}
			
		} catch (Exception e) {
			System.out.println("Usuário ou senha inválidos!");
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Usuário ou senha inválidos!"));
		return "";
	}

	public String sair() {
		admLogado = null;
		return "/index?faces-redirect=true";
	}

	public boolean estaLogado() {
		return (admLogado != null);
	}

	public LoginAdm getAdmLogado() {
		return admLogado;
	}

	public void setAdmLogado(LoginAdm admLogado) {
		this.admLogado = admLogado;
	}

	public LoginAdm getAdmForm() {
		return admForm;
	}

	public void setAdmForm(LoginAdm admForm) {
		this.admForm = admForm;
	}

}
