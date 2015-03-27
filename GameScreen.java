
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
/**
 * A <code>GameScreen</code> is a Panel on which the Domination game occurs.
 */
public class GameScreen extends JPanel implements ActionListener, KeyListener
{
	private String cheat;
	private Player playerOne;
	private Song payDaySong = new Song("payDay.wav");
	private Player playerTwo;
	private BattleGrid battleGrid;
	private JPanel playerPanelsContainer;
	private JPanel functionsPanel;
	private JButton homeButton;
	private JLabel timeLabel;
	private JLabel elapsedTime;
	private Timer gameTimer;
	private int numSeconds;
	private int numMinutes;
	private int numHours;
	private String seconds;
	private String minutes;
	private String hours;

	/**
	 * Constructs a new GameScreen on which the Game occurs.
	 * @param playerOne the 1st player.
	 * @param playerTwo the 2nd player.
	 * @param battleGrid the battleGrid on which the Domination game occurs.
	 */
	public GameScreen(Player playerOne, Player playerTwo, BattleGrid battleGrid)
	{
		setLayout(new BorderLayout());
		this.cheat = "";
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.battleGrid = battleGrid;
		playerPanelsContainer = new JPanel();
		playerPanelsContainer.setLayout(new BorderLayout());
		playerPanelsContainer.add(playerOne,BorderLayout.WEST);
		playerPanelsContainer.add(playerTwo,BorderLayout.EAST);
		functionsPanel = new JPanel();
		functionsPanel.setBackground(Color.BLACK);
		playerPanelsContainer.add(functionsPanel,BorderLayout.CENTER);
		numSeconds = numMinutes = numHours = 0;
//		numMinutes = 0;
//		numHours = 0;
		timeLabel = new JLabel("Elapsed Time:");
		timeLabel.setForeground(Color.WHITE);
		functionsPanel.add(timeLabel);
		elapsedTime = new JLabel("00:00:00");
		elapsedTime.setForeground(Color.WHITE);
		timeLabel.setFont(new Font("Tahoma",Font.PLAIN,24));
		elapsedTime.setFont(new Font("Tahoma",Font.PLAIN,24));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		elapsedTime.setHorizontalAlignment(SwingConstants.CENTER);
		seconds = "";
		minutes = "";
		hours = "";
		gameTimer = new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				numSeconds++;
				if(numSeconds==30)
				{
					playerPayDay(500);
				}
				if(numSeconds==60)
				{
					numMinutes++;
					playerPayDay(500);
					if(numMinutes==60)
					{
						numHours++;
						numMinutes = 0;
					}
					numSeconds = 0;
				}
				seconds = numSeconds<10?":0"+numSeconds:":"+numSeconds;
//				if(numSeconds<10)
//					seconds = ":0"+numSeconds;
//				else
//					seconds = ":"+numSeconds;
				minutes = numMinutes<10?":0"+numMinutes:":"+numMinutes;
//				if(numMinutes<10)
//					minutes = ":0"+numMinutes;
//				else
//					minutes = ":"+numMinutes;
				hours = numHours<10?"0"+numHours:""+numHours;
//				if(numHours<10)
//					hours = "0"+numHours;
//				else
//					hours = ""+numHours;
				elapsedTime.setText(hours+minutes+seconds);
			}
		});
		functionsPanel.setLayout(new GridLayout(4,1));
		homeButton = new JButton("Home");
		homeButton.addActionListener(this);
		functionsPanel.add(elapsedTime);
		functionsPanel.add(homeButton);
		//add(playerOne,BorderLayout.WEST);
		add(battleGrid,BorderLayout.CENTER);
		//add(playerTwo,BorderLayout.EAST);
		add(playerPanelsContainer,BorderLayout.SOUTH);
		addKeyListener(this);
	}
	public void startTimer()
	{
		gameTimer.start();
	}

	public void playerPayDay(int money)
	{
		playerOne.gainMoney(money);
		playerTwo.gainMoney(money);
		new Thread(payDaySong).start();
	}
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		int row1 = battleGrid.getMarkerRow1();
		int col1 = battleGrid.getMarkerCol1();
		int row2 = battleGrid.getMarkerRow2();
		int col2 = battleGrid.getMarkerCol2();

		if(keyCode == e.VK_W)
		{
			battleGrid.playerOneMoveMarker("UP");
			playerOne.setCheat("");

		}
		else if(keyCode == e.VK_A)
		{
			battleGrid.playerOneMoveMarker("LEFT");
			playerOne.setCheat("");
		}
		else if(keyCode == e.VK_S)
		{
			battleGrid.playerOneMoveMarker("DOWN");
			playerOne.setCheat("");
		}
		else if(keyCode == e.VK_D)
		{
			battleGrid.playerOneMoveMarker("RIGHT");
			playerOne.setCheat("");
		}
		else if(keyCode == e.VK_UP)
		{
			battleGrid.playerTwoMoveMarker("UP");
			playerTwo.setCheat("");
		}
		else if(keyCode == e.VK_RIGHT)
		{
			battleGrid.playerTwoMoveMarker("RIGHT");
			playerTwo.setCheat("");
		}
		else if(keyCode == e.VK_DOWN)
		{
			battleGrid.playerTwoMoveMarker("DOWN");
			playerTwo.setCheat("");
		}
		else if(keyCode == e.VK_LEFT)
		{
			battleGrid.playerTwoMoveMarker("LEFT");
			playerTwo.setCheat("");
		}
		else if(keyCode==e.VK_1 && playerOne.getMoneyBalance()>=50)
		{
			MonsterRunner.addPlayerOneItem(row1,new Monster(playerOne,playerTwo,BattleGrid.blueMonsterAnimation,BattleGrid.blueMonsterFightAnimation,row1));
		}
		else if(keyCode==e.VK_NUMPAD1 && playerTwo.getMoneyBalance()>=50)
		{
			MonsterRunner.addPlayerTwoItem(row2,new Monster(playerTwo,playerOne,BattleGrid.redMonsterAnimation,BattleGrid.redMonsterFightAnimation,row2));
		}
		else if(keyCode==e.VK_2 && playerOne.getMoneyBalance()>=50)
		{
			MonsterRunner.addPlayerOneItem(row1,new BoltMonster(playerOne,playerTwo,BattleGrid.blueBoltMonsterAnimation,BattleGrid.blueBoltMonsterFightAnimation,row1));
		}
		else if(keyCode==e.VK_NUMPAD2 && playerTwo.getMoneyBalance()>=50)
		{
			MonsterRunner.addPlayerTwoItem(row2,new BoltMonster(playerTwo,playerOne,BattleGrid.redBoltMonsterAnimation,BattleGrid.redBoltMonsterFightAnimation,row2));
		}
		else if(keyCode==e.VK_3 && playerOne.getMoneyBalance()>=100)
		{
			MonsterRunner.addPlayerOneItem(row1,new JuggernautMonster(playerOne,playerTwo,BattleGrid.blueJuggernautMonsterAnimation,BattleGrid.blueJuggernautMonsterFightAnimation,row1));
		}
		else if(keyCode==e.VK_NUMPAD3 && playerTwo.getMoneyBalance()>=100)
		{
			MonsterRunner.addPlayerTwoItem(row2,new JuggernautMonster(playerTwo,playerOne,BattleGrid.redJuggernautMonsterAnimation,BattleGrid.redJuggernautMonsterFightAnimation,row2));
		}
		else if(keyCode==e.VK_NUMPAD4 && playerTwo.getMoneyBalance()>=50)
		{
			if(battleGrid.isOpenLocation(row2,17-col2))
			{
				battleGrid.toggleOccupiedGridLocation(row2,17-col2);
				MonsterRunner.addPlayerTwoItem(row2,new Obstacle(playerTwo,playerOne,BattleGrid.obstacleAnimation,row2,col2));
			}
		}
		else if(keyCode==e.VK_4 && playerOne.getMoneyBalance()>=50)
		{
			if(battleGrid.isOpenLocation(row1,col1))
			{
				battleGrid.toggleOccupiedGridLocation(row1,col1);
				MonsterRunner.addPlayerOneItem(row1,new Obstacle(playerOne,playerTwo,BattleGrid.obstacleAnimation,row1,col1));
			}
		}
		else if(keyCode==e.VK_5 && playerOne.getMoneyBalance()>=75)
		{
			if(battleGrid.isOpenLocation(row1,col1))
			{
				battleGrid.toggleOccupiedGridLocation(row1,col1);
				MonsterRunner.addPlayerOneItem(row1,new FireObstacle(playerOne,playerTwo,BattleGrid.blueFireAnimation,row1,col1));
			}
		}
		else if(keyCode==e.VK_NUMPAD5 && playerTwo.getMoneyBalance()>=75)
		{
			if(battleGrid.isOpenLocation(row2,17-col2))
			{
				battleGrid.toggleOccupiedGridLocation(row2,17-col2);
				MonsterRunner.addPlayerTwoItem(row2,new FireObstacle(playerTwo,playerOne,BattleGrid.redFireAnimation,row2,col2));
			}
		}
		else if(keyCode==e.VK_6 && playerOne.getMoneyBalance()>=75)
		{
			if(battleGrid.isOpenLocation(row1,col1))
			{
				battleGrid.toggleOccupiedGridLocation(row1,col1);
				MonsterRunner.addPlayerOneItem(row1,new SpringObstacle(playerOne,playerTwo,BattleGrid.blueSpringObstacle,row1,col1));
			}
		}
		else if(keyCode==e.VK_NUMPAD6 && playerTwo.getMoneyBalance()>=75)
		{
			if(battleGrid.isOpenLocation(row2,17-col2))
			{
				battleGrid.toggleOccupiedGridLocation(row2,17-col2);
				MonsterRunner.addPlayerTwoItem(row2,new SpringObstacle(playerTwo,playerOne,BattleGrid.redSpringObstacle,row2,col2));
			}
		}
		else if(keyCode==e.VK_7 && playerOne.getMoneyBalance()>=100 && playerOne.getPlayerHealth()<100)
		{
			MonsterRunner.addPlayerOneItem(0,new PowerUp(playerOne,"FirstAid"));
		}
		else if(keyCode==e.VK_NUMPAD7 && playerTwo.getMoneyBalance()>=100 && playerTwo.getPlayerHealth()<100)
		{
			MonsterRunner.addPlayerTwoItem(0,new PowerUp(playerTwo,"FirstAid"));
		}
		else if(keyCode==e.VK_8 && playerOne.getMoneyBalance()>=200 && playerOne.getPlayerHealth()<100)
		{
			MonsterRunner.addPlayerOneItem(0,new PowerUp(playerOne,"Hospital"));
		}
		else if(keyCode==e.VK_NUMPAD8 && playerTwo.getMoneyBalance()>=200 && playerTwo.getPlayerHealth()<100)
		{
			MonsterRunner.addPlayerTwoItem(0,new PowerUp(playerTwo,"Hospital"));
		}
		else if(keyCode==e.VK_9 && playerOne.getMoneyBalance()>=300 && playerOne.getPlayerHealth()<100)
		{
			MonsterRunner.addPlayerOneItem(0,new PowerUp(playerOne,"Reincarnation"));
		}
		else if(keyCode==e.VK_NUMPAD9 && playerTwo.getMoneyBalance()>=300 && playerTwo.getPlayerHealth()<100)
		{
			MonsterRunner.addPlayerTwoItem(0,new PowerUp(playerTwo,"Reincarnation"));
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
	//	System.out.println("the keyCode is " + e.getKeyCode());
		if (keyCode == e.VK_OPEN_BRACKET)
		{
			playerTwo.addCheat("1");
		}
		else if (keyCode == e.VK_CLOSE_BRACKET)
		{
			playerTwo.addCheat("2");
		}
		else if (keyCode == e.VK_BACK_SLASH)
		{
			playerTwo.addCheat("3");
		}
		else if (keyCode == e.VK_Z)
		{
			playerOne.addCheat("1");
		}
		else if (keyCode == e.VK_X)
		{
			playerOne.addCheat("2");
		}
		else if (keyCode == e.VK_C)
		{
			playerOne.addCheat("3");

//			System.out.println("reachedLALALALALA");
		}
		String cheatOne = playerOne.getCheat().toString();
		String cheatTwo = playerTwo.getCheat().toString();
		if (!(cheatOne.equals("1") || cheatOne.equals("12") || cheatOne.equals("123") || cheatOne.equals("3") ||
			cheatOne.equals("32") || cheatOne.equals("321")))
		{
			playerOne.setCheat("");
			repaint();
		}
		if (!(cheatTwo.equals("1") || cheatTwo.equals("12") || cheatTwo.equals("123") || cheatTwo.equals("3") ||
			cheatTwo.equals("32") || cheatTwo.equals("321")))
		{
			playerTwo.setCheat("");
			repaint();
		}
		if (playerOne.getCheat().toString().equals("123"))
		{
			playerOne.setCheat("");
			playerOne.gainMoney(500);
			repaint();
		}
		if (playerTwo.getCheat().toString().equals("123"))
		{
			playerTwo.setCheat("");
			playerTwo.gainMoney(500);
			repaint();
		}
		if (playerOne.getCheat().toString().equals("321"))
		{
			playerOne.setCheat("");
			playerOne.boostHealth(50);
			repaint();
		}
		if (playerTwo.getCheat().toString().equals("321"))
		{
			playerTwo.setCheat("");
			playerTwo.boostHealth(50);
			repaint();
		}
	}
	public void keyTyped(KeyEvent e)
	{
	//	System.out.println("reached");


	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==homeButton)
		{
			createNewGame();
			Domination.changeScreen("HOME");
		}
	}
	public void createNewGame()
	{
		playerOne.refreshStats();
		playerTwo.refreshStats();

		for(int i = 0;i<13;i++)
		{
			Vector<Item> playerOneRow = MonsterRunner.playerOneItems.get(i);
			Vector<Item> playerTwoRow = MonsterRunner.playerTwoItems.get(i);
			playerOneRow.clear();
			playerTwoRow.clear();
		}
		battleGrid.playerOneMoveMarker("RESET");
		battleGrid.playerTwoMoveMarker("RESET");
		battleGrid.resetOpenGridLocations();
		elapsedTime.setText("00:00:00");
		gameTimer.stop();
		numSeconds = numMinutes = numHours = 0;
//		numMinutes= 0;
//		numHours = 0;
	}
}
