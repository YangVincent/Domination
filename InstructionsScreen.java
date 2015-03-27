import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import javax.swing.JComponent;

/**
 * An <code>InstructionsScreen</code> displays instructions and how-to's for a Domination game
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class InstructionsScreen extends JPanel implements ActionListener
{
	//FIELDS
	JLabel instructionLabel;
	JEditorPane instructionsEditorPane;
	JButton homeButton;
	JPanel header;
	Domination domination;
	JScrollPane scrollPane;

	//CONSTRUCTORS
	/**
	 * Constructs a new InstructionsScreen
	 * @param domination the Domination frame in which this InstructionsScreen is shown.
	 */
	public InstructionsScreen(Domination domination)
	{
		this.domination = domination;
		header = new JPanel();
		header.setLayout(new BorderLayout());
		header.setBackground(Color.BLACK);
		instructionLabel = new JLabel("Instructions");
    	instructionsEditorPane = new JEditorPane();
		homeButton = new JButton("Home");
		homeButton.addActionListener(this);
		setLayout(new BorderLayout());
		instructionLabel.setForeground(Color.WHITE);
		instructionLabel.setFont(new Font("Tahoma",Font.BOLD,48));
		header.add(instructionLabel,BorderLayout.WEST);
		header.add(homeButton,BorderLayout.EAST);
		add(header,BorderLayout.NORTH);
		add(instructionsEditorPane,BorderLayout.CENTER);
		instructionsEditorPane.setContentType("text/html");
	//	instructionsEditorPane.setContentType("text/rtf");
		instructionsEditorPane.setEditable(false);
	//    scrollPane = new JScrollPane(instructionsEditorPane);
	//	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//	add(scrollPane);
//		URL instructionsURL = this.getClass().getResource("/readmeHTML.htm");

		try
		{
			instructionsEditorPane.setPage(this.getClass().getResource("InstructionsTextHTML.html"));
			//instructionsEditorPane.setVisible();
			System.out.println("visibility is " + instructionsEditorPane.isVisible());
			System.out.println("set");
		}
		catch (IOException ex)
		{
			instructionsEditorPane.setText("Could not find Instructions File");
		}
	}

	//METHODS
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==homeButton)
		{
			domination.changeScreen("HOME");
		}
	}
}
