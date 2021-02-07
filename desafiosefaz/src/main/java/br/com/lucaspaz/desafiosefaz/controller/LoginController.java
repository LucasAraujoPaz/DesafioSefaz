package br.com.lucaspaz.desafiosefaz.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lucaspaz.desafiosefaz.entity.Usuario;
import br.com.lucaspaz.desafiosefaz.exceptions.UsuarioNotFoundException;
import br.com.lucaspaz.desafiosefaz.services.ServletService;
import br.com.lucaspaz.desafiosefaz.services.UsuarioServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UsuarioServiceImpl service = new UsuarioServiceImpl();
	private ServletService servletService = new ServletService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("LoginServlet get is called.");

	    servletService.renderizarPaginaIndex(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("LoginServlet post is called.");
	    
	    String login = request.getParameter("input_login");
	    
	    if (login != null) {
	    	permitirOuNegarAcesso(request, response);
	    }

	}


	private void permitirOuNegarAcesso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String email = request.getParameter("input_email");
	    String senha = request.getParameter("input_senha");

		Integer id = conferirLogin(email, senha);

		if (id != null && id != -1) {
			permitirAcesso(id, request, response);
		} else {
			negarAcesso(id, request, response);
		}
	}

	private Integer conferirLogin(String email, String senha) {
		Usuario usuario = new Usuario();

		try {
			usuario = service.getUsuarioByEmail(email);
		} catch (UsuarioNotFoundException e) {
			return null;
		}

		if (usuario.getSenha().equals(senha)) return usuario.getId();
		else return -1;
	}

	private void permitirAcesso(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("id_logado", id);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("logado.jsp");
		requestDispatcher.forward(request, response);
	}

	private void negarAcesso(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("id_rejeitado", id);

		servletService.renderizarPaginaIndex(request, response);		
	}
	
}