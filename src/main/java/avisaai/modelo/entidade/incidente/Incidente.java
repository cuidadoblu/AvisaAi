package avisaai.modelo.entidade.incidente;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import avisaai.modelo.entidade.comunidade.Comunidade;
import avisaai.modelo.entidade.foto.Foto;
import avisaai.modelo.entidade.localidade.Localidade;
import avisaai.modelo.entidade.usuario.Usuario;
import avisaai.modelo.enumeracao.categoria.Categoria;
import avisaai.modelo.enumeracao.situacao.Situacao;

@Entity
@Table(name = "incidente")
public class Incidente implements Serializable {

	static final long serialVersionUID = -3363285045481180558L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_incidente")
	private Long id;

	@Column(name = "titulo_incidente", length = 100, nullable = false)
	private String titulo;

	@Column(name = "descricao_incidente", length = 950, nullable = false)
	private String descricao;

	@Column(name = "data_incidente", nullable = false)
	private LocalDateTime dataHora;

	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_comunidade", referencedColumnName = "id_comunidade")
	private Comunidade comunidade;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_localidade", referencedColumnName = "id_localidade")
	private Localidade localidade;

	@Column(name = "situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Foto> fotoIncidente;

	public Incidente() {
	}

	public Incidente(String titulo, String descricao, LocalDateTime dataHora, Categoria categoria,
			Comunidade comunidade, Usuario usuario, Localidade localidade, Situacao situacao) {
		setTitulo(titulo);
		setDescricao(descricao);
		setDataHora(dataHora);
		setCategoria(categoria);
		setComunidade(comunidade);
		setUsuario(usuario);
		setLocalidade(localidade);
		setSituacao(situacao);
		fotoIncidente = new ArrayList<Foto>();
	}
	
	public Incidente(Long id, String titulo, String descricao, LocalDateTime dataHora, Categoria categoria,
			Comunidade comunidade, Usuario usuario, Localidade localidade, Situacao situacao) {
		setId(id);
		setTitulo(titulo);
		setDescricao(descricao);
		setDataHora(dataHora);
		setCategoria(categoria);
		setComunidade(comunidade);
		setUsuario(usuario);
		setLocalidade(localidade);
		setSituacao(situacao);
		fotoIncidente = new ArrayList<Foto>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime data) {
		this.dataHora = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Foto> getFotoIncidente() {
		return fotoIncidente;
	}

	public void adicionarFotoIncidente(List<Foto> fotoIncidente, Foto foto) {
		this.fotoIncidente.add(foto);
	}

	public void removerFotoIncidente(List<Foto> fotoIncidente, Foto foto) {
		this.fotoIncidente.add(foto);
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
		Incidente outro = (Incidente) obj;
		return Objects.equals(id, outro.id);
	}
}