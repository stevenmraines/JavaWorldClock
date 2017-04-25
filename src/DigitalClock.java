import java.awt.Font;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JTextField;

public class DigitalClock extends JTextField implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread localThread = new Thread(this);
	private String timeZoneString;
	private int s, m, h;
	
	// No-arg constructor
	public DigitalClock() {
		timeZoneString = TimeZone.getDefault().getID();
		this.setDoubleBuffered(true);
		localThread.start();
		this.setEditable(false);
	}
	
	// Time Zone constructor
	public DigitalClock(String timeZoneString) {
		this.timeZoneString = timeZoneString;
		this.setDoubleBuffered(true);
		localThread.start();
		this.setEditable(false);
	}

	@Override
	public void run() {
		while(true) {
			try {
				h = Calendar.getInstance(TimeZone.getTimeZone(timeZoneString)).get(Calendar.HOUR);
				m = Calendar.getInstance(TimeZone.getTimeZone(timeZoneString)).get(Calendar.MINUTE);
				s = Calendar.getInstance(TimeZone.getTimeZone(timeZoneString)).get(Calendar.SECOND);
				
				this.setText(h + ":" + m + ":" + s);
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setTimeZoneString(String timeZoneString) {
		this.timeZoneString = timeZoneString;
	}
	
	public String getTimeZoneString() {
		return timeZoneString;
	}
}
