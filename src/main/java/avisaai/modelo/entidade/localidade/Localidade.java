package avisaai.modelo.entidade.localidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import avisaai.modelo.entidade.incidente.Incidente;

public class Localidade implements Serializable {

	private static final long serialVersionUID = -8373112043596696044L;

	private Long id;

	private String tipoVia;

	private String logradouro;

	private short numero;

	private String bairro;

	private String cidade;

	private String estado;

	private String complemento;

	private List<Incidente> incidentes;

	public Localidade() {
	}

	public Localidade(String logradouro, String tipoVia, short numero, String bairro, String cidade, String estado,
			String complemento) {

		setLogradouro(logradouro);
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

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
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
		return Objects.equals(id, outro.id);
	}
}