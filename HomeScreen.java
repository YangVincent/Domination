import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A <code>HomeScreen</code> displays the main starting screen of Domination
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class HomeScreen extends JPanel implements MouseListener
{
	//FIELDS
	JLabel playButton, instructionsButton, creditsButton, exitButton;
	JPanel functionPanel;
	Domination domination;

	//CONSTRUCTORS
	/**
	 * Constructs a new HomeScreen
	 * @param domination the Domination frame in which this HomeScreen is shown
	 */
	public HomeScreen(Domination domination)
	{
		setLayout(new BorderLayout());
		this.domination = domination;
		playButton = new JLabel("Play");
		instructionsButton = new JLabel("Instructions");
		creditsButton = new JLabel("Credits");
		exitButton = new JLabel("Exit");
		functionPanel = new JPanel();
		add(functionPanel, BorderLayout.SOUTH);
		functionPanel.setBackground(Color.BLACK);
		Box boxFunctionContainer = Box.createVerticalBox();
		functionPanel.setLayout(new GridLayout(1,3));
		playButton.setFont(new Font("Tahoma",Font.BOLD,36));
		instructionsButton.setFont(new Font("Tahoma",Font.BOLD,36));
		creditsButton.setFont(new Font("Tahoma",Font.BOLD,36));
		exitButton.setFont(new Font("Tahoma",Font.BOLD,36));
		playButton.setForeground(Color.WHITE);
		instructionsButton.setForeground(Color.WHITE);
		creditsButton.setForeground(Color.WHITE);
		exitButton.setForeground(Color.WHITE);
		playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		instructionsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creditsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		boxFunctionContainer.add(playButton);
		boxFunctionContainer.add(Box.createVerticalStrut(30));
		boxFunctionContainer.add(instructionsButton);
		boxFunctionContainer.add(Box.createVerticalStrut(30));
		boxFunctionContainer.add(creditsButton);
		boxFunctionContainer.add(Box.createVerticalStrut(30));
		boxFunctionContainer.add(exitButton);
		playButton.addMouseListener(this);
		instructionsButton.addMouseListener(this);
		creditsButton.addMouseListener(this);
		exitButton.addMouseListener(this);
		functionPanel.add(new JLabel());
		functionPanel.add(boxFunctionContainer,BorderLayout.CENTER);
		functionPanel.add(new JLabel());
	}
	public void paintComponent(Graphics g)
	{
		Image background = Toolkit.getDefaultToolkit().getImage("mainScreen.gif");
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		g.drawImage(background,0,0,width,height,this);
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e)
	{
		if(e.getSource()==playButton)
		{
			playButton.setForeground(new Color(0,191,255));
		}
		else if(e.getSource()==instructionsButton)
		{
			instructionsButton.setForeground(new Color(0,191,255));
		}
		else if(e.getSource()==creditsButton)
		{
			creditsButton.setForeground(new Color(0,191,255));
		}
		else if(e.getSource()==exitButton)
		{
			exitButton.setForeground(Color.RED);
		}
	}
	public void mouseExited(MouseEvent e)
	{
		if(e.getSource()==playButton)
		{
			playButton.setForeground(Color.WHITE);
		}
		else if(e.getSource()==instructionsButton)
		{
			instructionsButton.setForeground(Color.WHITE);
		}
		else if(e.getSource()==creditsButton)
		{
			creditsButton.setForeground(Color.WHITE);
		}
		else if(e.getSource()==exitButton)
		{
			exitButton.setForeground(Color.WHITE);
		}
	}
	public void mousePressed(MouseEvent e)
	{
		if(e.getSource()==playButton)
		{
			domination.changeScreen("PLAYERNAMES");
		}
		else if(e.getSource()==instructionsButton)
		{
			domination.changeScreen("INSTRUCTIONS");
		}
		else if(e.getSource()==creditsButton)
		{
			domination.changeScreen("CREDITS");
		}
		else if(e.getSource()==exitButton)
		{
			System.exit(0);
		}
	}
	public void mouseReleased(MouseEvent e) {}
}
