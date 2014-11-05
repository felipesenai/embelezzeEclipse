package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produtos;

public class ProdutoDao {
	
	private static EntityManager entityManager;

	
	
	public ProdutoDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public boolean salvar(Produtos produto){
		try {
			entityManager.persist(produto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Produtos buscarPorId(Long ID){
		return entityManager.find(Produtos.class, ID);
	}
	
	public void excluirProdutoPorId(Long l){
		Produtos produto = entityManager.getReference(Produtos.class, l);
		entityManager.remove(produto);
	}
	
	public void atualizar(Produtos produto){
		entityManager.merge(produto);
	}
	
	public List<Produtos> listarProdutoPorNome(String nomeProduto){
		Query query = entityManager.createQuery("From Produto",Produtos.class);
		return query.getResultList();
	}
}
