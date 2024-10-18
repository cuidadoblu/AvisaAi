package avisaai.modelo.entidade.localidade;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import avisaai.modelo.entidade.incidente.Incidente;

@Entity
@Table(name = "localidade", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "tipo_localidade", "logradouro_localidade", "numero_localidade",
				"bairro_localidade", "cidade_localidade", "estado_localidade", "complemento_localidade" }) })
public class Localidade implements Serializable {

	private static final long serialVersionUID = -8373112043596696044L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_localidade")
	private Long id;

	@Column(name = "tipo_localidade", length = 15, nullable = false)
	private String tipo;

	@Column(name = "logradouro_localidade", length = 30, nullable = false)
	private String logradouro;

	@Column(name = "numero_localidade", nullable = false)
	private short numero;

	@Column(name = "bairro_localidade", length = 30, nullable = false)
	private String bairro;

	@Column(name = "cidade_localidade", length = 30, nullable = false)
	private String cidade;

	@Column(name = "estado_localidade", length = 30, nullable = false)
	private String estado;

	@Column(name = "complemento_localidade", length = 30, nullable = false)
	private String complemento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localidade", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Incidente> incidentes;

	public Localidade() {
	}

	public Localidade(String logradouro, String tipo, short numero, String bairro, String cidade, String estado,
			String complemento) {

		setLogradouro(logradouro);
		setTipo(tipo);
		setBairro(bairro);
		setCidade(cidade);
		setEstado(estado);
		setComplemento(complemento);
		incidentes = new ArrayList<Incidente>();
	}

	public Localidade(Long id, String tipo, String logradouro, short numero, String bairro, String cidade,
			String estado, String complemento) {

		setId(id);
		setLogradouro(logradouro);
		setTipo(tipo);
		setBairro(bairro);
		setCidade(cidade);
		setEstado(estado);
		setComplemento(complemento);
		incidentes = new ArrayList<Incidente>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public short getNumero() {
		return numero;
	}

	public void setNumero(short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public List<Incidente> getIncidentes() {
		return incidentes;
	}

	public void setIncidentes(List<Incidente> incidentes) {
		this.incidentes = incidentes;
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
		Localidade outro = (Localidade) obj;
		return id == outro.id;
	}
}