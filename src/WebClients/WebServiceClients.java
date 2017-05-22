package WebClients;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebServiceClients {
	
	//ia bai edo ez
	public boolean iaAktibatutaEdoEz(){
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
	
	//paketia jaso en json	
	
	
}
