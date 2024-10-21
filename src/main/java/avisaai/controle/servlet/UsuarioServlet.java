package avisaai.controle.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import avisaai.modelo.dao.contato.ContatoDAO;
import avisaai.modelo.dao.contato.ContatoDAOImpl;
import avisaai.modelo.dao.usuario.UsuarioDAO;
import avisaai.modelo.dao.usuario.UsuarioDAOImpl;
import avisaai.modelo.entidade.usuario.Usuario;
import avisaai.modelo.entidade.usuario.contato.Contato;

@WebServlet(urlPatterns = {"/login", "/cadastro-usuario", "/alterar-senha", "/definir-senha", "/inserir-usuario", "/atualizar-usuario", "/excluir-usuario", "/exibir-perfil", "/erro"})
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
					
				case "/cadastro-usuario":
					mostrarTelaCadastro(requisicao, resposta);
					break;
					
				case "/alterar-senha":
					mostrarTelaAlterarSenha(requisicao, resposta);
					break;
					
				case "/definir-senha":
					mostrarTelaDefinirSenha(requisicao, resposta);
					break;
					
				case "/perfil-usuario":
					mostrarTelaPerfilUsuario(requisicao, resposta);
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
					
				case "/erro":
					erro(requisicao, resposta);
					break;
				}
				
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void mostrarTelaLogin(HttpServletRequest requisicao, HttpServletResponse resposta)
					throws ServletException, IOException {
			
			requisicao.getRequestDispatcher("/recursos/paginas/usuario/login.jsp").forward(requisicao, resposta); 
	}

	private void mostrarTelaCadastro(HttpServletRequest requisicao, HttpServletResponse resposta)
					throws ServletException, IOException {
		
			requisicao.getRequestDispatcher("/recursos/paginas/usuario/cadastro-usuario.jsp").forward(requisicao, resposta);
	}
	private void mostrarTelaAlterarSenha(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws ServletException, IOException {
		
			requisicao.getRequestDispatcher("/recursos/paginas/usuario/alterar-senha.jsp").forward(requisicao, resposta);
	}

	private void mostrarTelaDefinirSenha(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws ServletException, IOException {
		
			HttpSession sessao = requisicao.getSession();
			requisicao.getRequestDispatcher("/recursos/paginas/usuario/redefinir-senha.jsp").forward(requisicao, resposta);
	}
	
	private void mostrarTelaPerfilUsuario(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		
			HttpSession sessao = requisicao.getSession();
			requisicao.getRequestDispatcher("/recursos/paginas/usuario/perfil-usuario.jsp").forward(requisicao, resposta);
	}
	
	private void inserirUsuario(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws SQLException, ServletException, IOException {
		
			String nome = requisicao.getParameter("nome");
			String sobrenome = requisicao.getParameter("sobrenome");
			String senha = requisicao.getParameter("senha");
			
			String telefone = requisicao.getParameter("telefone");
			String email = requisicao.getParameter("email");
			
			Contato contato = new Contato(telefone, email);
			contatoDAO.inserirContato(contato);
			
			usuarioDAO.inserirUsuario(new Usuario(nome, sobrenome, senha, contato, null, null));
			
			requisicao.getRequestDispatcher("login").forward(requisicao, resposta);
	}
	
	private void atualizarUsuario(HttpServletRequest requisicao, HttpServletResponse resposta)
				throws SQLException, ServletException, IOException {
		
			Long id = Long.parseLong(requisicao.getParameter("id"));
			String nome = requisicao.getParameter("nome");
			String sobrenome = requisicao.getParameter("sobrenome");
			String senha = requisicao.getParameter("senha");
			
			String telefone = requisicao.getParameter("telefone");
			String email = requisicao.getParameter("email");
			Contato contato = new Contato(telefone, email);
			
			contatoDAO.atualizarContato(new Contato(telefone, email));
			
			//Adicionar papel aqui e trocar o null no construtor
			
			usuarioDAO.atualizarUsuario(new Usuario(id, nome, sobrenome, senha, contato, null, null));
			
			requisicao.getRequestDispatcher("exibir-perfil").forward(requisicao, resposta);
	}
	
	private void excluirUsuario(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {
		
		Long id = Long.parseLong(requisicao.getParameter("id_usuario"));
		Usuario usuario = usuarioDAO.consultarUsuarioId(id);
		usuarioDAO.deletarUsuario(usuario);
		
		Long idContato = Long.parseLong(requisicao.getParameter("id_contato"));
		Contato contato = contatoDAO.consultarContatoId(idContato);
		contatoDAO.deletarContato(contato);
		
		requisicao.getRequestDispatcher("login").forward(requisicao, resposta);
	}
	private void exibirPerfil(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
	
		HttpSession sessao = requisicao.getSession();
		requisicao.getRequestDispatcher("/recursos/paginas/usuario/perfil-usuario.jsp").forward(requisicao, resposta);
	}
	
	private void erro(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		
		requisicao.getRequestDispatcher("/recursos/paginas/erro/erro-404.jsp").forward(requisicao, resposta);
	}
}