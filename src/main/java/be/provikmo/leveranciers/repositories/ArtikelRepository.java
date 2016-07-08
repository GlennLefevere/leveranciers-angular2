/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import be.provikmo.leveranciers.model.Artikel;

/**
 * @author Glenn Lefevere
 *
 */
@Service
public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
}
