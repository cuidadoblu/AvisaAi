package avisaai.modelo.dao.comentario;

import java.util.List;

import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.comentario.resposta.Resposta;
import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.entidade.usuario.Usuario;

public interface ComentarioDAO {

	void inserirComentario(Comentario comentario);

	void deletarComentario(Comentario comentario);

	void atualizarComentario(Comentario comentario);

	List<Comentario> recuperarComentarios();

	List<Comentario> consultarComentarioIncidente(Incidente incidente);

	List<Comentario> consultarComentarioUsuario(Usuario usuario);

	List<Resposta> consultarRespostasComentario(Comentario comentario);

	Comentario consultarComentarioId();
}