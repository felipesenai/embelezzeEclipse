package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class ClienteDao {

	private EntityManager entityManager;

	public ClienteDao() {
		entityManager = Util.getEntityManager();
	}

	public boolean salvar(Cliente cliente) {
		try {
			entityManager.merge(cliente);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Cliente buscarPorId(Long ID) {
		return entityManager.find(Cliente.class, ID);
	}

	public Cliente excluirClientePorId(Long l) {
		Cliente cliente = entityManager.getReference(Cliente.class, l);
		entityManager.remove(cliente);
		return cliente;
	}

	public void atualizar(Cliente cliente) {
		entityManager.merge(cliente);
	}

	public List<Cliente> listarCliente() {
		Query query = entityManager.createQuery("From Cliente", Cliente.class);
		return query.getResultList();
	}

}
