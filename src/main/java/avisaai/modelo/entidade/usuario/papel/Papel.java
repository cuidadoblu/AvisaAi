package avisaai.modelo.entidade.usuario.papel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import avisaai.modelo.entidade.usuario.Usuario;

public class Papel implements Serializable {

	private static final long serialVersionUID = -9156052456128817067L;

	private Long id;

	private String nome;

	private List<Usuario> usuarios;

	public Papel() {
	}

	public Papel(Long id, String nome, List<Usuario> usuarios) {
		setId(id);
		setNome(nome);
		setUsuarios(usuarios);
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		return Objects.equals(id, outro.id);
	}
}