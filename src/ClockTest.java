import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimeZone;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Java World Clock displays 8 24-hour clocks whose individual time zones
 * may be set using drop down menus.
 * 
 * @author Steven Raines
 *
 */
public class ClockTest {
	private static JFrame frame = new JFrame("Java World Clock");
	private static JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
	private static JPanel[] clockPanels = new JPanel[8];
	private static DigitalClock[] clocks = new DigitalClock[8];
	private static JComboBox<String> timeZoneSelector0 = new JComboBox<String>();
	private static JComboBox<String> timeZoneSelector1 = new JComboBox<String>();
	private static JComboBox<String> timeZoneSelector2 = new JComboBox<String>();
	private static JComboBox<String> timeZoneSelector3 = new JComboBox<String>();
	private static JComboBox<String> timeZoneSelector4 = new JComboBox<String>();
	private static JComboBox<String> timeZoneSelector5 = new JComboBox<String>();
	private static JComboBox<String> timeZoneSelector6 = new JComboBox<String>();
	private static JComboBox<String> timeZoneSelector7 = new JComboBox<String>();
	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu info = new JMenu("Info");
	private static JMenuItem about = new JMenuItem("About");
	
	/**
	 * The main method.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// Get a list of time zones
		String[] timeZoneArray = TimeZone.getAvailableIDs();
		
		// Populate time zone selectors
		for(String tz: timeZoneArray) {
			timeZoneSelector0.addItem(tz);
			timeZoneSelector1.addItem(tz);
			timeZoneSelector2.addItem(tz);
			timeZoneSelector3.addItem(tz);
			timeZoneSelector4.addItem(tz);
			timeZoneSelector5.addItem(tz);
			timeZoneSelector6.addItem(tz);
			timeZoneSelector7.addItem(tz);
		}
		
		// Add actionListeners to the drop down menus
		timeZoneSelector0.addActionListener(new SelectorActionListener());
		timeZoneSelector1.addActionListener(new SelectorActionListener());
		timeZoneSelector2.addActionListener(new SelectorActionListener());
		timeZoneSelector3.addActionListener(new SelectorActionListener());
		timeZoneSelector4.addActionListener(new SelectorActionListener());
		timeZoneSelector5.addActionListener(new SelectorActionListener());
		timeZoneSelector6.addActionListener(new SelectorActionListener());
		timeZoneSelector7.addActionListener(new SelectorActionListener());
		
		// Create the clocks
		clocks[0] = new DigitalClock(Color.RED);
		clocks[1] = new DigitalClock(Color.ORANGE);
		clocks[2] = new DigitalClock(Color.YELLOW);
		clocks[3] = new DigitalClock(Color.GREEN);
		clocks[4] = new DigitalClock(Color.BLUE);
		clocks[5] = new DigitalClock(new Color(94, 0, 163));
		clocks[6] = new DigitalClock(new Color(200, 0, 200));
		clocks[7] = new DigitalClock(Color.PINK);
		
		// Set the current items in the lists as the current time zones of the clocks
		timeZoneSelector0.setSelectedItem(clocks[0].getTimeZoneString());
		timeZoneSelector1.setSelectedItem(clocks[1].getTimeZoneString());
		timeZoneSelector2.setSelectedItem(clocks[2].getTimeZoneString());
		timeZoneSelector3.setSelectedItem(clocks[3].getTimeZoneString());
		timeZoneSelector4.setSelectedItem(clocks[4].getTimeZoneString());
		timeZoneSelector5.setSelectedItem(clocks[5].getTimeZoneString());
		timeZoneSelector6.setSelectedItem(clocks[6].getTimeZoneString());
		timeZoneSelector7.setSelectedItem(clocks[7].getTimeZoneString());
		
		// Initialize clock panels
		for(int i = 0; i < clockPanels.length; i++) {
			clockPanels[i] = new JPanel(new BorderLayout(5, 5));
			Border clockPanelBorder = new LineBorder(Color.LIGHT_GRAY, 1);
			Border margin = new EmptyBorder(5, 5, 5, 5);
			clockPanels[i].setBorder(new CompoundBorder(clockPanelBorder, margin));
		}
		
		// Add selectors and clocks to the clock panels
		clockPanels[0].add(timeZoneSelector0, BorderLayout.NORTH);
		clockPanels[1].add(timeZoneSelector1, BorderLayout.NORTH);
		clockPanels[2].add(timeZoneSelector2, BorderLayout.NORTH);
		clockPanels[3].add(timeZoneSelector3, BorderLayout.NORTH);
		clockPanels[4].add(timeZoneSelector4, BorderLayout.NORTH);
		clockPanels[5].add(timeZoneSelector5, BorderLayout.NORTH);
		clockPanels[6].add(timeZoneSelector6, BorderLayout.NORTH);
		clockPanels[7].add(timeZoneSelector7, BorderLayout.NORTH);
		clockPanels[0].add(clocks[0], BorderLayout.CENTER);
		clockPanels[1].add(clocks[1], BorderLayout.CENTER);
		clockPanels[2].add(clocks[2], BorderLayout.CENTER);
		clockPanels[3].add(clocks[3], BorderLayout.CENTER);
		clockPanels[4].add(clocks[4], BorderLayout.CENTER);
		clockPanels[5].add(clocks[5], BorderLayout.CENTER);
		clockPanels[6].add(clocks[6], BorderLayout.CENTER);
		clockPanels[7].add(clocks[7], BorderLayout.CENTER);
		
		// Add GUI elements to mainPanel
		for(int i = 0; i < clockPanels.length; i++) {
			mainPanel.add(clockPanels[i]);
		}
		
		// Add the menu stuff
		menuBar.add(info);
		info.add(about);
		frame.setJMenuBar(menuBar);
		about.addActionListener(new MenuActionListener());
		
		// Add everything to and setup the JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);
		frame.setSize(580, 540);
		frame.setVisible(true);
	}

	/**
	 * The action listener for the drop down time zone selection menus.
	 * 
	 * @author Steven Raines
	 *
	 */
	private static class SelectorActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> jcb = (JComboBox<String>)e.getSource();
			
			if(jcb == timeZoneSelector0) {
				clocks[0].setTimeZoneString(jcb.getSelectedItem().toString());
			}
			else if(jcb == timeZoneSelector1) {
				clocks[1].setTimeZoneString(jcb.getSelectedItem().toString());
			}
			else if(jcb == timeZoneSelector2) {
				clocks[2].setTimeZoneString(jcb.getSelectedItem().toString());
			}
			else if(jcb == timeZoneSelector3) {
				clocks[3].setTimeZoneString(jcb.getSelectedItem().toString());
			}
			else if(jcb == timeZoneSelector4) {
				clocks[4].setTimeZoneString(jcb.getSelectedItem().toString());
			}
			else if(jcb == timeZoneSelector5) {
				clocks[5].setTimeZoneString(jcb.getSelectedItem().toString());
			}
			else if(jcb == timeZoneSelector6) {
				clocks[6].setTimeZoneString(jcb.getSelectedItem().toString());
			}
			else {
				clocks[7].setTimeZoneString(jcb.getSelectedItem().toString());
			}
		}		
	}
	
	/**
	 * The action listener for the menu bar of the program.
	 * 
	 * @author Steven Raines
	 *
	 */
	private static class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Created by: Steven Raines\n"
					+ "Version: 1.0");
		}
	}
}
