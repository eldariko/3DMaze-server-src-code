package model;


import java.util.Date;
import java.util.HashMap;


import algorithms.mazeGenerators.Maze3d;

import algorithms.search.Solution;
import boot.Controller;
import boot.ServerProperties;
import server.MyClientHandler;
import server.MyServer;

/**
 * The Class ServerModel.
 */
public class ServerModel implements Model{
	
	
	/** The Constant PORT. */
	public final static int PORT = 6969;
	
	/** The server. */
	private MyServer server ;
	
	/** The maze name to maze file size. */
	private HashMap<String, Long> mazeNameToMazeFileSize;

	/** The file name to maze size. */
	private HashMap<String, Integer> fileNameToMazeSize;

	/** The solution table. */
	private HashMap<Maze3d, Solution> solutionTable;

	/** The save mazes and last save. */
	private HashMap<String, Date> saveMazesAndLastSave;
	
	/** The solution manager. */
	private HashMapManager<Maze3d, Solution> solutionManager;

	/** The maze table manager. */
	private HashMapManager<String, Maze3d> mazeTableManager;
	
	/** The save mazes and lase save manager. */
	private HashMapManager<String, Date> saveMazesAndLaseSaveManager;
	
	/** The current solution. */
	private Solution currentSolution = null;
	
	/** The sp. */
	private ServerProperties sp;
	
	/**
	 * Instantiates a new server model.
	 */
	public ServerModel(){

	}
	

	
	/* (non-Javadoc)
	 * @see model.Model#getSolution(java.lang.String)
	 */
	@Override
	public Solution getSolution(String mazeName) {
		return currentSolution;
		
		
	}
	
	/* (non-Javadoc)
	 * @see model.Model#startServer()
	 */
	@Override
	public void startServer() {
		server = new MyServer(sp.getServerPort(), new MyClientHandler(), sp.getNumOfClients());
		server.start();
		
	}

	/* (non-Javadoc)
	 * @see model.Model#stopServer()
	 */
	@Override
	public void stopServer() {
		try {
			if(server!=null)
				server.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	/* (non-Javadoc)
	 * @see model.Model#setController(boot.Controller)
	 */
	@Override
	public void setController(Controller c) {
	}
	
	/**
	 * Gets the maze name to maze file size.
	 *
	 * @return the maze name to maze file size
	 */
	public HashMap<String, Long> getMazeNameToMazeFileSize() {
		return mazeNameToMazeFileSize;
	}



	/**
	 * Sets the maze name to maze file size.
	 *
	 * @param mazeNameToMazeFileSize the maze name to maze file size
	 */
	public  void setMazeNameToMazeFileSize(HashMap<String, Long> mazeNameToMazeFileSize) {
		this.mazeNameToMazeFileSize = mazeNameToMazeFileSize;
	}



	/**
	 * Gets the file name to maze size.
	 *
	 * @return the file name to maze size
	 */
	public HashMap<String, Integer> getFileNameToMazeSize() {
		return fileNameToMazeSize;
	}



	/**
	 * Sets the file name to maze size.
	 *
	 * @param fileNameToMazeSize the file name to maze size
	 */
	public void setFileNameToMazeSize(HashMap<String, Integer> fileNameToMazeSize) {
		this.fileNameToMazeSize = fileNameToMazeSize;
	}



	/* (non-Javadoc)
	 * @see model.Model#getSolutionTable()
	 */
	public HashMap<Maze3d, Solution> getSolutionTable() {
		return solutionTable;
	}



	/**
	 * Sets the solution table.
	 *
	 * @param solutionTable the solution table
	 */
	public void setSolutionTable(HashMap<Maze3d, Solution> solutionTable) {
		this.solutionTable = solutionTable;
	}



	/**
	 * Gets the save mazes and last save.
	 *
	 * @return the save mazes and last save
	 */
	public HashMap<String, Date> getSaveMazesAndLastSave() {
		return saveMazesAndLastSave;
	}



	/**
	 * Sets the save mazes and last save.
	 *
	 * @param saveMazesAndLastSave the save mazes and last save
	 */
	public void setSaveMazesAndLastSave(HashMap<String, Date> saveMazesAndLastSave) {
		this.saveMazesAndLastSave = saveMazesAndLastSave;
	}



	/**
	 * Gets the solution manager.
	 *
	 * @return the solution manager
	 */
	public HashMapManager<Maze3d, Solution> getSolutionManager() {
		return solutionManager;
	}



	/**
	 * Sets the solution manager.
	 *
	 * @param solutionManager the solution manager
	 */
	public void setSolutionManager(HashMapManager<Maze3d, Solution> solutionManager) {
		this.solutionManager = solutionManager;
	}



	/**
	 * Gets the maze table manager.
	 *
	 * @return the maze table manager
	 */
	public HashMapManager<String, Maze3d> getMazeTableManager() {
		return mazeTableManager;
	}



	/**
	 * Sets the maze table manager.
	 *
	 * @param mazeTableManager the maze table manager
	 */
	public void setMazeTableManager(HashMapManager<String, Maze3d> mazeTableManager) {
		this.mazeTableManager = mazeTableManager;
	}



	/**
	 * Gets the save mazes and lase save manager.
	 *
	 * @return the save mazes and lase save manager
	 */
	public HashMapManager<String, Date> getSaveMazesAndLaseSaveManager() {
		return saveMazesAndLaseSaveManager;
	}



	/**
	 * Sets the save mazes and lase save manager.
	 *
	 * @param saveMazesAndLaseSaveManager the save mazes and lase save manager
	 */
	public void setSaveMazesAndLaseSaveManager(HashMapManager<String, Date> saveMazesAndLaseSaveManager) {
		this.saveMazesAndLaseSaveManager = saveMazesAndLaseSaveManager;
	}



	/**
	 * Gets the current solution.
	 *
	 * @return the current solution
	 */
	public Solution getCurrentSolution() {
		return currentSolution;
	}



	/**
	 * Sets the current solution.
	 *
	 * @param currentSolution the new current solution
	 */
	public void setCurrentSolution(Solution currentSolution) {
		this.currentSolution = currentSolution;
	}



	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public static int getPort() {
		return PORT;
	}



	/* (non-Javadoc)
	 * @see model.Model#setProperties(boot.ServerProperties)
	 */
	@Override
	public void setProperties(ServerProperties sp) {
		this.sp=sp;
		
	}



	/* (non-Javadoc)
	 * @see model.Model#getProperties()
	 */
	@Override
	public ServerProperties getProperties() {
		return sp;
	}


}
