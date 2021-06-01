package Controlers;

import java.io.*;
import java.net.*;
import java.util.*;

import Models.*;
import Views.FenetrePrincipale;


/**
 * Class to control the main application
 */
public class Principale {

	private static FenetrePrincipale f = new FenetrePrincipale();
	
	private Socket s = null;
	private ServerSocket ss = null;
	private InetSocketAddress isA = null;
	
	private ArrayList<Employe> employees = new ArrayList<Employe>();
	
	public static void updateContent(String[] items) {
		f.getPointings().updateList(items);
	}
	
	private void setSocket() throws IOException {
        isA = new InetSocketAddress("localhost", 8085);
        ss = new ServerSocket(isA.getPort());
        s = ss.accept();
    }
	
	//-------------------------------------------------------------M�thode pour employees
	
	public void addEmploye(Employe e) {
		employees.add(e);
	}
	
	public void rmEmploye(Employe e) {
		employees.remove(e);
	}
	
	public boolean isEmployeExist(int idEmp) {
		boolean find = false;
		Iterator<Employe> i = employees.iterator();
		while(i.hasNext()) {
			if(i.next().getId() == idEmp) {
				find = true;
			}
		}
		return find;
	}
	
	/**
	 * M�thode pour recevoir un objet de la classe Pointage via l'�mulateur
	 * @return Un pointeur cr�e et envoy� par l'�mulateur
	 * @throws IOException si les sockets sont pas/mal initialis�s
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
