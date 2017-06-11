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

/**
 * The Class WebServiceClients.
 */
public class WebServiceClients {

	/** The Constant FICHEROPROPIEDADES. */
	static final String FICHEROPROPIEDADES = "ipConf.properties";

	/** The host. */
	static String host;

	/** The Constant ERROR. */
	private static final int ERROR = 200;

	/**
	 * Ia aktibatuta edo ez.
	 *
	 * @return true, if successful
	 */
	public static boolean iaAktibatutaEdoEz() {
		String respuesta = null;
		Client client = null;

		try {
			cargarPropiedades();
			client = Client.create();
			WebResource webResource = client.resource("http://" + host + ":8080/BoxMexWebApp/BoxMexWebApp/iaSysOnOff");
			ClientResponse response = webResource.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);

			if (response.getStatus() != ERROR) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			respuesta = response.getEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.destroy();
		}
		return Boolean.valueOf(respuesta);
	}

	/**
	 * Paketia jaso zerbitzaritik.
	 *
	 * @return the string
	 */
	public static String paketiaJasoZerbitzaritik() {
		String respuesta = null;
		Client client = null;

		try {
			cargarPropiedades();
			client = Client.create();
			WebResource webResource = client.resource("http://" + host + ":8080/BoxMexWebApp/BoxMexWebApp/listaEspera");
			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

			respuesta = response.getEntity(String.class);

		} catch (UniformInterfaceException e) {
			System.out.println("La lista de espera de ENTRADA esta vacia");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.destroy();
		}
		return respuesta;
	}

	/**
	 * Paketia jaso zerbitzaritik kanpora ateratzeko.
	 *
	 * @return the string
	 */
	public static String paketiaJasoZerbitzaritikKanporaAteratzeko() {
		String respuesta = null;
		Client client = null;

		try {
			cargarPropiedades();
			client = Client.create();
			WebResource webResource = client.resource("http://" + host + ":8080/BoxMexWebApp/BoxMexWebApp/listaSalida");
			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

			respuesta = response.getEntity(String.class);

		} catch (UniformInterfaceException e) {
			System.out.println("La lista de espera de ENTRADA esta vacia");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.destroy();
		}
		return respuesta;
	}

	/**
	 * Cargar ip del archivo de configuracion.
	 *
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void cargarPropiedades() throws FileNotFoundException, IOException {
		Properties propiedades = new Properties();
		propiedades.load(new FileInputStream(FICHEROPROPIEDADES));
		host = propiedades.getProperty("ipAplication", "127.0.0.1");
	}

}
