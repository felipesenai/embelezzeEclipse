package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

@ManagedBean
public class ClienteMb {
	
	private Cliente cliente;
	private EntityManager entityManger;


	private List<Cliente> listaClientes;
	private Long ID;
	private String nome;
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

	
	public void init(){
		setCliente(new Cliente());
		entityManger = Util.getEntityManager();
	}
	
	public List<Cliente> getListaClientes() {
		if (listaClientes == null) {
			Query query = Util.getEntityManager().createQuery(
					"SELECT c FROM Cliente c", Cliente.class);
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
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
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

	public String salvar() {
		System.out.println("Salvou!");
		Cliente cliente = new Cliente();

		cliente.setNome(nome);
		cliente.setCPF(CPF);
		cliente.setRua(rua);
		cliente.setNumero(numero);
		cliente.setBairro(bairro);
		cliente.setCEP(CEP);
		cliente.setCidade(cidade);
		cliente.setUF(UF);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setCelular(celular);

		Util.getEntityManager().persist(cliente);
		return "";

	}
	
	public String excluir(Long ID){
	    Cliente cliente = entityManger.find(Cliente.class, ID);
		entityManger.remove(cliente);
		listaClientes = null;
		return "listagemClientes";
	}
}
