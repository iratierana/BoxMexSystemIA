package WebClients;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class WebServiceClients {
	
	public static boolean iaAktibatutaEdoEz(){
		String respuesta = null;
		Client client = null;	
		
		try {
			client = Client.create();
			WebResource webResource = client.resource(
					"http://localhost:8080/BoxMexWebApp/BoxMexWebApp/iaSysOnOff"					
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
			client = Client.create();
			WebResource webResource = client.resource(
					"http://localhost:8080/BoxMexWebApp/BoxMexWebApp/listaEspera"					
					);
			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
						
			respuesta = response.getEntity(String.class);
			
		}catch (UniformInterfaceException e) {
			System.out.println("La lista de espera del SERVIDOR esta vacia");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			client.destroy();			
		}	
		return respuesta;
	}
		
}
