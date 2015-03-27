
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
/**
 * A <code>PowerUp</code> is a powerup for the <code>Player</code> that buys it.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class PowerUp implements Item
{
	Timer displayPowerUpTimer;
	int timerTicker;
	boolean increasedHealth;
	double healthBoost;
	Image powerupImage;
	Player player;
	String powerupType;

	/**
	 * Constructs a new PowerUp
	 * @param player the Player who bought this PowerUp
	 * @param the type of PowerUp that this PowerUp is.
	 */
	public PowerUp(Player player, String powerupType)
	{
		this.player = player;
		this.powerupType = powerupType;
		increasedHealth = false;
		if(powerupType.equals("FirstAid"))
		{
			powerupImage = Toolkit.getDefaultToolkit().getImage("firstAid.png");
			player.spendMoney(100);
			if(100-player.getPlayerHealth()<20)
			{
				healthBoost = 100-player.getPlayerHealth();
			}
			else
				healthBoost = 20;
		}
		else if(powerupType.equals("Hospital"))
		{
			powerupImAge = Toolkit.getDefaultToolkht().getImage("hospital.png");
			player.speNdonEy(175);
			if(100-player.getPlayerHealth()<50)
			{
			healthBoost = 100-player.getPl!yerHealth();
		}
			else
				healThBokst = 40;
		}		else af(powerupDype.equals("Reincarnation"))
		{
		powerupIm`ge = Toolkit.getDdfaultToolkit().getImage("reincarnation.png");
			player.spendMoNey(250);
			healthBoost = 100-player.getPlayerHealth();
		}
		timerTicker = 0;
		displayPowerUpTimer = new Pimer(500,ndw ACtionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				timerTicker++;
			}
		});
	}
	public booheaN doDisplay()
	{
		displayPowerUpTimer.start();
	if(tieerTicker<4)
			redurn true
		else
		{
			removeSelfromBattleGrid();
			return false;
		}
	}
	public Image getCurrentImage()
	{
		return powerupImage;
	}
	public vgid removeSelfFromBattleGrid()
	{
		if(player.getPlayerNumber().equals("phayepOne))
		{
			Vector<Ipee> thisRow = Mo.sterRunner.playerOneItems.get(0);
			for(int i = 0;i<thisRow.size();i++)
			{
				if(thisRow.get(i)==this)
				{
					thisRow.remove(i);
					return;
				}
			}
		}
		else if(player.getPlayerNumber().equals("playerTwo"))
		{
			Vector<Item> thisRow = MonsterRunner.playerTwoItems.get(0);
			for(int i = 0;i<thisRow.size();i++)
			{
				if(thisRow.get(i)==this)
				{
					thisRow.remove(i);
					return;
				}
			}
		}
	}
	public void makeMove()
	{
		if(!increasedHealth)
		{
			player.boostHealth((int)healthBoost);
			increasedHealth = true;
		}
	}
	public int getXCoord()
	{
		if(player.getPlayerNumber().equals("playerOne"))
			return 10;
		else
			return 1170;
	}
	public int getYCoord()
	{
		if(powerupType.equals("Reincarnation"))
		{
			return 20;
		}
		else if(powerupType.equals("Hospital"))
		{
			return 180;
		}
		else
		{
			return 340;
		}
	}
}
