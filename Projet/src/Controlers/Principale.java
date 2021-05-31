package Controlers;

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;

import Models.Pointage;
import Views.FenetrePrincipale;
import Views.WindowPointings;


/**
 * Class to control the main application
 */
public class Principale {

	private static FenetrePrincipale f = new FenetrePrincipale();
	
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
	}
	
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
