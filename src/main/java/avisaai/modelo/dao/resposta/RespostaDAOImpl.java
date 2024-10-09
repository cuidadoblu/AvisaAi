package avisaai.modelo.dao.resposta;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.comentario.resposta.Resposta;
import avisaai.modelo.entidade.usuario.Usuario;
import avisaai.modelo.factory.conexao.ConexaoFactory;

public class RespostaDAOImpl implements RespostaDAO {

	private final ConexaoFactory fabrica = new ConexaoFactory();

	public void inserirResposta(Resposta resposta) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(resposta);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}

	public void deletarResposta(Resposta resposta) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.remove(resposta);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}

	public void atualizarResposta(Resposta resposta) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(resposta);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {
			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}

	public List<Resposta> recuperarRespostas() {

		Session sessao = null;
		List<Resposta> respostas = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Resposta> criteria = construtor.createQuery(Resposta.class);

			Root<Resposta> raizResposta = criteria.from(Resposta.class);
			criteria.select(raizResposta);

			respostas = sessao.createQuery(criteria).getResultList();
			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return respostas;
	}

	public List<Resposta> consultarRespostaComentarioOrigem(Comentario comentario) {

		Session sessao = null;
		List<Resposta> respostas = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Resposta> criteria = construtor.createQuery(Resposta.class);
			Root<Resposta> raizResposta = criteria.from(Resposta.class);

			Join<Resposta, Comentario> juncaoComentario = raizResposta.join("id_comentarioOrigem");
			criteria.where(construtor.equal(juncaoComentario.get("id"), comentario.getId()));

			respostas = sessao.createQuery(criteria).getResultList();
			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return respostas;
	}

	public List<Resposta> consultarRespostaUsuario(Usuario usuario) {

		Session sessao = null;
		List<Resposta> respostas = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Resposta> criteria = construtor.createQuery(Resposta.class);
			Root<Resposta> raizResposta = criteria.from(Resposta.class);

			Join<Resposta, Usuario> juncaoUsuario = raizResposta.join("usuario");
			criteria.where(construtor.equal(juncaoUsuario.get("id"), usuario.getId()));

			respostas = sessao.createQuery(criteria).getResultList();
			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return respostas;
	}

	public Resposta consultarRespostaId() {

		Session sessao = null;
		Resposta resposta = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Resposta> criteria = construtor.createQuery(Resposta.class);
			Root<Resposta> raizResposta = criteria.from(Resposta.class);

			ParameterExpression<Long> id = construtor.parameter(Long.class);
			criteria.select(raizResposta).where(construtor.equal(raizResposta.get("id"), id));

			resposta = sessao.createQuery(criteria).getSingleResult();

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {
			if (sessao != null) {
				sessao.close();
			}
		}
		return resposta;
	}
}