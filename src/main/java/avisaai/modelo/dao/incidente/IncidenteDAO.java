package avisaai.modelo.dao.incidente;

import java.util.List;

import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.entidade.localidade.Localidade;
import avisaai.modelo.entidade.usuario.Usuario;

public interface IncidenteDAO {

	void inserirIncidente(Incidente incidente);

	void deletarIncidente(Incidente incidente);

	void atualizarIncidente(Incidente incidente);

	List<Comentario> consultarQuantidadeComentariosIncidente(Incidente incidente);

	List<Incidente> consultarIncidentesComunidade(Comunidade comunidade);

	List<Incidente> consultarIncidentesCategoria(Incidente incidente);

	List<Incidente> consultarIncidentesUsuarioPorData(Usuario usuario, Incidente incidente);

	List<Incidente> consultarIncidentesLocalidadePorData(Localidade localidade, Incidente incidente);

	List<Incidente> consultarIncidentesLocalidade(Localidade localidade, Incidente incidente);

	List<Incidente> consultarIncidentesSituacao(Incidente incidente);
	
	Incidente consultarIncidenteId(Long id);
}