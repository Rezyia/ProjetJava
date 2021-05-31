package Controlers;

import java.io.*;
import java.awt.*;
import javax.swing.*;

import Views.FenetrePrincipale;
import Views.WindowPointings;


/**
 * Class to control the main application
 */
public class Principale {

	private static FenetrePrincipale f = new FenetrePrincipale();
	
	/**
	 * Main program
	 * @param args	String Array for called arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		JFrame frame = f.getWindow();
		String[] items = {"test"};
		f.getWindow().updateList(items);
	}
}
