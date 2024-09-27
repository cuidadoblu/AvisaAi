package avisaai.modelo.entidade.usuario.contato;

import java.io.Serializable;
import java.util.Objects;

public class Contato implements Serializable{
	
	private static final long serialVersionUID = -6243156960143903528L;

	private Long id;
	
	private String telefone;
	
	private String email;
	
	public Contato() {}
	
	public Contato(String telefone, String email) {
		setTelefone(telefone);
		setEmail(email);
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