package avisaai.modelo.entidade.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.entidade.usuario.papel.Papel;

public class Usuario implements Serializable {

	private static final long serialVersionUID = -2518745741977867337L;

	private Long id;

	private String nome;

	private String sobrenome;

	private String senha;

	private Contato contato;

	private Foto fotoPerfil;

	private Papel papel;

	private List<Comunidade> comunidadesAcompanhadas;

	private List<Comentario> comentariosFeitos;

	private List<Incidente> incidentesCadastrados;

	private List<Incidente> incidentesAcompanhados;

	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, String senha, Contato contato, Foto foto, Papel papel) {
		setNome(nome);
		setSobrenome(sobrenome);
		setSenha(senha);
		setContato(contato);
		setFoto(foto);
		setPapel(papel);
		comunidadesAcompanhadas = new ArrayList<Comunidade>();
		comentariosFeitos = new ArrayList<Comentario>();
		incidentesCadastrados = new ArrayList<Incidente>();
		incidentesAcompanhados = new ArrayList<Incidente>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Foto getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(Foto fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public void adicionarPapel(Papel papel) {
		this.papeis.add(papel);
	}

	public void removerPapel(Papel papel) {
		this.papeis.remove(papel);
	}

	public List<Comunidade> getComunidadesAcompanhadas() {
		return comunidadesAcompanhadas;
	}

	public List<Comentario> getComentariosFeitos() {
		return comentariosFeitos;
	}

	public List<Incidente> getIncidentesCadastrados() {
		return incidentesCadastrados;
	}

	public List<Incidente> getIncidentesAcompanhados() {
		return incidentesAcompanhados;
	}

	public void entrarNaComunidade(Comunidade comunidade) {

		comunidadesAcompanhadas.add(comunidade);
	}

	public void sairDaComunidade(Comunidade comunidade) {
		comunidadesAcompanhadas.remove(comunidade);
	}

	public void cadastrarComentarios(Comentario comentario) {
		comentariosFeitos.add(comentario);
	}

	public void excluirComentario(Comentario comentario) {
		comentariosFeitos.remove(comentario);
	}

	public void cadastrarIncidente(Incidente incidente) {
		incidentesCadastrados.add(incidente);
	}

	public void excluirIncidente(Incidente incidente) {
		incidentesCadastrados.remove(incidente);
	}

	public void acompanharIncidente(Incidente incidente) {
		incidentesAcompanhados.add(incidente);
	}

	public void desacompanharIncidente(Incidente incidente) {
		incidentesAcompanhados.remove(incidente);
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
		Usuario outro = (Usuario) obj;
		return Objects.equals(id, outro, id);
	}
}