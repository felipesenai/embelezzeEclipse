package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produto;

public class ProdutoDao {
	
	private static EntityManager entityManager;

	
	
	public ProdutoDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public boolean salvar(Produto produto){
		try {
			entityManager.persist(produto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Produto buscarPorId(Long ID){
		return entityManager.find(Produto.class, ID);
	}
	
	public void excluirProdutoPorId(Integer l){
		Produto produto = entityManager.getReference(Produto.class, l);
		entityManager.remove(produto);
	}
	
	public void atualizar(Produto produto){
		entityManager.merge(produto);
	}
	
	public List<Produto> listarProdutoPorNome(String nomeProduto){
		Query query = entityManager.createQuery("From Produto",Produto.class);
		return query.getResultList();
	}
}
