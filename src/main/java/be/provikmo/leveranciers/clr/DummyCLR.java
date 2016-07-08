/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Gemeente;
import be.provikmo.leveranciers.model.Land;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.model.Provincie;
import be.provikmo.leveranciers.repositories.ArtikelRepository;
import be.provikmo.leveranciers.repositories.GemeenteRepository;
import be.provikmo.leveranciers.repositories.LandRepository;
import be.provikmo.leveranciers.repositories.ProvincieRepository;
import be.provikmo.leveranciers.services.api.ArtikelService;
import be.provikmo.leveranciers.services.api.LeverancierService;

/**
 * @author Glenn Lefevere
 *
 */
@Component
public class DummyCLR implements CommandLineRunner {

	@Autowired
	private LeverancierService leveranciersService;

	@Autowired
	private GemeenteRepository gemeenteService;

	@Autowired
	private ArtikelService artikelService;

	@Autowired
	private ArtikelRepository artikelRepository;

	@Autowired
	private LandRepository landService;

	@Autowired
	private ProvincieRepository provincieService;

	/** {@inheritDoc} */
	@Override
	public void run(String... arg0) throws Exception {
		Land land = new Land();
		land.setNaam("België");

		landService.save(land);

		Gemeente gemeente = new Gemeente();
		gemeente.setPostcode(8370);
		gemeente.setGemeente("Blankenberge");

		gemeenteService.save(gemeente);

		Provincie provincie = new Provincie();
		provincie.setNaam("West-Vlaanderen");
		provincie.setTaal(0);

		provincieService.save(provincie);

		Artikel artikel = new Artikel();
		artikel.setOmschrijving("Laarzen");

		Artikel artikel2 = new Artikel();
		artikel2.setOmschrijving("Brand werende jas");

		artikelRepository.save(artikel);
		artikelRepository.save(artikel2);

		Leverancier leverancier = new Leverancier();

		leverancier.setNaam("Glenn");
		leverancier.setStraat("Eenstraat");
		leverancier.setTelefoon("050438325");
		leverancier.setFax("050438325");
		leverancier.setEmail("glenn@email.be");
		leverancier.setWebsite("www.glenn.be");
		leverancier.setWebshop(true);

		leverancier.setLand(landService.findAll().get(0));
		leverancier.setGemeente(gemeenteService.findAll().get(0));
		leverancier.setProvincie(provincieService.findAll().get(0));

		Leverancier l = leveranciersService.save(leverancier);

		artikelService.findAllJoinLeveranciers().forEach(a -> l.addArtikel(a));

		// Leverancier result =
		leveranciersService.save(leverancier);

	}
}
