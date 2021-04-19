package model;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import boot.Controller;
import boot.ServerProperties;

/**
 * The Interface Model.
 */
public interface Model {

	/**
	 * Start server.
	 */
	public void startServer();

	/**
	 * Stop server.
	 */
	public void stopServer();

	/**
	 * Gets the solution table.
	 *
	 * @return the solution table
	 */
	public HashMap<Maze3d, Solution> getSolutionTable();
	
	/**
	 * Sets the controller.
	 *
	 * @param c the new controller
	 */
	public void setController(Controller c);

	/**
	 * Gets the solution.
	 *
	 * @param mazeName the maze name
	 * @return the solution
	 */
	Solution getSolution(String mazeName);
	
	/**
	 * Sets the properties.
	 *
	 * @param sp the new properties
	 */
	public void setProperties(ServerProperties sp);
	
	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public ServerProperties getProperties();

}
