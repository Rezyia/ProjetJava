
package Views;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controlers.ControlerMain;
import Models.Employee;


public class WEmployees extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
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
		        int index = lsm.getSelectedIndices()[0];
		        
		        Employee emp = controler.getEmp(index);
		        
		        lHeader.setText("Employee " + emp.getId() + " :");
		        lID.setText(" ID : " + emp.getId());
		        lFirstName.setText(" First name : " + emp.getFirstname());
		        lLastName.setText(" Last name : " + emp.getName());
		        lDept.setText(" Department : " + emp.getDepartment().getnameDep());
		        lIsWorking.setText(" Is working : ");
		        LocalTime timeBegin = emp.getPlanningDay(LocalDate.now().getDayOfWeek().toString().toLowerCase())[0];
		        LocalTime timeEnd = emp.getPlanningDay(LocalDate.now().getDayOfWeek().toString().toLowerCase())[1];
		        lPlanning.setText(" Planning : " + timeBegin + " to " + timeEnd);
		        lOvertime.setText(" Overtime : ");
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
                jButton1ActionPerformed(evt);
            }
        });
        buttons.add(bAdd);

        bModify.setText("Modify...");
        bModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        buttons.add(bModify);

        bDelete.setText("Delete...");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        buttons.add(bDelete);

        header.add(buttons, java.awt.BorderLayout.SOUTH);

        add(header);
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
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
    // End of variables declaration                   
}
