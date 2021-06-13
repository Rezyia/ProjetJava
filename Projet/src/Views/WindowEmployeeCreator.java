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

/**
 * Fenêtre qui permet la création et la modification d'un employé
 * @author dylan
 *
 */
public class WindowEmployeeCreator {
	private static JFrame f;
	private static JLabel lDepartment;
	private static JLabel lName;
	private static JLabel lFirstName;
	private static JLabel lCreate;
	private static JLabel lMonday;
	private static JLabel lTuesday;
	private static JLabel lWednesday;
	private static JLabel lThursday;
	private static JLabel lFriday;
	private static JComboBox<Department> cbDepartment;
	private static JComboBox<LocalTime> cbMondayBegin;
	private static JComboBox<LocalTime> cbMondayEnd;
	private static JComboBox<LocalTime> cbTuesdayBegin;
	private static JComboBox<LocalTime> cbTuesdayEnd;
	private static JComboBox<LocalTime> cbWednesdayBegin;
	private static JComboBox<LocalTime> cbWednesdayEnd;
	private static JComboBox<LocalTime> cbThursdayBegin;
	private static JComboBox<LocalTime> cbThursdayEnd;
	private static JComboBox<LocalTime> cbFridayBegin;
	private static JComboBox<LocalTime> cbFridayEnd;
	private static JTextField tfName;
	private static JTextField tfFirstName;
	private static JButton bCreate;
	private static JButton bModify;
	private static JButton bCreateDepartment;
	private static JButton bCancel;
	
	public static ControlerMain cm;
	protected static Employee emp_modif = null;
	
	/**
	 * Classe utilisé pour affiché correctement les départements dans un ComboBox
	 * @author dylan
	 *
	 */
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
	
	/**
	 * Classe utilisé pour affiché correctement un LocalTime dans un ComboBox
	 * @author dylan
	 *
	 */
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
	 * @return Un JComboBox<Department> contenant tout les département du controleur principale
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
	
	/**
	 * Créer la ComboBox des Plannings
	 * @return Un JComboBox<LocalTime> contenant tout les heures arrondi au quart d'heure
	 */
	public static JComboBox<LocalTime> createComboBoxPlanning(){
		ArrayList<LocalTime> timeBefore = Toolbox.getAllTimeAfter(LocalTime.of(0, 0));
		JComboBox<LocalTime> cb = new JComboBox<LocalTime>();
		Iterator<LocalTime> i = timeBefore.iterator();
		while(i.hasNext()) {
			cb.addItem(i.next());
		}
		cb.setRenderer(ltr);
		return cb;
	}
	
	/**
	 * Permet d'update la ComboBox des Departement lorsqu'on en ajoute un via la fenêtre de création de département
	 */
	public void updateDepts() {
		cbDepartment.removeAllItems();
		for (Department s:cm.getAllDepartment()) {
			cbDepartment.addItem(s);
		}
	}
	
	/**
	 * Créer le champ pour le nom de l'employé
	 * @return Un JTextField pour taper le nom de l'employé
	 */
	public static JTextField createTextFieldName() {
		final JTextField tf = new JTextField();
		tf.setToolTipText("Nom");
		tf.setBounds(100,100, 150,20);
		return tf;
	}
	
	/**
	 * Créer le champ pour le prénom de l'employé
	 * @return Un JTextField pour taper le prénom de l'employé
	 */
	public static JTextField createTextFieldFirstName() {
		final JTextField tf = new JTextField();
		tf.setToolTipText("Pr\u00e9nom");
		tf.setBounds(105,150, 150,20);
		return tf;
	}
	
	/**
	 * Créer le bouton pour créer l'employé
	 * @return Un JButton pour créer l'employé
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
		    		//On ne veut pas crée d'employé sans nom, prénom ou département
		    		if(dpt==null || name.equals("") || firstname.equals("")) {
		    			throw new Exception();
		    		}
		    		Employee emp = new Employee(dpt, name, firstname);
		    		//La méthode setPlanning(LocalTime, LocalTime, String) lance une exception si entrée incohérente
		    		emp.setPlanning((LocalTime)cbMondayBegin.getSelectedItem(), (LocalTime)cbMondayEnd.getSelectedItem(), "monday");
		    		emp.setPlanning((LocalTime)cbTuesdayBegin.getSelectedItem(), (LocalTime)cbTuesdayEnd.getSelectedItem(), "tuesday");
		    		emp.setPlanning((LocalTime)cbWednesdayBegin.getSelectedItem(), (LocalTime)cbWednesdayEnd.getSelectedItem(), "wednesday");
		    		emp.setPlanning((LocalTime)cbThursdayBegin.getSelectedItem(), (LocalTime)cbThursdayEnd.getSelectedItem(), "thursday");
		    		emp.setPlanning((LocalTime)cbFridayBegin.getSelectedItem(), (LocalTime)cbFridayEnd.getSelectedItem(), "friday");
		    		cm.addEmploye(emp);
		    		lCreate.setText("Employ\u00e9 cr\u00e9\u00e9");
		    		
		    		cm.updateLists();
			    	
		    	}catch(Exception exc) {
		    		System.out.println(exc+" Invalid or missing argument");
		    		lCreate.setText(exc+" Invalid or missing argument");
		    	}	
		    }  
		}); 
	    return b;
	}
	
	/**
	 * Créer le bouton pour modifier l'employé
	 * @return Un JButton pour modifier l'employé
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
		    		//On ne veut pas rendre incomplet un employé
		    		if(dpt==null || name.equals("") || firstname.equals("")) {
		    			throw new Exception();
		    		}
		    		emp_modif.setName(name);
		    		emp_modif.setFirstname(firstname);
		    		emp_modif.setDepartment(dpt);
		    		//La méthode setPlanning(LocalTime, LocalTime, String) lance une exception si entrée incohérente
		    		emp_modif.setPlanning((LocalTime)cbMondayBegin.getSelectedItem(), (LocalTime)cbMondayEnd.getSelectedItem(), "monday");
		    		emp_modif.setPlanning((LocalTime)cbTuesdayBegin.getSelectedItem(), (LocalTime)cbTuesdayEnd.getSelectedItem(), "tuesday");
		    		emp_modif.setPlanning((LocalTime)cbWednesdayBegin.getSelectedItem(), (LocalTime)cbWednesdayEnd.getSelectedItem(), "wednesday");
		    		emp_modif.setPlanning((LocalTime)cbThursdayBegin.getSelectedItem(), (LocalTime)cbThursdayEnd.getSelectedItem(), "thursday");
		    		emp_modif.setPlanning((LocalTime)cbFridayBegin.getSelectedItem(), (LocalTime)cbFridayEnd.getSelectedItem(), "friday");
		    		lCreate.setText("Employ\u00e9 modifi\u00e9");
		    		
		    		cm.updateLists();
		    		
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
	 * @return Un JButton pour ouvrir la fenêtre de création de département
	 * @see WindowDepartmentCreator
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
	 * Initialise et affiche la fenêtre de création d'employé
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
		cbMondayBegin = createComboBoxPlanning();
		cbMondayBegin.setBounds(50, 200, 100, 20);
		cbMondayEnd = createComboBoxPlanning();
		cbMondayEnd.setBounds(50, 220, 100, 20);
		
		lTuesday = new JLabel("Mardi");
		lTuesday.setBounds(150, 180, 100, 20);
		cbTuesdayBegin = createComboBoxPlanning();
		cbTuesdayBegin.setBounds(150, 200, 100, 20);
		cbTuesdayEnd = createComboBoxPlanning();
		cbTuesdayEnd.setBounds(150, 220, 100, 20);
		
		lWednesday = new JLabel("Mercredi");
		lWednesday.setBounds(250, 180, 100, 20);
		cbWednesdayBegin = createComboBoxPlanning();
		cbWednesdayBegin.setBounds(250, 200, 100, 20);
		cbWednesdayEnd = createComboBoxPlanning();
		cbWednesdayEnd.setBounds(250, 220, 100, 20);
		
		lThursday = new JLabel("Jeudi");
		lThursday.setBounds(350, 180, 100, 20);
		cbThursdayBegin = createComboBoxPlanning();
		cbThursdayBegin.setBounds(350, 200, 100, 20);
		cbThursdayEnd = createComboBoxPlanning();
		cbThursdayEnd.setBounds(350, 220, 100, 20);
		
		lFriday = new JLabel("Vendredi");
		lFriday.setBounds(450, 180, 100, 20);
		cbFridayBegin = createComboBoxPlanning();
		cbFridayBegin.setBounds(450, 200, 100, 20);
		cbFridayEnd = createComboBoxPlanning();
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
	 * Initialise et affiche la fenêtre de modification d'un employé donné
	 */
	public static void setWindow(WindowEmployeeCreator wec, Employee emp, ControlerMain ctrlmain) {
		emp_modif = emp;
		setWindow(wec, ctrlmain);
		f.setVisible(false);
		f.setTitle("Modifier employ\u00e9");
		cbMondayBegin.setSelectedItem(emp_modif.getPlanningDay("monday")[0]);
		cbMondayEnd.setSelectedItem(emp_modif.getPlanningDay("monday")[1]);
		
		cbTuesdayBegin.setSelectedItem(emp_modif.getPlanningDay("tuesday")[0]);
		cbTuesdayEnd.setSelectedItem(emp_modif.getPlanningDay("tuesday")[1]);
		
		cbWednesdayBegin.setSelectedItem(emp_modif.getPlanningDay("wednesday")[0]);
		cbWednesdayEnd.setSelectedItem(emp_modif.getPlanningDay("wednesday")[1]);
		
		cbThursdayBegin.setSelectedItem(emp_modif.getPlanningDay("thursday")[0]);
		cbThursdayEnd.setSelectedItem(emp_modif.getPlanningDay("thursday")[1]);
		
		cbFridayBegin.setSelectedItem(emp_modif.getPlanningDay("friday")[0]);
		cbFridayEnd.setSelectedItem(emp_modif.getPlanningDay("friday")[1]);
		
		tfName.setText(emp_modif.getName());
		tfFirstName.setText(emp_modif.getFirstname());
		cbDepartment.setSelectedItem(emp_modif.getDepartment());
		
		f.remove(bCreate);
		bModify = createButtonModify();
		f.add(bModify);
		f.setVisible(true);
	}
	
	/**
	 * Main de test
	 * @param args
	 */
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
