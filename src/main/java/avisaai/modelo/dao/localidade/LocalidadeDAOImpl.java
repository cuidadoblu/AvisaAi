package avisaai.modelo.dao.localidade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import avisaai.modelo.entidade.localidade.Localidade;
import avisaai.modelo.factory.conexao.ConexaoFactory;

public class LocalidadeDAOImpl {

	private final SessionFactory fabrica = ConexaoFactory.getConexao();

	public void inserirLocalidade(Localidade localidade) {
		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.save(localidade);

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

	public void deletarLocalidade(Localidade localidade) {
		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.remove(localidade);

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

	public Localidade consultarLocalidadeId(Long id) {
		Session sessao = null;
		Localidade localidade = null;

		try {
			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Localidade> criteria = construtor.createQuery(Localidade.class);
			Root<Localidade> raizLocalidade = criteria.from(Localidade.class);

			criteria.select(raizLocalidade).where(construtor.equal(raizLocalidade.get("id"), id));

			localidade = sessao.createQuery(criteria).getSingleResult();

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

		return localidade;
	}

	public void atualizarLocalidade(Localidade localidade) {
		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.update(localidade);

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

	public List<Localidade> recuperarLocalidades() {
		Session sessao = null;
		List<Localidade> localidades = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Localidade> criteria = construtor.createQuery(Localidade.class);
			Root<Localidade> raizLocalidade = criteria.from(Localidade.class);

			criteria.select(raizLocalidade);

			localidades = sessao.createQuery(criteria).getResultList();

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

		return localidades;
	}

	public List<Localidade> consultarLocalidadePorLogradouro(String logradouro) {
		EntityManager entityManager = fabrica.createEntityManager();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Localidade> query = criteriaBuilder.createQuery(Localidade.class);
		Root<Localidade> root = query.from(Localidade.class);

		query.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get(Localidade_.logradouro))));

		List<Localidade> localidades = entityManager.createQuery(query).getResultList();
		entityManager.close();
		return localidades;
	}

	public List<Localidade> consultarLocalidadePorCidade(String cidade) {
		EntityManager entityManager = fabrica.createEntityManager();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Localidade> query = criteriaBuilder.createQuery(Localidade.class);
		Root<Localidade> root = query.from(Localidade.class);

		query.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get(Localidade_.cidade))));

		List<Localidade> localidades = entityManager.createQuery(query).getResultList();
		entityManager.close();
		return localidades;
	}

	public List<Localidade> consultarLocalidadePorBairro(String bairro) {
		EntityManager entityManager = fabrica.createEntityManager();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Localidade> query = criteriaBuilder.createQuery(Localidade.class);
		Root<Localidade> root = query.from(Localidade.class);

		query.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get(Localidade_.bairro))));

		List<Localidade> localidades = entityManager.createQuery(query).getResultList();
		entityManager.close();
		return localidades;
	}
}