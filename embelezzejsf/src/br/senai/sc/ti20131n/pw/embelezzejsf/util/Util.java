package br.senai.sc.ti20131n.pw.embelezzejsf.util;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Util {
	
	private static final String ENTITY_MANAGER = "ENTITY_MANAGER";
	private static EntityManagerFactory entityManagerFactory;

	public static void iniciarPersistenceUnit() {
		if (entityManagerFactory == null)
			entityManagerFactory = Persistence
					.createEntityManagerFactory("embelezze_pu");
	}
	
	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static void fecharPersistenceUnit() {
		entityManagerFactory.close();		
	}
	
	public static EntityManager getEntityManager(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest resquest = (HttpServletRequest) context.getExternalContext().getRequest();
		EntityManager entityManager = (EntityManager) resquest.getAttribute(ENTITY_MANAGER);
		return entityManager;
	}

	public static void setEntityManager(ServletRequest request,
			EntityManager entityManager) {
		request.setAttribute(ENTITY_MANAGER, entityManager);
		
	}
}

