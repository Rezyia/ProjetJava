package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controlers.ControlerMain;
import Models.*;

public class WindowEmployeeCreation {
	private static JFrame f;
	public static JLabel lDepartment;
	public static JLabel lName;
	public static JLabel lFirstName;
	public static JComboBox<Object> cbDepartment;
	public static JTextField tfName;
	public static JTextField tfFirstName;
	public static JButton bCreate;
	public static JButton bCancel;
	
	public static ControlerMain cm;
	
	public static JComboBox<Object> createComboBoxDepartment() {
		JComboBox<Object> cb = new JComboBox<Object>(cm.getAllDepartment());
		return cb;
	}
	
	public static JButton createButtonCreate() {
		JButton b=new JButton("Create");
	    b.setBounds(50,150,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	try {
		    		Department dpt = (Department) cbDepartment.getSelectedItem();
		    		String name = tfName.getText();
		    		String firstname = tfFirstName.getText();
		    		Employee emp = new Employee(dpt, name, firstname);
		    		cm.addEmploye(emp);
		    	}catch(Exception exc) {
		    		System.out.println(exc+" Invalid or missing argument");
		    	}	
		    }  
		}); 
	    return b;
	}
	
	public static void main(String[] args) {
		f = new JFrame("Création employé");
		cm = new ControlerMain();
		f.add(cbDepartment);
		f.add(bCreate);
	}
	
	
}
