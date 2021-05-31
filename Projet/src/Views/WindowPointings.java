package Views;

import javax.swing.*;

import Controlers.Principale;
import Models.Pointage;

import java.io.*;
import java.awt.*;
import javax.swing.*;


/**
 * Class to modelize the windows for the pointings.
 */
public class WindowPointings {

	// Attributes :
	private static int defaultWidth = 720;
	private static int defaultHeight = 480;
	
	private static int defaultLabelHeight = 30;
	
	private String[] pointages = {"None"};

	public JPanel panel;
	
	
	/**
	 * Default constructor
	 */
	public WindowPointings() {
		panel = new JPanel();
		panel.setBounds(0, 40, defaultWidth, defaultHeight);

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		panel.add(createList(pointages));
		panel.add(createPointingDetails());
	}
	
	/**
	 * Parameterized constructor
	 * @param frame Parent frame to get its sizes
	 */
	public WindowPointings(JFrame frame) {
		panel = new JPanel();
		panel.setBounds(0, 40, frame.getWidth(), frame.getHeight()-FenetrePrincipale.headerHeight);

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setBackground(new Color(120, 120, 120));
		
		panel.add(createList(pointages));
		panel.add(createPointingDetails());
		
		System.out.println(panel.getWidth() + " - " + panel.getHeight());

	}

	
	/**
	 * Creates the list view for the Pointings.
	 * @return	JList<Pointage> of the list.
	 */
	public JList<String> createList(String[] items) {
		JList<String> list = new JList<String>(items);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		//JScrollPane scroller = new JScrollPane(list);
		//scroller.setPreferredSize(new Dimension());
		
		return list;
	}
	
	
	/**
	 * Updates the list with the DefaultListModel used.
	 * @param dlm {@link DefaultListModel} with the list elements.
	 */
	public void updateList(String[] items) {
		panel.remove(0);
		panel.add(createList(items));
	}
	
	/**
	 * Creates the details view for the selected Pointing.
	 * @return JPanel of the details
	 */
	public JPanel createPointingDetails() {
		JPanel detail = new JPanel();
		detail.setBounds(panel.getWidth()/2, 0, panel.getWidth()/2, panel.getHeight());
		GroupLayout layout = new GroupLayout(detail);
		detail.setLayout(layout);

		detail.setBackground(new Color(0,120,0));
		
		JLabel l1 = createLabel("First name : ", 10, 50 + (WindowPointings.defaultLabelHeight)*0);
		JLabel l2 = createLabel("Last name : ", 10, 50 + (WindowPointings.defaultLabelHeight)*1);
		JLabel l3 = createLabel("ID : ", 10, 50 + (WindowPointings.defaultLabelHeight)*2);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(l1)
				.addComponent(l2)
				.addComponent(l3)
		);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(l1)
							.addComponent(l2)
							.addComponent(l3)
		));
		
		detail.add(l1);
		detail.add(l2);
		detail.add(l3);
		
		return detail;
	}
	
	
	/**
	 * Creates a label
	 * @param text	String of the text to be displayed.
	 * @param x		Integer for the X position of the top left border of the label.
	 * @param y		Integer for the Y position of the top left border of the label.
	 * @return JLabel with the value <code>text</code> at the coordinates <code>X</code> ; <code>Y</code>.
	 */
	public JLabel createLabel(String text, int x, int y) {
		JLabel label = new JLabel(text);
		
		label.setBounds(x, y, panel.getWidth()/2-2*x, WindowPointings.defaultLabelHeight);
		
		return label;
	}
}
