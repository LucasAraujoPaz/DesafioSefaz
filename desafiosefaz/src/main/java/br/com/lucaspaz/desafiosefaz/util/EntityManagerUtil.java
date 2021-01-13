package br.com.lucaspaz.desafiosefaz.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private EntityManagerUtil() {}
	
	private static EntityManager em;

	public static EntityManager getEntityManager() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory("br.com.lucaspaz.desafiosefaz")
						.createEntityManager();
		}
		return em;
	}
}
