
package Views;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controlers.ControlerMain;
import Models.Employee;


public class WEmployees extends javax.swing.JPanel {

	private static final long serialVersionUID = -7929775240787225787L;
	
	private static ControlerMain controler;

    public WEmployees() {
        initComponents();
    }

    public WEmployees(ControlerMain c) {
    	controler = c;
        initComponents();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        main = new javax.swing.JPanel();
        scrollList = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        
        header = new javax.swing.JPanel();
        lHeader = new javax.swing.JLabel();
        
        info = new javax.swing.JPanel();
        lID = new javax.swing.JLabel();
        lFirstName = new javax.swing.JLabel();
        lLastName = new javax.swing.JLabel();
        lDept = new javax.swing.JLabel();
        lIsWorking = new javax.swing.JLabel();
        lPlanning = new javax.swing.JLabel();
        lOvertime = new javax.swing.JLabel();
        
        buttons = new javax.swing.JPanel();
        bAdd = new javax.swing.JButton();
        bModify = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        
        selectedEmp = null;
        wec = new WindowEmployeeCreator();
        
        setLayout(new java.awt.GridLayout());

        main.setLayout(new java.awt.BorderLayout());

        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = controler.getEmployees();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        ListSelectionModel selectionModel = list.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        int index = lsm.getMinSelectionIndex(), id;
		        
		        if (index == -1) {
			        bModify.setEnabled(false);
			        bDelete.setEnabled(false);

		        } else {
					String idS = list.getModel().getElementAt(index);
					id = Integer.parseInt(idS.replaceAll("[\\D]", ""));

		        	Employee emp = controler.getEmp(id);
			        selectedEmp = emp;
			        
			        // Display details on the right panel :
			        lHeader.setText("Employee " + emp.getId() + " :");
			        lID.setText(" ID : " + emp.getId());
			        lFirstName.setText(" First name : " + emp.getFirstname());
			        lLastName.setText(" Last name : " + emp.getName());
			        lDept.setText(" Department : " + emp.getDepartment().getnameDep());
			        lIsWorking.setText(" Is working : " + emp.isWorking());
			        
			        lPlanning.setText(" Planning : \n");
			        for (String day : Employee.workingDays) {
				        LocalTime timeBegin = emp.getPlanningDay(day)[0];
				        LocalTime timeEnd = emp.getPlanningDay(day)[1];
				        lPlanning.setText(lPlanning.getText() + " " + day + " : " + timeBegin + " to " + timeEnd + " - ");

			        }
			        lOvertime.setText(" Overtime : " + emp.getOvertime());
			        
			        // Enable modify and delete buttons :
			        bModify.setEnabled(true);
			        bDelete.setEnabled(true);
		        }
			}
		});

        
        scrollList.setViewportView(list);

        main.add(scrollList, java.awt.BorderLayout.CENTER);

        add(main);

        header.setLayout(new java.awt.BorderLayout());

        lHeader.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lHeader.setText("Employee :");
        lHeader.setPreferredSize(new java.awt.Dimension(34, 40));
        header.add(lHeader, java.awt.BorderLayout.NORTH);

        info.setLayout(new javax.swing.BoxLayout(info, javax.swing.BoxLayout.Y_AXIS));

        lID.setText(" ID :");
        info.add(lID);

        lFirstName.setText(" First name :");
        info.add(lFirstName);

        lLastName.setText(" Last name :");
        info.add(lLastName);

        lDept.setText(" Department :");
        info.add(lDept);
        
        lIsWorking.setText(" Is working :");
        info.add(lIsWorking);
        
        lPlanning.setText(" Planning :");
        info.add(lPlanning);
        
        lOvertime.setText(" Total overtime :");
        info.add(lOvertime);

        header.add(info, java.awt.BorderLayout.CENTER);

        
        buttons.setLayout(new java.awt.GridLayout());

        bAdd.setText("Add...");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });
        buttons.add(bAdd);

        bModify.setText("Modify...");
        bModify.setEnabled(false);
        bModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModifyActionPerformed(evt);
            }
        });
        buttons.add(bModify);

        bDelete.setText("Delete...");
        bDelete.setEnabled(false);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });
        buttons.add(bDelete);

        header.add(buttons, java.awt.BorderLayout.SOUTH);

        add(header);
    }// </editor-fold>                        

    
    
    private void bAddActionPerformed(java.awt.event.ActionEvent evt) { 
		WindowEmployeeCreator.setWindow(wec, controler);

    }                                        

    private void bModifyActionPerformed(java.awt.event.ActionEvent evt) {
		WindowEmployeeCreator.setWindow(wec, selectedEmp, controler);
    }                                        

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                         
		int index = list.getSelectionModel().getMinSelectionIndex(), id;
		String idS = list.getModel().getElementAt(index);
		
		id = Integer.parseInt(idS.replaceAll("[\\D]", ""));
		
		if(controler.isEmployeExist(id)) {
			Employee emp = controler.getEmp(id);
			controler.rmEmploye(emp);

			list.clearSelection();
			
			list.setModel(new javax.swing.AbstractListModel<String>() {
			    String[] strings = controler.getEmployees();
			    public int getSize() { return strings.length; }
			    public String getElementAt(int i) { return strings[i]; }
			});
			scrollList.setViewportView(list);
			SwingUtilities.updateComponentTreeUI(scrollList);
			System.out.println("Delete successful : " + emp + "at index : " + index + " with id : " + id);
		}
		else {
			System.out.println("deja supprime");
		}     
    }                                        


    public void updateList() {
		list.clearSelection();
		
		list.setModel(new javax.swing.AbstractListModel<String>() {
		    String[] strings = controler.getEmployees();
		    public int getSize() { return strings.length; }
		    public String getElementAt(int i) { return strings[i]; }
		});

    }
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bModify;
    private javax.swing.JButton bDelete;
    
    private javax.swing.JLabel lHeader;
    
    private javax.swing.JLabel lID;
    private javax.swing.JLabel lFirstName;
    private javax.swing.JLabel lLastName;
    private javax.swing.JLabel lDept;
    private javax.swing.JLabel lIsWorking;
    private javax.swing.JLabel lPlanning;
    private javax.swing.JLabel lOvertime;

    private javax.swing.JList<String> list;
    private javax.swing.JScrollPane scrollList;

    private javax.swing.JPanel main;
    private javax.swing.JPanel buttons;
    private javax.swing.JPanel header;
    private javax.swing.JPanel info;
    
	private WindowEmployeeCreator wec;
    Employee selectedEmp;

    // End of variables declaration                   
}
