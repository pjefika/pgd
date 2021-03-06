package entidades.pps;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import util.JSFUtil;

@Entity
@Table(name="pdg_pps_Codigo_pp")
public class CodigoPp {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	private Date dataPostagem;
	
	@ManyToOne
	private Codigo codigo;
	
	@ManyToOne
	private Pp pp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}
	
	public String getDataPostagemFormatada() {
		
		return JSFUtil.formatarData(this.dataPostagem);
		
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Codigo getCodigo() {
		return codigo;
	}

	public void setCodigo(Codigo codigo) {
		this.codigo = codigo;
	}

	public Pp getPp() {
		return pp;
	}

	public void setPp(Pp pp) {
		this.pp = pp;
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
		CodigoPp other = (CodigoPp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CodigoPp [id=" + id + "]";
	}
	
}
