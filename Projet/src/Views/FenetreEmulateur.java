package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.swing.*;


public class FenetreEmulateur {
private static JFrame f;
	
	public static JLabel labelId;
	public static JLabel labelTime;
	public static JTextField tfId;
	
	
	
	//Fonction pour afficher l'heure du jour
	public final static JLabel ShowTime(LocalDateTime Today) {
		
		
		
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
	
	//fonction mis en qu
	public LocalDateTime arrondiQuartHeure(LocalDateTime t) {
		int diff = t.getMinute()%15;
		LocalDateTime tqh = null;
		if(diff < 8) {
			tqh = t.minusMinutes(diff);
		}else if(diff >= 8) {
			tqh = t.plusMinutes(15-diff);
		}
		tqh = tqh.minusSeconds(tqh.getSecond());
		tqh = tqh.minusNanos(tqh.getNano());
		return tqh;
	}
	
	
	
	public static void main(String[] args) throws IOException{
		f = new JFrame("Emulateur Pointeuse");
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//texte s'affichant quand on récupère l'id
		labelId = new JLabel(); 
		labelId.setBounds(50,100, 150,20);  
		

		labelTime = ShowTime(LocalDateTime.now());
		
		
		
		tfId= CreateTextField();
		tfId.setText("Veuillez rentrer votre ID");
		
		JButton bpoint = CreateButton();
		bpoint.addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
	    		String Sid = tfId.getText();
	    		
	    		labelId.setText(Sid);
	    		labelTime = ShowTime(LocalDateTime.now());//essayer de metr
	        }  
	    });  
	    f.add(bpoint);f.add(tfId); f.add(labelId) ;f.add(labelTime);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}
	
}
