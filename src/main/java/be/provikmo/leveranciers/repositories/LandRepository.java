/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import be.provikmo.leveranciers.model.Land;

/**
 * @author Glenn Lefevere
 *
 */
@Service
public interface LandRepository extends JpaRepository<Land, Long> {

}
