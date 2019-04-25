import java.awt.*;
import java.lang.String;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
public class Example {

public static void main(String[] args) {
	
	JFrame frame = new JFrame("Bean");
	Bean bean = new Bean();
	JTextField tb1 = new JTextField();
	JTextField tb2 = new JTextField();
	JButton ok = new JButton("ok");
	JButton choose = new JButton("Wybierz kolor");
	Controller control = new Controller();
	bean.addVetoableChangeListener(control);
	choose.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		bean.setBeanColor(JColorChooser.showDialog(null, "Wybierz swoj kolor", bean.getBeanColor()));
		SwingUtilities.updateComponentTreeUI(frame);
		}
	});
	
	ok.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!tb1.getText().isEmpty())
			bean.setBeanString(tb1.getText());
			try {
				if(!tb2.getText().isEmpty())
				bean.setBeanValue(Integer.parseInt(tb2.getText()));
			} catch (NumberFormatException | PropertyVetoException e) {
				e.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(frame);
			
		}
	});
	
		frame.setSize(400, 200);
	    frame.getContentPane().setLayout(new BorderLayout());
	    frame.getContentPane().add(bean,BorderLayout.CENTER);
	    frame.getContentPane().add(ok, BorderLayout.EAST);
	    frame.getContentPane().add(tb1, BorderLayout.NORTH);
	    frame.getContentPane().add(tb2, BorderLayout.SOUTH);
	    frame.getContentPane().add(choose, BorderLayout.WEST);
	    frame.setVisible(true);
	    frame.addWindowListener(new ProgramWindowAdapter()); 
	  }
 }
class ProgramWindowAdapter extends WindowAdapter {
	 public void windowClosing(WindowEvent e) {
	 System.exit(0);
	 } 
}