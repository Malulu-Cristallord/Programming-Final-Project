import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class LoginPage extends JFrame implements Login{

	private String fileName = "UserNames";
	private Scanner sc = new Scanner(fileName);
	private MainPage mainPage;
    private JTextField tfUserName, tfPassword;
    private JButton btnEnroll, btnLogin;
    
    public LoginPage() {
        createLayout();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        fileCheck(fileName);
        createFw();
        
    }

    public void fileCheck(String fileName) {
    	
    }
    public void createFw() {
    	
    	/*
    	 * Needs to create an exception where file doesn't exist, and 
    	 */
    }
    public void createLayout() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        tfUserName = new JTextField(20);
        tfPassword = new JTextField(20);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(tfUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(tfPassword, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnEnroll = new JButton("Enroll");
        btnEnroll.setPreferredSize(new Dimension(100, 30));
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(btnEnroll);
        buttonPanel.add(btnLogin);
        btnEnroll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			checkU();
        		}catch(Exception exc) {
        			error(0);
        		}
        	}
        });
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			//reads the file to check whether the user name exists
        			//if yes, reads again to check the password
        		}catch(Exception exc) {
        			error(0);
        		}
        	}
        });
        
        
        //form panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);
        
        // Main panel
        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.add(formPanel);
        allPanel.setPreferredSize(new Dimension(500, 300));
        add(allPanel);
    }
    public void loginSuccess(){
    	JFrame successFrame = new JFrame();
    	JLabel successLabel = new JLabel("Login success!");
    	JPanel successPanel = new JPanel();
    	successPanel.add(successLabel);
    	successFrame.add(successPanel);
    	successFrame.setVisible(true);
    	successFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void enroll(String userName, String password) {
    	try {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void checkU() {
    	//file writer checks if user name exists
    }
    
    public void checkP() {
    	
    }
    
    public void error(int errorCode) {

    	switch(errorCode) {
    	default:
    		JOptionPane.showMessageDialog(this, "<ERROR> Unknown error!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	case 1:
    		JOptionPane.showMessageDialog(this, "<ERROR#01> Username can't be empty!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	case 2:
    		JOptionPane.showMessageDialog(this, "<ERROR#02> Password should be at least 8 letters long!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	case 3:
    		JOptionPane.showMessageDialog(this, "<ERROR#03> Username not found!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	case 4:
    		JOptionPane.showMessageDialog(this, "<ERROR#04> Incorrect password!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	}
    }
    
    
    
    
}

class FileNotFoundError extends Exception {
	public FileNotFoundError(String Error) {
		super(Error);
	}
}
class UsernameNotFoundError extends Exception{
	public UsernameNotFoundError(String Error) {
		super(Error);
	}
}
class PasswordError extends Exception{
	public PasswordError(String Error) {
		super(Error);
	}
}
class PasswordLengthError extends Exception{
	public PasswordLengthError(String Error) {
		super(Error);
	}
}
class NullUsernameError extends Exception{
	public NullUsernameError(String Error) {
		super(Error);
	}
}

