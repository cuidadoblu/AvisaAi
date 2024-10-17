package avisaai.modelo.dao.comunidade;

import java.util.List;

import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.localidade.Localidade;

public interface ComunidadeDAO {

	void inserirComunidade(Comunidade comunidade);

	void deletarComunidade(Comunidade comunidade);

	void atualizarComunidade(Comunidade comunidade);

	Comunidade consultarComunidadeBairro(Localidade localidade);

	List<Comunidade> recuperarComunidades();
	
	Comunidade consultarComunidadeId(Long id);
}