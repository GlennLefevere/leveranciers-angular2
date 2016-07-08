package be.provikmo.leveranciers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import be.provikmo.leveranciers.model.LevArt;

/**
 * @author Glenn Lefevere
 *
 */
@Service
public interface LevArtRepository extends JpaRepository<LevArt, Long> {

}
