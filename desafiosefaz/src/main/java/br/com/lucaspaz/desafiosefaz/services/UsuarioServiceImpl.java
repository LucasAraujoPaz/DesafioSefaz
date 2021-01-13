package br.com.lucaspaz.desafiosefaz.services;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.lucaspaz.desafiosefaz.entity.Usuario;
import br.com.lucaspaz.desafiosefaz.exceptions.EmailAlreadyUsedException;
import br.com.lucaspaz.desafiosefaz.exceptions.UsuarioNotFoundException;
import br.com.lucaspaz.desafiosefaz.repository.UsuarioRepository;
import br.com.lucaspaz.desafiosefaz.repository.UsuarioRepositoryImpl;

public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;
	
	public UsuarioServiceImpl() {
		this.repository = new UsuarioRepositoryImpl();
	}

	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Usuario getUsuarioById(int id) {
		Usuario usuario = repository.getUsuarioById(id);
		
		if (usuario == null) {
			throw new UsuarioNotFoundException();
		} 
		
		return usuario;
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {

		try {
			return repository.getUsuarioByEmail(email);			  
		} catch (NoResultException e) {
			throw new UsuarioNotFoundException();
		}
	
	}

	@Override
	public List<Usuario> getTodosOsUsuarios() {
		return repository.getTodosOsUsuarios();
	}

	public boolean incluirUsuario(Usuario usuario){
		
		try {
			repository.getUsuarioByEmail(usuario.getEmail());
			throw new EmailAlreadyUsedException();
		} catch (NoResultException e) {
			repository.incluirUsuario(usuario);			
		}
	
		return true;
	 }

	@Override
	public boolean alterarUsuario(Usuario usuario) {

		try {
			repository.getUsuarioById(usuario.getId());
		} catch (NoResultException e) {
			throw new UsuarioNotFoundException();			
		}
		
		repository.alterarUsuario(usuario);
		
		return true;
	}

	@Override
	public boolean removerUsuario(Usuario usuario) {

		try {
			repository.getUsuarioById(usuario.getId());
		} catch (NoResultException e) {
			throw new UsuarioNotFoundException();			
		}

		repository.removerUsuario(usuario);

		return true;
	}

}
