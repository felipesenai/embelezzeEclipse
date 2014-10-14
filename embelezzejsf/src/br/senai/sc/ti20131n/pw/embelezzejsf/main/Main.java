package br.senai.sc.ti20131n.pw.embelezzejsf.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class Main {
	
	public static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("embelezze_pu");
	
	public static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	public static ClienteDao dao = new ClienteDao(entityManager);
	
	public static Cliente cliente = new Cliente();
	
	public static void main(String[] args) {
				
		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();
		//atualizarCliente();
		//listarClientes();
	//	deletarCliente();
//		listarClientes();
		entityManager.close();
		entityManagerFactory.close();
	}



}