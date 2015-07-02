import java.awt.Component;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.security.auth.Destroyable;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.View;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Times1 extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	private Calendar alarm1;
	private String notification1;
	private int hr = -1;
	private int min  = -1;
	public Alarm a;
	public JButton closebutton;
	
	public Times1(Alarm a){
		this.a = a;
		this.closebutton = new JButton("Close ALARM1");
		this.closebutton.setVisible(false);
		this.closebutton.addActionListener(this);
		this.a.panel.add(this.closebutton);
		}
	
	public Times1() {
		;
	}
	
	public void actionPerformed(ActionEvent event){
		System.out.println("action performed inVOked");
		//this.alarm1 = Calendar.getInstance();
		//DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
		//this.closebutton.setVisible(true);
		
		String timestring = "";
		if( event.getSource() == this.closebutton){
			//System.out.println("DELETE CLOSE ALARM1");
		//	this.a.panel.remove(this.closebutton); //to remove button
			this.closebutton.setVisible(false);
			this.a.panel.revalidate();
			this.a.panel.repaint();
			
		}else{
			String t = ":";
			String timehr;
			String temphr;
			String tempmin;
			//String timestring;
			String timemin;
			this.notification1 = JOptionPane.showInputDialog("Enter notification");
			boolean flag = true;
			while(flag){
				Object[] option = {"00","01","02","03",
									"04","05","06","07",
									"08","09","10","11",
									"12","13","14","15",
									"16","17","18","19",
									"20","21","22","23"};
				String inputhr = (String)JOptionPane.showInputDialog(this.a.frame,
													"SELECT HOUR",
													"HOUR SELECT",
													JOptionPane.PLAIN_MESSAGE,
													null,
													option,
													"00");
				String inputmin = JOptionPane.showInputDialog("Enter mins");
				this.hr = new Integer(inputhr);
				this.min = new Integer(inputmin);
				if( (this.hr > 24) || (this.min>59) || (this.hr<0) || (this.min<0)){
				flag = true;
				System.out.println("Invalid hr or min");
				}else{
				flag = false;
				}
			}
	
			//Conversion of integer time to string...
			if(this.hr<10){
				timehr = "0";
				temphr = Integer.toString(this.hr);
				timehr = timehr.concat(temphr);
			}else{
				timehr = Integer.toString(this.hr);
			}
			
			if(this.min<10){
				timemin = "0";
				tempmin = Integer.toString(this.min);
				timemin = timemin.concat(tempmin);
			}else{
				timemin = Integer.toString(this.min);
			}
			

			timestring = timehr.concat(t);
			timestring = timestring.concat(timemin);
		
			this.setButton();
		
		}
		
		if( this.hr != -1 && this.min != -1)
			this.triggerAlarm(timestring);

	}
	
	private void setButton(){		//not working properly;
		System.out.println("SETBUTTON INVOKED");
		
		this.closebutton.setVisible(true);
		this.a.panel.revalidate();
		this.a.panel.repaint();
		this.a.frame.add(this.a.panel);
	
	}
		
		
		

	
	private void triggerAlarm(String timestring){
		boolean b = true;
		int ringAlarm = 0;
		
		
		System.out.println("TriggerALARM invoked");
		
		
	if((this.hr!=-1)&&(this.min!=-1)){
		while(b){
			this.alarm1 = Calendar.getInstance();
			
			
			DateFormat dateformat = new SimpleDateFormat("HH:mm");	//HH:mm means 0-23 hr format.
																	//hh:mm means 1-12 hr format.
			//System.out.println(dateformat.format(this.alarm1.getTime()));
			String currentTime = dateformat.format(this.alarm1.getTime());
			
			if(currentTime.equals(new String(timestring))){
			//System.out.println("matched");
			ringAlarm = this.getAlarm();
			break;
			}else{
			//System.out.println("no");
			;
			}
		}
	}
		if(ringAlarm == 1)
			this.alarmAction();
	}

	private int getAlarm(){
		System.out.println("getAlarm invoked");
		return 1;
	}

	

	  private void alarmAction(){
		System.out.println("alarmAction invoked");
		File soundfile = new File("/home/ankit07/Desktop/AlarmClockBeep.wav");
		AudioInputStream soundIn = null;
		try {
			soundIn = AudioSystem.getAudioInputStream(soundfile);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioFormat format = soundIn.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, 	format );  // what is it doing ???
		try{
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(soundIn);
			clip.start();		//clip.stop() also exists;
				//System.out.println(clip.getMicrosecondPosition());
				//System.out.println(clip.getMicrosecondLength());
				// System.out.println(clip.getMicrosecondLength() + " : " + clip.getMicrosecondPosition());		
										//this will show why length and position
		}catch(IOException ex){
			System.out.println(ex);
		}catch(LineUnavailableException ex){
			System.out.println(ex);
		}
		
		Object[] option = {"END","SNOOZE"};
int endSnooze = JOptionPane.showOptionDialog(this.a.frame, this.notification1,	"ALARM",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, option,option[0]);
		if(endSnooze==0)
			this.afterAlarmAction();
		else
			this.afterAlarmAction(this.hr,this.min);
		//System.out.println(endSnooze);
		//System.out.println(this.notification1);
		/*After alarm effects*/
		
	}

	 private void afterAlarmAction(){	 // reset the alarm hr = -1 and min = -1;		
		 System.out.println("afterAlarmAction invoked");
		 this.hr = -1;
		 this.min = -1;
	 }
	 
	 private void afterAlarmAction(int hr,int min){		//snooze the alarm
		System.out.println("afterAlarmAction Snooze invoked");
		Object[] option = {"5 MIN","10 MIN"};
		String t = ":";
		String timehr;
		String temphr;
		String tempmin;
		String timestring;
		String timemin;
			
		System.out.println(hr +  " " +" " +min);
		int snoozeAlarmFor = JOptionPane.showOptionDialog(this.a.frame, "SNOOZE FOR", "SNOOZE", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, option, option[0]);
		// snoozeAlarmFor = 0 = 5 mins;	snooze
		// snoozeAlarmFor = 1 = 10 mins; snooze
		if(snoozeAlarmFor == 0){
			min +=5;
			if(min>60){
				min -=60;
				hr += 1;
				if(hr>23)
					hr = 0;
			}
		}else if(snoozeAlarmFor==1){
			min+=10;
			if(min>60){
				min -= 60;
				hr += 1;
				if(hr>23)
					hr = 0;
			}
		}
		this.hr = hr;
		this.min = min;
		
		// COnversion of integer time to string
		if(this.hr<10){
			timehr = "0";
			temphr = Integer.toString(this.hr);
			timehr = timehr.concat(temphr);
		}else{
			timehr = Integer.toString(this.hr);
		}
		
		if(this.min<10){
			timemin = "0";
			tempmin = Integer.toString(this.min);
			timemin = timemin.concat(tempmin);
		}else{
			timemin = Integer.toString(this.min);
		}
		

		timestring = timehr.concat(t);
		timestring = timestring.concat(timemin);
	
	
		this.triggerAlarm(timestring);
	 
	 }
}


