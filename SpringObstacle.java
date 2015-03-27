import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A <code>SpringObstacle</code> is an <code>Obtacle</code> that springs opposing <code>Monster</code>s backward.
 */
public class SpringObstacle extends Obstacle
{
	//FIELDS
	int numSprings, maxNumSprings, animationTicker;
	Timer animationTimer;
	Image currentImage;
	Image[] animatedObstacle;
	Player player, opponent;

	//CONSTRUCTORS
	/**
	 * Constructs a new SpringObstacle.
	 * @param player the Player that owns this SpringObstacle.
	 * @param opponent the Player against the Player that owns this SpringObstacle.
	 * @param animatedObstacle the set of images that represent this SpringObstacle.
	 * @param row the row in which this SpringObstacle resides.
	 * @param col the column in which this SpringObstacle resides.
	 */
	public SpringObstacle(Player player, Player opponent, Image[] animatedObstacleImages, int row, int col)
	{
		super(player,opponent,animatedObstacleImages,row,col);
		numSprings = 0;
		maxNumSprings = 10;
		animatedObstacle = animatedObstacleImages;
		this.player = player;
		this.opponent = opponent;
		player.updateStat("numSpringObstaclesPurchased");
		animationTicker = 0;
		currentImage = animatedObstacle[0];
		animationTimer = new Timer(30,new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(animationTicker<=8)
				{
					currentImage = animatedObstacle[animationTicker];
					animationTicker++;
				}
				else
				{
					currentImage = animatedObstacle[0];
					animationTimer.stop();
				}
			}
		});
	}
	//METHODS
	public void makeMove()
	{
		if(numSprings>=maxNumSprings)
			removeSelfFromBattleGrid();
	}
	public void playSpringAnimation()
	{
		animationTimer.stop();
		animationTicker = 0;

		animationTimer.start();
	//	System.out.println("num springs is " + numSprings);
		numSprings++;
		new Thread(new Song("springSound.wav")).start();
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
