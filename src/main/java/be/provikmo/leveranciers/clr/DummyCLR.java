/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.clr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Gemeente;
import be.provikmo.leveranciers.model.Land;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.model.Provincie;
import be.provikmo.leveranciers.model.Translation;
import be.provikmo.leveranciers.repositories.ArtikelRepository;
import be.provikmo.leveranciers.services.api.ArtikelService;
import be.provikmo.leveranciers.services.api.GemeenteService;
import be.provikmo.leveranciers.services.api.LandService;
import be.provikmo.leveranciers.services.api.LeverancierService;
import be.provikmo.leveranciers.services.api.ProvincieService;
import be.provikmo.leveranciers.services.api.TranslationService;

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
	private GemeenteService gemeenteService;

	@Autowired
	private LandService landService;

	@Autowired
	private ProvincieService provincieService;

	@Autowired
	private TranslationService translationService;

	/** {@inheritDoc} */
	@Override
	public void run(String... arg0) throws Exception {
		Land land = new Land();
		land.setNaam("België");

		landService.saveOrUpdate(land);

		Provincie provincie = new Provincie();
		provincie.setNaam("West-Vlaanderen");

		provincieService.saveOrUpdate(provincie);

		Gemeente gemeente = new Gemeente();
		gemeente.setPostcode(8370L);
		gemeente.setGemeente("Blankenberge");

		gemeenteService.saveOrUpdate(gemeente);

		Artikel artikel = new Artikel();
		artikel.setOmschrijving("ART_LAARZEN_KEY");

		Artikel artikel2 = new Artikel();
		artikel2.setOmschrijving("ART_JAS_KEY");

		artikelRepository.save(artikel);
		artikelRepository.save(artikel2);

		Leverancier leverancier = new Leverancier();

		leverancier.setNaam("Glenn");
		leverancier.setTelefoon("050438325");
		leverancier.setFax("050438325");
		leverancier.setEmail("glenn@email.be");
		leverancier.setWebsite("www.glenn.be");
		leverancier.setWebshop(true);
		leverancier.setLand(land);
		leverancier.setGemeente(gemeente);
		leverancier.setProvincie(provincie);

		Translation translation1 = new Translation();
		translation1.setLocale("nl_BE");
		translation1.setTekst("Laarzen");
		translation1.setWaarde("ART_LAARZEN_KEY");

		Translation translation2 = new Translation();
		translation2.setLocale("nl_BE");
		translation2.setTekst("Brand werende jas");
		translation2.setWaarde("ART_JAS_KEY");

		translationService.saveTransLation(translation1);
		translationService.saveTransLation(translation2);

		Leverancier result = leveranciersService.save(leverancier);

		List<Artikel> artikels = artikelService.findAllJoinLeveranciers();

		artikels.forEach(a -> leveranciersService.addArtikelToLeverancier(result.getId(), a));

		System.out.println(leveranciersService.findAll().size());

	}
}
