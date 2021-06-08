package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

import Controlers.ControlerEmulator;
import Controlers.ControlerNetwork;
import Controlers.Toolbox;

import javax.swing.*;




public class WindowEmulator {
	private static JFrame f;
	public static JLabel labelId;
	public static JLabel labelTime;
	public static JLabel labelTimeRound;
	public static JTextField tfId;
	public static JButton bpoint;
	public static JButton bport;
	
	
	/**
     * Fonction to show the time on parameter on screen
     * @param Today LocalDateTime to show
     */
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
		labelTimeRound.setText(DayPointRound+"/"+MonthPointRound+" "+HourPointRound+"h"+MinPointRound+"m arrondi au quart d'heure pr\u00e8s");

		
		
	}
	
	/**
     * Create a field to enter an ID
     */
	public static JTextField CreateTextField() {
		final JTextField tf=new JTextField();  
	    tf.setBounds(50,50, 150,20);  
	    
	    return tf;
	}
	
	/**
     * Create the button who take the id on the field and show it on screen
     */
	public static JButton CreateButtonPoint(){
		
	    JButton b=new JButton("Click Here");
	    b.setBounds(200,50,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		LocalDateTime LDTnow = LocalDateTime.now();
		    		try {
					int id = Integer.parseInt(tfId.getText());
			    		//CtrlEmu = new ControlerEmulator();
			    		//CtrlEmu.sendPointing(id, LDTnow);
			    		labelId.setText("employ\u00e9 avec l'ID : "+id+" a \u00e9t\u00e9 point\u00e9");
		    		}
		    		catch(NumberFormatException e1) {
		    			labelId.setText("veuillez rentrer un ID valide");
		    		}
		    		
		    		ShowTime(LDTnow);
		        }  
		    }); 
	    return b;
	}
	
	/**
     * Button to access WindowNetwork
     */
public static JButton CreateButtonPort(){
		
	    JButton b=new JButton("Change Port");
	    b.setBounds(300,50,110,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	WindowNetwork WNet = new WindowNetwork();
		    	//Attention Controleur temporaire pour voir le bon fonctionnement de la fonction
		    	ControlerEmulator CtrlEmu = new ControlerEmulator();
		    	WNet.setWindow(CtrlEmu);
		    }
	    });
	    return b;
}
	

	
	
	public static void main(String[] args) throws IOException{
		f = new JFrame("Emulateur Pointeuse");
		f.setSize(1920,1080);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//texte s'affichant quand on rÃƒÂ©cupÃƒÂ¨re l'id
		labelId = new JLabel(); 
		labelId.setBounds(50,100, 300,20);  
		
		labelTime = new JLabel(); 
		labelTime.setBounds(50,150, 150,20); 
		labelTimeRound = new JLabel(); 
		labelTimeRound.setBounds(50,200, 300,20);

		ShowTime(LocalDateTime.now());
		
		
		
		tfId= CreateTextField();
		tfId.setText("Veuillez rentrer votre ID");
		
		bpoint = CreateButtonPoint();
		bport = CreateButtonPort();
		 
	    f.add(bpoint);f.add(bport);f.add(tfId); f.add(labelId) ;f.add(labelTime);f.add(labelTimeRound);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}
	
}
