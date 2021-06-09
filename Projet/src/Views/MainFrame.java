/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javax.swing.SwingUtilities;

import Controlers.ControlerMain;

/**
 *
 * @author Rezyia
 */
public class MainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private static ControlerMain controler;
	
	private enum windowType {
		WINDOW_EMPLOYEES,
		WINDOW_POINTINGS,
		WINDOW_SETTINGS
	}
	
    public MainFrame() {
        initComponents();
    }
    
    public MainFrame(ControlerMain c) {
    	controler = c;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bPointings = new javax.swing.JButton();
        bEmployees = new javax.swing.JButton();
        bSettings = new javax.swing.JButton();
        bHelp = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        windowPointings = new WPointings(controler);
        windowEmployees = new WEmployees(controler);
        windowSettings = new WPointings(controler);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setExtendedState(1);
        setPreferredSize(new java.awt.Dimension(720, 480));

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.setForeground(new java.awt.Color(238, 238, 238));
        jPanel1.setPreferredSize(new java.awt.Dimension(572, 40));
        jPanel1.setLayout(new java.awt.GridLayout());

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
        jPanel1.add(bPointings);

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
        jPanel1.add(bEmployees);

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
        jPanel1.add(bSettings);

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
        jPanel1.add(bHelp);

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(windowPointings, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    public void changeWindow(windowType w) {
    	if (windowEmployees.getParent() != null) jPanel2.remove(windowEmployees);
    	if (windowSettings.getParent() != null)jPanel2.remove(windowSettings);
    	if (windowPointings.getParent() != null)jPanel2.remove(windowPointings);
    	
    	if (w==windowType.WINDOW_EMPLOYEES) jPanel2.add(windowEmployees);
    	else if (w==windowType.WINDOW_POINTINGS) jPanel2.add(windowPointings);
    	else if (w==windowType.WINDOW_SETTINGS) jPanel2.add(windowSettings);
    	
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
    	changeWindow(windowType.WINDOW_SETTINGS);
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

    
    public WPointings getWindowPointings() {
    	return windowPointings;
    }
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton bEmployees;
    private javax.swing.JButton bHelp;
    private javax.swing.JButton bPointings;
    private javax.swing.JButton bSettings;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private WPointings windowPointings;
    private WPointings windowSettings;
    private WEmployees windowEmployees;

    // End of variables declaration                   
}
