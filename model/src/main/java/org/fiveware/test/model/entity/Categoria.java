package org.fiveware.test.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "FVC_CATEGORIA")
@NamedQuery(name = Categoria.SELECT_BY_NAME, query = "SELECT c FROM Categoria c WHERE c.descricao = ?1")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 5332437243143650680L;
	
	public static final String SELECT_BY_NAME = "selectCategoriaByName";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NU_ID")
	private Integer id;
	
	@Column(name = "DS_DESCRICAO")
	private String descricao;

	public Categoria() {}
	
	public Categoria(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descricao=" + descricao + "]";
	}
	
}
