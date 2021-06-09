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
	public static JButton bModify;
	public static JButton bCreateDepartment;
	public static JButton bCancel;
	
	public static ControlerMain cm;
	protected static Employee emp_modif;
	
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
	
	public static JComboBox<LocalTime> createComboBoxPlanningBegin(LocalTime lt){
		ArrayList<LocalTime> timeBefore = Toolbox.getAllTimeBefore(lt);
		JComboBox<LocalTime> cb = new JComboBox<LocalTime>();
		Iterator<LocalTime> i = timeBefore.iterator();
		while(i.hasNext()) {
			cb.addItem(i.next());
		}
		cb.setRenderer(ltr);
		return cb;
	}
	
	public static JComboBox<LocalTime> createComboBoxPlanningEnd(LocalTime lt){
		ArrayList<LocalTime> timeBefore = Toolbox.getAllTimeAfter(lt);
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
		    		emp.setPlanning((LocalTime)cbMondayBegin.getSelectedItem(), (LocalTime)cbMondayEnd.getSelectedItem(), "monday");
		    		emp.setPlanning((LocalTime)cbTuesdayBegin.getSelectedItem(), (LocalTime)cbTuesdayEnd.getSelectedItem(), "tuesday");
		    		emp.setPlanning((LocalTime)cbWednesdayBegin.getSelectedItem(), (LocalTime)cbWednesdayEnd.getSelectedItem(), "wednesday");
		    		emp.setPlanning((LocalTime)cbThursdayBegin.getSelectedItem(), (LocalTime)cbThursdayEnd.getSelectedItem(), "thursday");
		    		emp.setPlanning((LocalTime)cbFridayBegin.getSelectedItem(), (LocalTime)cbFridayEnd.getSelectedItem(), "friday");
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
	 * Créer le bouton pour créer l'employé
	 * @return le bouton pour créer l'employé
	 */
	public static JButton createButtonModify() {
		JButton b=new JButton("Modify");
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
		    		emp_modif.setName(name);
		    		emp_modif.setFirstname(firstname);
		    		emp_modif.setDepartment(dpt);
		    		emp_modif.setPlanning((LocalTime)cbMondayBegin.getSelectedItem(), (LocalTime)cbMondayEnd.getSelectedItem(), "monday");
		    		emp_modif.setPlanning((LocalTime)cbTuesdayBegin.getSelectedItem(), (LocalTime)cbTuesdayEnd.getSelectedItem(), "tuesday");
		    		emp_modif.setPlanning((LocalTime)cbWednesdayBegin.getSelectedItem(), (LocalTime)cbWednesdayEnd.getSelectedItem(), "wednesday");
		    		emp_modif.setPlanning((LocalTime)cbThursdayBegin.getSelectedItem(), (LocalTime)cbThursdayEnd.getSelectedItem(), "thursday");
		    		emp_modif.setPlanning((LocalTime)cbFridayBegin.getSelectedItem(), (LocalTime)cbFridayEnd.getSelectedItem(), "friday");
		    		lCreate.setText("Employ\u00e9 modifi\u00e9");
		    		
		    		//Pour voir si l'employé est correctement modifier dans le controleur
		    		System.out.println(cm.getEmployee(emp_modif.getId()));
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
	public static void setWindow(WindowEmployeeCreator wec, ControlerMain ctrlmain) {
		f = new JFrame("Cr\u00e9ation employé");
		f.setSize(600,400);
		
		cm = ctrlmain;
		
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
		cbMondayBegin = createComboBoxPlanningBegin(LocalTime.of(0, 0));
		cbMondayBegin.setBounds(50, 200, 100, 20);
		cbMondayEnd = createComboBoxPlanningEnd(LocalTime.of(0, 0));
		cbMondayEnd.setBounds(50, 220, 100, 20);
		
		lTuesday = new JLabel("Mardi");
		lTuesday.setBounds(150, 180, 100, 20);
		cbTuesdayBegin = createComboBoxPlanningBegin(LocalTime.of(0, 0));
		cbTuesdayBegin.setBounds(150, 200, 100, 20);
		cbTuesdayEnd = createComboBoxPlanningEnd(LocalTime.of(0, 0));
		cbTuesdayEnd.setBounds(150, 220, 100, 20);
		
		lWednesday = new JLabel("Mercredi");
		lWednesday.setBounds(250, 180, 100, 20);
		cbWednesdayBegin = createComboBoxPlanningBegin(LocalTime.of(0, 0));
		cbWednesdayBegin.setBounds(250, 200, 100, 20);
		cbWednesdayEnd = createComboBoxPlanningEnd(LocalTime.of(0, 0));
		cbWednesdayEnd.setBounds(250, 220, 100, 20);
		
		lThursday = new JLabel("Jeudi");
		lThursday.setBounds(350, 180, 100, 20);
		cbThursdayBegin = createComboBoxPlanningBegin(LocalTime.of(0, 0));
		cbThursdayBegin.setBounds(350, 200, 100, 20);
		cbThursdayEnd = createComboBoxPlanningEnd(LocalTime.of(0, 0));
		cbThursdayEnd.setBounds(350, 220, 100, 20);
		
		lFriday = new JLabel("Vendredi");
		lFriday.setBounds(450, 180, 100, 20);
		cbFridayBegin = createComboBoxPlanningBegin(LocalTime.of(0, 0));
		cbFridayBegin.setBounds(450, 200, 100, 20);
		cbFridayEnd = createComboBoxPlanningEnd(LocalTime.of(0, 0));
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
	
	/**
	 * Initialise la fenêtre de modification d'un employé donné
	 */
	public static void setWindow(WindowEmployeeCreator wec, Employee emp, ControlerMain ctrlmain) {
		emp_modif = emp;
		f = new JFrame("Modification employé");
		f.setSize(600,400);
		
		cm = ctrlmain;
		
		lDepartment = new JLabel("D\u00e9partement :");
		lDepartment.setBounds(50,50,150,20);
		lName = new JLabel("Nom :");
		lName.setBounds(50, 100, 150, 20);
		lFirstName = new JLabel("Pr\u00e9nom :");
		lFirstName.setBounds(50, 150, 150, 20);
		lCreate = new JLabel("");
		lCreate.setBounds(50,300,300,20);
		
		cbDepartment = createComboBoxDepartment();
		cbDepartment.setSelectedItem(emp_modif.getDepartment());
		tfName = createTextFieldName();
		tfName.setText(emp_modif.getName());
		tfFirstName = createTextFieldFirstName();
		tfFirstName.setText(emp_modif.getFirstname());
		
		bCreateDepartment = createButtonCreateDpt(wec);
		bModify = createButtonModify();
		
		lMonday = new JLabel("Lundi");
		lMonday.setBounds(50, 180, 100, 20);
		cbMondayBegin = createComboBoxPlanningBegin(emp_modif.getPlanningDay("monday")[1]);
		cbMondayBegin.setBounds(50, 200, 100, 20);
		cbMondayBegin.setSelectedItem(emp_modif.getPlanningDay("monday")[0]);
		cbMondayEnd = createComboBoxPlanningEnd(emp_modif.getPlanningDay("monday")[0]);
		cbMondayEnd.setBounds(50, 220, 100, 20);
		cbMondayEnd.setSelectedItem(emp_modif.getPlanningDay("monday")[1]);
		
		lTuesday = new JLabel("Mardi");
		lTuesday.setBounds(150, 180, 100, 20);
		cbTuesdayBegin = createComboBoxPlanningBegin(emp_modif.getPlanningDay("tuesday")[1]);
		cbTuesdayBegin.setBounds(150, 200, 100, 20);
		cbTuesdayBegin.setSelectedItem(emp_modif.getPlanningDay("tuesday")[0]);
		cbTuesdayEnd = createComboBoxPlanningEnd(emp_modif.getPlanningDay("tuesday")[0]);
		cbTuesdayEnd.setBounds(150, 220, 100, 20);
		cbTuesdayEnd.setSelectedItem(emp_modif.getPlanningDay("tuesday")[1]);
		
		lWednesday = new JLabel("Mercredi");
		lWednesday.setBounds(250, 180, 100, 20);
		cbWednesdayBegin = createComboBoxPlanningBegin(emp_modif.getPlanningDay("wednesday")[1]);
		cbWednesdayBegin.setBounds(250, 200, 100, 20);
		cbWednesdayBegin.setSelectedItem(emp_modif.getPlanningDay("wednesday")[0]);
		cbWednesdayEnd = createComboBoxPlanningEnd(emp_modif.getPlanningDay("wednesday")[0]);
		cbWednesdayEnd.setBounds(250, 220, 100, 20);
		cbWednesdayEnd.setSelectedItem(emp_modif.getPlanningDay("wednesday")[1]);
		
		lThursday = new JLabel("Jeudi");
		lThursday.setBounds(350, 180, 100, 20);
		cbThursdayBegin = createComboBoxPlanningBegin(emp_modif.getPlanningDay("thursday")[1]);
		cbThursdayBegin.setBounds(350, 200, 100, 20);
		cbThursdayBegin.setSelectedItem(emp_modif.getPlanningDay("thursday")[0]);
		cbThursdayEnd = createComboBoxPlanningEnd(emp_modif.getPlanningDay("thursday")[0]);
		cbThursdayEnd.setBounds(350, 220, 100, 20);
		cbThursdayEnd.setSelectedItem(emp_modif.getPlanningDay("thursday")[1]);
		
		lFriday = new JLabel("Vendredi");
		lFriday.setBounds(450, 180, 100, 20);
		cbFridayBegin = createComboBoxPlanningBegin(emp_modif.getPlanningDay("friday")[1]);
		cbFridayBegin.setBounds(450, 200, 100, 20);
		cbFridayBegin.setSelectedItem(emp_modif.getPlanningDay("friday")[0]);
		cbFridayEnd = createComboBoxPlanningEnd(emp_modif.getPlanningDay("friday")[0]);
		cbFridayEnd.setBounds(450, 220, 100, 20);
		cbFridayEnd.setSelectedItem(emp_modif.getPlanningDay("friday")[1]);
		
		
		f.add(lDepartment);
		f.add(cbDepartment);
		f.add(bCreateDepartment);
		f.add(lName);
		f.add(tfName);
		f.add(lFirstName);
		f.add(tfFirstName);
		f.add(lCreate);
		f.add(bModify);
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
		ControlerMain cm = new ControlerMain();
		WindowEmployeeCreator wec = new WindowEmployeeCreator();
		Department dpt = new Department("DI");
		cm.addDepartment(dpt);
		
		Employee emp = new Employee(dpt, "Vinet", "Dylan");
		cm.addEmploye(emp);
		
		setWindow(wec, emp, cm);
	}
	
	
}
