package boot;

import java.io.Serializable;

/**
 * The Class ServerProperties.
 */
public class ServerProperties implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The server port. */
	private int serverPort;
	
	/** The num of clients. */
	private int numOfClients;
	
	
	/**
	 * Gets the server port.
	 *
	 * @return the server port
	 */
	public int getServerPort() {
		return serverPort;
	}
	
	/**
	 * Sets the server port.
	 *
	 * @param serverPort the new server port
	 */
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	/**
	 * Gets the num of clients.
	 *
	 * @return the num of clients
	 */
	public int getNumOfClients() {
		return numOfClients;
	}
	
	/**
	 * Sets the num of clients.
	 *
	 * @param numOfClients the new num of clients
	 */
	public void setNumOfClients(int numOfClients) {
		this.numOfClients = numOfClients;
	}

}
