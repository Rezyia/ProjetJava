
package Views;

import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.source.tree.Scope;

import Controlers.ControlerMain;
import Controlers.Toolbox;
import Models.Employee;
import Models.Pointing;


public class WPointings extends javax.swing.JPanel {

	private static final long serialVersionUID = -5326257363166640986L;
	
	private static ControlerMain controler;
	public static int test = 0;
	

	public WPointings() {
        initComponents();
    } 
    
	
    public WPointings(ControlerMain c) {
    	controler = c;
        initComponents();
    }
 
    
    /**
     * Initialize the components of the WPointings
     */
    private void initComponents() {

        main = new javax.swing.JPanel();
        scrollList = new javax.swing.JScrollPane();
        list = new javax.swing.JList<String>();
        checkBox = new javax.swing.JCheckBox();
        
        header = new javax.swing.JPanel();
        lHeader = new javax.swing.JLabel(); // "Pointing " + <ID Pointing> + " - Employee " + <ID Employee> + " - Date : " + "<Date>"
        
        info = new javax.swing.JPanel();
        lInOut = new javax.swing.JLabel();
        lPointedTime = new javax.swing.JLabel();
        lPlannedTime = new javax.swing.JLabel();
        
        setLayout(new java.awt.GridLayout());

        main.setLayout(new java.awt.BorderLayout());

        scrollList.setViewportView(list);
        main.add(scrollList, java.awt.BorderLayout.CENTER);

        updatePointings(controler.getPointingsOfTheDay());
        
        checkBox.setText("Show all pointings");
        checkBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
                checkboxActionPerformed(e);
				
			}
        });
        main.add(checkBox, java.awt.BorderLayout.PAGE_END);

        add(main);

        header.setLayout(new java.awt.BorderLayout());

        lHeader.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lHeader.setText("Pointing <IDEmp> - <Date>");
        lHeader.setPreferredSize(new java.awt.Dimension(34, 40));
        header.add(lHeader, java.awt.BorderLayout.NORTH);

        info.setLayout(new javax.swing.BoxLayout(info, javax.swing.BoxLayout.Y_AXIS));

        lInOut.setText(" In/Out : ");
        info.add(lInOut);

        lPointedTime.setText(" Hour pointed :");
        info.add(lPointedTime);

        lPlannedTime.setText(" Hour planned : ");
        info.add(lPlannedTime);

        header.add(info, java.awt.BorderLayout.CENTER);

        add(header);
    }// </editor-fold>                        

    
    /**
     * CheckBox checked : all Pointings displayed
     * CheckBox unchecked : Pointings of the current day displayed
     * @param evt ItemEvent object received from the event
     */
    private void checkboxActionPerformed(ItemEvent evt) {                                           
    	if (evt.getStateChange() == ItemEvent.SELECTED) updatePointings(controler.getPointings());
    	else updatePointings(controler.getPointingsOfTheDay());
    }                         
    
    public void updateList() {
    	if (checkBox.isEnabled()) updatePointings(controler.getPointings());
    	else updatePointings(controler.getPointingsOfTheDay());
    }    
    
    
    /**
     * Updates the list of pointings
     * @param items String[] of items to load into the list
     */
    public void updatePointings(String[] items) {
    	list = new JList<String>();
        list.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return items.length; }
            public String getElementAt(int i) { return items[i]; }
        });
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.clearSelection();
        
        ListSelectionModel selectionModel = list.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			    if (!lsm.isSelectionEmpty()) {
			        lHeader.setText("Pointing " + list.getSelectedValue());
			    	loadPointingData(list.getSelectedIndex());
			    }
			}
		});
        scrollList.setViewportView(list);
        SwingUtilities.updateComponentTreeUI(scrollList);
    }
    
    
    /**
     * Loads the information from the selected Pointing to the right JPanel.
     * @param index index of the selected item in the JList.
     */
    private void loadPointingData(int index) {
    	Pointing p = controler.getPointingFromString(list.getModel().getElementAt(index));
    	Employee emp = controler.getEmployee(p.getIdEmp());
    	String day = p.getTime().getDayOfWeek().toString().toLowerCase();
    	    	
    	if (Toolbox.isDayOfWeekend(p.getTime())){ // Should never happen
            lPointedTime.setText(" Hour pointed : WEEKEND, no pointing data");
    		lPlannedTime.setText(" Hour planned : WEEKEND, no pointing data");
    	} else {
    		lPointedTime.setText(" Hour pointed : " + p.getTime().getHour() + ":" + p.getTime().getMinute());

        	if (p.getTime().getHour()<12) {
        		lInOut.setText(" In/Out : In");
        		lPlannedTime.setText(" Hour planned : " + emp.getPlanningDay(day)[0]);
        	}
        	else {
        		lInOut.setText(" In/Out : Out");
        		lPlannedTime.setText(" Hour planned : " + emp.getPlanningDay(day)[1]);
        	}
    	}	
    }
    

    // Variables declaration - do not modify                     
    private javax.swing.JCheckBox checkBox;
    private javax.swing.JLabel lInOut;
    private javax.swing.JLabel lPointedTime;
    private javax.swing.JLabel lPlannedTime;
    private javax.swing.JLabel lHeader;
    private javax.swing.JList<String> list;
    private javax.swing.JPanel main;
    private javax.swing.JPanel header;
    private javax.swing.JPanel info;
    private javax.swing.JScrollPane scrollList;
    // End of variables declaration                   
}
