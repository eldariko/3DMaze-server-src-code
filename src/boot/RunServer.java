package boot;

import model.Model;
import model.ServerModel;
import view.PropertiesWindow;
import view.commandLineView;

/**
 * The Class RunServer.
 */
public class RunServer {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		PropertiesWindow gui=new PropertiesWindow();
		gui.open();		
	}
	/**
	 * Run.
	 *
	 * @param sp the sp
	 */
	public void run(ServerProperties sp) {
		Controller c = new Controller();
		Model model = new ServerModel();
		model.setProperties(sp);
		commandLineView cli = new commandLineView();
		c.setModel(model);
		c.setView(cli);
		cli.setController(c);
		model.setController(c);
		cli.start();
	}
	
	
}
