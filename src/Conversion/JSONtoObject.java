package Conversion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONtoObject {

	
	/**
	 * Recibe el pakete en formato json y lo pasa a un objeto de java.
	 *
	 * @param paketeStr pakete en json
	 * @return the ArrayList de integers
	 */
	public static ArrayList<Integer> conversion(final String paketeStr) {
		ArrayList<Integer> listProd = new ArrayList<Integer>();

		try {

			StringReader reader = new StringReader(paketeStr);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			JSONArray arrayProductos = (JSONArray) jsonObject.get("listaProductos");

			for (int kont = 0; kont < arrayProductos.size(); kont++) {
				JSONObject producto = (JSONObject) arrayProductos.get(kont);
				
				Long estanteria = (Long) producto.get("estanteriaId");
				
				listProd.add(estanteria.intValue());
			}


		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return listProd;
	}
}
