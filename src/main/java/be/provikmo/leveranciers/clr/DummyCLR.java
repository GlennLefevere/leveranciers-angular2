/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import be.provikmo.leveranciers.adres.model.Gemeente;
import be.provikmo.leveranciers.adres.model.Land;
import be.provikmo.leveranciers.adres.model.Provincie;
import be.provikmo.leveranciers.adres.model.Straat;
import be.provikmo.leveranciers.adres.services.api.LandService;
import be.provikmo.leveranciers.model.Adres;
import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.repositories.ArtikelRepository;
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
	private ArtikelService artikelService;

	@Autowired
	private ArtikelRepository artikelRepository;

	@Autowired
	private LandService landService;

	/** {@inheritDoc} */
	@Override
	public void run(String... arg0) throws Exception {
		Land land = new Land();
		land.setNaam("België");

		Provincie provincie = new Provincie();
		provincie.setNaam("West-Vlaanderen");

		Gemeente gemeente = new Gemeente();
		gemeente.setPostcode(8370);
		gemeente.setNaam("Blankenberge");

		Straat straat = new Straat();
		straat.setNaam("Zeebruggelaan");

		gemeente.addStraat(straat);
		
		provincie.addGemeente(gemeente);
		
		land.addProvincie(provincie);
		
		landService.save(land);
		
		Artikel artikel = new Artikel();
		artikel.setOmschrijving("Laarzen");

		Artikel artikel2 = new Artikel();
		artikel2.setOmschrijving("Brand werende jas");

		artikelRepository.save(artikel);
		artikelRepository.save(artikel2);

		Leverancier leverancier = new Leverancier();

		leverancier.setNaam("Glenn");
		leverancier.setTelefoon("050438325");
		leverancier.setFax("050438325");
		leverancier.setEmail("glenn@email.be");
		leverancier.setWebsite("www.glenn.be");
		leverancier.setWebshop(true);
		
		Adres adres = new Adres();
		adres.setLandNaam("België");
		adres.setProvincieNaam("West-vlaanderen");
		adres.setGemeenteNaam("Blankenberge");
		adres.setPostcode(8370);
		adres.setStraat("Zeebruggelaan");
		
		leverancier.setAdres(adres);
		
		Leverancier l = leveranciersService.save(leverancier);

		artikelService.findAllJoinLeveranciers().forEach(a -> l.addArtikel(a));

		// Leverancier result =
		leveranciersService.save(leverancier);

	}
}
