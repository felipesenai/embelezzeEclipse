package br.senai.sc.ti20131n.pw.embelezzejsf.test;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ServicoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Servico;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class ServicoDaoTest extends DBUnitTestServico{
	
	public static EntityManager entityManager;
	private static ServicoDao dao;
	
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
		dao = new ServicoDao();
	}
	
	@After
	public void close(){
		entityManager.getTransaction().rollback();
		entityManager.close();
		entityManager = null;
		dao = null;		
	}
	
	@Test
	public void testSalvarServico(){
		Servico servico = new Servico();
		servico.setNomeServico("Manicure");
		servico.setDescricaoServico("Manicure");
		servico.setPrecoServico(12.10);
				
		assertTrue(dao.salvar(servico));
	}	
	
	@Test
	public void verificaSeOServicoFoiRealmenteDeletado() {
		Servico servicoDeletado = dao.buscarPorId(1L);
		entityManager.getTransaction().begin();
		dao.excluirServicoPorId(servicoDeletado.getID());
		entityManager.getTransaction().commit();
		Assert.assertEquals(null, dao.buscarPorId(1L));
	}

	@Test
	public void buscarPorId(){
		Servico servico = dao.buscarPorId(1L);
		Assert.assertNotNull(servico);
	}
	
	@Test
	public void atualizaServico() {
		Servico servicoEditado = dao.buscarPorId(1L);
		servicoEditado.setNomeServico("Teste alteracao servico");
		servicoEditado.setDescricaoServico("Teste alteracao descricao");
		servicoEditado.setPrecoServico(10.00);		
		entityManager.getTransaction().begin();
		dao.atualizar(servicoEditado);
		entityManager.getTransaction().commit();
		Servico servico = dao.buscarPorId(1L);
	
		Assert.assertEquals(servicoEditado.getNomeServico(), servico.getNomeServico());
		Assert.assertEquals(servicoEditado.getDescricaoServico(), servico.getDescricaoServico());
		Assert.assertEquals(servicoEditado.getPrecoServico(), servico.getPrecoServico());
	}
}
