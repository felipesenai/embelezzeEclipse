package br.senai.sc.ti20131n.projetoembelezze;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.senai.sc.ti20131n.projetoembelezze.dao.ClienteDao;
import br.senai.sc.ti20131n.projetoembelezze.entity.Cliente;
import br.senai.sc.ti20131n.projetoembelezze.util.JpaUtil;

public class Main {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("embelezze_pu");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		ClienteDao dao = new ClienteDao(entityManager);
		
		Cliente cliente = new Cliente();
		
		cliente.setNome("Nelson");
		cliente.setCPF("000.000.000-00");
	    cliente.setRua("Rua da Araucára");
		cliente.setNumero(26);
		cliente.setBairro("Tapera");
		cliente.setCEP("88.888-888");
		cliente.setCidade("Florianopolis");
		cliente.setUF("SC");
		cliente.setTelefone("(48) 3232-3232");
		cliente.setCelular("(99) 999-9999");
		
		
		entityManager.getTransaction().begin();
		dao.salvar(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		 

	}

}
