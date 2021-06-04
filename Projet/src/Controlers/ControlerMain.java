package Controlers;

import java.io.*;
import java.net.*;
import java.util.*;

import Models.*;
import Views.WindowMain;


/**
 * Class to control the main application
 */
public class ControlerMain extends ControlerNetwork{

	private static WindowMain f = new WindowMain();
	
	
	private ServerSocket ss = null;
	
	
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<Department> departments = new ArrayList<Department>();
	private ArrayList<Pointing> pointings = new ArrayList<Pointing>();
	
	public static void updateContent(String[] items) {
		f.getPointings().updateList(items);
	}
	
	private void setSocket() throws IOException {
        isA = new InetSocketAddress(address, port);
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
		while(i.hasNext() && !find) {
			if(i.next().getId() == idEmp) {
				find = true;
			}
		}
		return find;
	}
	
	//-------------------------------------------------------------Méthode pour departments
	
	public void addDepartment(Department d) {
		departments.add(d);
	}
	
	public void rmDepartment(Department d) {
		departments.remove(d);
	}
	
	public boolean isDepartmentExist(String str) {
		boolean find = false;
		Iterator<Department> i = departments.iterator();
		while(i.hasNext() && !find) {
			if(i.next().getnameDep().equals(str)) {
				find = true;
			}
		}
		return find;
	}
	
	public Object[] getAllDepartment() {
		return departments.toArray();
	}
	
	//-------------------------------------------------------------Méthode pour pointings
	
	public void addPointing(Pointing p) {
		pointings.add(p);
	}
	
	public void rmPointing(Pointing p) {
		pointings.remove(p);
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
				"DhEQeAyecA",
				"TSQjcYwxSt",
				"bldUGljZbb",
				"FtSeBFTAAj",
				"jUoNnDxPFI",
				"nWIHBfTafO",
				"GrOlvXnnNG",
				"kiLrEXVAxP",
				"bCZyRhuWZN",
				"IDtQztlGuo",
				"OejJIoOLVS",
				"GlnwUUWozg",
				"fQKansvlPV",
				"vCzyJarAyP",
				"NrRGpIUAuf",
				"uOZJrUnxZx",
				"jhwiVSeMQg",
				"LNACPbkLVf",
				"EeFDSrEJZU",
				"EjRLAJLumE",
				"luSyEDmQgP",
				"sxrfBvvvgK",
				"rqRcDFUhWq",
				"pemFEXnabW",
				"rnzFAnoxyG",
				"CbsgDCBYmk",
				"sutOjvrmIO",
				"psvTzXQMCn",
				"FiijCNPmui",
				"qEaOHgfrtH",
				"UYTFJiWeZL",
				"nQcQiYhHGX",
				"aLXBfQidvd",
				"wOjBxXbzHD",
				"qKEZwEziBF",
				"glXulxYJXP",
				"qajIWXJVwJ",
				"AwVoPKAgnJ",
				"Lfuvrtjkbp",
				"ocMRdftAqc",
				"jqGXwLewIu",
				"knUJhXRjHx",
				"vdpfvBfIiF",
				"WrnsPbuCYj",
				"rmgOAbgKdj",
				"NNXdSxIjfu",
				"vKsHvEQpsP",
				"RKLgdTOJeZ",
				"TsHGIZGUOL"
		};
		updateContent(items);
	}
}
