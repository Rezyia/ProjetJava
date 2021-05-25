package Controlers;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import Views.WindowPointings;


/**
 * Class to control the main application
 */
public class Principale {

	// Attributes :
	public static int headerHeight = 40;
	private static JFrame f;
	
	
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
		header.setBounds(0, 0, Principale.f.getWidth(), headerHeight);
		header.setBackground(new Color(200, 200, 200));
		header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
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
		Principale.f.add(createHeader());
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
	
	
	/**
	 * Main program
	 * @param args	String Array for called arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		Principale.f = new JFrame();
		Principale.f.setSize(1920,1080);
		Principale.f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		/*
		JPanel main = new JPanel();
		main.setBounds(0, 40, Principale.f.getWidth(), Principale.f.getHeight()-headerHeight);
		main.setLayout(new BorderLayout());
		/* main layout (BorderLayout) :
		 *  West -> Liste des trucs utilisés
		 *  East -> Détails et actions pour truc sélectionné
		 */
		
		/*
		 * Techniquement il faudrait pouvoir charger ici les differents mains 
		 * en faisant "new WindowEmployees()" par exemple, et que ça charge 
		 * tout depuis le constructeur de la classe appelee
		 * 
		 * Par exemple : click sur bHeaderEmployees -> constructeur WindowEmployees
		 * 		-> crée un JPanel avec contenu de la fenetre (static ?) 
		 * 		-> ajoute le JPanel correspondant dans/à la place du main
		 */
		
		Principale.f.add(createHeader());
		
		// Test ajout pointages :
		WindowPointings pointings = new WindowPointings(Principale.f);
		Principale.f.add(pointings.panel);
		
		Principale.f.setLayout(null);
		Principale.f.setVisible(true);
	}
}
