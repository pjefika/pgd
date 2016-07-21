package controllers.projetos;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.projetos.FaseProjeto;
import entidades.projetos.Projeto;
import models.projetos.FaseProjetoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class FaseProjetoBean {

	private FaseProjeto faseProjeto;
	
	private FaseProjeto faseProjetoModifica;
	
	@EJB
	private FaseProjetoServico faseProjetoServico;
	
	public FaseProjetoBean() {
		
		this.faseProjeto = new FaseProjeto();
		
		this.faseProjetoModifica = new FaseProjeto();
		
	}
	
	public void cadastrarFaseProjeto(Projeto projeto) {
		
		try {
			
			this.faseProjetoServico.cadastrarFaseProjeto(faseProjeto, projeto);
			JSFUtil.addInfoMessage("Fase cadastrada com sucesso.");
			this.faseProjetoModifica = new FaseProjeto();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void modificaFaseProjeto() {
		
		try {
			
			this.faseProjetoServico.modificarFaseProjeto(this.faseProjetoModifica);
			JSFUtil.addInfoMessage("Fase modificada com sucesso.");
			this.faseProjetoModifica = new FaseProjeto();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public List<FaseProjeto> listarFaseProjetoEspecifico(Projeto projeto) {
		
		return this.faseProjetoServico.listarFaseProjetoEspecifico(projeto);
		
	}

	public FaseProjeto getFaseProjeto() {
		return faseProjeto;
	}

	public void setFaseProjeto(FaseProjeto faseProjeto) {
		this.faseProjeto = faseProjeto;
	}

	public FaseProjeto getFaseProjetoModifica() {
		return faseProjetoModifica;
	}

	public void setFaseProjetoModifica(FaseProjeto faseProjetoModifica) {
		this.faseProjetoModifica = faseProjetoModifica;
	}	

}
