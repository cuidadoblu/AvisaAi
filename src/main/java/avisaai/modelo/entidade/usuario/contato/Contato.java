package avisaai.modelo.entidade.usuario.contato;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

	private static final long serialVersionUID = -6243156960143903528L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contato")
	private Long id;

	@Column(name = "telefone_contato", length = 13, nullable = false)
	private String telefone;

	@Column(name = "email_contato", length = 45, nullable = false)
	private String email;

	public Contato() {
	}

	public Contato(String telefone, String email) {
		setTelefone(telefone);
		setEmail(email);
	}
	
	public Contato(Long id, String telefone, String email) {
		setId(id);
		setTelefone(telefone);
		setEmail(email);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Contato outro = (Contato) obj;
		return id == outro.id;
	}
}