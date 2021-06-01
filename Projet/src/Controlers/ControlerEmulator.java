package Controlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.time.LocalDateTime;

import Models.Pointing;

public class ControlerEmulator extends ControlerNetwork{
    
    private void setSocket() throws IOException{
    	isA = new InetSocketAddress(address, port);
        s = new Socket(isA.getHostName(), isA.getPort());
    }
    
    /**
     * M�thode pour cr�er et envoyer � l'application principale des pointages
     * @param idEmp Identifiant de l'employ� qui cr�e le pointage
     * @param time Date et heure � laquel le poinatge a �t� fait
     * @throws IOException si les sockets sont pas/mal initialis�s
     */
    public void sendPointing(int idEmp, LocalDateTime time) {
    	try{
            System.out.println("TCPClientObjet launched...");
            setSocket();
            System.out.println("Hello, the client is connected");
            Pointing pt = new Pointing(idEmp, Toolbox.roundToNearestQuarter(time));
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
