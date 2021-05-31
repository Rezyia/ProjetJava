package views;

import javax.swing.*;

import Controlers.Principale;
import Models.Pointage;

import java.io.*;
import java.awt.*;
import javax.swing.*;

public class WindowEmployees{
  // Attributes :
	private static int defaultWidth = 720;
	private static int defaultHeight = 480;
	
	private static int defaultLabelHeight = 30;
	
	private DefaultListModel<String> dlmEmployees;
  
  public WindowEmployees() {
		panel = new JPanel();
		panel.setBounds(0, 40, defaultWidth, defaultHeight);

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		dlmEmployees = new DefaultListModel<String>();
		
		panel.add(createList(dlmEmployees));
    panel.add(createEmployeeDetails());
  }
  
  public WindowEmployee(JFrame frame){
    panel = new JPanel();
		panel.setBounds(0, 40, frame.getWidth(), frame.getHeight()-FenetrePrincipale.headerHeight);
	
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setBackground(new Color(120, 120, 120));
		
		panel.add(createList(employees));
		panel.add(createEmployeeDetails());
		
		System.out.println(panel.getWidth() + " - " + panel.getHeight());

	}
}
