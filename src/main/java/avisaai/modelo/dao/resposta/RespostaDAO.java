package avisaai.modelo.dao.resposta;

import java.util.List;

import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.comentario.resposta.Resposta;
import avisaai.modelo.entidade.usuario.Usuario;

public interface RespostaDAO {

	void inserirResposta(Resposta resposta);

	void deletarResposta(Resposta resposta);

	void atualizarResposta(Resposta resposta);

	List<Resposta> recuperarRespostas();

	List<Resposta> consultarRespostaComentarioOrigem(Comentario comentario);

	List<Resposta> consultarRespostaUsuario(Usuario usuario);

	Resposta consultarRespostaId();
}