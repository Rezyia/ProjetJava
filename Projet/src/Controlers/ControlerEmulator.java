package Controlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import Models.Pointing;

public class ControlerEmulator extends ControlerNetwork{
	
	private ArrayList<Pointing> pointings;
	
	public void addPointing(Pointing p) {
		pointings.add(p);
	}
	
	public void rmPointing(Pointing p) {
		pointings.remove(p);
	}
    
    private void setSocket() throws IOException{
    	isA = new InetSocketAddress(address, port);
        s = new Socket(isA.getHostName(), isA.getPort());
    }
    
    /**
     * Méthode pour créer et envoyer à l'application principale des pointages
     * @param idEmp Identifiant de l'employé qui crée le pointage
     * @param time Date et heure à laquel le poinatge a été fait
     * @throws IOException si les sockets sont pas/mal initialisés
     */
    public void sendPointing(int idEmp, LocalDateTime time) {
    	try{
            System.out.println("TCPClientObjet launched...");
            setSocket();
            System.out.println("Hello, the client is connected");
            Pointing pt = new Pointing(idEmp, Toolbox.roundToNearestQuarter(time));
            addPointing(pt);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            Iterator<Pointing> i = pointings.iterator();
            while(s.isConnected() && i.hasNext()) {
            	Pointing p = i.next();
            	out.writeObject(p);
            	out.flush();
            	rmPointing(p);
            }
            out.close();
            s.close();
        }catch(IOException e){
            System.out.println("IOException TCPClientObjet");
        }
    }
}
