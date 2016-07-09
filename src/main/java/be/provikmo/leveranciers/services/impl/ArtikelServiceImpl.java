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

import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Artikel_;
import be.provikmo.leveranciers.repositories.ArtikelRepository;
import be.provikmo.leveranciers.services.api.ArtikelService;

/**
 * @author Glenn Lefevere
 *
 */
@Repository
@Transactional(readOnly = true)
public class ArtikelServiceImpl implements ArtikelService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ArtikelRepository artikelRepository;

	public Artikel findById(Long id) {
		return artikelRepository.findOne(id);
	}

	public List<Artikel> findAll() {
		return artikelRepository.findAll();
	}

	public List<Artikel> findAllJoinLeveranciers() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Artikel> cq = cb.createQuery(Artikel.class).distinct(true);
		Root<Artikel> root = cq.from(Artikel.class);

		root.fetch(Artikel_.levArts, JoinType.LEFT);

		cq.select(root);

		TypedQuery<Artikel> query = em.createQuery(cq);
		List<Artikel> results = query.getResultList();

		return results;
	}

	public Artikel findByIdJoinLeveranciers(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Artikel> cq = cb.createQuery(Artikel.class).distinct(true);
		Root<Artikel> root = cq.from(Artikel.class);

		cq.where(cb.equal(root.get(Artikel_.id), id));

		TypedQuery<Artikel> query = em.createQuery(cq);

		List<Artikel> results = query.getResultList();

		Artikel result = null;

		if (!CollectionUtils.isEmpty(results)) {
			result = results.get(0);
		}
		return result;
	}

	public Artikel save(Artikel artikel) {
		return artikelRepository.save(artikel);
	}

	@Override
	public List<Artikel> findByOmschrijving(String query) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Artikel> cq = cb.createQuery(Artikel.class).distinct(true);
		Root<Artikel> root = cq.from(Artikel.class);
		
		cq.where(cb.like(cb.upper(root.get(Artikel_.omschrijving)), "%" + query + "%"));
		
		return em.createQuery(cq).getResultList();
	}
}
