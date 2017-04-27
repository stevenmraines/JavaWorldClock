import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * DigitalClock extends JLabel and implements the Runnable interface
 * in order to provide a digital clock object.
 * 
 * @author Steven Raines
 *
 */
public class DigitalClock extends JLabel implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread localThread = new Thread(this);
	private String timeZoneString;
	private String s, m, h;
	private File fontFile = new File("fonts/digital-7.ttf");
	private Font clockFont;
	private FontMetrics clockFontMetrics;
	private Dimension clockSize;
	
	/**
	 * Default No-arg constructor. The time zone is set to match
	 * the default time zone of the computer.
	 */
	public DigitalClock() {
		timeZoneString = TimeZone.getDefault().getID();
		this.setDoubleBuffered(true);
		localThread.start();
		this.setOpaque(true);
		setClockFont();
		this.setPreferredSize(new Dimension(315, 100));
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * The time zone constructor allows you to specify a starting time zone
	 * for the clock.
	 * 
	 * @param timeZoneString a string representing the time zone of the clock.
	 */
	public DigitalClock(String timeZoneString) {
		this.timeZoneString = timeZoneString;
		this.setDoubleBuffered(true);
		localThread.start();
		this.setOpaque(true);
		setClockFont();
		this.setPreferredSize(new Dimension(315, 100));
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * The overridden run method updates the time displayed on the clock.
	 */
	@Override
	public void run() {
		while(true) {
			try {
				h = Integer.toString(Calendar.getInstance(
						TimeZone.getTimeZone(timeZoneString)).get(Calendar.HOUR));
				m = Integer.toString(Calendar.getInstance(
						TimeZone.getTimeZone(timeZoneString)).get(Calendar.MINUTE));
				s = Integer.toString(Calendar.getInstance(
						TimeZone.getTimeZone(timeZoneString)).get(Calendar.SECOND));
				
				// Apply any necessary leading 0s
				if(h.length() == 1) {
					h = "0" + h;
				}
				if(m.length() == 1) {
					m = "0" + m;
				}
				if(s.length() == 1) {
					s = "0" + s;
				}
				
				this.setText(h + ":" + m + ":" + s);
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sets the font to a large, green, digital font with
	 * a black background and sets the size of the component.
	 */
	private void setClockFont() {
		try {
			if(fontFile.exists()) {
				clockFont = Font.createFont(Font.TRUETYPE_FONT, fontFile)
						.deriveFont(Font.PLAIN, 100f);
				this.setFont(clockFont);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		this.setForeground(Color.GREEN);
		this.setBackground(Color.BLACK);
		
		String exampleText = "00:00:00";  // Represents max width of any clock string
		int maxWidth = 0;
		clockFontMetrics = getFontMetrics(clockFont);
		maxWidth = clockFontMetrics.stringWidth(exampleText);
		Insets inset = this.getInsets();
		clockSize = new Dimension(maxWidth + inset.left + inset.right,
	            clockFontMetrics.getHeight() + 44 + inset.top + inset.bottom);
		this.setPreferredSize(clockSize);
	}
	
	/**
	 * Mutator for the time zone of the clock.
	 * 
	 * @param timeZoneString a string representing the time zone.
	 */
	public void setTimeZoneString(String timeZoneString) {
		this.timeZoneString = timeZoneString;
	}
	
	/**
	 * Accessor for the time zone of the clock.
	 * 
	 * @return a string representing the time zone.
	 */
	public String getTimeZoneString() {
		return timeZoneString;
	}
}
