package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controlers.ControlerMain;
import Models.Department;

public class WindowDepartmentCreator {
	private static JFrame f;
	public static JLabel lNameDpt;
	public static JTextField tfNameDpt;
	public static JButton bCreate;
	public static JLabel lCreate;
	
	public static ControlerMain cm;
	
	public WindowDepartmentCreator(ControlerMain cm) {
		WindowDepartmentCreator.cm = cm;
		setWindow();
	}
	
	private static JTextField createTextFieldNameDpt() {
		final JTextField tf = new JTextField();
		tf.setBounds(180,50, 150,20);
		return tf;
	}

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
		    		lCreate.setText("Ajout r\u00e9ussi");
		    	}catch(Exception exc) {
		    		System.out.println(exc+" Invalid or missing argument");
		    	}	
		    }  
		}); 
	    return b;
	}
	
	
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
	
	public static void main(String[] args) {
		new WindowDepartmentCreator(new ControlerMain());
	}
	
}
