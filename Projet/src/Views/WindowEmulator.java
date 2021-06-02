package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import Controlers.Toolbox;

import javax.swing.*;




public class WindowEmulator {
	private static JFrame f;
	public static JLabel labelId;
	public static JLabel labelTime;
	public static JLabel labelTimeRound;
	public static JTextField tfId;
	public static JButton bpoint;
	
	
	
	//Fonction pour afficher l'heure du jour
	public static void ShowTime(LocalDateTime Today) {	
		
		int MonthPoint = Today.getMonthValue();
		int DayPoint = Today.getDayOfMonth();
		int HourPoint = Today.getHour();
		int MinPoint = Today.getMinute();
		labelTime.setText(DayPoint+"/"+MonthPoint+" "+HourPoint+"h"+MinPoint+"m");
		

		LocalDateTime ldtRound; 
		ldtRound = Toolbox.roundToNearestQuarter(Today);
		int MonthPointRound = ldtRound.getMonthValue();
		int DayPointRound = ldtRound.getDayOfMonth();
		int HourPointRound = ldtRound.getHour();
		int MinPointRound = ldtRound.getMinute();
		labelTimeRound.setText(DayPointRound+"/"+MonthPointRound+" "+HourPointRound+"h"+MinPointRound+"m arrondi au quart d'heure près");

		
		
	}
	
	//Fonction type pour crÃ©er un champs pour taper l'id 
	public static JTextField CreateTextField() {
		final JTextField tf=new JTextField();  
	    tf.setBounds(50,50, 150,20);  
	    
	    return tf;
	}
	
	//crÃ©ation de bouton
	public static JButton CreateButton(){
		
	    JButton b=new JButton("Click Here");
	    b.setBounds(200,50,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		LocalDateTime LDTnow = LocalDateTime.now();
		    		try {
					int id = Integer.parseInt(tfId.getText());
			    		//CtrlEmu = new ControlerEmulator();
			    		//CtrlEmu.sendPointing(id, LDTnow);
			    		labelId.setText("employé avec l'ID : "+id+" a été pointé");
		    		}
		    		catch(NumberFormatException e1) {
		    			labelId.setText("veuillez rentrer un ID valide");
		    		}
		    		
		    		ShowTime(LDTnow);
		        }  
		    }); 
	    return b;
	}
	
	//Fonction test pour essayer de rÃ©cuper l'id
	public int catchId( JTextField tf) {
		
		String Sid = tf.getText();
		int Iid=Integer.parseInt(Sid);  
		
		return Iid;
	}
	
	
	
	
	public static void main(String[] args) throws IOException{
		f = new JFrame("Emulateur Pointeuse");
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//texte s'affichant quand on rÃ©cupÃ¨re l'id
		labelId = new JLabel(); 
		labelId.setBounds(50,100, 300,20);  
		
		labelTime = new JLabel(); 
		labelTime.setBounds(50,150, 150,20); 
		labelTimeRound = new JLabel(); 
		labelTimeRound.setBounds(50,200, 300,20);

		ShowTime(LocalDateTime.now());
		
		
		
		tfId= CreateTextField();
		tfId.setText("Veuillez rentrer votre ID");
		
		bpoint = CreateButton();
		 
	    f.add(bpoint);f.add(tfId); f.add(labelId) ;f.add(labelTime);f.add(labelTimeRound);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}
	
}
