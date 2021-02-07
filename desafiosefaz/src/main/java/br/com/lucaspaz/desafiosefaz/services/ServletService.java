package br.com.lucaspaz.desafiosefaz.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lucaspaz.desafiosefaz.entity.Telefone;
import br.com.lucaspaz.desafiosefaz.entity.Usuario;

public class ServletService {

	private UsuarioService service = new UsuarioServiceImpl();

	public void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("input_id");
		String nome = request.getParameter("input_nome");
		String senha = request.getParameter("input_senha");
		
		Usuario usuarioAntigo = service.getUsuarioById(Integer.valueOf(id));

		usuarioAntigo.setNome(nome);
		usuarioAntigo.setSenha(senha);

		List<Telefone> telefones = requestTelefones(request, usuarioAntigo);

		usuarioAntigo.setTelefones(telefones);

		service.alterarUsuario(usuarioAntigo);		
		renderizarTodosOsUsuarios(request, response);
	}

	public void adicionarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nome = request.getParameter("input_nome");
		String senha = request.getParameter("input_senha");
		String email = request.getParameter("input_email");

		Usuario usuarioNovo = new Usuario();
		usuarioNovo.setNome(nome);
		usuarioNovo.setEmail(email);
		usuarioNovo.setSenha(senha);

		List<Telefone> telefones = requestTelefones(request, usuarioNovo);

		usuarioNovo.setTelefones(telefones);
		
		service.incluirUsuario(usuarioNovo);
		
		String tipoDeAdicao = String.valueOf(request.getAttribute("tipo_de_adicao"));
		if ( tipoDeAdicao.equals("cadastrar") ){
			renderizarPaginaIndex(request, response);
		} else {
			renderizarTodosOsUsuarios(request, response);
		}
		
	}

	public List<Telefone> requestTelefones(HttpServletRequest request, Usuario usuario) {
		
		int numeroDeTelefones = Integer
				.valueOf(request.getParameter("input_numero_de_telefones"));
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		if ( usuario.getTelefones() != null) {
			telefones = usuario.getTelefones();
			usuario.getTelefones().clear();
		}	
			
		for (int i = 1; i <= numeroDeTelefones; i++) {

			String telefoneCompleto = request.getParameter("input_telefone" + i);
			if (telefoneCompleto.length() < 14) break;
			
			int ddd = Integer.valueOf(telefoneCompleto.substring(1, 3));
			String numero = telefoneCompleto.substring(5);
			String tipo = request.getParameter("tipo" + i);
			
			Telefone telefone = new Telefone();			
			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUsuario(usuario);
			
			telefones.add(telefone);
		}
		
		return telefones;
	}

	public void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("delete");
		
		service.removerUsuario(Integer.valueOf(id));
		
		response.sendRedirect(request.getContextPath() + "/usuarios");
	}
	
	public void renderizarPaginaIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");		
		requestDispatcher.forward(request, response);
	}

	public void renderizarPaginaCadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("cadastrar.jsp");		
		requestDispatcher.forward(request, response);
	}

	public void renderizarTodosOsUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> listUsuarios = service.getTodosOsUsuarios();

		request.setAttribute("listUsuarios", listUsuarios);		

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("logado.jsp");		
		requestDispatcher.forward(request, response);
	}

	public void renderizarPaginaAlterar(HttpServletRequest request, HttpServletResponse response, String idUsuario) throws ServletException, IOException {
		Usuario usuario = service.getUsuarioById(Integer.valueOf(idUsuario));		

		request.setAttribute("usuario", usuario);		

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("alterar.jsp");		
		requestDispatcher.forward(request, response);	
	}

	public void renderizarPaginaIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("incluir.jsp");		
		requestDispatcher.forward(request, response);	
	}
}
