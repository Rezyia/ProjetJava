package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Controlers.ControlerNetwork;

public class WindowNetwork {
	private static JFrame f;
	public static JTextField tfPort;
	public static JTextField tfAddress;
	public static JLabel lPort;
	public static JLabel lAddress;
	public static JLabel lUpdate;
	public static JButton bUpdate;
	public static JButton bCancel;
	
	private static ControlerNetwork cn;
	
	//Fonction type pour cr√©er un champs pour taper l'adresse
	public static JTextField createTextFieldAddress() {
		final JTextField tf=new JTextField();  
	    tf.setText(cn.getAddress());
	    tf.setBounds(100,50, 150,20);
	    return tf;
	}
	
	//Fonction type pour cr√©er un champs pour taper le port
		public static JTextField createTextFieldPort() {
			final JTextField tf=new JTextField();  
		    tf.setText(cn.getPort()+"");
		    tf.setBounds(100,100, 150,20);
		    return tf;
		}

	//cr√©ation de boutons
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
	
	public static JButton createUpdateButton(){
	    JButton b=new JButton("Update");
	    b.setBounds(50,150,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		cn.setAddress(tfAddress.getText());
		    		cn.setPort(Integer.parseInt(tfPort.getText()));
		    		lUpdate.setText("Mise ‡ jour r\u00e9ussie");
		        }  
		    }); 
	    return b;
	}
	
	public void setWindow(ControlerNetwork cn) {
		this.cn = cn;
		
		f = new JFrame("Option rÈseau");
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
	
	public static void main(String[] args) {
		WindowNetwork wn = new WindowNetwork();
		wn.setWindow(new ControlerNetwork());
	}
}
