package avisaai.modelo.dao.comunidade;

import java.util.List;

import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.localidade.Localidade;
import avisaai.modelo.entidade.usuario.Usuario;

public interface ComunidadeDAO {

	void inserirComunidade(Comunidade comunidade);

	void deletarComunidade(Comunidade comunidade);

	void atualizarComunidade(Comunidade comunidade);

	Comunidade consultarComunidadeBairro(Localidade localidade);

	List<Usuario> consultarQuantidadeUsuariosComunidade(Comunidade comunidade);

	List<Comunidade> recuperarComunidades();
	
	Comunidade consultarComunidadeId();
}