package Views;

import javax.swing.*;
import Controlers.ControlerNetwork;

public class WindowOption {
	private static JFrame f;
	public static JTextField tfPort;
	public static JTextField tfAddress;
	
	//Fonction type pour créer un champs pour taper l'id 
	public static JTextField createTextField() {
		final JTextField tf=new JTextField();  
	    tf.setBounds(50,50, 150,20);  
	    return tf;
	}
		
	//Fonction test pour essayer de récuper l'id
	public int catchPort( JTextField tf) {
		String sPort = tf.getText();
		int port=Integer.parseInt(sPort);  
		return port;
	}
	
	public static void main(String[] args) {
		f = new JFrame("Option r�seau");
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ControlerNetwork cn = new ControlerNetwork();
		
		tfAddress = createTextField();
		tfAddress.setText(cn.getAddress());
		tfAddress.setBounds(50,100, 150,20);
		
		tfPort = createTextField();
		tfPort.setText(cn.getPort()+"");
		tfPort.setBounds(50,150, 150,20);
		
		f.add(tfAddress);
		f.add(tfPort);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
	}
}
