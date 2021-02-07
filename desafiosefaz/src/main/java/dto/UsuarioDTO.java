package dto;

import java.util.List;

import br.com.lucaspaz.desafiosefaz.entity.Telefone;
import br.com.lucaspaz.desafiosefaz.entity.Usuario;

public class UsuarioDTO {
	
	public UsuarioDTO() {} 
	
	public UsuarioDTO(Integer id, String nome, String email, List<Telefone> telefones) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefones = telefones;
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId(); 
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.telefones = usuario.getTelefones();
	}
	
	private Integer id;
	
	private String nome;
	
	private String email; 
	
	private List<Telefone> telefones;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}

