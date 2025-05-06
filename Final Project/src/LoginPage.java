import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class LoginPage extends JFrame implements Login{

	private static final long serialVersionUID = 4334680347938578924L;
	private String fileName = "UserNames.txt";
    private JTextField tfUserName;
    private JPasswordField tfPassword;
    private JButton btnEnroll, btnLogin, btnClearFile;
    
    public LoginPage() {
    	System.out.println("File path: " + new File(fileName).getAbsolutePath());
    	
        createLayout();
        setTitle("Welcome to Mycrohard Accounting App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        fileCheck(fileName);
        initializeScanner();
        
    }

    public void fileCheck(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Save file not found. New file created.");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "<ERROR#00> Failed to create save file!",
                        "File Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            System.out.println("Save file found.");
        }
    }
    public void initializeScanner() {
    	try {
        	Scanner sc = new Scanner(new File(fileName));
        	sc.close();
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    public void createLayout() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        tfUserName = new JTextField(20);
        tfPassword = new JPasswordField(20);

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
        btnEnroll.setPreferredSize(new Dimension(80, 30));
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(80, 30));
        btnClearFile = new JButton("Clear file and exit");
        btnClearFile.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(btnEnroll);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnClearFile);
        btnEnroll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			checkU();
        			checkP();
        			enroll(tfUserName.getText(), String.valueOf(tfPassword.getPassword()));
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
        	    	Scanner sc = new Scanner(new File(fileName));
        	        String inputU = tfUserName.getText().trim();
        	        String inputP = String.valueOf(tfPassword.getPassword()); 
        	        boolean userFound = false;
        	        try {
        	        	while(sc.hasNextLine()) {
        	        		String line = sc.nextLine();
        	        		String[] parts = line.split("XXXX");
        	        		if(parts.length == 3) {
        	        			String storedU = parts[1];
        	        			String storedP = parts[2];
        	        			if(storedU.equals(inputU)) {
    	        					userFound = true;
        	        				if(storedP.equals(inputP)) {
        	        					loginSuccess(inputU);
        	        					break;
        	        				}else {
        	        					error(4);
        	        					break;
        	        				}
        	        			}
        	        		}
        	        	}	
        	        }finally {
        	        	sc.close();
        	        }  
        	        
        	        if(!userFound) {
        	        	error(3);
        	        }
        		}catch(Exception exc) {
        			exc.printStackTrace();
        			error(0);
        		}
        	}
        });
        btnClearFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			File file = new File(fileName);
        			file.delete();
        			dispose();
        		}catch(Exception ex) {
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
    public void loginSuccess(String name){
    	dispose();
        JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.PLAIN_MESSAGE);
        
        new MainPage(name);
    }   
    public void enroll(String userName, String password) {
    	try {
			checkU();
			checkP();
			int index = 0;
	        File file = new File(fileName);
	        if (file.exists()) {
	            BufferedReader reader = new BufferedReader(new FileReader(file));
	            while (reader.readLine() != null) {
	                index++;
	            }
	            reader.close();
	        }

	        // Write new user
	        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
	        writer.write(index + "XXXX" + userName + "XXXX" + password);
	        writer.newLine();
	        writer.close();

	        JOptionPane.showMessageDialog(this, "Enrollment successful!");
		}catch (NullUsernameError e) {
			error(1);
		}catch (PasswordLengthError e) {
			error(2);
		}catch (RepeatUsernameError e) {
			error(5);
		}catch(IOException e) {
			error(0);
			e.printStackTrace();
		}
    }
    public void checkU() throws NullUsernameError, RepeatUsernameError, FileNotFoundException{
    	if(tfUserName.getText().equals(null)) throw new NullUsernameError();
    	ArrayList<String> names = new ArrayList<>();
    	Scanner sc = new Scanner(new File(fileName));
    	while(sc.hasNextLine()) {
    		String line = sc.nextLine();
    		String[] parts = line.split("XXXX");
    		if(parts.length == 3) {
    			names.add(parts[1]);
    		}
    		
    	}
    	for(int i = 0; i < names.size(); i++) {
			if(tfUserName.getText().equals(names.get(i))) throw new RepeatUsernameError();
		}
    	sc.close();
    }
    public void checkP() throws PasswordLengthError{
    	if(String.valueOf(tfPassword.getPassword()).length() < 8) throw new PasswordLengthError();
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
    	case 5:
    		JOptionPane.showMessageDialog(this, "<ERROR#05> Cannot enroll, username repeated!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	case 6:
    		JOptionPane.showMessageDialog(this, "<ERROR#06> File not found!\nPlease restart the program\nReinstall if needed.", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	}
    }
    
    
    
    
}

class FileNotFoundError extends Exception {
	public FileNotFoundError() {
	
	}
}
class UsernameNotFoundError extends Exception{
	public UsernameNotFoundError() {
		
	}
}
class PasswordError extends Exception{
	public PasswordError() {
		
	}
}
class PasswordLengthError extends Exception{
	public PasswordLengthError() {
		
	}
}
class NullUsernameError extends Exception{
	public NullUsernameError() {
		
	}
}
class RepeatUsernameError extends Exception{
	public RepeatUsernameError() {
		
	}
}

