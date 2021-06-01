package Controlers;

import java.io.*;
import java.net.*;
import java.util.*;

import Models.*;
import Views.WindowMain;


/**
 * Class to control the main application
 */
public class ControlerMain {

	private static WindowMain f = new WindowMain();
	
	private Socket s = null;
	private ServerSocket ss = null;
	private InetSocketAddress isA = null;
	
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public static void updateContent(String[] items) {
		f.getPointings().updateList(items);
	}
	
	private void setSocket() throws IOException {
        isA = new InetSocketAddress("localhost", 8085);
        ss = new ServerSocket(isA.getPort());
        s = ss.accept();
    }
	
	//-------------------------------------------------------------Méthode pour employees
	
	public void addEmploye(Employee e) {
		employees.add(e);
	}
	
	public void rmEmploye(Employee e) {
		employees.remove(e);
	}
	
	public boolean isEmployeExist(int idEmp) {
		boolean find = false;
		Iterator<Employee> i = employees.iterator();
		while(i.hasNext()) {
			if(i.next().getId() == idEmp) {
				find = true;
			}
		}
		return find;
	}
	
	/**
	 * Méthode pour recevoir un objet de la classe Pointage via l'émulateur
	 * @return Un pointeur crée et envoyé par l'émulateur
	 * @throws IOException si les sockets sont pas/mal initialisés
	 */
	public Pointing receivePointing() {
		try{
            System.out.println("TCPServerHello launched...");
            setSocket();
            System.out.println("Hello, the server is on");
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            Pointing pt = (Pointing) in.readObject();
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
				"oZmzktQwhZ",
				"BYhIseRLUU",
				"dsRDzaEXco",
				"XNqRFeUNOK",
				"VZidDSletD",
				"HrxeewKEIY",
				"NseyZDklcs",
				"uBsLgSLSfj",
				"LBPGRHHEZA",
				"xLvrztlAVH",
				"uZTKpMucbz",
				"tEjTGuwWIk",
				"QtcjqMhlvD",
				"CZoVzDvidP",
				"mpIPHyRgFf",
				"eWHbmxijKz",
				"UTUBVLIkzk",
				"iNPgEHxeHC",
				"kjuaPVJiXv",
				"TLTnMmrUwQ",
				"OmtceVpJgV",
				"rzVcXBPXbr",
				"kWVvxRSOfe",
				"TvckNzbAGb",
				"zQqMKewUOb",
				"fOXgXvIOVg",
				"oBgOqoblRk",
				"ksptgjyzRA",
				"ZRrirTTEwI",
				"KQRzSrrLNe",
				"JbQNrJBYAe",
				"GmajMSYTrZ",
				"LJaTtjPTjp",
				"qsukQkAJge",
				"IGKPadIpiM",
				"rkFXKHFBur",
				"PnYUEqveXj",
				"YnhlUnrxjg",
				"GJehJVrVFW",
				"GMvNTLrkOj",
				"LfaJLtlwFA",
				"PFAHnLxDtf",
				"SSJnoEMAPC",
				"UnvLhNHVwf",
				"JBYdwSUqcT",
				"brDSVoldzn",
				"XOkpuhdMCx",
				"MRjrICfuuV",
				"MIpjWfRTOW",
				"DhEQeAyecA"
		};
		updateContent(items);
	}
}
