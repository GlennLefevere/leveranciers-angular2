/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.model.Leverancier_;
import be.provikmo.leveranciers.repositories.LeveranciersRepository;
import be.provikmo.leveranciers.services.api.LeverancierService;

/**
 * @author Glenn Lefevere
 *
 */
@Repository
@Transactional(readOnly = true)
public class LeverancierServiceImpl implements LeverancierService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private LeveranciersRepository leveranciersRepository;

	public List<Leverancier> findAll() {
		return leveranciersRepository.findAll();
	}

	public Leverancier findById(Long id) {
		return leveranciersRepository.findOne(id);
	}

	public Leverancier save(Leverancier leverancier) {
		return leveranciersRepository.save(leverancier);
	}

	public Leverancier findByIdJoinArtikel(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Leverancier> cq = cb.createQuery(Leverancier.class).distinct(true);
		Root<Leverancier> root = cq.from(Leverancier.class);

		root.fetch(Leverancier_.levArts, JoinType.INNER);

		cq.where(cb.equal(root.get(Leverancier_.id), id));

		TypedQuery<Leverancier> query = em.createQuery(cq);

		List<Leverancier> results = query.getResultList();

		Leverancier result = null;

		if (!CollectionUtils.isEmpty(results)) {
			result = results.get(0);
		}

		return result;
	}

	public List<Leverancier> findByNaam(String query) {
		return leveranciersRepository.findByNaam(query);
	}

}
