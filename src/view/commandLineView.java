package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import boot.Controller;

/**
 * The Class commandLineView.
 */
public class commandLineView extends Thread implements View  {
	
	/** The cont. */
	Controller cont;
	
	/** The line. */
	String line = null;
	/** The in. */
	private BufferedReader in;

	/* (non-Javadoc)
	 * @see view.View#setController(boot.Controller)
	 */
	@Override
	public void setController(Controller c) {
		this.cont = c;
		this.in = new BufferedReader(new InputStreamReader(System.in));

	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void run() {
		System.out.println("Manual - start = to start the server ");
		System.out.println( "     stop = to stop the server");
		System.out.println( "     exit = to close the program");

		try {
			
			while (!(line = in.readLine()).equals("exit")) {
				if (line.equals("start")) {
					cont.startServer();
					System.out.println("server is running");
				} else if (line.equals("stop")) {
					cont.stopServer();
				} else
					System.out.println("invalid request");

			}
			cont.stopServer();
		System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
