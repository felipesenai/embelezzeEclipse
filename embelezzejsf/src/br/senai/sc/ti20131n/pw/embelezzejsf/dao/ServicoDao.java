package br.senai.sc.ti20131n.pw.embelezzejsf.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Servico;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

public class ServicoDao {

	private EntityManager entityManager;

	public ServicoDao() {
		entityManager = Util.getEntityManager();
	}

	public boolean salvar(Servico servico) {
		try {
			entityManager.merge(servico);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Servico buscarPorId(Long ID) {
		return entityManager.find(Servico.class, ID);
	}

	public void excluirServicoPorId(Long l) {
		Servico servico = entityManager.getReference(Servico.class, l);
		entityManager.remove(servico);
	}

	public void atualizar(Servico servico) {
		entityManager.merge(servico);
	}

	public List<Servico> listarServico() {

		Query query = entityManager.createQuery("From Servico", Servico.class);
		return query.getResultList();
	}

}