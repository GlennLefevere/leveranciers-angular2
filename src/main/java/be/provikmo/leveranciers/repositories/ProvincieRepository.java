/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import be.provikmo.leveranciers.model.Provincie;

/**
 * @author Glenn Lefevere
 *
 */
@Service
public interface ProvincieRepository extends JpaRepository<Provincie, Long> {

}
