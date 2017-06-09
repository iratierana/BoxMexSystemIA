package WebClients;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class WebServiceClients {
	
	final static String FICHEROPROPIEDADES = "ipConf.properties";
	
	static String host;
	
	
	public static boolean iaAktibatutaEdoEz(){
		String respuesta = null;
		Client client = null;	
		
		try {
			cargarPropiedades();
			client = Client.create();
			WebResource webResource = client.resource(

					"http://"+host+":8080/BoxMexWebApp/BoxMexWebApp/iaSysOnOff"	
					);
			ClientResponse response = webResource.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}			
			
			respuesta = response.getEntity(String.class);
	

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			client.destroy();
		}	
		return Boolean.valueOf(respuesta);
	}
	
	public static String paketiaJasoZerbitzaritik(){
		String respuesta = null;
		Client client = null;	
		
		try {
			cargarPropiedades();
			client = Client.create();
			WebResource webResource = client.resource(
					"http://"+host+":8080/BoxMexWebApp/BoxMexWebApp/listaEspera"		
					);
			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
						
			respuesta = response.getEntity(String.class);
			
		}catch (UniformInterfaceException e) {
			System.out.println("La lista de espera de ENTRADA esta vacia");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			client.destroy();			
		}	
		return respuesta;
	}

	public static String paketiaJasoZerbitzaritikKanporaAteratzeko(){
		String respuesta = null;
		Client client = null;	
		
		try {
			cargarPropiedades();
			client = Client.create();
			WebResource webResource = client.resource(
					"http://"+host+":8080/BoxMexWebApp/BoxMexWebApp/listaSalida"
					);
			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
						
			respuesta = response.getEntity(String.class);
			
		}catch (UniformInterfaceException e) {
			System.out.println("La lista de espera de ENTRADA esta vacia");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			client.destroy();			
		}	
		return respuesta;
	}
	
	/**
	 * Cargar ip del archivo de configuracion.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void cargarPropiedades() throws FileNotFoundException, IOException{
		Properties propiedades = new Properties();
		propiedades.load(new FileInputStream(FICHEROPROPIEDADES));
		host = propiedades.getProperty("ipAplication", "127.0.0.1");
	}

}
