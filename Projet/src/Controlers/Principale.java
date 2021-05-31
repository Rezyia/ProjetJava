package Controlers;

import java.io.*;
<<<<<<< HEAD
=======
import java.net.*;
import java.awt.*;
import javax.swing.*;

import Models.Pointage;
>>>>>>> b5de0d1ad14ee53e5676efa7bf564b43a9033ce2
import Views.FenetrePrincipale;


/**
 * Class to control the main application
 */
public class Principale {

	private static FenetrePrincipale f = new FenetrePrincipale();
	
<<<<<<< HEAD
	
	public static void updateContent(String[] items) {
		f.getPointings().updateList(items);;
=======
	Socket s = null;
	ServerSocket ss = null;
	InetSocketAddress isA = null;
	
	private void setSocket() throws IOException {
        isA = new InetSocketAddress("localhost", 8085);
        ss = new ServerSocket(isA.getPort());
        s = ss.accept();
    }
	
	/**
	 * Méthode pour recevoir un objet de la classe Pointage via l'émulateur
	 * @return Un pointeur crée et envoyé par l'émulateur
	 */
	public Pointage receivePointing() {
		try{
            System.out.println("TCPServerHello launched...");
            setSocket();
            System.out.println("Hello, the server is on");
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            Pointage pt = (Pointage) in.readObject();
            //System.out.println(pt);
            in.close();
            s.close();
            ss.close();
            return pt;
        }catch(Exception e){
            System.out.println("Exception TCP : receivePointing()");
        }
		return null;
>>>>>>> b5de0d1ad14ee53e5676efa7bf564b43a9033ce2
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
