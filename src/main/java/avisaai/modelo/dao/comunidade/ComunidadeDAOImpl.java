package avisaai.modelo.dao.comunidade;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import avisaai.modelo.entidade.comunidade.Comunidade_;
import avisaai.modelo.entidade.localidade.Localidade_;
import avisaai.modelo.factory.conexao.ConexaoFactory;
import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.localidade.Localidade;
import avisaai.modelo.entidade.usuario.Usuario;

public class ComunidadeDAOImpl implements ComunidadeDAO {

	private final SessionFactory fabrica = ConexaoFactory.getConexao();

	public void inserirComunidade(Comunidade comunidade) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.save(comunidade);

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

	public void deletarComunidade(Comunidade comunidade) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.remove(comunidade);

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

	public void atualizarComunidade(Comunidade comunidade) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.update(comunidade);

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

	public List<Comunidade> recuperarComunidades() {

		Session sessao = null;
		List<Comunidade> comunidades = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Comunidade> criteria = construtor.createQuery(Comunidade.class);
			Root<Comunidade> raizComunidade = criteria.from(Comunidade.class);

			criteria.select(raizComunidade);

			comunidades = sessao.createQuery(criteria).getResultList();

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

		return comunidades;
	}

	public Comunidade consultarComunidadeBairro(Localidade localidade) {

		Session sessao = null;
		Comunidade comunidade = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Comunidade> criteria = construtor.createQuery(Comunidade.class);
			Root<Comunidade> raizComunidade = criteria.from(Comunidade.class);

			Join<Comunidade, Localidade> juncaoLocalidade = raizComunidade.join(Comunidade_.localidade);

			ParameterExpression<String> bairro = construtor.parameter(String.class);
			criteria.where(construtor.equal(juncaoLocalidade.get(Localidade_.bairro), bairro));

			comunidade = sessao.createQuery(criteria).setParameter(bairro, localidade.getBairro()).getSingleResult();

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
		return comunidade;
	}

	public List<Usuario> consultarQuantidadeUsuariosComunidade(Comunidade comunidade) {
		
		Session sessao = null;
		List<Usuario> usuarios = null;
		
		try {
			
			sessao = fabrica.openSession();
			sessao.beginTransaction();
			
			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			
			CriteriaQuery<Usuario> criteria = construtor.createQuery(Usuario.class);
			Root<Usuario> raizUsuario = criteria.from(Usuario.class);
			
			criteria.select(raizUsuario);
			
			Join<Usuario, Comunidade> juncaoComunidade = raizUsuario.join(Usuario_.id);
			
			ParameterExpression<Long> idComunidade = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoComunidade.get(Comunidade_.id), idComunidade));
			
			usuarios = sessao.createQuery(criteria)comunidade.setParameter(idComunidade, comunidade.getId()).getResultList();
			
			sessao.getTransaction().commit();
			
		} catch(Exception sqlException) {
			
			sqlException.printStackTrace();
			
			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}
		} finally {
			if (sessao != null) {
				sessao.close();
			}
		}
		
		return usuarios;
	}
}