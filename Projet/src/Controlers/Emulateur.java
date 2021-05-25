package Controlers;

import java.io.IOException;

import javax.swing.*;
import java.awt.event.*;  
import java.time.*;

public class Emulateur {
	
	private static JFrame f;
	
	//Fonction pour afficher l'heure du jour
	public final static JLabel ShowTime() {
		LocalDateTime Today = LocalDateTime.now();
		
		
		int MonthPoint = Today.getMonthValue();

		int DayPoint = Today.getDayOfMonth();
		int HourPoint = Today.getHour();
		int MinPoint = Today.getMinute();
		
		
		final JLabel label = new JLabel();
		label.setBounds(50,150, 150,20);  
		label.setText(DayPoint+"/"+MonthPoint+" "+HourPoint+"h"+MinPoint+"m");
		return label;
	}
	
	//Fonction type pour créer un champs pour taper l'id 
	public final static JTextField CreateTextField() {
		final JTextField tf=new JTextField();  
	    tf.setBounds(50,50, 150,20);  
	    
	    return tf;
	}
	
	//création de bouton
	public static JButton CreateButton(){
		
	    JButton b=new JButton("Click Here");
	    b.setBounds(200,50,95,20);
	    return b;
	}
	
	//Fonction test pour essayer de récuper l'id
	public int catchId( JTextField tf) {
		
		String Sid = tf.getText();
		int Iid=Integer.parseInt(Sid);  
		
		return Iid;
	}
	
	
	
	public static void main(String[] args) throws IOException{
		Emulateur.f = new JFrame("Emulateur Pointeuse");
		Emulateur.f.setSize(1920,1080);
		Emulateur.f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//texte s'affichant quand on récupère l'id
		final JLabel labelId = new JLabel(); 
		labelId.setBounds(50,100, 150,20);  
		
		final JLabel labelTime = ShowTime();
		
		final JTextField tf= CreateTextField();
		tf.setText("Veuillez rentrer votre ID");
		
		JButton b = CreateButton();
	    b.addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
	    		String Sid = tf.getText();
	    		
	    		labelId.setText(Sid);
	        }  
	    });  
	    f.add(b);f.add(tf); f.add(labelId) ;f.add(labelTime);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}

}
