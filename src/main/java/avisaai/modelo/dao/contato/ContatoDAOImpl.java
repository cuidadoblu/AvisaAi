package avisaai.modelo.dao.contato;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import AvisaAi.modelo.factory.conexao.ConexaoFactory;
import avisaai.modelo.entidade.usuario.Usuario;
import avisaai.modelo.entidade.usuario.contato.Contato;

public class ContatoDAOImpl implements ContatoDAO {

	private final SessionFactory fabrica = ConexaoFactory.getConexao();

	public void inserirContato(Contato contato) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.save(contato);

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

	public void deletarContato(Contato contato) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.remove(contato);

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

	public void atualizarContato(Contato contato) {

		Session sessao = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			sessao.update(contato);

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

	public Contato recuperarContatoUsuario(Usuario usuario) {

		Session sessao = null;
		Contato contato = null;

		try {

			sessao = fabrica.openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Contato> criteria = construtor.createQuery(Contato.class);
			Root<Contato> raizContato = criteria.from(Contato.class);

			criteria.select(raizContato);

			contato = sessao.createQuery(criteria).getSingleResult();

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
		return contato;
	}
	
	public Contato consultarContatoId() {
		
		Session sessao = null;
		Contato contato = null;
		
		try {
			
			sessao = fabrica.openSession();
			sessao.beginTransaction();
			
			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Contato> criteria = construtor.createQuery(Contato.class);
			Root<Contato> raizContato = criteria.from(Contato.class);
			
			criteria.select(raizContato).where(construtor.equal(raizContato.get("id"), id));
			
			contato = sessao.createQuery(criteria).getSingleResult();
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

		return contato;
	}
}