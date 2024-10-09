package avisaai.modelo.dao.localidade;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import avisaai.modelo.entidade.localidade.Localidade;
import avisaai.modelo.factory.conexao.ConexaoFactory;

public class LocalidadeDAOImpl {

	private final ConexaoFactory fabrica = new ConexaoFactory();

	public void inserirLocalidade(Localidade localidade) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
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

			sessao = fabrica.getConexao().openSession();
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
			sessao = fabrica.getConexao().openSession();
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

			sessao = fabrica.getConexao().openSession();
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

			sessao = fabrica.getConexao().openSession();
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

		Session sessao = null;
		List<Localidade> localidades = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Localidade> criteria = construtor.createQuery(Localidade.class);
			Root<Localidade> raizLocalidade = criteria.from(Localidade.class);

			criteria.select(raizLocalidade).where(construtor.equal(raizLocalidade.get("logradouro"), logradouro));

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

	public List<Localidade> consultarLocalidadePorCidade(String cidade) {

		Session sessao = null;
		List<Localidade> localidades = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Localidade> criteria = construtor.createQuery(Localidade.class);
			Root<Localidade> raizLocalidade = criteria.from(Localidade.class);

			criteria.select(raizLocalidade).where(construtor.equal(raizLocalidade.get("cidade"), cidade));

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

	public List<Localidade> consultarLocalidadePorBairro(String bairro) {

		Session sessao = null;
		List<Localidade> localidades = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Localidade> criteria = construtor.createQuery(Localidade.class);
			Root<Localidade> raizLocalidade = criteria.from(Localidade.class);

			criteria.select(raizLocalidade).where(construtor.equal(raizLocalidade.get("bairro"), bairro));

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
}