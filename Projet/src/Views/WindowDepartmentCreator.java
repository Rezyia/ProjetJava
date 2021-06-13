package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controlers.ControlerMain;
import Models.Department;

/**
 * Fen�tre qui permet la cr�ation d'un d�partement
 * @author dylan
 *
 */
public class WindowDepartmentCreator {
	private static JFrame f;
	public static JLabel lNameDpt;
	public static JTextField tfNameDpt;
	public static JButton bCreate;
	public static JLabel lCreate;
	
	public static ControlerMain cm;
	public static WindowEmployeeCreator wec;
	
	public WindowDepartmentCreator(ControlerMain cm, WindowEmployeeCreator wec) {
		WindowDepartmentCreator.cm = cm;
		WindowDepartmentCreator.wec = wec;
		setWindow();
	}
	
	/**
	 * Cr�e le TextField pour taper le nom du d�partement
	 * @return Un JTextField permetant de taper le nom du d�partment
	 */
	private static JTextField createTextFieldNameDpt() {
		final JTextField tf = new JTextField();
		tf.setBounds(180,50, 150,20);
		return tf;
	}

	/**
	 * Cr�e le bouton qui permet la cr�ation d'un d�partement
	 * @return Un JButton qui permet de cr�e un d�partement
	 */
	private static JButton createButtonCreate() {
		JButton b=new JButton("Create");
	    b.setBounds(50,100,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	try {
		    		if(tfNameDpt.getText().equals("")) {
		    			throw new Exception();
		    		}
		    		cm.addDepartment(new Department(tfNameDpt.getText()));
		    		tfNameDpt.setText("");
		    		wec.updateDepts();
		    		lCreate.setText("Ajout r\u00e9ussi");
		    	}catch(Exception exc) {
		    		System.out.println(exc+" Invalid or missing argument");
		    	}	
		    }  
		}); 
	    return b;
	}
	
	/**
	 * Initialise et affiche la fen�tre de cr�ation de d�partement
	 */
	public static void setWindow() {
		f = new JFrame("Cr\u00e9ation d\u00e9partement");
		f.setSize(370,200);
		
		lNameDpt = new JLabel("Nom d\u00e9partement :");
		lNameDpt.setBounds(50,50,150,20);
		
		lCreate = new JLabel("");
		lCreate.setBounds(50,125,300,20);
		
		tfNameDpt = createTextFieldNameDpt();
		bCreate = createButtonCreate();
		
		f.add(lNameDpt);
		f.add(tfNameDpt);
		f.add(lCreate);
		f.add(bCreate);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
	}
		
}
