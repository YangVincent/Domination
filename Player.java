
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * A <code>Player</code> represents a real-life Player who is playing the Domination game.
 */
public class Player extends JPanel
{
	private StringBuilder cheat;
	private String playerNumber;
	private String playerName;
	double health;
	private int money;

	//statistics
	HashMap<String,Integer> statistics;
	Image moneyIcon;
	JLabel playerNameLabel;
	Graphics2D g2;
	Image background;
	Image store;
	/**
	 * Constructs a new Player
	 * @param playerNumber the number of this Player
	 */
	public Player(String playerNumber)
	{
		background = Toolkit.getDefaultToolkit().getImage(playerNumber+"Panel.jpg");
		store = Toolkit.getDefaultToolkit().getImage("store.png");
		health = 100.0;
		money = 2000;
		this.playerNumber = playerNumber;
		playerNameLabel = new JLabel();
		setPreferredSize(new Dimension(Domination.screenWidth/2-75,Domination.screenHeight/3));
		playerNameLabel.setFont(new Font("Tahoma",Font.BOLD,36));
		playerNameLabel.setForeground(Color.WHITE);
		playerNameLabel.setText(playerName);
		add(playerNameLabel);
		moneyIcon = Toolkit.getDefaultToolkit().getImage("money.png");
		statistics = new HashMap<String,Integer>();
		statistics.put("numMonstersPurchased",0);
		statistics.put("numBoltMonstersPurchased",0);
		statistics.put("numJuggernautMonstersPurchased",0);
		statistics.put("numObstaclesPurchased",0);
		statistics.put("numFireObstaclesPurchased",0);
		statistics.put("numSpringObstaclesPurchased",0);
		statistics.put("numFights",0);
		statistics.put("numFirstAidsPurchased", 0);
		statistics.put("numHospitalsPurchased", 0);
		statistics.put("numReincarnationsPurchased", 0);
		cheat = new StringBuilder(3);
		//add(store,BorderLayout.SOUTH);
	}

	public void paintComponent(Graphics g)
	{
		g2 = (Graphics2D)g;
		g2.scale(Domination.ratioX, Domination.ratioY);
		g2.setColor(Color.WHITE);
		g2.drawImage(background,0,0,565,267,this);
		g2.fillRoundRect(130,50,280,10,5,5);
		g2.setFont(new Font("Tahoma",Font.BOLD,14));
		g2.drawString("Health: "+getPlayerHealth()+"%",15,60);
		g2.drawString("Money: "+getMoneyBalance()+"", 455, 60);
		g2.drawImage(moneyIcon,425,42,25,25,this);
		g2.setColor(playerNumber.equals("playerOne")?Color.CYAN:Color.RED);
//		if(playerNumber.equals("playerOne"))
//			g2.setColor(Color.CYAN);
//		else
//			g2.setColor(Color.RED);
		g2.fillRoundRect(130,51,(int)(278*(getPlayerHealth()/100.0)),8,4,4);
		g2.drawImage(store,30,70,505,200,this);
	}

	public void setName(String name)
	{
		playerName = name;
		playerNameLabel.setText(name);
	}
	public String getPlayerName()
	{
		return playerName;
	}
	public int getMoneyBalance()
	{
		return money;
	}
	public double getPlayerHealth()
	{
		return health;
	}
	public void reduceHealth(int percentage)
	{
		health-=percentage;
		repaint();
	}
	public void boostHealth(int healthBoost)
	{
		health+=healthBoost;
		if (health > 100)
			health = 100;
		repaint();
	}
	public String getPlayerNumber()
	{
		return playerNumber;
	}
	public void spendMoney(int cost)
	{
		money-=cost;
		repaint();
	}
	public void gainMoney(int gain)
	{
		money += gain;
		repaint();
	}
	public void refreshStats()
	{
		health = 100.0;
		money = 2000;
		setName("");
	}

	public void updateStat(String stat)
	{
		int current = statistics.get(stat);
		statistics.remove(stat);
		statistics.put(stat,current+1);
	}

	public HashMap<String,Integer> getStatistics()
	{
		return statistics;
	}
	public void addCheat(String str)
	{
		cheat.append(str);
	}
	public StringBuilder getCheat()
	{
		return cheat;
	}
	public void setCheat(String str)
	{
		cheat = new StringBuilder(str);
	}
}
