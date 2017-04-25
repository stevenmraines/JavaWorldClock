import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimeZone;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClockTest {
	// GUI objects
	private static JFrame frame = new JFrame("Java World Clock");
	private static JPanel mainPanel = new JPanel(new GridBagLayout());
	private static JPanel[] selectorPanels = new JPanel[8];
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
	private static GridBagConstraints gbc = new GridBagConstraints();
	
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
		for(int i = 0; i < clocks.length; i++) {
			clocks[i] = new DigitalClock();
		}
		
		// Set the current items in the lists as the current time zones of the clocks
		timeZoneSelector0.setSelectedItem(clocks[0].getTimeZoneString());
		timeZoneSelector1.setSelectedItem(clocks[1].getTimeZoneString());
		timeZoneSelector2.setSelectedItem(clocks[2].getTimeZoneString());
		timeZoneSelector3.setSelectedItem(clocks[3].getTimeZoneString());
		timeZoneSelector4.setSelectedItem(clocks[4].getTimeZoneString());
		timeZoneSelector5.setSelectedItem(clocks[5].getTimeZoneString());
		timeZoneSelector6.setSelectedItem(clocks[6].getTimeZoneString());
		timeZoneSelector7.setSelectedItem(clocks[7].getTimeZoneString());
		
		// Initialize selector panels and clock panels
		for(int i = 0; i < selectorPanels.length; i++) {
			selectorPanels[i] = new JPanel();
			clockPanels[i] = new JPanel();
		}
		
		// Add selectors and clocks to their panels
		selectorPanels[0].add(timeZoneSelector0);
		selectorPanels[1].add(timeZoneSelector1);
		selectorPanels[2].add(timeZoneSelector2);
		selectorPanels[3].add(timeZoneSelector3);
		selectorPanels[4].add(timeZoneSelector4);
		selectorPanels[5].add(timeZoneSelector5);
		selectorPanels[6].add(timeZoneSelector6);
		selectorPanels[7].add(timeZoneSelector7);
		clockPanels[0].add(clocks[0]);
		clockPanels[1].add(clocks[1]);
		clockPanels[2].add(clocks[2]);
		clockPanels[3].add(clocks[3]);
		clockPanels[4].add(clocks[4]);
		clockPanels[5].add(clocks[5]);
		clockPanels[6].add(clocks[6]);
		clockPanels[7].add(clocks[7]);
		
		// Add GUI elements to mainPanel
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(selectorPanels[0], gbc);
		gbc.gridx = 1;
		mainPanel.add(selectorPanels[1], gbc);
		gbc.gridx = 2;
		mainPanel.add(selectorPanels[2], gbc);
		gbc.gridx = 3;
		mainPanel.add(selectorPanels[3], gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(clockPanels[0], gbc);
		gbc.gridx = 1;
		mainPanel.add(clockPanels[1], gbc);
		gbc.gridx = 2;
		mainPanel.add(clockPanels[2], gbc);
		gbc.gridx = 3;
		mainPanel.add(clockPanels[3], gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		mainPanel.add(selectorPanels[4], gbc);
		gbc.gridx = 1;
		mainPanel.add(selectorPanels[5], gbc);
		gbc.gridx = 2;
		mainPanel.add(selectorPanels[6], gbc);
		gbc.gridx = 3;
		mainPanel.add(selectorPanels[7], gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		mainPanel.add(clockPanels[4], gbc);
		gbc.gridx = 1;
		mainPanel.add(clockPanels[5], gbc);
		gbc.gridx = 2;
		mainPanel.add(clockPanels[6], gbc);
		gbc.gridx = 3;
		mainPanel.add(clockPanels[7], gbc);
		
		// Add everything to and setup the JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}

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
}
