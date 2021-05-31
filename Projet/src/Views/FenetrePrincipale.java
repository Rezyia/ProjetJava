package Views;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlers.Principale;

public class FenetrePrincipale {
	// Attributes :
		public static int headerHeight = 40;
		private static JFrame f;
		
		
		public FenetrePrincipale() {
			f = new JFrame();
			f.setSize(1920,1080);
			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			f.add(createHeader());
			WindowPointings pointings = new WindowPointings(f);
			f.add(pointings.panel);
			
			f.setLayout(null);
			f.setVisible(true);
		}
		
		
		/** Function to create a new button for the header
		 * @param	title 	String containing title of the button
		 * @return 		JButton created with style for header 
		 */
		private static JButton createHeaderButton(String title) {
			
			JButton b = new JButton(title);
			
			//b.setBorderPainted(false);
			b.setFocusPainted(false);
			b.setContentAreaFilled(false);
			
			b.setPreferredSize(new Dimension(100, headerHeight));
			b.setMargin(new Insets(0,0,0,0));
			
			return b;
		}
		
		
		/** Function to create the header
		 *  @return			JPanel of the header
		 */
		private static JPanel createHeader() {
			JPanel header = new JPanel();
			header.setBounds(0, 0, FenetrePrincipale.f.getWidth(), headerHeight);
			header.setBackground(new Color(200, 200, 200));
			header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			
			JButton bHeaderPointages = createHeaderButton("Pointages");
			JButton bHeaderEmployees = createHeaderButton("Employees");
			JButton bHeaderSettings = createHeaderButton("Settings");
			JButton bHeaderDocumentation= createHeaderButton("ALED");
			
			// Ajout actions des boutons : 
			bHeaderPointages.addActionListener(e ->
			{
				FenetrePrincipale.loadMainWindowContent(new JPanel());
			});
			
			bHeaderEmployees.addActionListener(e ->
			{
				FenetrePrincipale.loadMainWindowContent(new JPanel());
			});
			
			bHeaderSettings.addActionListener(e ->
			{
				FenetrePrincipale.loadMainWindowContent(new JPanel());
			});
			
			bHeaderDocumentation.addActionListener(e ->
			{
				try {
					goToDoc("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			header.add(bHeaderPointages);
			header.add(bHeaderEmployees);
			header.add(bHeaderSettings);
			header.add(bHeaderDocumentation);
			
			return header;
		}
		
		/**
		 *  Function to load a new content for the main panel
		 *  @param	panel	new JPanel to display
		 */
		private static void loadMainWindowContent(JPanel panel) {
			//System.out.println("Nb elems : " + Principale.f.getComponentCount());
			//Principale.f.getComponent(0).setBackground(new Color(10, 10, 10));
			//Principale.f.removeAll();
			FenetrePrincipale.f.add(createHeader());
		}
		
		
		/**
		 * Function to open the javadoc
		 * @param path Path to javadoc index
		 * @throws IOException
		 */
		private static void goToDoc(String path) throws IOException {
			/*
			 * Il faut reussir a faire ouvrir la doc grace a cette fonction
			 */
			Desktop.getDesktop().open(new File(path));
		}
		
		public JFrame getWindow() {
			return FenetrePrincipale.f;
		}
}
