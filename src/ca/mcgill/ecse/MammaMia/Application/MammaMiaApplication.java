package ca.mcgill.ecse.MammaMia.Application;

import ca.mcgill.ecse.MammaMia.Persistence.PersistenceMammaMia;
import ca.mcgill.ecse.MammaMia.View.MammaMiaPage;

public class MammaMiaApplication {

	public static void main(String[] args){
		//load model
		PersistenceMammaMia.loadMammaMiaModel();
		
		//start UI
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new MammaMiaPage().setVisible(true);
			}
		});
	}
	
}
