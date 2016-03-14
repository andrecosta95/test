package org.fiveware.test.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "FVL_LIVRO")
@NamedQuery(name = Livro.SELECT_BY_NAME, query = "SELECT l FROM Livro l WHERE l.nome = ?1")
public class Livro implements Serializable {

	private static final long serialVersionUID = -6373857220728109587L;

	public static final String SELECT_BY_NAME = "selectLivroByName";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NU_ID")
	private Long id;

	@Column(name = "NM_NOME")
	private String nome;

	@Column(name = "NM_EDITORA")
	private String editora;

	@Column(name = "NU_EDICAO")
	private Short edicao;

	@Column(name = "NU_PAGINAS")
	private Short qtdPaginas;

	@Column(name = "NU_PESO")
	private Float peso;

	@Column(name = "NU_ISBN")
	private Long isbn;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;

	@Column(name = "DS_IDIOMA")
	private String idioma;

	@Column(name = "FL_EBOOK", columnDefinition = "BIT")
	private boolean contemEbook;

	@Column(name = "DT_LANCAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

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

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Short getEdicao() {
		return edicao;
	}

	public void setEdicao(Short edicao) {
		this.edicao = edicao;
	}

	public Short getQtdPaginas() {
		return qtdPaginas;
	}

	public void setQtdPaginas(Short qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean isContemEbook() {
		return contemEbook;
	}

	public void setContemEbook(boolean contemEbook) {
		this.contemEbook = contemEbook;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", nome=" + nome + ", editora=" + editora + ", edicao=" + edicao + ", qtdPaginas="
				+ qtdPaginas + ", peso=" + peso + ", isbn=" + isbn + ", idioma=" + idioma + ", contemEbook="
				+ contemEbook + ", dataLancamento=" + dataLancamento + "]";
	}

}
