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

import be.provikmo.leveranciers.model.Leverancier;
import be.provikmo.leveranciers.repositories.LeveranciersRepository;

/**
 * @author Glenn Lefevere
 *
 */
@RestController
@RequestMapping("/api/artikels")
public class ArtikelController {

	@Autowired
	private LeveranciersRepository leveranciersService;

	@RequestMapping(path = "/levByArt/{artId}", method = RequestMethod.GET)
	private @ResponseBody List<Leverancier> findLiveranciersByArtikelId(@PathVariable Long artId) {
		// return leveranciersService.findByArtikelId(artId);
		return null;
	}

}
