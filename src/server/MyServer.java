package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The Class MyServer.
 */
public class MyServer {

	/** The port. */
	int port;
	
	/** The server. */
	ServerSocket server;

	/** The clinet handler. */
	ClinetHandler clinetHandler;
	
	/** The num of clients. */
	int numOfClients;
	
	/** The threadpool. */
	ExecutorService threadpool;

	/** The stop. */
	volatile boolean stop;

	/** The main server thread. */
	Thread mainServerThread;

	/** The clients handled. */
	int clientsHandled = 0;

	/**
	 * Instantiates a new my server.
	 *
	 * @param port the port
	 * @param clinetHandler the clinet handler
	 * @param numOfClients the num of clients
	 */
	public MyServer(int port, ClinetHandler clinetHandler, int numOfClients) {
		this.port = port;
		this.clinetHandler = clinetHandler;
		this.numOfClients = numOfClients;
	}

	/**
	 * Start.
	 */
	public void start() {
		System.out.println("starting server on port: "+port);
		try {
			server = new ServerSocket(port);
		} catch (IOException e1) {
			System.err.println(e1.toString());
		}

		try {
			server.setSoTimeout(100 * 1000);
		} catch (SocketException e1) {
			System.err.println(e1.toString());
		}
		threadpool = Executors.newFixedThreadPool(numOfClients);

		mainServerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop) {
					try {
						final Socket someClient = server.accept();
						if (someClient != null) {
							threadpool.execute(new Runnable() {
								@Override
								public void run() {
									try {
										clientsHandled++;
										System.out.println("\thandling client " + clientsHandled);
								  clinetHandler.handleClient(someClient.getInputStream(),someClient.getOutputStream());
								  someClient.close();
										System.out.println("\tdone handling client " + clientsHandled);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (SocketTimeoutException e) {
						System.out.println("no clinet connected...");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("done accepting new clients.");
			} // end of the mainServerThread task
		});

		mainServerThread.start();

	}

	/**
	 * Close.
	 *
	 * @throws Exception the exception
	 */
	public void close() throws Exception {
		stop = true;
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadpool.shutdown();
		// wait 10 seconds over and over again until all running jobs have
		// finished
		boolean allTasksCompleted = false;
		clinetHandler.exit();
		while (!(allTasksCompleted = threadpool.awaitTermination(10, TimeUnit.SECONDS)));

		System.out.println("all the tasks have finished");
        System.out.println("waiting for main server will done ..");
		mainServerThread.join();
		System.out.println("main server thread is done");
		
		server.close();
		System.out.println("server is safely closed");
	}

}
