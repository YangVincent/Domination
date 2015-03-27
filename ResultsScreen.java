
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
/**
 * A <code>ResultsScreen</code> displays the results of a Domination game.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class ResultsScreen extends JPanel implements ActionListener
{
	Player playerOne, playerTwo;
	JLabel playerOneName, playerTwoName, playerOneWinnerOrLoser, playerTwoWinnerOrLoser, playerOneNumMonstersBought;
	HashMap<String,Integer> playerOneStats;
	HashMap<String,Integer> playerTwoStats;
	JPanel playerResultsContainer, playerOnePanel, playerTwoPanel, playerOneStatsPanel, playerTwoStatsPanel;
	JScrollPane playerOneScrollPane, playerTwoScrollPane;
	JTextArea playerOneStatsTextArea, playerTwoStatsTextArea;
	StringBuffer P1Results, P2Results;
	JButton homeButton;
	Domination domination;

	/**
	 * Constructs a new ResultsScreen.
	 * @param playerOne the 1st Player.
	 * @param playerTwo the 2nd Player.
	 * @param domination the Domination frame that displays this ResultsScreen.
	 */
	public ResultsScreen(Player playerOne,Player playerTwo, Domination domination)
	{
		this.domination = domination;
		homeButton = new JButton("Home");
		homeButton.setPreferredSize(new Dimension(Domination.screenWidth,50));
		homeButton.addActionListener(this);

		P1Results = new StringBuffer();
		P2Results = new StringBuffer();

		this.playerOne = playerOne;
		this.playerTwo = playerTwo;

		playerOneStatsTextArea = new JTextArea();
		playerTwoStatsTextArea = new JTextArea();

		playerOneScrollPane = new JScrollPane(playerOneStatsTextArea);
		playerTwoScrollPane = new JScrollPane(playerTwoStatsTextArea);

		playerOneStatsTextArea.setEditable(false);
		playerOneStatsTextArea.setFont(new java.awt.Font("Monaco", 0, 19));
		playerOneStatsTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

		playerTwoStatsTextArea.setEditable(false);
		playerTwoStatsTextArea.setFont(new java.awt.Font("Monaco", 0,19));
		playerTwoStatsTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

		setLayout(new BorderLayout());

		playerOneName  = new JLabel(playerOne.getName());
		playerTwoName = new JLabel(playerTwo.getName());

		playerOneStats = playerOne.getStatistics();
		playerTwoStats = playerTwo.getStatistics();

		playerResultsContainer = new JPanel();
		playerResultsContainer.setLayout(new GridLayout(1,2));
		playerOnePanel = new JPanel();
		playerTwoPanel = new JPanel();
		playerOnePanel.setLayout(new BorderLayout());
		playerTwoPanel.setLayout(new BorderLayout());
		playerOneStatsPanel = new JPanel();
		playerTwoStatsPanel = new JPanel();
		playerOneStatsPanel.setLayout(new GridLayout(12,2));
		playerTwoStatsPanel.setLayout(new GridLayout(12,2));
		playerOneWinnerOrLoser = new JLabel();
		playerTwoWinnerOrLoser = new JLabel();
		playerOnePanel.add(playerOneWinnerOrLoser,BorderLayout.NORTH);
		playerTwoPanel.add(playerTwoWinnerOrLoser,BorderLayout.NORTH);
		playerOneWinnerOrLoser.setPreferredSize(new Dimension(Domination.screenWidth/2,60));
		playerTwoWinnerOrLoser.setPreferredSize(new Dimension(Domination.screenWidth/2,60));

		playerOneWinnerOrLoser.setHorizontalAlignment(SwingConstants.CENTER);
		playerTwoWinnerOrLoser.setHorizontalAlignment(SwingConstants.CENTER);

		playerOneWinnerOrLoser.setFont(new Font("Tahoma",Font.BOLD,36));
		playerTwoWinnerOrLoser.setFont(new Font("Tahoma",Font.BOLD,36));

		playerOnePanel.add(playerOneScrollPane,BorderLayout.CENTER);
		playerTwoPanel.add(playerTwoScrollPane,BorderLayout.CENTER);



		playerResultsContainer.add(playerOnePanel);
		playerResultsContainer.add(playerTwoPanel);

		add(playerResultsContainer,BorderLayout.CENTER);

		JLabel resultsLabel = new JLabel("RESULTS");
		resultsLabel.setFont(new Font("Tahoma",Font.BOLD,48));
		resultsLabel.setPreferredSize(new Dimension(Domination.screenWidth,50));
		resultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(resultsLabel,BorderLayout.NORTH);
		add(homeButton,BorderLayout.SOUTH);
	}
	public void updateResults()
	{
		setWinnerLabel();
		P1Results.append("          \n");
		P1Results.append("        Coins Remaining:\t\t"+playerOne.getMoneyBalance()+""+"\n\n\n");
		P1Results.append("        Health Remaining:\t\t"+playerOne.getPlayerHealth()+"%"+"\n\n\n");
		P1Results.append("        Monsters Purchased:\t\t"+playerOneStats.get("numMonstersPurchased")+""+"\n\n\n");
		P1Results.append("        BoltMonsters Purchased:\t\t"+playerOneStats.get("numBoltMonstersPurchased")+""+"\n\n\n");
		P1Results.append("        JuggernautMonsters Purchased:\t"+playerOneStats.get("numJuggernautMonstersPurchased")+""+"\n\n\n");
		P1Results.append("        Obstacles Purchased:\t\t"+(playerOneStats.get("numObstaclesPurchased") -
			playerOneStats.get("numFireObstaclesPurchased") - playerOneStats.get("numSpringObstaclesPurchased"))+""+"\n\n\n");
		P1Results.append("        FireObstacles Purchased:\t"+playerOneStats.get("numFireObstaclesPurchased")+""+"\n\n\n");
		P1Results.append("        SpringObstacles Purchased:\t"+playerOneStats.get("numSpringObstaclesPurchased")+""+"\n\n\n");
		P1Results.append("        FirstAids Purchased:\t\t"+playerOneStats.get("numFirstAidsPurchased")+""+"\n\n\n");
		P1Results.append("        Hospitals Purchased:\t\t"+playerOneStats.get("numHospitalsPurchased")+""+"\n\n\n");
		P1Results.append("        Reincarnations Purchased:\t"+playerOneStats.get("numReincarnationsPurchased")+""+"\n\n\n");

		P2Results.append("          \n");
		P2Results.append("        Coins Remaining:\t\t"+playerTwo.getMoneyBalance()+""+"\n\n\n");
		P2Results.append("        Health Remaining:\t\t"+playerTwo.getPlayerHealth()+"%"+"\n\n\n");
		P2Results.append("        Monsters Purchased:\t\t"+playerTwoStats.get("numMonstersPurchased")+""+"\n\n\n");
		P2Results.append("        BoltMonsters Purchased:\t\t"+playerTwoStats.get("numBoltMonstersPurchased")+""+"\n\n\n");
		P2Results.append("        JuggernautMonsters Purchased:\t"+playerTwoStats.get("numJuggernautMonstersPurchased")+""+"\n\n\n");

		P2Results.append("        Obstacles Purchased:\t\t"+(playerTwoStats.get("numObstaclesPurchased") -
			playerTwoStats.get("numFireObstaclesPurchased") - playerTwoStats.get("numSpringObstaclesPurchased"))+""+"\n\n\n");
		P2Results.append("        FireObstacles Purchased:\t"+playerTwoStats.get("numFireObstaclesPurchased")+""+"\n\n\n");
		P2Results.append("        SpringObstacles Purchased:\t"+playerTwoStats.get("numSpringObstaclesPurchased")+""+"\n\n\n");
		P2Results.append("        FirstAids Purchased:\t\t"+playerTwoStats.get("numFirstAidsPurchased")+""+"\n\n\n");
		P2Results.append("        Hospitals Purchased:\t\t"+playerTwoStats.get("numHospitalsPurchased")+""+"\n\n\n");
		P2Results.append("        Reincarnations Purchased:\t"+playerTwoStats.get("numReincarnationsPurchased")+""+"\n\n\n");
		playerOneStatsTextArea.setText(P1Results.toString());
		playerTwoStatsTextArea.setText(P2Results.toString());
	}
	public void setWinnerLabel()
	{
		if(playerOne.getPlayerHealth()<=0)
		{
			playerOneWinnerOrLoser.setText("DOMINATED");
			playerTwoWinnerOrLoser.setText("DOMINATOR");
		}
		else
		{
			playerOneWinnerOrLoser.setText("DOMINATOR");
			playerTwoWinnerOrLoser.setText("DOMINATED");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==homeButton)
		{
			domination.changeScreen("HOME");
		}
	}


}
