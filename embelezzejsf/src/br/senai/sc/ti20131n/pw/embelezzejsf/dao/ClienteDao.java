package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;

public class ClienteDao {
	
	private static EntityManager entityManager;

	
	public ClienteDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public boolean salvar(Cliente cliente){
		try {
			entityManager.persist(cliente);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Cliente buscarPorId(Long ID){
		try {
			return entityManager.find(Cliente.class, ID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
