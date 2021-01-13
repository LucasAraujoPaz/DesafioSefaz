package br.com.lucaspaz.desafiosefaz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lucaspaz.desafiosefaz.entity.Telefone;
import br.com.lucaspaz.desafiosefaz.entity.Usuario;
import br.com.lucaspaz.desafiosefaz.services.UsuarioService;
import br.com.lucaspaz.desafiosefaz.services.UsuarioServiceImpl;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
	
	private UsuarioService service = new UsuarioServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("------------ UsuarioServlet get is called.");

		String editarUsuarioId = request.getParameter("edit");
		String excluirUsuarioId = request.getParameter("delete");
		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("incluir")) {
			renderAddPage(request, response);
			return;
		}
		
		if (editarUsuarioId != null) {
			renderEditPage(request, response, editarUsuarioId);
			return;
		}
		
		if (excluirUsuarioId != null) {
			deleteUsuario(request, response);
			return;
		}

		renderizarTodosOsUsuarios(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------ UsuarioServlet post is called.");		//falta implementar telefones
		
		String idUsuario = request.getParameter("input_id");		
		String login = request.getParameter("input_login");

		if (login != null) {
			renderizarTodosOsUsuarios(request, response);
			return;
		} 
		
		if (idUsuario != null) {
			alterarUsuario(request, response);
			return;
		} 
		
		criarUsuario(request, response);

	}

	private void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("input_id");
		String nome = request.getParameter("input_nome");
		String senha = request.getParameter("input_senha");
		
		Usuario usuarioAntigo = service.getUsuarioById(Integer.valueOf(id));

		usuarioAntigo.setNome(nome);
		usuarioAntigo.setSenha(senha);
		System.out.println("--------------indo a telefones");
		List<Telefone> telefones = requestTelefones(request, usuarioAntigo);
		System.out.println("--------------depois de telefones");
		//usuarioAntigo.setTelefones(null);
		usuarioAntigo.setTelefones(telefones);

		service.alterarUsuario(usuarioAntigo);		
		renderizarTodosOsUsuarios(request, response);
	}

	private void criarUsuario(HttpServletRequest request, HttpServletResponse response)
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
		renderizarTodosOsUsuarios(request, response);
	}

	private List<Telefone> requestTelefones(HttpServletRequest request, Usuario usuario) {
		
		int numeroDeTelefones = Integer
				.valueOf(request.getParameter("input_numero_de_telefones"));
		
		System.out.println("--------------numero de telefones = " + numeroDeTelefones);
		List<Telefone> telefones = new ArrayList<Telefone>();
		if ( usuario.getTelefones() != null) {
			telefones = usuario.getTelefones();
			usuario.getTelefones().clear();
		}
		System.out.println("--------------tamanho de telefones = " + telefones.size());	
			
		for (int i = 1; i <= numeroDeTelefones; i++) {

			String telefoneCompleto = request.getParameter("input_telefone" + i);
			if (telefoneCompleto.length() < 14) break;
			
			int ddd = Integer.valueOf(telefoneCompleto.substring(1, 3));
			String numero = telefoneCompleto.substring(5);
			String tipo = request.getParameter("tipo" + i);
			
			System.out.println("--------------telefone = " + telefoneCompleto + tipo);
			Telefone telefone = new Telefone();			
			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUsuario(usuario);
			
			telefones.add(telefone);
		}
		
		return telefones;
	}

	private void renderizarTodosOsUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> listUsuarios = service.getTodosOsUsuarios();

		request.setAttribute("listUsuarios", listUsuarios);		

		RequestDispatcher requestdispatcher = request.getRequestDispatcher("logado.jsp");		
		requestdispatcher.forward(request, response);
	}

	private void renderEditPage(HttpServletRequest request, HttpServletResponse response, String idUsuario) throws ServletException, IOException {
		Usuario usuario = service.getUsuarioById(Integer.valueOf(idUsuario));		

		request.setAttribute("usuario", usuario);		

		RequestDispatcher requestdispatcher = request.getRequestDispatcher("alterar.jsp");		
		requestdispatcher.forward(request, response);	
	}

	private void renderAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("incluir.jsp");		
		requestdispatcher.forward(request, response);	
	}

	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("delete");
		
		Usuario usuario = service.getUsuarioById(Integer.valueOf(id));
		
		service.removerUsuario(usuario);
		
		response.sendRedirect(request.getContextPath() + "/usuarios");
	}
	
}
/*		    response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");

UsuarioServiceImpl repository = new UsuarioServiceImpl();

List<Usuario> usuarios = repository.getTodosOsUsuarios();

String[] nomesDosUsuarios = new String[usuarios.size()]; 
for (int i = 0; i < usuarios.size(); i++) {
	nomesDosUsuarios[i] = usuarios.get(i).getNome();
}

System.out.print("--------------- USUARIOS TO ARRAY: " + java.util.Arrays.toString(nomesDosUsuarios));

String usuariosJsonString = new Gson().toJson(usuarios);
System.out.print("-------------- JSONSTRING: " + usuariosJsonString);

response.getWriter().print(usuariosJsonString);
response.getWriter().flush();
System.out.println("----------- UsuarioServlet post is done.");*/	
