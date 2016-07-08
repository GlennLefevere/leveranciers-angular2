package be.provikmo.leveranciers.services.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import be.provikmo.leveranciers.model.LevArt;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.model.Leverancier_;
import be.provikmo.leveranciers.repositories.LevArtRepository;
import be.provikmo.leveranciers.repositories.LeveranciersRepository;
import be.provikmo.leveranciers.services.api.LevArtService;

@Repository
@Transactional(readOnly = true)
public class LevArtServiceImpl implements LevArtService{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LevArtRepository levArtRepository;

	@Override
	public void deleteById(Long id) {
		LevArt la = levArtRepository.findOne(id);
		
		CriteriaBuilder cb = em.getCriteriaBuilder(); //em is EntityManager
		CriteriaQuery<Leverancier> cq = cb.createQuery(Leverancier.class).distinct(true);
		Root<Leverancier> root = cq.from(Leverancier.class);

		root.fetch(Leverancier_.levArts, JoinType.INNER);
		
		Expression<List<LevArt>> levArts = root.get(Leverancier_.levArts);
		
		Predicate p = cb.isMember(la, levArts);
		
		cq.where(p);
		
		List<Leverancier> results = em.createQuery(cq).getResultList();
		
		for (Leverancier leverancier : results) {
			leverancier.getLevArts().remove(la);
			em.merge(leverancier);
			em.flush();
		}
		
	}
}
