package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Controlers.ControlerNetwork;

public class WindowOption {
	private static JFrame f;
	public static JTextField tfPort;
	public static JTextField tfAddress;
	public static JLabel lPort;
	public static JLabel lAddress;
	public static JButton bUpdate;
	public static JButton bCancel;
	private static ControlerNetwork cn;
	
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
	
	//création de boutons
	public static JButton createCancelButton(){
	    JButton b=new JButton("Cancel");
	    b.setBounds(150,150,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		tfAddress.setText(cn.getAddress());
		    		tfPort.setText(cn.getPort()+"");
		        }  
		    }); 
	    return b;
	}
	
	public static JButton createUpdateButton(){
	    JButton b=new JButton("Update");
	    b.setBounds(50,150,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		cn.setAddress(tfAddress.getText());
		    		cn.setPort(Integer.parseInt(tfPort.getText()));
		        }  
		    }); 
	    return b;
	}
	
	public static void main(String[] args) {
		f = new JFrame("Option r�seau");
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		cn = new ControlerNetwork();
		
		lAddress = new JLabel("Adresse");
		lAddress.setBounds(50,50, 150,20);
		
		lPort = new JLabel("Port");
		lPort.setBounds(50,100, 150,20);
		
		tfAddress = createTextField();
		tfAddress.setText(cn.getAddress());
		tfAddress.setBounds(100,50, 150,20);
		
		tfPort = createTextField();
		tfPort.setText(cn.getPort()+"");
		tfPort.setBounds(100,100, 150,20);
		
		bCancel = createCancelButton();
		bUpdate = createUpdateButton();
		
		f.add(lAddress);
		f.add(lPort);
		f.add(tfAddress);
		f.add(tfPort);
		f.add(bUpdate);
		f.add(bCancel);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
	}
}
