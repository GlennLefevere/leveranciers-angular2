/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import be.provikmo.leveranciers.model.Leverancier;

/**
 * @author Glenn Lefevere
 *
 */
@Service
public interface LeveranciersRepository extends JpaRepository<Leverancier, Long> {

	@Query("select l from Leverancier l where UPPER(l.naam) like %?1%")
		List<Leverancier> findByNaam(String naam);
}
