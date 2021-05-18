package Controlers;

import java.awt.*;
import javax.swing.*;

public class Principale {

	static int headerHeight = 40;
	
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
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, f.getWidth(), headerHeight);
		header.setBackground(new Color(200, 200, 200)); // Pour faire la distinction pour l'instant
		header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		/* header layout (FlowLayout) :
		 * Left to Right -> boutons de selection pour chaque sous-fenetre 
		 */

		
		JPanel main = new JPanel();
		main.setBounds(0, 40, f.getWidth(), f.getHeight()-headerHeight);
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
		
		header.add(createHeaderButton("Pointages"));
		header.add(createHeaderButton("Employees"));
		header.add(createHeaderButton("Settings"));
		header.add(createHeaderButton("ALED"));
		
		f.add(header);
		f.add(main);
		f.setLayout(null);
		f.setVisible(true);
	}
}
