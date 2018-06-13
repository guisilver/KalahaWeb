package nl.gui.kalahaweb.model.rest.resource;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import nl.gui.kalahaweb.model.domain.Kalaha;

/**
 * This class was created following the REST architecture. It provides the data
 * in JSON format.
 * 
 * @author Guilherme Silveira
 *
 */
@Path("kalaha")
public class KalahaResource {

	@GET
	public String getKalaha() {
		return Kalaha.getKalahaJson();
	}

	@POST
	@Path("/{pit}")
	public void playKalaha(@PathParam("pit") Integer pit) throws IOException {
		try {
			Kalaha.play(pit);
		} catch (IllegalArgumentException e) {
			//
		}
	}

	@PUT
	public void newGame() throws IOException {
		Kalaha.newGame();
	}
}
