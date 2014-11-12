package br.senai.sc.ti20131n.pw.embelezzejsf.util;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames={"Faces Servlet"})
public class Filter implements javax.servlet.Filter{


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		EntityManager entityManager = Util.createEntityManager();
		
		Util.setEntityManager(request, entityManager);
		
		try {
			entityManager.getTransaction().begin();
			filterChain.doFilter(request, response);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		Util.iniciarPersistenceUnit();		
	}
	
	@Override
	public void destroy() {
		Util.fecharPersistenceUnit();
	}
}
