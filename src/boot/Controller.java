package boot;

import model.Model;
import view.View;

/**
 * The Class Controller.
 */
public class Controller {

	/** The model. */
	Model model ; 
	
	/** The ui. */
	View ui ; 
	
	/**
	 * Sets the view.
	 *
	 * @param ui the new view
	 */
	public void setView(View ui) {
		this.ui = ui;
		
	}
	
	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model){
		this.model = model;
	}

	/**
	 * Start server.
	 */
	public void startServer() {
		model.startServer();
		
	}


	/**
	 * Stop server.
	 */
	public void stopServer() {
		model.stopServer();
		
	}

	
}
