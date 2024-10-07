package avisaai.modelo.entidade.usuario.papel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "papel")
public class Papel implements Serializable {

	private static final long serialVersionUID = -9156052456128817067L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_papel")
	private Long id;

	@Column(name = "nome_papel", length = 15, nullable = false)
	private String nome;

	public Papel() {
	}

	public Papel(String nome) {
		setNome(nome);
	}

	public Papel(Long id, String nome) {
		setId(id);
		setNome(nome);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Papel outro = (Papel) obj;
		return id == outro.id;
	}
}