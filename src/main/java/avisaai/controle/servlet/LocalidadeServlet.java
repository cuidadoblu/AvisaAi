package avisaai.controle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import avisaai.modelo.dao.localidade.LocalidadeDAO;
import avisaai.modelo.dao.localidade.LocalidadeDAOImpl;
import avisaai.modelo.entidade.localidade.Localidade;

@WebServlet(urlPatterns = { "/localidades", "/cadastro-localidade", "/editar-localidade", "/inserir-localidade",
		"/consultar-localidade", "/atualizar-localidade", "/excluir-localidade", "/erro"})

public class LocalidadeServlet extends HttpServlet {

	private static final long serialVersionUID = -827990208860075084L;
	private LocalidadeDAO localidadeDAO;

	public void init() {
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

			case "/localidades":
				mostrarTelaConsultaLocalidade(requisicao, resposta);
				break;

			case "/cadastro-localidade":
				mostrarTelaCadastroLocalidade(requisicao, resposta);
				break;

			case "/editar-localidade":
				mostrarTelaAtualizaLocalidade(requisicao, resposta);
				break;

			case "/atualizar-localidade":
				atualizarLocalidade(requisicao, resposta);
				break;

			case "/inserir-localidade":
				inserirLocalidade(requisicao, resposta);
				break;

			case "/excluir-localidade":
				excluirLocalidade(requisicao, resposta);
				break;

			case "/erro":
				erro(requisicao, resposta);
				break;
			}

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void mostrarTelaCadastroLocalidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		requisicao.getRequestDispatcher("/recursos/paginas/localidade/cadastro-localidade.jsp").forward(requisicao, resposta);
	}

	private void inserirLocalidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		String logradouro = requisicao.getParameter("logradouro");
		String tipo = requisicao.getParameter("tipo");
		String numero = requisicao.getParameter("numero");
		String bairro = requisicao.getParameter("bairro");
		String cidade = requisicao.getParameter("cidade");
		String estado = requisicao.getParameter("estado");
		String complemento = requisicao.getParameter("complemento");

		Localidade localidade = new Localidade(logradouro, tipo, Short.parseShort(numero), bairro, cidade, estado,
				complemento);
		localidadeDAO.inserirLocalidade(localidade);

		resposta.sendRedirect("localidades");
	}

	private void excluirLocalidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		String id = requisicao.getParameter("id");

		Localidade localidade = localidadeDAO.consultarLocalidadePorId(id);

		if (localidade == null) {
			requisicao.getRequestDispatcher("erro").forward(requisicao, resposta);
			return;
		}

		localidadeDAO.deletarLocalidade(localidade);

		resposta.sendRedirect("localidades");
	}

	private void atualizarLocalidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		String id = requisicao.getParameter("id");
		String logradouro = requisicao.getParameter("logradouro");
		String tipo = requisicao.getParameter("tipo");
		String numero = requisicao.getParameter("numero");
		String bairro = requisicao.getParameter("bairro");
		String cidade = requisicao.getParameter("cidade");
		String estado = requisicao.getParameter("estado");
		String complemento = requisicao.getParameter("complemento");

		Localidade localidade = localidadeDAO.consultarLocalidadePorId(id);

		if (localidade == null) {
			requisicao.getRequestDispatcher("erro").forward(requisicao, resposta);
			return;
		}

		localidade.setLogradouro(logradouro);
		localidade.setTipo(tipo);
		localidade.setNumero(Short.parseShort(numero));
		localidade.setBairro(bairro);
		localidade.setCidade(cidade);
		localidade.setEstado(estado);
		localidade.setComplemento(complemento);

		localidadeDAO.atualizarLocalidade(localidade);

		resposta.sendRedirect("localidades");

	}

	private void mostrarTelaAtualizaLocalidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		requisicao.getRequestDispatcher("/recursos/paginas/localidade/cadastro-localidade.jsp").forward(requisicao, resposta);
	}

	private void mostrarTelaConsultaLocalidade(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		requisicao.getRequestDispatcher("/recursos/paginas/localidade/consulta-localidade.jsp").forward(requisicao, resposta);
	}

	private void erro(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {

		requisicao.getRequestDispatcher("/recursos/paginas/erro/erro-404.jsp").forward(requisicao, resposta);
	}
}
