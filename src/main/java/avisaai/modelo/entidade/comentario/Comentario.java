package avisaai.modelo.entidade.comentario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import avisaai.modelo.entidade.comentario.resposta.Resposta;
import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.entidade.usuario.Usuario;

public class Comentario implements Serializable {

	private static final long serialVersionUID = -8093615765047897535L;

	private Long id;

	private String conteudo;

	private LocalDateTime dataHora;

	private Usuario usuario;

	private Incidente incidente;

	private List<Resposta> respostas;

	public Comentario() {
	}

	public Comentario(String conteudo, LocalDateTime dataHora, Usuario usuario, Incidente incidente) {
		setConteudo(conteudo);
		setDataHora(dataHora);
		setUsuario(usuario);
		setIncidente(incidente);
		respostas = new ArrayList<Resposta>();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void adicionarResposta(Resposta resposta) {
		this.respostas.add(resposta);
		resposta.setComentarioOrigem(this);
	}

	public void removerResposta(Resposta resposta) {
		this.respostas.remove(resposta);
		resposta.setComentarioOrigem(null);
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
		Comentario outro = (Comentario) obj;
		return Objects.equals(id, outro.id);
	}
}