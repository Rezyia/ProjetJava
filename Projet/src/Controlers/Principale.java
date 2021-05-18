package Controlers;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Principale {

	static int headerHeight = 40;
	
	/* Function to create a new button for the header
	 *  - In : String containing title of the button
	 *  - Out : JButton created with style for header 
	 */
	private static JButton createHeaderButton(String title, Function<Void, Void> function) {
		
		JButton b = new JButton(title);
		
		//b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setContentAreaFilled(false);
		
		b.setPreferredSize(new Dimension(100, headerHeight));
		b.setMargin(new Insets(0,0,0,0));
		
		return b;
	}
	
	private static void goToDoc() throws IOException {
		/*
		 * Il faut reussir a faire ouvrir la doc grace a cette fonction
		 */
		Desktop.getDesktop().open(new File(""));
	}
	
	public static void main(String[] args) throws IOException{
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
		
		
		JButton bHeaderPointages = createHeaderButton("Pointages");
		JButton bHeaderEmployees = createHeaderButton("Employees");
		JButton bHeaderSettings = createHeaderButton("Settings");
		JButton bHeaderDocumentation= createHeaderButton("ALED");
		
		// Ajout 
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
		
		f.add(header);
		f.add(main);
		f.setLayout(null);
		f.setVisible(true);
	}
}
