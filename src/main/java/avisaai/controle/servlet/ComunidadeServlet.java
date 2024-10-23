package avisaai.controle.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import avisaai.modelo.dao.comunidade.ComunidadeDAO;
import avisaai.modelo.dao.comunidade.ComunidadeDAOImpl;
import avisaai.modelo.dao.localidade.LocalidadeDAO;
import avisaai.modelo.dao.localidade.LocalidadeDAOImpl;
import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.localidade.Localidade;

@WebServlet(urlPatterns = { "/comunidades", "/cadastro-comunidade", "/perfil-comunidade", "/atualizar-comunidade",
		"/inserir-comunidade", "editar-comunidade", "/excluir-comunidade", "/erro" })

public class ComunidadeServlet extends HttpServlet {

	private static final long serialVersionUID = 9041251404722080496L;

	private ComunidadeDAO comunidadeDAO;
	private LocalidadeDAO localidadeDAO;

	public void init() {
		comunidadeDAO = new ComunidadeDAOImpl();
		localidadeDAO = new LocalidadeDAOImpl();
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

			case "/comunidades":
				mostrarTelaConsultaComunidade(requisicao, resposta);
				break;

			case "/cadastro-comunidade":
				mostrarTelaCadastroComunidade(requisicao, resposta);
				break;

			case "/perfil-comunidade":
				mostrarTelaPerfilComunidade(requisicao, resposta);
				break;

			case "/atualizar-comunidade":
				atualizarComunidade(requisicao, resposta);
				break;

			case "/inserir-comunidade":
				inserirComunidade(requisicao, resposta);
				break;

			case "/editar-comunidade":
				mostrarTelaAtualizaComunidade(requisicao, resposta);
				break;

			case "/excluir-comunidade":
				excluirComunidade(requisicao, resposta);
				break;

			case "/erro":
				erro(requisicao, resposta);
				break;
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void mostrarTelaCadastroComunidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		HttpSession sessao = requisicao.getSession();
		requisicao.getRequestDispatcher("/recursos/paginas/comunidade/cadastro-comunidade.jsp").forward(requisicao,
				resposta);
	}

	private void mostrarTelaPerfilComunidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		HttpSession sessao = requisicao.getSession();
		requisicao.getRequestDispatcher("/recursos/paginas/comunidade/perfil-comunidade.jsp").forward(requisicao,
				resposta);
	}

	private void inserirComunidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {
		String nome = requisicao.getParameter("nome");
		String descricao = requisicao.getParameter("descricao");
		Long localidadeId = Long.parseLong(requisicao.getParameter("localidade-id"));
//		Foto fotoPerfil = requisicao.getParameter("foto");
//		String fotoExtencao = requisicao.getParameter("foto-extencao");

//		Foto foto = new Foto(fotoPerfil.getBytes(), fotoExtencao);

		Localidade localidade = localidadeDAO.consultarLocalidadeId(localidadeId);

		if (localidade == null) {
			requisicao.getRequestDispatcher("erro").forward(requisicao, resposta);
			return;
		}

		Comunidade comunidade = new Comunidade(nome, descricao, localidade, null);
		comunidadeDAO.inserirComunidade(comunidade);

		resposta.sendRedirect("comunidades");
	}

	private void excluirComunidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {

		Long id = Long.parseLong(requisicao.getParameter("id"));

		Comunidade comunidade = comunidadeDAO.consultarComunidadeId(id);

		if (comunidade == null) {
			requisicao.getRequestDispatcher("erro").forward(requisicao, resposta);
			return;
		}

		comunidadeDAO.deletarComunidade(comunidade);

		resposta.sendRedirect("comunidades");
	}

	private void atualizarComunidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {

		Long id = Long.parseLong(requisicao.getParameter("id"));
		String nome = requisicao.getParameter("nome");
		String descricao = requisicao.getParameter("descricao");
		Long localidadeId = Long.parseLong(requisicao.getParameter("localidade-id"));
//		String fotoPerfil = requisicao.getParameter("foto");
//		String fotoExtencao = requisicao.getParameter("foto_extencao");
//
//		Foto foto = new Foto(fotoPerfil.getBytes(), fotoExtencao);

		Localidade localidade = localidadeDAO.consultarLocalidadeId(localidadeId);

		if (localidade == null) {
			requisicao.getRequestDispatcher("erro").forward(requisicao, resposta);
			return;
		}

		Comunidade comunidade = comunidadeDAO.consultarComunidadeId(id);

		if (comunidade == null) {
			requisicao.getRequestDispatcher("erro").forward(requisicao, resposta);
			return;
		}

		comunidade.setLocalidade(localidade);
		comunidade.setDescricao(descricao);
//		comunidade.setFotoPerfil(foto);
		comunidade.setNome(nome);

		comunidadeDAO.atualizarComunidade(comunidade);

		resposta.sendRedirect("comunidades");

	}

	private void mostrarTelaAtualizaComunidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		HttpSession sessao = requisicao.getSession();
		requisicao.getRequestDispatcher("/recursos/paginas/comunidade/cadastro-comunidade.jsp").forward(requisicao,
				resposta);
	}

	private void mostrarTelaConsultaComunidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		requisicao.getRequestDispatcher("/recursos/paginas/comunidade/consulta-comunidade.jsp").forward(requisicao,
				resposta);
	}

	private void erro(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		HttpSession sessao = requisicao.getSession();
		requisicao.getRequestDispatcher("/recursos/paginas/erro/erro-404.jsp").forward(requisicao, resposta);
	}
}
