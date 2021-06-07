package Views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import Controlers.ControlerMain;
import Models.Department;
import Models.*;

public class WindowEmployeeCreator {
	private static JFrame f;
	public static JLabel lDepartment;
	public static JLabel lName;
	public static JLabel lFirstName;
	public static JLabel lCreate;
	public static JComboBox<Department> cbDepartment;
	public static JTextField tfName;
	public static JTextField tfFirstName;
	public static JButton bCreate;
	public static JButton bCreateDepartment;
	public static JButton bCancel;
	
	public static ControlerMain cm; 
	
	static class DepartmentRenderer extends BasicComboBoxRenderer{

		private static final long serialVersionUID = 1L;
		
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			Department dpt = (Department) value;
			if(dpt!=null) {
				setText(dpt.getnameDep());
			}
			return this;
		}
		
	}
	
	public static DepartmentRenderer dpr = new DepartmentRenderer();
	
	/**
	 * Cr�er la ComboBox des d�partement
	 * @return la ComboBox des d�partement
	 */
	public static JComboBox<Department> createComboBoxDepartment() {
		ArrayList<Department> liste = cm.getAllDepartment();
		JComboBox<Department> cb = new JComboBox<Department>();
		Iterator<Department> i = liste.iterator();
		while(i.hasNext()) {
			cb.addItem(i.next());
		}
		cb.setRenderer(dpr);
		cb.setBounds(135,50,150,20);
		return cb;
	}
	
	public void updateDepts() {
		cbDepartment.removeAllItems();
		for (Department s:cm.getAllDepartment()) {
			cbDepartment.addItem(s);
		}
	}
	
	/**
	 * Cr�er le champ pour le nom de l'employ�
	 * @return le champ pour le nom de l'employ�
	 */
	public static JTextField createTextFieldName() {
		final JTextField tf = new JTextField();
		tf.setBounds(100,100, 150,20);
		return tf;
	}
	
	/**
	 * Cr�er le champ pour le pr�nom de l'employ�
	 * @return le champ pour le pr�nom de l'employ�
	 */
	public static JTextField createTextFieldFirstName() {
		final JTextField tf = new JTextField();
		tf.setBounds(105,150, 150,20);
		return tf;
	}
	
	/**
	 * Cr�er le bouton pour cr�er l'employ�
	 * @return le bouton pour cr�er l'employ�
	 */
	public static JButton createButtonCreate() {
		JButton b=new JButton("Create");
	    b.setBounds(50,250,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	try {
		    		Department dpt = (Department) cbDepartment.getSelectedItem();
		    		String name = tfName.getText();
		    		String firstname = tfFirstName.getText();
		    		if(dpt==null || name.equals("") || firstname.equals("")) {
		    			throw new Exception();
		    		}
		    		Employee emp = new Employee(dpt, name, firstname);
		    		cm.addEmploye(emp);
		    		lCreate.setText("Employ\u00e9 cr\u00e9\u00e9");
		    	}catch(Exception exc) {
		    		System.out.println(exc+" Invalid or missing argument");
		    		lCreate.setText(exc+" Invalid or missing argument");
		    	}	
		    }  
		}); 
	    return b;
	}
	
	/**
	 * Cr�er le bouton pour cr�er un d�partement
	 * @return le bouton pour cr�er un d�partement
	 */
	public static JButton createButtonCreateDpt(WindowEmployeeCreator wec) {
		JButton b=new JButton("Create new Department");
	    b.setBounds(300,50,180,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	new WindowDepartmentCreator(cm, wec);
		    }  
		}); 
	    return b;
	}
	
	/**
	 * Initialise la fen�tre de cr�ation d'employ�
	 */
	public static void setWindow(WindowEmployeeCreator wec) {
		f = new JFrame("Cr\u00e9ation employ�");
		f.setSize(600,400);
		
		cm = new ControlerMain();
		
		lDepartment = new JLabel("D\u00e9partement :");
		lDepartment.setBounds(50,50,150,20);
		lName = new JLabel("Nom :");
		lName.setBounds(50, 100, 150, 20);
		lFirstName = new JLabel("Pr\u00e9nom :");
		lFirstName.setBounds(50, 150, 150, 20);
		lCreate = new JLabel("");
		lCreate.setBounds(50,300,300,20);
		
		cbDepartment = createComboBoxDepartment();
		tfName = createTextFieldName();
		tfFirstName = createTextFieldFirstName();
		
		bCreateDepartment = createButtonCreateDpt(wec);
		bCreate = createButtonCreate();
		
		f.add(lDepartment);
		f.add(cbDepartment);
		f.add(bCreateDepartment);
		f.add(lName);
		f.add(tfName);
		f.add(lFirstName);
		f.add(tfFirstName);
		f.add(lCreate);
		f.add(bCreate);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
	}
	
	public static void main(String[] args) {
		WindowEmployeeCreator wec = new WindowEmployeeCreator();
		setWindow(wec);
	}
	
	
}
