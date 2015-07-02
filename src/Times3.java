import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Times3 implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	private Calendar alarm1;
	private String notification1;
	private int hr;
	private int min;
	
	
	
	public void actionPerformed(ActionEvent event){
		//this.alarm1 = Calendar.getInstance();
		//DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
		
		
		boolean flag = true;
			while(flag){
				String inputhr = JOptionPane.showInputDialog("Enter hr in 24 hr format");
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
		
		System.out.println(this.hr + " " + this.min);
	}
}
	
