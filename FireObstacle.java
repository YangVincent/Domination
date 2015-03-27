import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A <code>FireObstacle</code> is an <code>Obstacle</code> that does not take damage but instead deals damage to
 * all opponents that walk through. It dies when it has done a certain amount of damage.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class FireObstacle extends Obstacle
{
	//FIELDS
	int damageDealt;
	int damagingCapability;
	double damageRate;
	Timer animationTimer;
	int animationTicker;
	Image currentImage;
	Image[] animatedObstacle;
	Player player, opponent;

	//CONSTRUCTORS
	/**
	 * Constructs a new FireObstacle
	 * @param player The player who owns the FireObstacle
	 * @param opponent The player who is against the player who owns the FireObstacle
	 * @param animatedObstacleImages the set of images that represent the FireObstacle
	 * @param row the row in which the FireObstacle resides
	 * @param col the column in which the FireObstacle resides
	 */
	public FireObstacle(Player player, Player opponent, Image[] animatedObstacleImages, int row, int col)
	{
		super(player,opponent,animatedObstacleImages,row,col);
		damageDealt = 0;
		damagingCapability = 500;
		damageRate = 75.0;
		animatedObstacle = animatedObstacleImages;
		this.player = player;
		this.opponent = opponent;
		player.updateStat("numFireObstaclesPurchased");
		animationTicker = 0;
		animationTimer = new Timer(150,new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(animationTicker!=7)
				{
					currentImage = animatedObstacle[animationTicker];
					animationTicker++;
				}
				else
				{
					currentImage = animatedObstacle[7];
					animationTicker = 0;
				}
			}
		});
		animationTimer.start();
	}
	public void makeMove()
	{
		if(damageDealt>damagingCapability)
			removeSelfFromBattleGrid();
	}
	public Image getCurrentImage()
	{
		return currentImage;
	}
	public void increaseDamageDealt(int damage)
	{
		damageDealt+=damage;
	}
	public double getDamageRate()
	{
		return damageRate;
	}
}
