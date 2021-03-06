package controllers.pps;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.pps.Pp;
import entidades.pps.SequenciaRelatorioPp;
import models.pps.PpServico;
import models.pps.SequenciaRelatorioPpServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class PpBean {
	
	private Pp pp;
	
	@EJB
	private PpServico ppServico;
	
	@EJB
	private SequenciaRelatorioPpServico sequenciaRelatorioPpServico;
	
	public PpBean() {
		
		this.pp = new Pp();
		
	}
	
	public void cadastrarPp() {
		
		try {
			
			this.ppServico.cadastrarPp(this.pp);
			JSFUtil.addInfoMessage("PP cadastrado com sucesso.");
			this.pp = new Pp();			
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void modificarPp() {
		
		try {
			
			this.ppServico.modificarPp(this.pp);
			JSFUtil.addInfoMessage("PP modificado com sucesso.");
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public List<Pp> listarPp() {
		
		return this.ppServico.listarPp();
		
	}
	
	public Pp listarPpEspecifico() {
		
		try {
			
			this.pp = this.ppServico.listarPpEspecifico(this.pp);
			
			return this.pp;			
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
						
		}
		
	}
	
	public List<Pp> listarPpEmTrabalho() {
		
		
		List<SequenciaRelatorioPp> sequencia = this.sequenciaRelatorioPpServico.listarSequenciaAtivo();
		
		return this.ppServico.listarPpEmTrabalho(sequencia);
		
	}
	
	public Pp getPp() {
		return pp;
	}

	public void setPp(Pp pp) {
		this.pp = pp;
	}	

}
