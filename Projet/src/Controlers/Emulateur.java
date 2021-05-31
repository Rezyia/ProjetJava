package Controlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.time.LocalDateTime;

import Models.Pointage;

public class Emulateur {

	Socket s = null;
    InetSocketAddress isA = null;
    
    public LocalDateTime roundToNearestQuarter(LocalDateTime t) {
		int diff = t.getMinute()%15;
		LocalDateTime tqh = null;
		if(diff < 8) {
			tqh = t.minusMinutes(diff);
		}else if(diff >= 8) {
			tqh = t.plusMinutes(15-diff);
		}
		tqh = tqh.minusSeconds(tqh.getSecond());
		tqh = tqh.minusNanos(tqh.getNano());
		return tqh;
	}
    
    private void setSocket() throws IOException{
    	isA = new InetSocketAddress("localhost", 8085);
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
            Pointage pt = new Pointage(idEmp, roundToNearestQuarter(time));
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(pt);
            out.flush();
            out.close();
            s.close();
        }catch(IOException e){
            System.out.println("IOException TCPClientObjet");
        }
    }
}
