package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Controlers.ControlerNetwork;

/**
 * Fen�tre pour �diter les options de r�seau
 * @author dylan
 *
 */
public class WindowNetwork {
	
	private static JFrame f;
	private static JTextField tfPort;
	private static JTextField tfAddress;
	private static JLabel lPort;
	private static JLabel lAddress;
	private static JLabel lUpdate;
	private static JButton bUpdate;
	private static JButton bCancel;
	
	private static ControlerNetwork cn;
	
	/**
	 * Fonction type pour créer un champs pour taper l'adresse
	 * @return Un JTextField pour taper l'adresse
	 */
	public static JTextField createTextFieldAddress() {
		final JTextField tf=new JTextField();  
	    tf.setText(cn.getAddress());
	    tf.setBounds(100,50, 150,20);
	    return tf;
	}
	
	/**
	 * Fonction type pour créer un champs pour taper le port
	 * @return Un JTextField pour taper le port
	 */
		public static JTextField createTextFieldPort() {
			final JTextField tf=new JTextField();  
		    tf.setText(cn.getPort()+"");
		    tf.setBounds(100,100, 150,20);
		    return tf;
		}

	/**
	 * création de boutons d'annulation
	 * @return Un JButton qui r�initialise les valeurs dans les textfield
	 */
	public static JButton createCancelButton(){
	    JButton b=new JButton("Cancel");
	    b.setBounds(150,150,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		tfAddress.setText(cn.getAddress());
		    		tfPort.setText(cn.getPort()+"");
		    		lUpdate.setText("Valeurs r\u00e9initialis\u00e9es");
		        }  
		    }); 
	    return b;
	}
	
	/**
	 * cr�ation de boutons de modification
	 */
	public static JButton createUpdateButton(){
	    JButton b=new JButton("Update");
	    b.setBounds(50,150,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		cn.setAddress(tfAddress.getText());
		    		cn.setPort(Integer.parseInt(tfPort.getText()));
		    		lUpdate.setText("Mise � jour r\u00e9ussie");
		        }  
		    }); 
	    return b;
	}
	
	/**
	 * Cr�e et affiche la fen�tre de param�tre r�seau
	 * @param cn Le ControlerNetwork
	 */
	public void setWindow(ControlerNetwork cn) {
		this.cn = cn;
		
		f = new JFrame("Option r�seau");
		f.setSize(720,480);
		//f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		lAddress = new JLabel("Adresse");
		lAddress.setBounds(50,50, 150,20);
		
		lPort = new JLabel("Port");
		lPort.setBounds(50,100, 150,20);
		
		lUpdate = new JLabel("");
		lUpdate.setBounds(50,125,300,20);
		
		tfAddress = createTextFieldAddress();
		tfPort = createTextFieldPort();
		
		bCancel = createCancelButton();
		bUpdate = createUpdateButton();
		
		f.add(lAddress);
		f.add(lPort);
		f.add(tfAddress);
		f.add(tfPort);
		f.add(lUpdate);
		f.add(bUpdate);
		f.add(bCancel);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
	}
	
	/**
	 * Main de test, pour v�rifier le fonctionnement
	 * @param args
	 */
	public static void main(String[] args) {
		WindowNetwork wn = new WindowNetwork();
		wn.setWindow(new ControlerNetwork());
	}
}
