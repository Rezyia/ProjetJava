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
     * @param CtrlEmu : to obtain the method of pointing
     */
	public static JButton CreateButtonPoint(ControlerEmulator CtrlEmu){
		
	    JButton b=new JButton("Click Here");
	    b.setBounds(200,50,95,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    		LocalDateTime LDTnow = LocalDateTime.now();
		    		try {
					int id = Integer.parseInt(tfId.getText());
			    		CtrlEmu.sendPointing(id, LDTnow);
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
     * @param CtrlEmu : to obtain the method of create the window network
     */
public static JButton CreateButtonPort(ControlerEmulator CtrlEmu){
		
	    JButton b=new JButton("Change Port");
	    b.setBounds(300,50,110,20);
	    b.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	WindowNetwork WNet = new WindowNetwork();
		    	WNet.setWindow(CtrlEmu);
		    }
	    });
	    return b;
}
	

	
	/**
	 * Principal Method to create the principal window of the Emulator
	 * @param CtrlEmu : to obtain different method
	 */
	public static void setWindowEmu(ControlerEmulator CtrlEmu) throws IOException{
		f = new JFrame("Emulateur Pointeuse");
		f.setSize(760,440);
		
		//texte s'affichant quand on recupere l'id
		labelId = new JLabel(); 
		labelId.setBounds(50,100, 300,20);  
		
		labelTime = new JLabel(); 
		labelTime.setBounds(50,150, 150,20); 
		labelTimeRound = new JLabel(); 
		labelTimeRound.setBounds(50,200, 300,20);

		ShowTime(LocalDateTime.now());
		
		
		
		tfId= CreateTextField();
		tfId.setText("Veuillez rentrer votre ID");
		
		bpoint = CreateButtonPoint(CtrlEmu);
		bport = CreateButtonPort(CtrlEmu);
		 
	    f.add(bpoint);f.add(bport);f.add(tfId); f.add(labelId) ;f.add(labelTime);f.add(labelTimeRound);
		
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}
	
}
