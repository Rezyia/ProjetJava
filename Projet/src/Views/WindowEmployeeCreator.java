package Views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import Controlers.ControlerMain;
import Controlers.Toolbox;
import Models.Department;
import Models.*;

public class WindowEmployeeCreator {
	private static JFrame f;
	public static JLabel lDepartment;
	public static JLabel lName;
	public static JLabel lFirstName;
	public static JLabel lCreate;
	public static JLabel lMonday;
	public static JLabel lTuesday;
	public static JLabel lWednesday;
	public static JLabel lThursday;
	public static JLabel lFriday;
	public static JComboBox<Department> cbDepartment;
	public static JComboBox<LocalTime> cbMondayBegin;
	public static JComboBox<LocalTime> cbMondayEnd;
	public static JComboBox<LocalTime> cbTuesdayBegin;
	public static JComboBox<LocalTime> cbTuesdayEnd;
	public static JComboBox<LocalTime> cbWednesdayBegin;
	public static JComboBox<LocalTime> cbWednesdayEnd;
	public static JComboBox<LocalTime> cbThursdayBegin;
	public static JComboBox<LocalTime> cbThursdayEnd;
	public static JComboBox<LocalTime> cbFridayBegin;
	public static JComboBox<LocalTime> cbFridayEnd;
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
	
	static class TimeRenderer extends BasicComboBoxRenderer{

		private static final long serialVersionUID = 1L;
		
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			LocalTime time = (LocalTime) value;
			String hour;
			String minute;
			if(time!=null) {
				if(time.getHour()<=9) {
					hour = "0"+time.getHour();
				}else {
					hour = time.getHour()+"";
				}
				
				if(time.getMinute()<=9) {
					minute = "0"+time.getMinute();
				}else {
					minute = time.getMinute()+"";
				}
				
				setText(hour+":"+minute);
			}
			return this;
		}
		
	}
	
	public static DepartmentRenderer dpr = new DepartmentRenderer();
	public static TimeRenderer ltr = new TimeRenderer();
	
	/**
	 * Créer la ComboBox des département
	 * @return la ComboBox des département
	 */
	public static JComboBox<Department> createComboBoxDepartment() {
		ArrayList<Department> dptListe = cm.getAllDepartment();
		JComboBox<Department> cb = new JComboBox<Department>();
		Iterator<Department> i = dptListe.iterator();
		while(i.hasNext()) {
			cb.addItem(i.next());
		}
		cb.setRenderer(dpr);
		cb.setBounds(135,50,150,20);
		return cb;
	}
	
	public static JComboBox<LocalTime> createComboBoxPlanningBegin(){
		ArrayList<LocalTime> timeBefore = Toolbox.getAllTimeBefore(LocalTime.of(23, 45));
		JComboBox<LocalTime> cb = new JComboBox<LocalTime>();
		Iterator<LocalTime> i = timeBefore.iterator();
		while(i.hasNext()) {
			cb.addItem(i.next());
		}
		cb.setRenderer(ltr);
		return cb;
	}
	
	public static JComboBox<LocalTime> createComboBoxPlanningEnd(){
		ArrayList<LocalTime> timeBefore = Toolbox.getAllTimeAfter(LocalTime.of(0, 0));
		JComboBox<LocalTime> cb = new JComboBox<LocalTime>();
		Iterator<LocalTime> i = timeBefore.iterator();
		while(i.hasNext()) {
			cb.addItem(i.next());
		}
		cb.setRenderer(ltr);
		return cb;
	}
	
	public void updateDepts() {
		cbDepartment.removeAllItems();
		for (Department s:cm.getAllDepartment()) {
			cbDepartment.addItem(s);
		}
	}
	
	/**
	 * Créer le champ pour le nom de l'employé
	 * @return le champ pour le nom de l'employé
	 */
	public static JTextField createTextFieldName() {
		final JTextField tf = new JTextField();
		tf.setToolTipText("Nom");
		tf.setBounds(100,100, 150,20);
		return tf;
	}
	
	/**
	 * Créer le champ pour le prénom de l'employé
	 * @return le champ pour le prénom de l'employé
	 */
	public static JTextField createTextFieldFirstName() {
		final JTextField tf = new JTextField();
		tf.setToolTipText("Pr\u00e9nom");
		tf.setBounds(105,150, 150,20);
		return tf;
	}
	
	/**
	 * Créer le bouton pour créer l'employé
	 * @return le bouton pour créer l'employé
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
	 * Créer le bouton pour créer un département
	 * @return le bouton pour créer un département
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
	 * Initialise la fenêtre de création d'employé
	 */
	public static void setWindow(WindowEmployeeCreator wec) {
		f = new JFrame("Cr\u00e9ation employé");
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
		
		lMonday = new JLabel("Lundi");
		lMonday.setBounds(50, 180, 100, 20);
		cbMondayBegin = createComboBoxPlanningBegin();
		cbMondayBegin.setBounds(50, 200, 100, 20);
		cbMondayEnd = createComboBoxPlanningEnd();
		cbMondayEnd.setBounds(50, 220, 100, 20);
		
		lTuesday = new JLabel("Mardi");
		lTuesday.setBounds(150, 180, 100, 20);
		cbTuesdayBegin = createComboBoxPlanningBegin();
		cbTuesdayBegin.setBounds(150, 200, 100, 20);
		cbTuesdayEnd = createComboBoxPlanningEnd();
		cbTuesdayEnd.setBounds(150, 220, 100, 20);
		
		lWednesday = new JLabel("Mercredi");
		lWednesday.setBounds(250, 180, 100, 20);
		cbWednesdayBegin = createComboBoxPlanningBegin();
		cbWednesdayBegin.setBounds(250, 200, 100, 20);
		cbWednesdayEnd = createComboBoxPlanningEnd();
		cbWednesdayEnd.setBounds(250, 220, 100, 20);
		
		lThursday = new JLabel("Jeudi");
		lThursday.setBounds(350, 180, 100, 20);
		cbThursdayBegin = createComboBoxPlanningBegin();
		cbThursdayBegin.setBounds(350, 200, 100, 20);
		cbThursdayEnd = createComboBoxPlanningEnd();
		cbThursdayEnd.setBounds(350, 220, 100, 20);
		
		lFriday = new JLabel("Vendredi");
		lFriday.setBounds(450, 180, 100, 20);
		cbFridayBegin = createComboBoxPlanningBegin();
		cbFridayBegin.setBounds(450, 200, 100, 20);
		cbFridayEnd = createComboBoxPlanningEnd();
		cbFridayEnd.setBounds(450, 220, 100, 20);
		
		
		f.add(lDepartment);
		f.add(cbDepartment);
		f.add(bCreateDepartment);
		f.add(lName);
		f.add(tfName);
		f.add(lFirstName);
		f.add(tfFirstName);
		f.add(lCreate);
		f.add(bCreate);
		f.add(lMonday);
		f.add(cbMondayBegin);
		f.add(cbMondayEnd);
		f.add(lTuesday);
		f.add(cbTuesdayBegin);
		f.add(cbTuesdayEnd);
		f.add(lWednesday);
		f.add(cbWednesdayBegin);
		f.add(cbWednesdayEnd);
		f.add(lThursday);
		f.add(cbThursdayBegin);
		f.add(cbThursdayEnd);
		f.add(lFriday);
		f.add(cbFridayBegin);
		f.add(cbFridayEnd);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
	}
	
	public static void main(String[] args) {
		WindowEmployeeCreator wec = new WindowEmployeeCreator();
		setWindow(wec);
	}
	
	
}
