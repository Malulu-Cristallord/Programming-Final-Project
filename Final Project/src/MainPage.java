import javax.swing.*;
import java.awt.*;
public class MainPage extends JFrame{


	private static final long serialVersionUID = -697011069049333092L;
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JPanel fullPanel;
	private String userName;

	public MainPage(String userName){
		this.userName = userName;
		setTitle("Home");
		setSize(500,300);
		setLocationRelativeTo(null);
		createLayout();
		setVisible(true);
	}

	public void createLayout(){

		/*
			以下是Placeholder, 從Lab20上抓下來的，之後再編輯
		*/
		label1.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		label2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		label1.setText("Welcome, ");
		label2.setText(userName);
		
		fullPanel = new JPanel();
		fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
		fullPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		fullPanel.add(label1);
		fullPanel.add(label2);

		this.getContentPane().add(fullPanel, BorderLayout.CENTER);
	}
}
