import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Alarm extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1;  // why ???
	private JLabel currentTime;
	private JLabel currentDate;
	public JLabel labelTime;
	public JLabel labelDate;
	private Thread thread;
	private String current_time;
	private Times1 time1;
	private Times2 time2;
	private Times3 time3;
	public JFrame frame;
	public JPanel panel;
	
	public Alarm(){
		
		JLabel Alarm1;
		JLabel Alarm2;
		JLabel Alarm3;
	
		this.frame = new JFrame();
		this.frame.setTitle("ALARM");
		this.frame.setSize(800,400);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		this.panel = new JPanel();		// flowlayout
		
	
		
		Alarm1 = new JLabel("Alarm1");
		Alarm1.setMaximumSize(new Dimension(50,40));
		this.panel.add(Alarm1);
		JButton button1 = new JButton("NEW");
		this.panel.add(button1);
		time1 = new Times1(this);
		
		button1.setMnemonic(KeyEvent.VK_I);   
/*	    button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hi");
			}
		});
*/	
		
		
		Alarm2 = new JLabel("Alarm2");
		Alarm2.setMaximumSize(new Dimension(50,40));
		this.panel.add(Alarm2);
		JButton button2 = new JButton("NEW");
		this.panel.add(button2);
		
		Alarm3 = new JLabel("Alarm3");
		Alarm3.setMaximumSize(new Dimension(50,40));
		this.panel.add(Alarm3);
		JButton button3 = new JButton("NEW");
		this.panel.add(button3);

		this.labelDate = new JLabel("Date");
		this.labelDate.setHorizontalTextPosition(JLabel.LEFT);
		this.labelDate.setOpaque(true);
		this.labelDate.setBackground(Color.YELLOW);
		this.labelDate.setMaximumSize(new Dimension(40,30));
		add(labelDate);
		this.getDate();
		
		
		
	/*	this.labelTime = new JLabel("Time");
		this.labelTime.setMaximumSize(new Dimension(40,30));
		this.labelTime.setHorizontalTextPosition(JLabel.LEFT);
		this.labelTime.setBackground(Color.red);
		this.labelTime.setOpaque(true);
		add(labelTime);
		this.startThread();
	*/
	
		time2 = new Times2();
		time3 = new Times3();
		
		button1.addActionListener(time1);
		button2.addActionListener(time2);
		button3.addActionListener(time3);
		
		this.frame.add(this.panel);
	}	
	
	/*private void startThread(){
		this.thread = new Thread(this);
		thread.start();
	}*/
	
	private void getDate(){
		Calendar cal = Calendar.getInstance();
		DateFormat dateformat = new SimpleDateFormat("yyyy:mm:dd");
		this.currentDate = new JLabel();
	// System.out.println(dateformat.format(cal.getTime()));
		this.currentDate.setMaximumSize(new Dimension(100,30));
		this.currentDate.setText(dateformat.format(cal.getTime()));
		add(this.currentDate);
	}
	
	public void run(){
		this.currentTime = new JLabel();
		this.currentTime.setMaximumSize(new Dimension(70,30));
		
		while(true){
		//	setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
			Calendar cal = Calendar.getInstance();
			DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
		//	System.out.println(dateformat.format(cal.getTime()));  test
			this.current_time = dateformat.format(cal.getTime());
			this.currentTime.setText(this.current_time);
			add(currentTime);
		}
	}
	
	
	public static void main(String[] args) {


		Alarm gui = new Alarm();
	}
}
