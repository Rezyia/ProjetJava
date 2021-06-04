package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controlers.ControlerMain;
import Models.*;

public class WindowEmployeeCreator {
	private static JFrame f;
	public static JLabel lDepartment;
	public static JLabel lName;
	public static JLabel lFirstName;
	public static JComboBox<Object> cbDepartment;
	public static JTextField tfName;
	public static JTextField tfFirstName;
	public static JButton bCreate;
	public static JButton bCreateDepartment;
	public static JButton bCancel;
	
	public static ControlerMain cm;
	
	/**
	 * Créer la ComboBox des département
	 * @return la ComboBox des département
	 */
	public static JComboBox<Object> createComboBoxDepartment() {
		JComboBox<Object> cb = new JComboBox<Object>(cm.getAllDepartment());
		cb.setBounds(135,50,150,20);
		return cb;
	}
	
	/**
	 * Créer le champ pour le nom de l'employé
	 * @return le champ pour le nom de l'employé
	 */
	public static JTextField createTextFieldName() {
		final JTextField tf = new JTextField();
		tf.setBounds(100,100, 150,20);
		return tf;
	}
	
	/**
	 * Créer le champ pour le prénom de l'employé
	 * @return le champ pour le prénom de l'employé
	 */
	public static JTextField createTextFieldFirstName() {
		final JTextField tf = new JTextField();
		tf.setBounds(105,150, 150,20);
		return tf;
	}
	
	/**
	 * Créer le bouton pour créer l'employé
	 * @return le bouton pour créer l'employé
	 */
	public static JButton createButtonCreate() {
		JButton b=new JButton("Create");
	    b.setBounds(50,200,95,20);
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
	
	/**
	 * Créer le bouton pour créer un département
	 * @return le bouton pour créer un département
	 */
	public static JButton createButtonCreateDpt() {
		JButton b=new JButton("Create new Department");
	    b.setBounds(300,50,180,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	new WindowDepartmentCreator(cm);
		    }  
		}); 
	    return b;
	}
	
	/**
	 * Initialise la fenêtre de création d'employé
	 */
	public static void setWindow() {
		f = new JFrame("Cr\u00e9ation employé");
		f.setSize(600,350);
		
		cm = new ControlerMain();
		
		lDepartment = new JLabel("D\u00e9partement :");
		lDepartment.setBounds(50,50,150,20);
		lName = new JLabel("Nom :");
		lName.setBounds(50, 100, 150, 20);
		lFirstName = new JLabel("Pr\u00e9nom :");
		lFirstName.setBounds(50, 150, 150, 20);
		
		cbDepartment = createComboBoxDepartment();
		tfName = createTextFieldName();
		tfFirstName = createTextFieldFirstName();
		
		bCreateDepartment = createButtonCreateDpt();
		bCreate = createButtonCreate();
		
		f.add(lDepartment);
		f.add(cbDepartment);
		f.add(bCreateDepartment);
		f.add(lName);
		f.add(tfName);
		f.add(lFirstName);
		f.add(tfFirstName);
		f.add(bCreate);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
	}
	
	public static void main(String[] args) {
		setWindow();
	}
	
	
}
