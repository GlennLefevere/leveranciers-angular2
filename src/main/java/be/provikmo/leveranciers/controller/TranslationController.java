/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.provikmo.leveranciers.model.Translation;
import be.provikmo.leveranciers.services.api.TranslationService;

/**
 * @author Glenn Lefevere
 *
 */
@RestController
@RequestMapping("/api/translations")
public class TranslationController {

	@Autowired
	private TranslationService translationService;

	@RequestMapping(value = "/{key}/{locale}", method = RequestMethod.GET)
	public @RestartScope Translation findTranslation(@PathVariable String key, @PathVariable String locale) {
		return translationService.findTranslation(key, locale);
	}
}
