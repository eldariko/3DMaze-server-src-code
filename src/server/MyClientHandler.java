package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.AstarSearcher;
import algorithms.search.BFSSearcher;
import algorithms.search.MazeEucledeanDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import boot.Problem;
import model.HashMapManager;

/**
 * The Class MyClientHandler.
 */
public class MyClientHandler implements ClinetHandler{

//	/** The model. */
//	Model model ;
	
	/** The solution. */
	Solution solution;
	/** The solution table. */
	private HashMap<Maze3d, Solution> solutionTable;
	
	/** The maze table. */
	//private HashMap<String, Maze3d> mazeTable;

	private HashMapManager<Maze3d, Solution> solutionManager;

	//private HashMapManager<String, Maze3d> mazeTableManager;
	
	/* (non-Javadoc)
	 * @see server.ClinetHandler#handleClient(java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException {

		this.solutionManager = new HashMapManager<Maze3d, Solution>("solutionManagerFile", solutionTable);
		this.solutionTable = solutionManager.loadHashMapFromFile();	
		ObjectInputStream ois = null;
			ObjectOutputStream oos = null;
			Problem prob = null;
			try {

				ois = new ObjectInputStream(inFromClient);
				prob = (Problem) (ois.readObject());
				 solution = this.solveMaze(prob);
				
				oos = new ObjectOutputStream(outToClient);
				oos.writeObject(solution);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("class no found");

				e.printStackTrace();
			} finally {

				try {
					oos.close();
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	/**
	 * Solve maze.
	 *
	 * @param prob the prob
	 * @return the solution
	 */
	private Solution solveMaze (Problem prob){
		Maze3d maze = prob.getMaze();
		String algoName = prob.getAlgoName();
			if (!(solutionTable.containsKey(maze))) {			
		switch (algoName) {
					case "bfs":
						solutionTable.put(maze, new BFSSearcher().search(new SearchableMaze(maze)));
						return solutionTable.get(maze);
					case "astar e":
						solutionTable.put(maze, new AstarSearcher(new MazeEucledeanDistance()).search(new SearchableMaze(maze)));
						return solutionTable.get(maze);
					case "astar m":
						solutionTable.put(maze, new AstarSearcher(new MazeManhattanDistance()).search(new SearchableMaze(maze)));
						return solutionTable.get(maze);
					default:	
						System.out.println("error in algorithm");
				}
			}	
			return solutionTable.get(maze);	
		}
	
	/* (non-Javadoc)
	 * @see model.Model#getSolutionTable()
	 */
	public HashMap<Maze3d, Solution> getSolutionTable() {
		return solutionTable;
	}

	public void setSolutionTable(HashMap<Maze3d, Solution> solutionTable) {
		this.solutionTable = solutionTable;
	}



	@Override
	public void exit() throws IOException {
		if(solutionManager!=null){
		solutionManager.setHashMap(solutionTable);
		solutionManager.saveHashMap();
		}
	}
	
}
