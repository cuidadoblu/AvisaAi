package avisaai.modelo.entidade.foto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Foto() {
	}

	public Foto(byte[] conteudo, String extensao) {
		this.conteudo = conteudo;
		this.extensao = extensao;
	}

	public Foto(Long id, byte[] conteudo, String extensao) {
		setId(id);
		setConteudo(conteudo);
		setExtensao(extensao);
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

	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Foto outro = (Foto) obj;
		return Objects.equals(id, outro.id);
	}
}