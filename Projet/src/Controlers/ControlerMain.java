package Controlers;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JFrame;

import Models.*;
import Views.MainFrame;
import Views.WindowMain;


/**
 * Class to control the main application
 */
public class ControlerMain extends ControlerNetwork{

	//private static WindowMain f = new WindowMain();
	private static MainFrame f;
	
	
	private ServerSocket ss = null;
	
	
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<Department> departments = new ArrayList<Department>();
	private ArrayList<Pointing> pointings = new ArrayList<Pointing>();
	
	public static void updatePointings(String[] items) {
		f.getWindowPointings().updatePointings(items);
	}
	
	private void setSocket() throws IOException {
        isA = new InetSocketAddress(address, port);
        ss = new ServerSocket(isA.getPort());
        s = ss.accept();
    }
	
	//-------------------------------------------------------------M�thode pour employees
	
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
	
	//-------------------------------------------------------------M�thode pour departments
	
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
	
	public ArrayList<Department> getAllDepartment() {
		return departments;
	}
	
	//-------------------------------------------------------------M�thode pour pointings
	
	public void addPointing(Pointing p) {
		pointings.add(p);
	}
	
	public void rmPointing(Pointing p) {
		pointings.remove(p);
	}
	
	/**
	 * M�thode pour recevoir un objet de la classe Pointage via l'�mulateur
	 * @return Un pointeur cr�e et envoy� par l'�mulateur
	 * @throws IOException si les sockets sont pas/mal initialis�s
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

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        		ControlerMain controler = new ControlerMain();
                ControlerMain.f = new MainFrame(controler);
                
        		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                f.setVisible(true);
                
                
                updatePointings(Toolbox.getRandomString());
            }
        });
        
	}
}
