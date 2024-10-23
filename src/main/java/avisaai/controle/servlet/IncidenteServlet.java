package avisaai.controle.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import avisaai.modelo.dao.comunidade.ComunidadeDAO;
import avisaai.modelo.dao.comunidade.ComunidadeDAOImpl;
import avisaai.modelo.dao.incidente.IncidenteDAO;
import avisaai.modelo.dao.incidente.IncidenteDAOImpl;
import avisaai.modelo.dao.localidade.LocalidadeDAO;
import avisaai.modelo.dao.localidade.LocalidadeDAOImpl;
import avisaai.modelo.dao.usuario.UsuarioDAO;
import avisaai.modelo.dao.usuario.UsuarioDAOImpl;
import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.enumeracao.categoria.Categoria;
import avisaai.modelo.enumeracao.situacao.Situacao;

@WebServlet(urlPatterns = { "/perfil-incidente", "/consulta-incidente", "inserir-incidente", "/erro" })
public class IncidenteServlet extends HttpServlet {

	private static final long serialVersionUID = -2732576384429342823L;
	private IncidenteDAO incidenteDAO;
	private ComunidadeDAO comunidadeDAO;
	private LocalidadeDAO localidadeDAO;
	private UsuarioDAO usuarioDAO;

	public void init() {
		incidenteDAO = new IncidenteDAOImpl();
		comunidadeDAO = new ComunidadeDAOImpl();
		localidadeDAO = new LocalidadeDAOImpl();
		usuarioDAO = new UsuarioDAOImpl();
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
			
			case "/perfil-incidente":
				mostrarTelaPerfilIncidente(requisicao, resposta);
				break;

			case "/incidentes":
				mostrarTelaConsultaIncidente(requisicao, resposta);
				break;

			case "/inserir-incidente":
				inserirIncidente(requisicao, resposta);
				break;
				
			case "/atualizar-incidente":
				atualizarIncidente(requisicao,resposta);
				break;
			
			case "/excluir-incidente":
				excluirIncidente(requisicao, resposta);
				break;
				
			case "/erro":
				erro(requisicao, resposta);
				break;
			}
			
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void mostrarTelaPerfilIncidente(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		requisicao.getRequestDispatcher("/recursos/paginas/incidente/perfil-incidente.jsp").forward(requisicao,
				resposta);
	}

	private void mostrarTelaConsultaIncidente(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		requisicao.getRequestDispatcher("/recursos/paginas/incidente/consulta-incidente.jsp").forward(requisicao,
				resposta);
	}

	private void inserirIncidente(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {

		String titulo = requisicao.getParameter("titulo");
		String descricao = requisicao.getParameter("descricao");
		LocalDateTime dataHora = LocalDateTime.parse(requisicao.getParameter("dataHora"));
		Categoria categoria = Categoria.valueOf(requisicao.getParameter("categoria"));
		Situacao situacao = Situacao.valueOf(requisicao.getParameter("situacao"));

		Long idLocalidade = Long.parseLong(requisicao.getParameter("id-localidade"));

		Long idUsuario = Long.parseLong(requisicao.getParameter("id-usuario"));

		if (incidenteDAO.consultarIncidenteId(idUsuario) != null) {
			resposta.sendRedirect("incidentes");
		}

		incidenteDAO.inserirIncidente(new Incidente(titulo, descricao, dataHora, categoria,
				comunidadeDAO.consultarComunidadeBairro(localidadeDAO.consultarLocalidadeId(idLocalidade)),
				usuarioDAO.consultarUsuarioId(idUsuario), localidadeDAO.consultarLocalidadeId(idLocalidade), situacao));

		requisicao.getRequestDispatcher("perfil-incidente").forward(requisicao, resposta);
	}

	private void atualizarIncidente(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {

		Long idIncidente = Long.parseLong(requisicao.getParameter("id-incidente"));

		String titulo = requisicao.getParameter("titulo");
		String descricao = requisicao.getParameter("descricao");
		LocalDateTime dataHora = LocalDateTime.parse(requisicao.getParameter("data-hora"));
		Categoria categoria = Categoria.valueOf(requisicao.getParameter("categoria"));
		Situacao situacao = Situacao.valueOf(requisicao.getParameter("situacao"));

		Long idLocalidade = Long.parseLong(requisicao.getParameter("id-localidade"));

		Long idUsuario = Long.parseLong(requisicao.getParameter("id-usuario"));

		if (incidenteDAO.consultarIncidenteId(idUsuario) != null) {
			resposta.sendRedirect("incidentes");
		}
		
		incidenteDAO.atualizarIncidente(new Incidente(idIncidente, titulo, descricao, dataHora, categoria,
				comunidadeDAO.consultarComunidadeBairro(localidadeDAO.consultarLocalidadeId(idLocalidade)),
				usuarioDAO.consultarUsuarioId(idUsuario), localidadeDAO.consultarLocalidadeId(idLocalidade), situacao));

		requisicao.getRequestDispatcher("perfil-incidente").forward(requisicao, resposta);
	}

	private void excluirIncidente(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws SQLException, ServletException, IOException {

		Long idIncidente = Long.parseLong(requisicao.getParameter("id-incidente"));

		incidenteDAO.deletarIncidente(incidenteDAO.consultarIncidenteId(idIncidente));

		resposta.sendRedirect("feed-pessoal");
	}

	private void erro(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		requisicao.getRequestDispatcher("/recursos/paginas/erro/erro-404.jsp").forward(requisicao, resposta);
	}

}