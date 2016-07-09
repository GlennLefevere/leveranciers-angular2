/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.services.api;

import java.util.List;

import be.provikmo.leveranciers.model.Artikel;

/**
 * @author Glenn Lefevere
 *
 */
public interface ArtikelService {

	Artikel findById(Long id);

	List<Artikel> findAll();

	List<Artikel> findAllJoinLeveranciers();

	Artikel save(Artikel artikel);

	Artikel findByIdJoinLeveranciers(Long id);
	
	List<Artikel> findByOmschrijving(String query);
}
