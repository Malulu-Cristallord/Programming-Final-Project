import javax.swing.*;
import java.awt.*;
public class MainPage extends JFrame{

	private JLabel label = new JLabel();
	private JLabel label2 = new JLabel();
	private JPanel fullPanel;

	public MainPage(){
		this.setTitle("Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,300);
		createLayout();
	}

	public void createLayout(){

		/*
			以下是Placeholder, 從Lab20上抓下來的，之後再編輯
		*/
		label.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		label2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		fullPanel = new JPanel();
		fullPanel.add(label);
		fullPanel.add(label2);
		fullPanel.setPreferredSize(new Dimension(500, 100));
		
		this.setLayout(new BorderLayout(20, 60));
		this.getContentPane().add(fullPanel, BorderLayout.CENTER);
		this.getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		this.getContentPane().add(new JPanel(), BorderLayout.NORTH);
	}
}
