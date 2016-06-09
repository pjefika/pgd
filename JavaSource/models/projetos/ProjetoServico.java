package models.projetos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.projetos.Projeto;
import entidades.projetos.TipoProjeto;

@Stateless
public class ProjetoServico {

	@PersistenceContext(unitName="vu")  
	private EntityManager entityManager;

	public ProjetoServico() {

	}

	public void cadastrarProjeto(Projeto projeto) throws Exception {

		try {

			projeto.setConclusaoPj(0);



			this.entityManager.persist(projeto);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Projeto.");

		}

	}

	public void updaterProjeto(Projeto projeto) throws Exception {

		try {

			this.entityManager.merge(projeto);

		} catch (Exception e) {

			throw new Exception("Erro ao modificar Projeto.");

		}

	}

	@SuppressWarnings("unchecked")
	public List<Projeto> listarProjeto() {

		try {

			Query query = this.entityManager.createQuery("FROM Projeto p ORDER BY p.conclusaoPj ASC");
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<Projeto>();

		}

	}

	@SuppressWarnings("unchecked")
	public List<Projeto> listarProjetoStatusEspecifico(String status) {

		try {

			Query query = this.entityManager.createQuery("FROM Projeto p WHERE p.statusFase.nome =:param1 ORDER BY p.conclusaoPj DESC");
			query.setParameter("param1", status);
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<Projeto>();

		}

	}

	@SuppressWarnings("unchecked")
	public List<Projeto> listarProjetoAndamento() {

		try {

			Query query = this.entityManager.createQuery("FROM Projeto p");
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<Projeto>();

		}

	}

	public Projeto listarProjetoEspecifico(Projeto projeto) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM Projeto p WHERE p.id =:param1");
			query.setParameter("param1", projeto.getId());
			return (Projeto) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Projeto nao encontrado...");

		}

	}

	@SuppressWarnings("unchecked")
	public List<Projeto> listarProjetosRelatorio(TipoProjeto tipoProjeto) {

		try {

			Query query = this.entityManager.createQuery("FROM Projeto p WHERE p.tipoProjeto =:param1 AND (p.statusFase.nome =:param2 OR p.statusFase.nome =:param3)");
			query.setParameter("param1", tipoProjeto);
			query.setParameter("param2", "A iniciar");
			query.setParameter("param3", "Em andamento");
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<Projeto>();

		}

	}

	@SuppressWarnings("unchecked")
	public List<Projeto> listarProjetosEvolucao(Boolean evo) {

		try {

			Query query = this.entityManager.createQuery("FROM Projeto p WHERE p.evolucao =:param1 AND (p.statusFase.nome =:param2 OR p.statusFase.nome =:param3)");
			query.setParameter("param1", evo);
			query.setParameter("param2", "A iniciar");
			query.setParameter("param3", "Em andamento");
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<Projeto>();

		}

	}

}
