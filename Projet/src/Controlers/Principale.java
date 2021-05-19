package Controlers;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Principale {

	// Attributes :
	private static int headerHeight = 40;
	private static JFrame f;
	
	
	/* Function to create a new button for the header
	 *  - In : String containing title of the button
	 *  - Out : JButton created with style for header 
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
	
	
	/* Function to create the header
	 *  - Out : JPanel of the header
	 */
	private static JPanel createHeader() {
		JPanel header = new JPanel();
		header.setBounds(0, 0, Principale.f.getWidth(), headerHeight);
		header.setBackground(new Color(200, 200, 200)); // Pour faire la distinction pour l'instant
		header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		/* header layout (FlowLayout) :
		 * Left to Right -> boutons de selection pour chaque sous-fenetre 
		 */
		JButton bHeaderPointages = createHeaderButton("Pointages");
		JButton bHeaderEmployees = createHeaderButton("Employees");
		JButton bHeaderSettings = createHeaderButton("Settings");
		JButton bHeaderDocumentation= createHeaderButton("ALED");
		
		// Ajout actions des boutons : 
		bHeaderPointages.addActionListener(e ->
		{
			Principale.loadMainWindowContent(new JPanel());
		});
		
		bHeaderEmployees.addActionListener(e ->
		{
			Principale.loadMainWindowContent(new JPanel());
		});
		
		bHeaderSettings.addActionListener(e ->
		{
			Principale.loadMainWindowContent(new JPanel());
		});
		
		bHeaderDocumentation.addActionListener(e ->
		{
			try {
				goToDoc();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		header.add(bHeaderPointages);
		header.add(bHeaderEmployees);
		header.add(bHeaderSettings);
		header.add(bHeaderDocumentation);
		
		return header;
	}
	
	/* Function to load a new content for the main panel
	 *  - In : new JPanel to display 
	 */
	private static void loadMainWindowContent(JPanel panel) {
		//System.out.println("Nb elems : " + Principale.f.getComponentCount());
		//Principale.f.getComponent(0).setBackground(new Color(10, 10, 10));
		//Principale.f.removeAll();
		Principale.f.add(createHeader());
	}
	
	// Fonction pour ouvrir la doc
	private static void goToDoc() throws IOException {
		/*
		 * Il faut reussir a faire ouvrir la doc grace a cette fonction
		 */
		Desktop.getDesktop().open(new File(""));
	}
	
	
	
	public static void main(String[] args) throws IOException{
		Principale.f = new JFrame();
		Principale.f.setSize(1920,1080);
		Principale.f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
		JPanel main = new JPanel();
		main.setBounds(0, 40, Principale.f.getWidth(), Principale.f.getHeight()-headerHeight);
		main.setLayout(new BorderLayout());
		/* main layout (BorderLayout) :
		 *  West -> Liste des trucs utilis�s
		 *  East -> D�tails et actions pour truc s�lectionn�
		 */
		
		/*
		 * Techniquement il faudrait pouvoir charger ici les differents mains 
		 * en faisant "new WindowEmployees()" par exemple, et que �a charge 
		 * tout depuis le constructeur de la classe appelee
		 * 
		 * Par exemple : click sur bHeaderEmployees -> constructeur WindowEmployees
		 * 		-> cr�e un JPanel avec contenu de la fenetre (static ?) 
		 * 		-> ajoute le JPanel correspondant dans/� la place du main
		 */
		
		Principale.f.add(createHeader());
		//Principale.f.add(main);
		Principale.f.setLayout(null);
		Principale.f.setVisible(true);
	}
}
