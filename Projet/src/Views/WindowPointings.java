package Views;

import javax.swing.*;

import java.awt.*;


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

		//panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setLayout(new BorderLayout());
		
		panel.add(createList(pointages), BorderLayout.WEST);
		panel.add(createPointingDetails(), BorderLayout.EAST);
	}
	
	/**
	 * Parameterized constructor
	 * @param frame Parent frame to get its sizes
	 */
	public WindowPointings(JFrame frame) {
		panel = new JPanel();
		
		panel.setBounds(0, WindowMain.headerHeight, frame.getWidth(), frame.getHeight()-WindowMain.headerHeight);
		panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()-WindowMain.headerHeight*2));
	
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(120, 120, 120));
		
		//panel.add(createList(pointages));
		panel.add(createList(pointages), BorderLayout.WEST);
		panel.add(createPointingDetails(), BorderLayout.EAST);
	}

	
	/**
	 * Creates the list view for the Pointings.
	 * @return	JList<Pointage> of the list.
	 */
	public JScrollPane createList(String[] items) {
		JList<String> list = new JList<String>(items);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.setPreferredSize(new Dimension(panel.getWidth()/2, panel.getHeight()));
		list.setLayoutOrientation(JList.VERTICAL);
		
		list.setVisibleRowCount(-1);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setViewportView(list);
		
		
		return scroll;
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
		detail.setBackground(new Color(240,240,240));
		detail.setBounds(10, 10, panel.getWidth()-20, panel.getHeight()-20);
		detail.setPreferredSize(new Dimension(panel.getWidth()/2, panel.getHeight()));

		detail.setLayout(new GridLayout(0,2));

		// "name" + SelectedEmployee.name, ...
		JLabel l1 = createLabel("First name : ", 10, 50 + (WindowPointings.defaultLabelHeight)*0);
		JLabel l2 = createLabel("Last name : ", 10, 50 + (WindowPointings.defaultLabelHeight)*1);
		JLabel l3 = createLabel("ID : ", 10, 50 + (WindowPointings.defaultLabelHeight)*2);
		
		
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
		
		label.setBounds(x, y, panel.getWidth()/2-2*x, WindowPointings.defaultLabelHeight-2*y);
		label.setPreferredSize(new Dimension(panel.getWidth()/2-2*x, WindowPointings.defaultLabelHeight-2*y));
		
		return label;
	}
}
