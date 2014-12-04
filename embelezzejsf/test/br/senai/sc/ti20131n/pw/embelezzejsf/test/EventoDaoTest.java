package br.senai.sc.ti20131n.pw.embelezzejsf.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.EventoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.modelo.Evento;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class EventoDaoTest extends DBUnitTestEvento {

	public EventoDaoTest() {
		super();
	}

	public EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("embelezze_pu");

	public EntityManager entityManager = entityManagerFactory
			.createEntityManager();

	public EventoDao dao = new EventoDao();

	public Evento evento = new Evento();

	@BeforeClass
	public static void init() {
		Util.iniciarPersistenceUnit();
	}

	@AfterClass
	public static void finish() {
		Util.fecharPersistenceUnit();
	}

	@Before
	public void begin() {
		entityManager = Util.getEntityManager();
		entityManager.getTransaction().begin();
		dao = new EventoDao();
	}

	@After
	public void close() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		entityManager = null;
		dao = null;
	}
	
	@Test
	public void testSalvarEvento(){
		Evento evento = new Evento();
		Date date = new Date();
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			date = formatdate.parse("12/01/2014");
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		evento.setDescricao("Teste");
		evento.setInicio(date);
		evento.setFim(date);
		evento.setTitulo("Teste 1");
		evento.setStatus(true);
	//	assertNotNull(dao.salvar(evento));
	}
	
//	
//	@Test
//	public void atualizaEvento() {
//		Evento eventoEditado = dao.salvar(evento);
//		eventoEditado.setDescricao("TEste 2");
//		
//		entityManager.getTransaction().begin();
//		dao.atualizar(eventoEditado);
//		entityManager.getTransaction().commit();
//
//		Evento evento = dao.salvar(eventoEditado);
//		Assert.assertEquals(eventoEditado.getDescricao(), evento.getDescricao());
//
//		Assert.assertEquals(produtoEditado.getMarcaProduto(), produto.getMarcaProduto());
//		Assert.assertEquals(produtoEditado.getPrecoProduto(), produto.getPrecoProduto());
//		
//	}
	

	
	

}
