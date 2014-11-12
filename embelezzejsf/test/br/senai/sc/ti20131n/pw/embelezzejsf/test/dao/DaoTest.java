package br.senai.sc.ti20131n.pw.embelezzejsf.test.dao;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ProdutoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.test.DBUnitTest;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class DaoTest extends DBUnitTest{

	private static EntityManager entityManager;
	
	@BeforeClass
	public static void init(){
		Util.iniciarPersistenceUnit();
		entityManager = Util.createEntityManager();
	}
	
	@Test
	public void testEntityManager(){
		assertNotNull("Gerenciamento de entidade está nulo",entityManager);
	}
	
	@Test
	public void testeClienteDao(){
		ClienteDao dao = new ClienteDao();
		assertNotNull("O objeto de acesso a dados do cliente está nulo", dao);
		dao = null;
	}
	
	@Test
	public void testeProduto(){
		ProdutoDao dao = new ProdutoDao();
		assertNotNull("O obejto de aceso a dados do produto está nulo", dao);
	}
	
	@AfterClass
	public void close(){
		entityManager.close();
		Util.fecharPersistenceUnit();
	}
}
