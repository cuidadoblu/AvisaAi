package avisaai.controle.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login", "/cadastro", "/alterar-senha", "/definir-senha", "/inserir-usuario", "/atualizar-usuario", "/excluir-usuario", "/exibir-perfil"})
public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1959126762240015341L;
	private UsuarioDAO usuarioDAO;
	private ContatoDAO contatoDAO;

	public void init() {
		usuarioDAO = new UsuarioDAOImpl();
		contatoDAO = new ContatoDAOImpl();
	}

	protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		doGet(requisicao, resposta);
	}

	protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		
		String action = requisicao.getServletPath();

		try {

				switch (action) {
				
				case "/login":
					mostrarTelaLogin(requisicao, resposta);
					break;
					
				case "/cadastro":
					mostrarTelaCadastro(requisicao, resposta);
					break;
					
				case "/alterar-senha":
					mostrarTelaAlterarSenha(requisicao, resposta);
					break;
					
				case "/definir-senha":
					mostrarTelaDefinirSenha(requisicao, resposta);
					break;
					
				case "/inserir-usuario":
					inserirUsuario(requisicao, resposta);
					break;
					
				case "/atualizar-usuario":
					atualizarUsuario(requisicao, resposta);
					break;
					
				case "/excluir-usuario":
					excluirUsuario(requisicao, resposta);
					break;
					
				case "/exibir-perfil":
					exibirPerfil(requisicao, resposta);
					break;
					
				default:
					erro(requisicao, resposta);
					break;
				}
				
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void mostrarTelaLogin(HttpServletRequest requisicao, HttpServletResponse resposta)
					throws ServletException, IOException {
			
			HttpSession sessao = requisicao.getSession();
			requisicao.getRequestDispatcher("login-usuario.jsp").forward(requisicao, resposta); 
	}

	private void mostrarTelaCadastro(HttpServletRequest requisicao, HttpServletResponse resposta)
					throws ServletException, IOException {
		
			HttpSession sessao = requisicao.getSession();
			requisicao.getRequestDispatcher("cadastro-usuario.jsp").forward(requisicao, resposta);
	}
	private void mostrarTelaAlterarSenha(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws ServletException, IOException {
		
			HttpSession sessao = requisicao.getSession();
			requisicao.getRequestDispatcher("alterar-senha.jsp").forward(requisicao, resposta);
	}

	private void mostrarTelaDefinirSenha(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws ServletException, IOException {
		
			HttpSession sessao = requisicao.getSession();
			requisicao.getRequestDispatcher("definir-senha.jsp").forward(requisicao, resposta);
	}
	
	private void inserirUsuario(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws SQLException, ServletException, IOException {
		
			String nome = requisicao.getParameter("nome");
			String sobrenome = requisicao.getParameter("sobrenome");
			String senha = requisicao.getParameter("senha");
			
			String telefone = requisicao.getParameter("telefone");
			String email = requisicao.getParameter("email");
			Contato contato = new Contato(telefone, email);
			contatoDAO.inserirContato(new Contato(contato));
			
			usuarioDAO.inserirUsuario(new Usuario(nome, sobrenome, senha, contato));
			
			resposta.sendRedirect("feed-principal.jsp");
	}
	
	private void atualizarUsuario(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws SQLException, ServletException, IOException {
		
			Long id = Long.parseLong(requisicao.getParameter("id"));
			String nome = requisicao.getParameter("nome");
			String sobrenome = requisicao.getParameter("sobrenome");
			String senha = requisicao.getParameter("senha");
			
			Long id_contato = Long.parseLong(requisicao.getParameter("id"));
			String telefone = requisicao.getParameter("telefone");
			String email = requisicao.getParameter("email");
			Contato contato = new Contato(telefone, email);
			contatoDAO.atualizarContato(new Contato(id_contato, telefone, email));
			
			usuarioDAO.atualizarUsuario(new Usuario(id, nome, sobrenome, senha, contato));
			
			resposta.sendRedirect("perfil-usuario.jsp");
	}
	
	private void excluirUsuario(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {
		
		Long id = Long.parseLong(requisicao.getParameter("id_usuario"));
		Usuario usuario = usuarioDAO.recuperarUsuario(new Usuario(id));
		usuarioDAO.excluirUsuario(usuario);
		
		Long id_contato = Long.parseLong(requisicao.getParameter("id_contato"));
		Contato contato = contatoDAO.recuperarContato(new Contato(id_contato));
		contatoDAO.excluirContato(contato);
		
		resposta.sendRedirect("login-usuario.jsp");
	}
	private void exibirPerfil(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
	
		HttpSession sessao = requisicao.getSession();
		requisicao.getRequestDispatcher("perfil-pessoal.jsp").forward(requisicao, resposta);
	}
	
	private void erro(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		
		HttpSession sessao = requisicao.getSession();
		requisicao.getRequestDispatcher("erro-404.jsp").forward(requisicao, resposta);
	}
}