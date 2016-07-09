/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.services.api.ArtikelService;

/**
 * @author Glenn Lefevere
 *
 */
@RestController
@RequestMapping("/api/artikels")
public class ArtikelController {

	@Autowired
	private ArtikelService artikelService;

	@RequestMapping(path = "/levByArt/{artId}", method = RequestMethod.GET)
	private @ResponseBody List<Leverancier> findLiveranciersByArtikelId(@PathVariable Long artId) {
		return artikelService.findByIdJoinLeveranciers(artId).getLevArts().stream().map(la -> la.getLeverancier())
			.collect(Collectors.toList());
	}
	

	@RequestMapping(value = "/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Artikel> findByQuery(@PathVariable String query) {
		return artikelService.findByOmschrijving(query.toUpperCase());
	}

}
