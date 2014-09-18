package br.senai.sc.ti20131n.projetoembelezze;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.senai.sc.ti20131n.projetoembelezze.dao.ClienteDao;
import br.senai.sc.ti20131n.projetoembelezze.entity.Cliente;
import br.senai.sc.ti20131n.projetoembelezze.util.JpaUtil;

public class Main {
	
	public static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("embelezze_pu");
	
	public static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	public static ClienteDao dao = new ClienteDao(entityManager);
	
	public static Cliente cliente = new Cliente();
	
	public static void main(String[] args) {
		
		
		
		inserirCliente(cliente);
		
		entityManager.getTransaction().begin();
		//dao.salvar(cliente);
		//listarClientes();
		entityManager.getTransaction().commit();
		atualizarCliente();
		listarClientes();
		deletarCliente();
		listarClientes();
		entityManager.close();
		entityManagerFactory.close();
		 

	}




	private static void deletarCliente() {
		Cliente clienteDeletado = dao.buscarPorId(3L);
		entityManager.getTransaction().begin();
		dao.excluirClientePorId(clienteDeletado.getID());
		entityManager.getTransaction().commit();
	}




	private static void atualizarCliente() {
		Cliente clienteEditado = dao.buscarPorId(1L);
		clienteEditado.setNome("Aldair");
		clienteEditado.setTelefone("(48) 3223-0034");
		
		entityManager.getTransaction().begin();
		dao.atualizar(clienteEditado);
		entityManager.getTransaction().commit();

	}




	private static void inserirCliente(Cliente cliente) {
		cliente.setNome("Nelson");
		cliente.setCPF("000.000.010-00");
	    cliente.setRua("Rua da Araucára");
		cliente.setNumero(26);
		cliente.setBairro("Tapera");
		cliente.setCEP("88.888-888");
		cliente.setCidade("Florianopolis");
		cliente.setUF("SC");
		cliente.setTelefone("(48) 3232-3232");
		cliente.setCelular("(99) 9999-9999");
	}

	
	
	
	private static void listarClientes(){
		Query query = entityManager.createQuery("From Cliente", Cliente.class);
		List<Cliente> clientes = query.getResultList();
		
		for(Cliente cliente : clientes){
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Telefone: " + cliente.getTelefone());
			
		}
	}


}
