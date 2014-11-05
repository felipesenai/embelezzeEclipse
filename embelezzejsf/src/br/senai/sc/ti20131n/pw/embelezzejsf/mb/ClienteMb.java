package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import apple.laf.JRSUIConstants.ShowArrows;
import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

@ManagedBean
public class ClienteMb {

	private Cliente cliente;
	private ClienteDao clienteDao;
	private EntityManager entityManager;
	private List<Cliente> listaClientes;

	@PostConstruct
	public void init() {
		// setCliente(new Cliente());
		// clienteDao = new ClienteDao(entityManager);
		// entityManager = Util.getEntityManager();
		cliente = new Cliente();
		clienteDao = new ClienteDao();
	}

	public List<Cliente> getListaClientes() {
		if (listaClientes == null) {
			Query query = entityManager.createQuery("SELECT c FROM Cliente c",
					Cliente.class);
			listaClientes = query.getResultList();
		}
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String salvar() {
			if (validaCamposVazios()) {
				System.out.println(cliente.getID());
				clienteDao.salvar(getCliente());
				addMessage("Cliente salvo com sucesso!");
				cliente = new Cliente();
			}
			return "";
	}

	public String editar(Long ID) {
		cliente = entityManager.find(Cliente.class, ID);
		return "formcadclientes";
	}

	public String excluir(Long ID) {
		Cliente cliente = entityManager.getReference(Cliente.class, ID);
		entityManager.remove(cliente);
		listaClientes = null;
		return "listagemClientes";
	}

	public void buttonAction(ActionEvent actionEvent) {
		addMessage("Cliente salvo com sucesso!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public boolean validaCamposVazios() {
		if (cliente.getNome().isEmpty()) {
			addMessage("Nome está vazio");
			return false;
		} else if (cliente.getCPF().isEmpty()) {
			addMessage("CPF está vazio");
			return false;
		}
		return true;
	}

}
