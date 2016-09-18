/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.services.api.ArtikelService;
import be.provikmo.leveranciers.services.api.LeverancierService;

/**
 * @author Glenn Lefevere
 *
 */
@RestController
@RequestMapping("/api/artikels")
public class ArtikelController {

	@Autowired
	private ArtikelService artikelService;

	@Autowired
	private LeverancierService leverancierService;

	@RequestMapping(path = "/levByArt/{artId}", method = RequestMethod.GET)
	public @ResponseBody List<Leverancier> findLiveranciersByArtikelId(@PathVariable String artId) {
		return artikelService.findByIdJoinLeveranciers(artId).getLeveranciers();
	}

	@RequestMapping(value = "/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Artikel> findByQuery(@PathVariable String query) {
		return artikelService.findByOmschrijving(query.toUpperCase());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Artikel> findAll() {
		return artikelService.findAll();
	}

	@RequestMapping(path = "/artByLev/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Artikel> findByLeverancierId(@PathVariable String id) {
		return leverancierService.findByIdJoinArtikel(id).getArtikels();
	}

}
