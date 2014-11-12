package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produtos;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class ProdutoDao {

	private EntityManager entityManager;

	public ProdutoDao() {
		entityManager = Util.getEntityManager();
	}

	public boolean salvar(Produtos produto) {
		try {
			entityManager.merge(produto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Produtos buscarPorId(Long ID) {
		return entityManager.find(Produtos.class, ID);
	}

	public Produtos excluirProdutoPorId(Long l) {
		Produtos produto = entityManager.getReference(Produtos.class, l);
		entityManager.remove(produto);
		return produto;
	}

	public void atualizar(Produtos produto) {
		entityManager.merge(produto);
	}

	public List<Produtos> listarProduto() {
		Query query = entityManager.createQuery("From Produtos", Produtos.class);
		return query.getResultList();
	}
}
