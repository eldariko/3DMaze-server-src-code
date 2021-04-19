package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The Interface ClinetHandler.
 */
public interface ClinetHandler {
	
	/**
	 * Handle client.
	 *
	 * @param inFromClient the in from client
	 * @param outToClient the out to client
	 * @throws IOException 
	 */
	void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException;
	
	void exit() throws IOException;
}
