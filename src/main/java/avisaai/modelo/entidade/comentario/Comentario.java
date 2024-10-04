package avisaai.modelo.entidade.comentario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import avisaai.modelo.entidade.comentario.resposta.Resposta;
import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.entidade.usuario.Usuario;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

	private static final long serialVersionUID = -8093615765047897535L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	private Long id;

	@Column(name = "conteudo_comentario", length = 300, nullable = false)
	private String conteudo;

	@Column(name = "data_hora_comentario", nullable = false)
	private LocalDateTime dataHora;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_incidente", nullable = false)
	private Incidente incidente;

	@OneToMany(mappedBy = "comentarioOrigem", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
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