
package Views;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.SwingUtilities;

import Controlers.ControlerMain;
import Controlers.ControlerNetwork;


public class MainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 5268180501824684352L;
	
	private static ControlerMain controler;
	
	private enum windowType {
		WINDOW_EMPLOYEES,
		WINDOW_POINTINGS,
		WINDOW_SETTINGS
	}
	
    public MainFrame() {
        initComponents();
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public MainFrame(ControlerMain c) {
    	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	ControlerMain.serialize(c);
            	
                e.getWindow().dispose();
                
                System.out.println("Closing application.");
                System.exit(0);
            }
            
            @Override
            public void windowOpened(WindowEvent e) {
            	ControlerMain.deserialize(c);
            	SwingUtilities.updateComponentTreeUI(c.getFrame());
            }
    	});
    	controler = c;
        initComponents();
    }


    /**
     * Initialize the components of the MainFrame
     */
    private void initComponents() {

        header = new javax.swing.JPanel();
        bPointings = new javax.swing.JButton();
        bEmployees = new javax.swing.JButton();
        bSettings = new javax.swing.JButton();
        bHelp = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        windowPointings = new WPointings(controler);
        windowEmployees = new WEmployees(controler);
        //windowSettings = new WSettings(controler);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setExtendedState(1);
        setPreferredSize(new java.awt.Dimension(720, 480));

        header.setBackground(new java.awt.Color(238, 238, 238));
        header.setForeground(new java.awt.Color(238, 238, 238));
        header.setPreferredSize(new java.awt.Dimension(572, 40));
        header.setLayout(new java.awt.GridLayout());

        bPointings.setBackground(new java.awt.Color(238, 238, 238));
        bPointings.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bPointings.setText("Pointings");
        bPointings.setBorderPainted(false);
        bPointings.setFocusPainted(false);
        bPointings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bPointings.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bPointings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bHelpMouseEntered(evt);
            }
        });
        bPointings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPointingsActionPerformed(evt);
            }
        });
        header.add(bPointings);

        bEmployees.setBackground(new java.awt.Color(153, 153, 153));
        bEmployees.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bEmployees.setForeground(new java.awt.Color(255, 255, 255));
        bEmployees.setBorderPainted(false);
        bEmployees.setFocusPainted(false);
        bEmployees.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bEmployees.setLabel("Employees");
        bEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bHelpMouseEntered(evt);
            }
        });
        bEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEmployeesActionPerformed(evt);
            }
        });
        header.add(bEmployees);

        bSettings.setBackground(new java.awt.Color(153, 153, 153));
        bSettings.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bSettings.setForeground(new java.awt.Color(255, 255, 255));
        bSettings.setBorderPainted(false);
        bSettings.setFocusPainted(false);
        bSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bSettings.setLabel("Settings");
        bSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bHelpMouseEntered(evt);
            }
        });
        bSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSettingsActionPerformed(evt);
            }
        });
        header.add(bSettings);

        bHelp.setBackground(new java.awt.Color(153, 153, 153));
        bHelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bHelp.setForeground(new java.awt.Color(255, 255, 255));
        bHelp.setBorderPainted(false);
        bHelp.setFocusPainted(false);
        bHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bHelp.setLabel("Documentation");
        bHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHelpActionPerformed(evt);
            }
        });
        header.add(bHelp);

        content.setLayout(new java.awt.BorderLayout());
        content.add(windowPointings, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * Resets the style of a button
     * @param b : JButton to reset
     */
    public void resetButtonStyle(JButton b) {
    	b.setBackground(new java.awt.Color(153, 153, 153));
    	b.setForeground(new java.awt.Color(255, 255, 255));
    }
    
    /**
     * Switch to another window.
     * @param w : windowType : <code>WINDOW_EMPLOYEES</code> ,<code>WINDOW_POINTINGS</code> or <code>WINDOW_SETTINGS</code> (WINDOW_SETTINGS is deprecated)
     */
    public void changeWindow(windowType w) {        
        resetButtonStyle(bEmployees);
		resetButtonStyle(bHelp);
		resetButtonStyle(bPointings);
		resetButtonStyle(bSettings);
        
    	if (windowEmployees.getParent() != null) content.remove(windowEmployees);
    	if (windowPointings.getParent() != null)content.remove(windowPointings);
    	
    	if (w==windowType.WINDOW_EMPLOYEES) {
    		content.add(windowEmployees);
    		bEmployees.setBackground(new java.awt.Color(238, 238, 238));
    		bEmployees.setForeground(new java.awt.Color(0, 0, 0));
    		windowEmployees.updateUI();

    	} else if (w==windowType.WINDOW_POINTINGS) {
    		content.add(windowPointings);
    		bPointings.setBackground(new java.awt.Color(238, 238, 238));
    		bPointings.setForeground(new java.awt.Color(0, 0, 0));
    		windowPointings.updateUI();

    	}
    	/*
    	else if (w==windowType.WINDOW_SETTINGS) {
    		bSettings.setBackground(new java.awt.Color(238, 238, 238));
    		bSettings.setForeground(new java.awt.Color(0, 0, 0));
    		WindowNetwork wn = new WindowNetwork();
    		wn.setWindow(controler);
    	}*/
    	
    	SwingUtilities.updateComponentTreeUI(this);
    }
    

    private void bPointingsActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	changeWindow(windowType.WINDOW_POINTINGS);
    }                                          

    private void bHelpMouseEntered(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
    }                                  

    private void bEmployeesActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	changeWindow(windowType.WINDOW_EMPLOYEES);
    }                                          

    private void bSettingsActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
		WindowNetwork wn = new WindowNetwork();
		wn.setWindow(controler);
    }                                         

    private void bHelpActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	
        /* Create and display the form */
    }

    
    /**
     * Updates the lists contained in the object
     */
    public void updateLists() {
    	windowEmployees.updateList();
    	windowPointings.updateList();
    }
     
    
    /**
     * Returns a reference to the WPointings contained in the object
     * @return
     */
    public WPointings getWindowPointings() {
    	return windowPointings;
    }
    
    
    private javax.swing.JButton bEmployees;
    private javax.swing.JButton bHelp;
    private javax.swing.JButton bPointings;
    private javax.swing.JButton bSettings;
    private javax.swing.JPanel header;
    private javax.swing.JPanel content;
    private WPointings windowPointings;
    private WEmployees windowEmployees;
    //private WSettings windowSettings;
    
    // End of variables declaration                   

}
