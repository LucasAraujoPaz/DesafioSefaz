package br.com.lucaspaz.desafiosefaz.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.lucaspaz.desafiosefaz.entity.Usuario;
import br.com.lucaspaz.desafiosefaz.util.EntityManagerUtil;

public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	private EntityManager em = EntityManagerUtil.getEntityManager();
	
	@Override
	public Usuario getUsuarioById(int id) {
		return em.find(Usuario.class, id);
	}
	
	@Override
	public Usuario getUsuarioByEmail(String email) {
		return em.createQuery("SELECT u from Usuario u WHERE u.email = :email", Usuario.class)
					.setParameter("email", email)
					.getSingleResult();
	}
	
	@Override
	 public List<Usuario> getTodosOsUsuarios() {
		return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	 }
	
	 @Override
	 public boolean incluirUsuario(Usuario usuario){		
		 
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();	
		
		return true;		
	 }
	
	@Override
	 public boolean alterarUsuario(Usuario usuario) {		

		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		
		return true;
	 }
	 
	 @Override
	 public boolean removerUsuario(Usuario usuario) {
		 
//		em.getTransaction().begin();
//		em.remove(em.merge(usuario));
//		//em.createQuery("DELETE FROM Usuario u WHERE u.id = :id")
//		//	.setParameter("id", id);
//		em.getTransaction().commit();	
//		
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
	
		return true;
	}

}
