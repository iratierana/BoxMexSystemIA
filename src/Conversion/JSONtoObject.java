package Conversion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONtoObject {

	private static final String path = "fileJSON.json";
	
	public void conversion() throws IOException, ParseException{
		FileReader reader;
		reader = new FileReader(path);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		
		
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
			
			int categoriaId = (int) object.get("categoriaId");
			System.out.println(categoriaId);
			
		}
		
	}
	
}
