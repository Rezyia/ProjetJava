package Controlers;

import java.io.*;
import Views.FenetrePrincipale;


/**
 * Class to control the main application
 */
public class Principale {

	private static FenetrePrincipale f = new FenetrePrincipale();
	
	
	public static void updateContent(String[] items) {
		f.getPointings().updateList(items);;
	}
	
	/**
	 * Main program
	 * @param args	String Array for called arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		String[] items = {
				"test",
				"gaonpfzji",
				"paoeg",
				"oqinerh",
				"oqigha^rog"
				
		};
		updateContent(items);
	}
}
