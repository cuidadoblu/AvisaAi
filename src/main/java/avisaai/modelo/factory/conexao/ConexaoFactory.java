package avisaai.modelo.factory.conexao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConexaoFactory {

	public SessionFactory getConexao() {
		
		Configuration configuracao = new Configuration();
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.comentario.Comentario.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.comentario.resposta.Resposta.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.comunidade.Comunidade.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.incidente.Incidente.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.foto.Foto.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.localidade.Localidade.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.usuario.Usuario.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.usuario.contato.Contato.class);
		configuracao.addAnnotatedClass(avisaai.modelo.entidade.usuario.papel.Papel.class);

		configuracao.configure("hibernate.cfg.xml");

		ServiceRegistry servico = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
		SessionFactory fabricaSessao = configuracao.buildSessionFactory(servico);
		
		return fabricaSessao;
	}
}