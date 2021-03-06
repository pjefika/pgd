package entidades.projetos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import util.JSFUtil;

@Entity
@Table(name="pgd_projetos_fase_projeto")
public class FaseProjeto {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private Integer porcentagem;
	
	@Lob
	private String observacoes;
	
	@ManyToOne
	private Projeto projeto;

	@ManyToOne
	private ProjetoFase projetoFase;
	
	@ManyToOne
	private StatusProjeto statusProjeto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public String getDataInicioFormatada() {
		
		return JSFUtil.formatarData(this.dataInicio);
		
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}
	
	public String getDataFimFormatada() {
		
		return JSFUtil.formatarData(this.dataFim);
		
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Integer porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public ProjetoFase getProjetoFase() {
		return projetoFase;
	}

	public void setProjetoFase(ProjetoFase projetoFase) {
		this.projetoFase = projetoFase;
	}	

	public StatusProjeto getStatusProjeto() {
		return statusProjeto;
	}

	public void setStatusProjeto(StatusProjeto statusProjeto) {
		this.statusProjeto = statusProjeto;
	}	
	
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		FaseProjeto other = (FaseProjeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FaseProjeto [id=" + id + "]";
	}
	
}
