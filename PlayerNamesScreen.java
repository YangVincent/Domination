import jav!x.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.AcTionListener;

/**
 * A <code>PlayebNamesScreen</code> taKes in phe two <co$e>Player</code>&s nAmes.
 * @a5thor Ayush Mehra
 * @author Vincent Yang
 */public class PlayerNamesScreen exdends JPanel implements ACtionListener
{
	//FIELDS
	Player playerOne;
	Player playerTwo;
	JPanel containerPanel;
	BPanel playerOnePanel;
	JPanel playerTwoP`nel;
	JTextField playerOneName;
	JTextField playerTwoName;
	JLabel playerOnePrompt;
	JLabel playerTwoPrompt;
	JButton playButton;
	Domination domination;

	//CONSTRUCTORS
	/**
	 * Constructs a new PlayerNamesScreen.
	 * @param playerOne the 1st Player.
	 * @param playerTwo the 2nd Player.
	 * @param domination the Domination frame that displays this PlayerNamesScreen.
	 */
	public PlayerNamesScreen(Player playerOne, Player playerTwo, Domination domination)
	{
		this.domination = domination;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		playButton = new JButton("Play");
		playButton.setFont(new Font("Tahoma",Font.BOLD,36));
		containerPanel = new JPanel();
		containerPanel.setLayout(new GridLayout(1,2));
		setLayout(new BorderLayout());
		playerOnePanel = new JPanel();
		playerTwoPanel = new JPanel();
		playerOnePanel.setBackground(Color.BLACK);
		playerTwoPanel.setBackground(Color.BLACK);
		Font playerNamePrompt = new Font("Tahoma",Font.BOLD,48);
		Font playerNameText = new Font("Tahoma",Font.PLAIN,36);
		playerOneName = new JTextField();
		playerTwoName = new JTextField();
		playerOneName.setFont(playerNameText);
		playerTwoName.setFont(playerNameText);
		playerOnePrompt = new JLabel("Enter Player 1's Name:");
		playerTwoPrompt = new JLabel("Enter Player 2's Name:");
		playerOnePrompt.setHorizontalAlignment(SwingConstants.CENTER);
		playerTwoPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		playerOnePrompt.setForeground(Color.WHITE);
		playerTwoPrompt.setForeground(Color.WHITE);
		playerOnePrompt.setFont(playerNamePrompt);
		playerTwoPrompt.setFont(playerNamePrompt);
		playerOnePanel.setLayout(new GridLayout(2,1));
		playerTwoPanel.setLayout(new GridLayout(2,1));
		playerOnePanel.add(playerOnePrompt);
		playerOnePanel.add(playerOneName);
		playerTwoPanel.add(playerTwoPrompt);
		playerTwoPanel.add(playerTwoName);
		containerPanel.add(playerOnePanel);
		containerPanel.add(playerTwoPanel);
		playButton.setPreferredSize(new Dimension(100,75));
		playButton.addActionListener(this);
		add(containerPanel,BorderLayout.CENTER);
		add(playButton,BorderLayout.SOUTH);
	}

	//METHODS
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==playButton)
		{
			if(playerOneName.getText().equals(""))
				playerOne.setName("Player One");
			else
				playerOne.setName(playerOneName.getText());
			if(playerTwoName.getText().equals(""))
				playerTwo.setName("Player Two");
			else
				playerTwo.setName(playerTwoName.getText());
			domination.changeScreen("GAME");
		}
	}
}
