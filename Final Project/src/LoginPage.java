import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    private User user = new User();
    private HomeFrame homeFrame;
    private JTextField tfUserName, tfPassword;
    private JButton btnEnroll, btnLogin;

    public LoginFrame() {
        createLayout();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createLayout() {
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
        			user.add(tfUserName.getText(), tfPassword.getText());
        		}catch(UserError ex){
        			error(1);
        		}catch(PasswordError ex) {
        			error(2);
        		}
        	}
        });
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			user.checkUserExist(tfUserName.getText());
        			user.checkPassword(tfUserName.getText(), tfPassword.getText());
        			loginSuccess();
        			dispose();
        			homeFrame = new HomeFrame();
        			homeFrame.setVisible(true);
        			homeFrame.loginSuccess(tfUserName.getText());
        			}catch(UserError ex){
        			error(3);
        		}catch(PasswordError ex) {
        			error(4);
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
    public void error(int errorCode) {
    	switch(errorCode) {
    	default:
    		JOptionPane.showMessageDialog(this, "<ERROR> Unknown error!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	case 1:
    		JOptionPane.showMessageDialog(this, "<ERROR#01> Username can't be empty!", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		break;
    	case 2:
    		JOptionPane.showMessageDialog(this, "<ERROR#02> Password should be exactly 8 letters long!", "ERROR!", JOptionPane.ERROR_MESSAGE);
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
