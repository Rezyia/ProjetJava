package Controlers;

import java.awt.*;
import javax.swing.*;

public class Principale {

	static int headerHeight = 50;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, f.getWidth(), headerHeight);
		header.setBackground(new Color(200, 200, 200)); // Pour faire la distinction pour l'instant
		header.setLayout(new FlowLayout());
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

		
		f.add(header);
		f.setLayout(null);
		f.setVisible(true);
	}
}
