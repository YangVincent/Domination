import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread;
/**
 * Represents the main window, <code>Domination</code>, for the Domination game.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class Domination extends JFrame
{
	//FIELDS
	private ThemeSong themeSongSound = new ThemeSong(this);
	public static Player playerOne, playerTwo;
	BattleGrid battleGrid;
	private Thread themeSong;
	public static JPanel screens;
	public static CardLayout cl;
	HomeScreen homeScreen;
	public static GameScreen gameScreen;
	PlayerNamesScreen playerNamesScreen;
	InstructionsScreen instructionsScreen;
	CreditsScreen creditsScreen;
	public static ResultsScreen resultsScreen;
	public static int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static double ratioX, ratioY;
	public static MonsterRunner monsterRunner;
	public static Thread monsterThread;

	//CONSTRUCTORS
	/**
	 * Constructs a new Domination window
	 * @param playerOne the 1st player
	 * @param playerTwo the 2nd player
	 * @param battleGrid the battleGrid on which the game occurs
	 */
	public Domination(Player playerOne, Player playerTwo, BattleGrid battleGrid)
	{
		this.ratioX = Domination.screenWidth/1280.0;
		this.ratioY = Domination.screenHeight/800.0;
		this.cl = new CardLayout();
		this.screens = new JPanel(cl);
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.battleGrid = battleGrid;
		monsterRunner = new MonsterRunner(battleGrid);
		monsterThread = new Thread(monsterRunner);
		themeSong = new Thread(themeSongSound);
		monsterThread.start();
		homeScreen = new HomeScreen(this);
		gameScreen = new GameScreen(playerOne,playerTwo,battleGrid);
		playerNamesScreen = new PlayerNamesScreen(playerOne,playerTwo, this);
		instructionsScreen = new InstructionsScreen(this);
		creditsScreen = new CreditsScreen(this);
		resultsScreen = new ResultsScreen(playerOne,playerTwo, this);
		screens.add(homeScreen,"HOME");
		screens.add(gameScreen,"GAME");
		screens.add(playerNamesScreen,"PLAYERNAMES");
		screens.add(instructionsScreen,"INSTRUCTIONS");
		screens.add(creditsScreen,"CREDITS");
		screens.add(resultsScreen,"RESULTS");
		add(screens,BorderLayout.CENTER);
		themeSong.start();
	}
	/**
	 * Checks for the end of the Domination game
	 */
	public static synchronized void checkForGameEnd()
	{
		if(playerOne.getPlayerHealth()<=0 || playerTwo.getPlayerHealth()<=0)
		{
			resultsScreen.updateResults();
			gameScreen.createNewGame();
			changeScreen("RESULTS");
		}
	}
	public static void changeScreen(String screenName)
	{
	    cl.show(screens,screenName);
	    if(screenName.equals("GAME"))
	    {
	    	gameScreen.startTimer();
	    	gameScreen.requestFocus();
	    }
	}
	/**
	 * Restarts music
	 */
	public void restartMusic()
	{
		new Thread(themeSongSound).start();
	}
}