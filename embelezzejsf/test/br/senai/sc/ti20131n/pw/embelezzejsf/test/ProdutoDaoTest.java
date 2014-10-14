package br.senai.sc.ti20131n.pw.embelezzejsf.test;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ProdutoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produto;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class ProdutoDaoTest {

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
		dao = new ProdutoDao(entityManager);
	}
	
	@After
	public void close(){
		entityManager.getTransaction().rollback();
		entityManager.close();
		entityManager = null;
		dao = null;		
	}
	
	@Test
	public void testSalvar(){
		Produto produto = new Produto();
		produto.setNomeProduto("Prada");
		produto.setMarcaProduto("Luna Rossa");;
	    produto.setPrecoProduto(289.00);
		
		assertTrue(dao.salvar(produto));
	}	

//	@Test
//	public void excluirPacote1(){
//		testSalvar();
//		dao.excluirPacotePorId(1L);
//		assertNull(dao.buscarPorId(1L));
//	}
//	
//	@Test
//	public void buscarPacotePorId(){		
//		Pacote pacote= dao.buscarPorId(1L);
//		assertNotNull(pacote);
//	}
//	
//	@Test
//	public void ListarTodos(){
//		int total = 10;
//		
//		for(int i = 0; i < total; i++);
//			testSalvar();	
//		
//		List<Pacote> bandas = dao.listarTodos();
//		assertEquals(10, bandas.size());
//	}
//	
//	@Test
//	public void editarPacote(){
//		testSalvar();		
//		Pacote pacote = new Pacote();
//		pacote.setNome("Pacote  de Luxo");
//		pacote.setEntrada(12);
//		pacote.setSaida(20);
//		pacote.setData(SimpleDateFormat.);
//		pacote.setGenero("Luxo");
//		pacote.setValor(150);	
//		dao.atualizar(pacote);
//		
//		
//	}
	
}
