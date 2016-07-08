/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.services.api.LevArtService;
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
	
	@Autowired
	private LevArtService levArtService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Leverancier> findAll() {
		return leveranciersService.findAll();
	}

	@RequestMapping(value = "/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Leverancier> findByQuery(@PathVariable String query) {
		return leveranciersService.findByNaam(query.toUpperCase());
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void saveLeverancier(@PathVariable Long id) {
		levArtService.deleteById(id);
	}

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public @ResponseBody Leverancier findAllJoinArtikels(@PathVariable Long id) {
		return leveranciersService.findByIdJoinArtikel(id);
	}

}
