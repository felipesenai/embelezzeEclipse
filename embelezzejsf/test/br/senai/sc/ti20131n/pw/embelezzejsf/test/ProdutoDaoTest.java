package br.senai.sc.ti20131n.pw.embelezzejsf.test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ProdutoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produtos;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;


public class ProdutoDaoTest extends DBUnitTestProduto{

	public static EntityManager entityManager;
	private static ProdutoDao dao;
	
	@BeforeClass
	public static void init(){
		Util.iniciarPersistenceUnit();	
	}
	
	@AfterClass
	public static void finish(){
		Util.fecharPersistenceUnit();
	}
	
	@Before
	public void begin(){
		entityManager = Util.getEntityManager();
		entityManager.getTransaction().begin();
		dao = new ProdutoDao();
	}
	
	@After
	public void close(){
		entityManager.getTransaction().rollback();
		entityManager.close();
		entityManager = null;
		dao = null;		
	}
	
	@Test
	public void testSalvarProduto(){
		Produtos produto = new Produtos();
		produto.setNomeProduto("Prada");
		produto.setMarcaProduto("Luna Rossa");;
	    produto.setPrecoProduto(289.00);
		
		assertTrue(dao.salvar(produto));
	}	

	@Test
	public void verificaSeOProdutoFoiRealmenteDeletado() {
		Produtos produtoDeletado = dao.buscarPorId(1L);
		entityManager.getTransaction().begin();
		dao.excluirProdutoPorId(produtoDeletado.getID());
		entityManager.getTransaction().commit();
		Assert.assertEquals(null, dao.buscarPorId(1L));
	}
	
	@Test
	public void buscarPorId(){
		Produtos produto = dao.buscarPorId(1L);
		Assert.assertNotNull(produto);
	}
	
	@Test
	public void atualizaCliente() {
		Produtos produtoEditado = dao.buscarPorId(1L);
		produtoEditado.setNomeProduto("Teste Alteração");
		produtoEditado.setMarcaProduto("Teste alteração marca");
		produtoEditado.setPrecoProduto(0.00);
		
		entityManager.getTransaction().begin();
		dao.atualizar(produtoEditado);
		entityManager.getTransaction().commit();

		Produtos produto = dao.buscarPorId(1L);
		Assert.assertEquals(produtoEditado.getNomeProduto(), produto.getNomeProduto());
		Assert.assertEquals(produtoEditado.getMarcaProduto(), produto.getMarcaProduto());
		Assert.assertEquals(produtoEditado.getPrecoProduto(), produto.getPrecoProduto());
		
	}
	
}
