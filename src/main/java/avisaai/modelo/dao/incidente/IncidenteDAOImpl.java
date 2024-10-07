package avisaai.modelo.dao.incidente;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import avisaai.modelo.entidade.comentario.Comentario_;
import avisaai.modelo.entidade.comunidade.Comunidade_;
import avisaai.modelo.entidade.incidente.Incidente_;
import avisaai.modelo.entidade.localidade.Localidade_;
import avisaai.modelo.entidade.usuario.Usuario_;
import avisaai.modelo.factory.conexao.ConexaoFactory;
import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.entidade.localidade.Localidade;
import avisaai.modelo.entidade.usuario.Usuario;
import avisaai.modelo.enumeracao.categoria.Categoria;
import avisaai.modelo.enumeracao.situacao.Situacao;

public class IncidenteDAOImpl implements IncidenteDAO {

	private final SessionFactory fabrica = ConexaoFactory.getConexao();

	public void inserirIncidente(Incidente incidente) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.save(incidente);

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

	}

	public void deletarIncidente(Incidente incidente) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.remove(incidente);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.clear();
			}
		}

	}

	public void atualizarIncidente(Incidente incidente) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.update(incidente);

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

	}

	public List<Comentario> consultarQuantidadeComentariosIncidente(Incidente incidente) {

		Session sessao = null;
		List<Comentario> comentarios = null;

		try {
			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Comentario> criteria = construtor.createQuery(Comentario.class);
			Root<Comentario> raizComentario = criteria.from(Comentario.class);

			criteria.select(raizComentario);

			Join<Comentario, Incidente> juncaoIncidente = raizComentario.join(Comentario_.incidente);

			ParameterExpression<Long> idIncidente = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoIncidente.get(Incidente_.id), idIncidente));

			comentarios = sessao.createQuery(criteria).setParameter(idIncidente, incidente.getId()).getResultList();

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

		return comentarios;
	}

	public List<Incidente> consultarIncidentesComunidade(Comunidade comunidade) {

		Session sessao = null;
		List<Incidente> incidentes = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Incidente> criteria = construtor.createQuery(Incidente.class);
			Root<Incidente> raizIncidente = criteria.from(Incidente.class);

			criteria.select(raizIncidente);

			Join<Incidente, Comunidade> juncaoComunidade = raizIncidente.join(Incidente_.comunidade);
//			
			ParameterExpression<Long> idComunidade = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoComunidade.get(Comunidade_.id), idComunidade));

			incidentes = sessao.createQuery(criteria).setParameter(idComunidade, comunidade.getId()).getResultList();

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

		return incidentes;

	}

	public List<Incidente> consultarIncidentesCategoria(Incidente incidente) {

		Session sessao = null;
		List<Incidente> incidentes = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Incidente> criteria = construtor.createQuery(Incidente.class);
			Root<Incidente> raizIncidente = criteria.from(Incidente.class);

			criteria.select(raizIncidente);

			Join<Incidente, Categoria> juncaoCategoria = raizIncidente.join(Incidente_.categoria);

			Predicate predicadoCategoriaIncidente = construtor
					.equal(juncaoCategoria.get(Incidente_.categoria.getName()), incidente.getCategoria());

			criteria.where(predicadoCategoriaIncidente);

			incidentes = sessao.createQuery(criteria).getResultList();

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

		return incidentes;

	}

	public List<Incidente> consultarIncidentesUsuarioPorData(Usuario usuario, Incidente incidente) {

		Session sessao = null;
		List<Incidente> incidentes = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Incidente> criteria = construtor.createQuery(Incidente.class);
			Root<Incidente> raizIncidente = criteria.from(Incidente.class);

			criteria.select(raizIncidente);

			Join<Incidente, Usuario> juncaoUsuario = raizIncidente.join(Incidente_.usuario);

			Predicate predicadoIdUsuario = construtor.equal(juncaoUsuario.get(Usuario_.id), usuario.getId());

			Predicate predicadoDataHoraIncidente = construtor.equal(raizIncidente.get(Incidente_.dataHora),
					incidente.getDataHora());

			Predicate predicadoResultado = construtor.and(predicadoIdUsuario, predicadoDataHoraIncidente);
			criteria.where(predicadoResultado);

			incidentes = sessao.createQuery(criteria).getResultList();

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

		return incidentes;

	}

	public List<Incidente> consultarIncidentesLocalidadePorData(Localidade localidade, Incidente incidente) {

		Session sessao = null;
		List<Incidente> incidentes = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Incidente> criteria = construtor.createQuery(Incidente.class);
			Root<Incidente> raizIncidente = criteria.from(Incidente.class);

			criteria.select(raizIncidente);

			Join<Incidente, Localidade> juncaoLocalidade = raizIncidente.join(Incidente_.localidade);

			Predicate predicadoIncidentesLocalidade = construtor.equal(juncaoLocalidade.get(Localidade_.incidentes),
					localidade.getId());

			Predicate predicadoDataHoraIncidente = construtor.equal(raizIncidente.get(Incidente_.dataHora),
					incidente.getDataHora());

			Predicate predicadoResultado = construtor.and(predicadoIncidentesLocalidade, predicadoDataHoraIncidente);
			criteria.where(predicadoResultado);

			incidentes = sessao.createQuery(criteria).getResultList();

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

		return incidentes;

	}

	public List<Incidente> consultarIncidentesSituacao(Incidente incidente) {

		Session sessao = null;
		List<Incidente> incidentes = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Incidente> criteria = construtor.createQuery(Incidente.class);
			Root<Incidente> raizIncidente = criteria.from(Incidente.class);

			criteria.select(raizIncidente);

			Join<Incidente, Situacao> juncaoSituacao = raizIncidente.join(Incidente_.situacao);

			Predicate predicadoSituacaoIncidente = construtor.equal(juncaoSituacao.get(Incidente_.situacao.getName()),
					incidente.getSituacao());

			criteria.where(predicadoSituacaoIncidente);

			incidentes = sessao.createQuery(criteria).getResultList();

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

		return incidentes;

	}

	public List<Incidente> consultarIncidentesLocalidade(Localidade localidade, Incidente incidente) {

		Session sessao = null;
		List<Incidente> incidentes = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Incidente> criteria = construtor.createQuery(Incidente.class);
			Root<Incidente> raizIncidente = criteria.from(Incidente.class);

			criteria.select(raizIncidente);

			Join<Incidente, Localidade> juncaoLocalidade = raizIncidente.join(Incidente_.localidade);

			ParameterExpression<String> idLocalidade = construtor.parameter(String.class);
			criteria.where(construtor.equal(juncaoLocalidade.get(Localidade_.id), idLocalidade));

			incidentes = sessao.createQuery(criteria).getResultList();

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

		return incidentes;
	}

	public Incidente consultarIncidenteId() {
		
		Session sessao = null;
		Incidente incidente = null;
		
		try {
			
			sessao = fabrica.openSession();
			sessao.beginTransaction();
			
			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Incidente> criteria = construtor.createQuery(Incidente.class);
			Root<Incidente> raizIncidente = criteria.from(Incidente.class);
			
			criteria.select(raizIncidente).where(construtor.equal(raizIncidente.get("id"), id));
			
			incidente = sessao.createQuery(criteria).getSingleResult();
			sessao.getTransaction().commit();
		
		} catch (Exception exception) {
			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
			exception.printStackTrace();
		} finally {
			if (sessao != null) {
				sessao.close();
			}
		}
		
		return incidente;
	}
}