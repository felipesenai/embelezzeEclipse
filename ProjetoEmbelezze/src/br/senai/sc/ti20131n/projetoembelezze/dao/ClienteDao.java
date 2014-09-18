package br.senai.sc.ti20131n.projetoembelezze.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.projetoembelezze.entity.Cliente;

public class ClienteDao {
	
	private static EntityManager entityManager;

	
	public ClienteDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public void salvar(Cliente cliente){
		entityManager.persist(cliente);
	}
	
	public Cliente buscarPorId(Long ID){
		return entityManager.find(Cliente.class, ID);
	}
	
	public void excluirClientePorId(Long l){
		Cliente cliente = entityManager.getReference(Cliente.class, l);
		entityManager.remove(cliente);
	}
	
	public void atualizar(Cliente cliente){
		entityManager.merge(cliente);
	}
	
	public List<Cliente> listarClientePorNome(String nome){
		Query query = entityManager.createQuery("From Cliente",Cliente.class);
		
		return query.getResultList();
	}

}
