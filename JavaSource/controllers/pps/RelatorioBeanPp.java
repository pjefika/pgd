package controllers.pps;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import entidades.pps.FasePp;
import entidades.pps.SequenciaRelatorioPp;
import entidades.pps.StatusFasePp;
import models.pps.FasePpServico;
import models.pps.InformacaoFaseServico;
import models.pps.PpServico;
import models.pps.SequenciaRelatorioPpServico;
import models.pps.StatusFasePpServico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RelatorioBeanPp implements Serializable{

	private PieChartModel graficoFase;

	private PieChartModel graficoStatus;

	@EJB
	private StatusFasePpServico statusFasePpServico;

	@EJB
	private PpServico ppServico;

	@EJB
	private FasePpServico fasePpServico;

	@EJB
	private InformacaoFaseServico informacaoFaseServico;
	
	@EJB
	private SequenciaRelatorioPpServico sequenciaRelatorioPpServico;

	public RelatorioBeanPp() {


	}

	@PostConstruct
	public void init() {

		criarGraficos();

	}

	public void criarGraficos() {

		this.criaGraficoFase();
		this.criaGraficoStatus();
	}

	public void criaGraficoFase() {

		this.graficoFase = new PieChartModel();

		List<FasePp> fases;

		fases = this.fasePpServico.listarFasePpAtivo();
		
		List<SequenciaRelatorioPp> sequencia;		
			
		sequencia = this.sequenciaRelatorioPpServico.listarSequenciaAtivo();		
		
		for (FasePp fasePp : fases) {

			Integer qnde = this.informacaoFaseServico.listarInformacaoFaseEspecificoFase(fasePp, sequencia).size();
			this.graficoFase.set(fasePp.getNome() + ": " + qnde, qnde);			

		}

		this.graficoFase.setTitle("Grafico Fase");
		this.graficoFase.setLegendPosition("e");
		this.graficoFase.setShowDataLabels(true);
		this.graficoFase.setSeriesColors("003245, 004356, 005466, 006476, 007486, 008597, 0095A7, 005B7, 0086C7, 00C6D7");

	}

	public void criaGraficoStatus() {

		this.graficoStatus = new PieChartModel();

		List<StatusFasePp> statusFase;

		statusFase = this.statusFasePpServico.listarStatusFasePpAtivo();

		for (StatusFasePp statusFasePp : statusFase) {

			Integer qnde = this.ppServico.listarPpPorStatusEspecificos(statusFasePp).size();

			this.graficoStatus.set(statusFasePp.getNome() + ": " + qnde, qnde);

		}

		this.graficoStatus.setTitle("Grafico Status");
		this.graficoStatus.setLegendPosition("w");
		this.graficoStatus.setShowDataLabels(true);
		this.graficoStatus.setSeriesColors("003245, 004356, 005466, 006476, 007486, 008597, 0095A7, 005B7, 0086C7, 00C6D7");

	}

	public PieChartModel getGraficoFase() {
		return graficoFase;
	}

	public void setGraficoFase(PieChartModel graficoFase) {
		this.graficoFase = graficoFase;
	}

	public PieChartModel getGraficoStatus() {
		return graficoStatus;
	}

	public void setGraficoStatus(PieChartModel graficoStatus) {
		this.graficoStatus = graficoStatus;
	}	

}
