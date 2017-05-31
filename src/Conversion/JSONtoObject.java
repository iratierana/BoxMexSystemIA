package Conversion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONtoObject {

	
	static String products;
	static List productos = new ArrayList();
	
	public static List conversion() throws IOException, ParseException, FileNotFoundException{
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(products);
		
		
		JSONArray libro = (JSONArray) jsonObject.get("pakete");
		
		Iterator iterator = libro.iterator();
		
		while (iterator.hasNext()) {
			JSONObject object = (JSONObject) iterator.next();
			
			int id = (int) object.get("productoId");
			System.out.println(id);
			
			
			String nombre = (String) object.get("nombre");
			System.out.println(nombre);
			
			String fechaCaducidad = (String) object.get("fechaCaducidad");
			System.out.println(fechaCaducidad);
			
			int estanteriaId = (int) object.get("estanteriaId");
			System.out.println(estanteriaId);
			productos.add(estanteriaId);
			
			int categoriaId = (int) object.get("categoriaId");
			System.out.println(categoriaId);
			
		}
		
		return productos;
		
	}
	
}
