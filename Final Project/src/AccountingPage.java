import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class AccountingPage extends JFrame{

	private JTextArea inputS, inputV;
	private JTextField output;
	private JLabel placeholder1, placeholder2;
	private JButton clearbtn, addbtn, historybtn;
	private JPanel btnPanel, inputPanel1, inputPanel2, outputPanel, fullPanel;

	public AccountingPage() {
		createLayout();
	}
	
	private void createLayout() {
		//set full panel
		fullPanel.setLayout(new BoxLayout.Y_AXIS);

		//set input panel
		placeholder1 = new JLabel("Input your accounting subject: ");
		placeholder2 = new JLabel("Input the money spent on it: ");
		inputS = new JTextArea();
		inputV = new JTextArea();	
		inputPanel1.add(placeholder1);
		inputPanel1.add(inputS);
		inputPanel2.add(placeholder2);
		inputPanel2.add(inputV);

		//set button panel
		//set output panel

		
	}
}
