/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.provikmo.leveranciers.model.Artikel;
import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.model.rest.LeverancierRest;
import be.provikmo.leveranciers.services.api.LeverancierService;
import be.provikmo.leveranciers.utils.EntityToRestUtil;

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

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public @ResponseBody LeverancierRest findAllJoinArtikels(@PathVariable String id) {
		return EntityToRestUtil.leverancierToLeverancierRest(leveranciersService.findByIdJoinArtikel(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	@ResponseStatus(value = HttpStatus.OK)
	public void saveNewLeverancier(@RequestBody Leverancier leverancier) {
		leveranciersService.save(leverancier);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void addNewArtikel(@PathVariable String id, @RequestBody Artikel artikel) {
		leveranciersService.addArtikelToLeverancier(id, artikel);
	}

	@RequestMapping(value = "/testing", method = RequestMethod.GET)
	public @ResponseBody List<LeverancierRest> findTest() {
		return leveranciersService.findAll().stream()
			.map(l -> EntityToRestUtil.leverancierToLeverancierRest(l))
			.collect(Collectors.toList());
	}
}
