package br.senai.sc.ti20131n.pw.embelezzejsf.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ClienteDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class ClienteDaoTest extends DBUnitTestCliente{
	
	public ClienteDaoTest(){
		super();
	}
			
			public EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("embelezze_pu");
			
			public EntityManager entityManager = entityManagerFactory.createEntityManager();
			
			public ClienteDao dao = new ClienteDao();
			
			public Cliente cliente = new Cliente();
			
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
				dao = new ClienteDao();
			}
			
			@After
			public void close(){
				entityManager.getTransaction().rollback();
				entityManager.close();
				entityManager = null;
				dao = null;		
			}
			
			
			@Test
			public void testSalvarCliente(){
				Cliente cliente = new Cliente();
				cliente.setNome("Felipe");
				cliente.setCPF("000.000.020-00");
			    cliente.setRua("Rua da Araucára 2");
				cliente.setNumero(27);
				cliente.setBairro("Red River");
				cliente.setCEP("99.999-999");
				cliente.setCidade("Florianopolis");
				cliente.setUF("SC");
				cliente.setTelefone("(48) 3232-2424");
				cliente.setCelular("(99) 9693-4546");
				
				assertTrue(dao.salvar(cliente));
			}	
			
			@Test
			public void verificaSeOClienteDeletadoFoiRealmenteDeletado() {
				Cliente clienteDeletado = dao.buscarPorId(1L);
				entityManager.getTransaction().begin();
				dao.excluirClientePorId(clienteDeletado.getID());
				entityManager.getTransaction().commit();
				Assert.assertEquals(null, dao.buscarPorId(1L));
			}
			

			@Test
			public void buscarPacotePorId(){		
				Cliente cliente = dao.buscarPorId(1L);
				Assert.assertNotNull(cliente);
			}
			
			@Test
			public void atualizaCliente() {
				Cliente clienteEditado = dao.buscarPorId(1L);
				clienteEditado.setNome("Aldair");
				clienteEditado.setTelefone("(48) 3223-0034");
				
				entityManager.getTransaction().begin();
				dao.atualizar(clienteEditado);
				entityManager.getTransaction().commit();

				Cliente cliente = dao.buscarPorId(1L);
				Assert.assertEquals(clienteEditado.getNome(), cliente.getNome());
				Assert.assertEquals(clienteEditado.getTelefone(), cliente.getTelefone());
				
			}
			

}
