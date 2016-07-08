/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.services.api.LeverancierService;

/**
 * @author Glenn Lefevere
 *
 */
@RestController
@RequestMapping("/api/leveranciers")
public class LeverancierController {

	@Autowired
	private LeverancierService leveranciersService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Leverancier> findAll() {
		return leveranciersService.findAll();
	}

	@RequestMapping(value = "/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Leverancier> findByQuery(@PathVariable String query) {
		return leveranciersService.findByNaam(query.toUpperCase());
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public @ResponseBody Leverancier saveLeverancier(@PathVariable Long id, @RequestBody Artikel artikel) {

		return null;
	}

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public @ResponseBody Leverancier findAllJoinArtikels(@PathVariable Long id) {
		return leveranciersService.findByIdJoinArtikel(id);
	}

}
