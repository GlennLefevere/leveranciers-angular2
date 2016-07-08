/**
 * (c) 2016 ADMB. All rights reserved.
 */
package be.provikmo.leveranciers.test;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import be.provikmo.leveranciers.model.Leverancier;

/**
 * @author Glenn Lefevere
 *
 */
public class SimpleArtikelSerializer extends JsonSerializer<List<Leverancier>> {

	/** {@inheritDoc} */
	@Override
	public void serialize(List<Leverancier> arg0, JsonGenerator arg1, SerializerProvider arg2)
		throws IOException, JsonProcessingException {

	}

}
