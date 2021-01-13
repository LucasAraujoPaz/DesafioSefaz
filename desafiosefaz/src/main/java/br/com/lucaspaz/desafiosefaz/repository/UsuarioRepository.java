package br.com.lucaspaz.desafiosefaz.repository;

import java.util.List;

import br.com.lucaspaz.desafiosefaz.entity.Usuario;

public interface UsuarioRepository {

	public Usuario getUsuarioById(int id);
	
	public Usuario getUsuarioByEmail(String email);
	
	public List<Usuario> getTodosOsUsuarios();
	
	public boolean incluirUsuario(Usuario usuario);
	
	public boolean alterarUsuario(Usuario usuario);
	
	public boolean removerUsuario(Usuario usuario);
	
}
