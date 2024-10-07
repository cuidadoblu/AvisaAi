package avisaai.modelo.entidade.comentario.resposta;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.usuario.Usuario;

@Entity
@Table(name = "resposta")
public class Resposta implements Serializable {

	private static final long serialVersionUID = -2479331445396479292L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resposta")
	private Long id;

	@Column(name = "conteudo_resposta", length = 300, nullable = false)
	private String conteudo;

	@Column(name = "data_hora_resposta", nullable = false)
	private LocalDateTime dataHora;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comentario_origem", referencedColumnName = "id_comentario", nullable = false)
	private Comentario comentarioOrigem;

	public Resposta() {
	}

	public Resposta(String conteudo, LocalDateTime dataHora, Usuario usuario, Comentario comentarioOrigem) {
		setConteudo(conteudo);
		setDataHora(dataHora);
		setUsuario(usuario);
		setConteudo(conteudo);
	}

	public Resposta(Long id, String conteudo, LocalDateTime dataHora, Usuario usuario, Comentario comentarioOrigem) {
		setId(id);
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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