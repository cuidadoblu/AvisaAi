package avisaai.modelo.entidade.foto;

import java.io.Serializable;
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

import avisaai.modelo.entidade.incidente.Incidente;

@Entity
@Table(name = "foto")
public class Foto implements Serializable {

	private static final long serialVersionUID = -832958979755616717L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto")
	private Long id;

	@Column(name = "conteudo_foto", nullable = false)
	private byte[] conteudo;

	@Column(name = "extensao_foto", nullable = false)
	private String extensao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_incidente", referencedColumnName = "id_incidente")
	private Incidente incidente;

	public Foto() {
	}

	public Foto(byte[] conteudo, String extensao, Incidente incidente) {
		this.conteudo = conteudo;
		this.extensao = extensao;
		this.incidente = incidente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getConteudo() {
		return conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}

	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		return Objects.equals(id, other.id);
	}
}