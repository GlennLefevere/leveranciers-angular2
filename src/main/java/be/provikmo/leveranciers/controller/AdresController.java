package be.provikmo.leveranciers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import be.provikmo.leveranciers.adres.model.Land;
import be.provikmo.leveranciers.adres.services.api.LandService;

@RestController
@RequestMapping("/api/adres")
public class AdresController {

	@Autowired
	private LandService landService;

	@RequestMapping(value = "/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Land> findByQuery(@PathVariable String query) {
		return landService.findByQuery(query.toUpperCase());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Land> findAll() {
		return landService.findAll();
	}
}
