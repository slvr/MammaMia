package ca.mcgill.ecse.MammaMia.Application;

import ca.mcgill.ecse.MammaMia.model.MammaMia;
import ca.mcgill.ecse.MammaMia.View.MammaMiaPage;
import ca.mcgill.ecse.MammaMia.Persistence.PersistenceObjectStream;

public class MammaMiaApplication {
	
	private static MammaMia mammaMia;
	private static String filename = "data.mammamia";

	public static void main(String[] args){
		//start UI
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new MammaMiaPage().setVisible(true);
			}
		});
	}
	
	public static MammaMia getMammaMia() {
		if (mammaMia == null) {
			// load model
			mammaMia = load();
		}
 		return mammaMia;
	}
	
	public static void save() {
		PersistenceObjectStream.serialize(mammaMia);
	}
	
	public static MammaMia load() {
		PersistenceObjectStream.setFilename(filename);
		mammaMia = (MammaMia) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if (mammaMia == null) {
			mammaMia = MammaMia.getInstance();
		}
		else {
			mammaMia.reinitialize();
		}
		return mammaMia;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}	
}
