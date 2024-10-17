package avisaai.modelo.dao.contato;

import avisaai.modelo.entidade.usuario.Usuario;
import avisaai.modelo.entidade.usuario.contato.Contato;

public interface ContatoDAO {
	
	void inserirContato(Contato contato);
	
	void deletarContato(Contato contato);
	
	void atualizarContato(Contato contato);
	
	Contato recuperarContatoUsuario(Usuario usuario);
	
	Contato consultarContatoId(Long id);
}