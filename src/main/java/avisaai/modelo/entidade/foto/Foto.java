package avisaai.modelo.entidade.foto;

import java.io.Serializable;
import java.util.Objects;

public class Foto implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Long id;

    private byte[] conteudo;

    private String extensao;

    public Foto() {}

    public Foto(byte[] conteudo, String extensao) {
        this.conteudo = conteudo;
        this.extensao = extensao;
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