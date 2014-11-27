package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;

@ManagedBean
public class ClienteMb {

	private Cliente cliente;
	private ClienteDao clienteDao;
	private List<Cliente> listaClientes;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		clienteDao = new ClienteDao();
	}

	public List<Cliente> getListaClientes() {
		if (listaClientes == null) {
			listaClientes = clienteDao.listarCliente();
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
			clienteDao.salvar(cliente);
			if (cliente.getID() == null || cliente.getID() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Cliente salvo com sucesso!", null));
				cliente = new Cliente();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Cliente alterado com sucesso!", null));

				return "listagemClientes";
			}
		}
		return "";
	}

	public String editar(Long ID) {
		cliente = clienteDao.buscarPorId(ID);
		return "formcadclientes";
	}

	public String excluir(Long ID) {
		cliente = clienteDao.buscarPorId(ID);
		cliente = clienteDao.excluirClientePorId(ID);
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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Preencha os campos obrigatórios - NOME"));
			return false;
		} else if (cliente.getCPF().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Preencha os campos obrigatórios - CPF"));
			return false;
		}
		return true;
	}

}
