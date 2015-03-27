import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

/**
 * Displays Credits for <code>Domination</code>
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class CreditsScreen extends JPanel implements ActionListener
{
	//FIELDS
	JLabel creditsLabel;
	JEditorPane creditsEditorPane;
	JButton homeButton;
	JPanel header;
	JScrollPane scrollPane;
	Domination domination;

	//CONSTRUCTORS
	/**
	 * Constructs a new CreditsScreen
	 * @param domination the domination frame that contains this CreditsScreen
	 */
	public CreditsScreen(Domination domination)
	{
		this.domination = domination;
		header = new JPanel();
		header.setLayout(new BorderLayout());
		header.setBackground(Color.BLACK);
		creditsLabel = new JLabel("Credits");
		creditsEditorPane = new JEditorPane();
		homeButton = new JButton("Home");
		homeButton.addActionListener(this);
		setLayout(new BorderLayout());
		creditsLabel.setForeground(Color.WHITE);
		creditsLabel.setFont(new Font("Tahoma",Font.BOLD,48));
		header.add(creditsLabel,BorderLayout.WEST);
		header.add(homeButton,BorderLayout.EAST);
		add(header,BorderLayout.NORTH);
		add(creditsEditorPane,BorderLayout.CENTER);
		creditsEditorPane.setContentType("text/html");
		creditsEditorPane.setEditable(false);
	    scrollPane = new JScrollPane(creditsEditorPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		URL creditsURL = this.getClass().getResource("/creditsHTML.htm");
		try
		{
			creditsEditorPane.setPage(creditsURL);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			creditsEditorPane.setText("Could not find Credits File");
		}
	}

	//METHODS

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==homeButton)
			domination.changeScreen("HOME");
	}
}
