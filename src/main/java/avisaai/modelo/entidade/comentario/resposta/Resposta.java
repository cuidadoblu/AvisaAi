package avisaai.modelo.entidade.comentario.resposta;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.incidente.Incidente;

public class Resposta implements Serializable {

	private static final long serialVersionUID = 1445043739428747495L;

	private Long id;

	private String conteudo;

	private LocalDateTime dataHora;

	private Usuario usuario;

	private Comentario comentarioOrigem;

	public Resposta() {
	}

	public Resposta(String conteudo, LocalDateTime dataHora, Incidente usuario, Comentario comentarioOrigem) {
		setConteudo(conteudo);
		setDataHora(dataHora);
		setUsuario(usuario);
		setConteudo(conteudo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Incidente getUsuario() {
		return usuario;
	}

	public void setUsuario(Incidente usuario) {
		this.usuario = usuario;
	}

	public Comentario getComentarioOrigem() {
		return comentarioOrigem;
	}

	public void setComentarioOrigem(Comentario comentarioOrigem) {
		this.comentarioOrigem = comentarioOrigem;
	}

	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resposta outro = (Resposta) obj;
		return Objects.equals(id, outro.id);
	}
}