package avisaai.modelo.entidade.usuario;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import avisaai.modelo.entidade.comentario.Comentario;
import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.foto.Foto;
import avisaai.modelo.entidade.incidente.Incidente;
import avisaai.modelo.entidade.usuario.contato.Contato;
import avisaai.modelo.entidade.usuario.papel.Papel;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -2518745741977867337L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "nome_usuario", length = 30, nullable = false)
	private String nome;

	@Column(name = "sobrenome_usuario", length = 30, nullable = false)
	private String sobrenome;

	@Column(name = "senha", length = 16, nullable = false)
	private String senha;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contato", referencedColumnName = "id_contato")
	private Contato contato;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_foto", referencedColumnName = "id_foto")
	private Foto fotoPerfil;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_papel", referencedColumnName = "id_papel")
	private Papel papel;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_membros_comunidades", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_comunidade"))
	private List<Comunidade> comunidadesAcompanhadas;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentariosFeitos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Incidente> incidentesCadastrados;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Incidente> incidentesAcompanhados;

	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, String senha, Contato contato, Foto fotoPerfil, Papel papel) {
		setNome(nome);
		setSobrenome(sobrenome);
		setSenha(senha);
		setContato(contato);
		setFotoPerfil(fotoPerfil);
		setPapel(papel);
		comunidadesAcompanhadas = new ArrayList<Comunidade>();
		comentariosFeitos = new ArrayList<Comentario>();
		incidentesCadastrados = new ArrayList<Incidente>();
		incidentesAcompanhados = new ArrayList<Incidente>();
	}

	public Usuario(Long id, String nome, String sobrenome, String senha, Contato contato, Foto fotoPerfil,
			Papel papel) {

		setId(id);
		setNome(nome);
		setSobrenome(sobrenome);
		setSenha(senha);
		setContato(contato);
		setFotoPerfil(fotoPerfil);
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
		return id == outro.id;
	}
}