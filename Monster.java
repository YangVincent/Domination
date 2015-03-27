import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * A <code>Monster</code> is an <code>Item</code> that moves in its row and attacks opponents.
 * It has the ability of dealing damage to an opposing <code>Player</code>.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class Monster implements Movable, Item
{
	//FIELDS
	boolean inFight, gained;
	protected Image icon;
	private int numPixelsPerMove;
	private double currentHealth;
	public static final int DEFAULT_HEALTH_CONSTANT = 1000;
	public static final int DEFAULT_SPEED_CONSTANT = 5;
	public static final int DEFAULT_DPS_CONSTANT = 25;
	private int xCoord;
	private int yCoord;
	private double attackPercentage;
	private int endOfBattleGrid, row, moneyValue;
	Player player, opponent;
	Image currentImage;
	Image[] animationImages, fightingImages;
	Timer movementAnimationTimer, fightingAnimationTimer, springBackTimer;
	int animationTicker;
	boolean currentlySpringingBack;
	//CONSTRUCTORS
	//picture constructor
	/**
	 * Constructs a new default Monster.
	 * @param playerOneOrTwo the Player who owns this Monster.
	 * @param opponent the Player who is against the Player who owns this Monster.
	 * @param animationImagesArray the set of images that represent this Monster when in its normal state.
	 * @param fightingImagesArray the set of images that represent this Monster when it is fighting.
	 * @param row the row in which this Monster resides.
	 */
	public Monster(Player playerOneOrTwo, Player opponent, Image[] animationImagesArray, Image[] fightingImagesArray, int row)
	{
		player = playerOneOrTwo;
		inFight = false;
		this.opponent = opponent;
		gained = false;
		moneyValue = 50; //originally 100
		player.spendMoney(moneyValue);
		player.updateStat("numMonstersPurchased");
		player.repaint();
		this.row = row;
		currentlySpringingBack = false;zz
		if(player.getPlayerNumber().equals("playerOne"))
		{
			xCoord = BattleGrid.getRowXCoord1(row);
			endOfBattleGrid = BattleGrid.getRowXCoord2(row);
		}
		else
		{
			xCoord = BattleGrid.getRowXCoord2(row);
			endOfBattleGrid = BattleGrid.getRowXCoord1(row)-10;
		}
		animationImages = animationImagesArray;
		fightingImages = fightingImagesArray;
		currentHealth = DEFAULT_HEALTH_CONSTANT;
		numPixelsPerMove = DEFAULT_SPEED_CONSTANT;
		attackPercentage=15;
		animationTicker = 0;
		movementAnimationTimer = new Timer(150,new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(animationTicker<=5)
				{
					currentImage = animationImages[animationTicker];
					animationTicker++;
				}
				else
				{
					currentImage = animationImages[0];
					animationTicker = 1;
				}
			}
		});
		fightingAnimationTimer = new Timer(150,new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(animationTicker<=5)
				{
					currentImage = fightingImages[animationTicker];
					animationTicker++;
				}
				else
				{
					currentImage = fightingImages[0];
					animationTicker = 1;
				}
			}
		});

		springBackTimer = new Timer(75,new ActionListener()
		{
			int velocity1 = -35;
			int velocity2 = 35;
			public void actionPerformed(ActionEvent e)
			{
				if(player.getPlayerNumber().equals("playerOne"))
				{
					if(velocity1<0)
					{
						xCoord+=velocity1;
						velocity1+=2;
						currentlySpringingBack = true;
					}
					else
					{
						currentlySpringingBack = false;
						springBackTimer.stop();
						velocity1 = -35;
						velocity2 = 35;
					}
				}
				else
				{
					if(velocity2>0)
					{
						xCoord+=velocity2;
						velocity2-=2;
						currentlySpringingBack = true;
					}
					else
					{
						currentlySpringingBack = false;
						springBackTimer.stop();
						velocity1 = -35;
						velocity2 = 35;
					}
				}

			}
		});
		movementAnimationTimer.start();
	}
	/**
	 * Constructs a new Monster object that appears as the image indicated by the String parameter with
	 * a custom speed, health, attack, and value
	 */
	public Monster(Player playerOneOrTwo, Player opponent, Image[] animationImagesArray, Image[] fightingImagesArray, double speedPercentage, double healthPercentage, double attackPercentage, int moneyValue, int row)
	{
		this.player = playerOneOrTwo;
		this.opponent = opponent;
		this.animationImages = animationImagesArray;
		this.fightingImages = fightingImagesArray;
		this.row = row;
		currentlySpringingBack = false;
		xCoord = player.getPlayerNumber().equals("playerOne")?BattleGrid.getRowXCoord1(row):BattleGrid.getRowXCoord2(row);
		endOfBattleGrid = player.getPlayerNumber().equals("playerOne")?BattleGrid.getRowXCoord2(row):BattleGrid.getRowXCoord1(row)-10;
//		if(player.getPlayerNumber().equals("playerOne"))
//		{
//			xCoord = BattleGrid.getRowXCoord1(row);
//			endOfBattleGrid = BattleGrid.getRowXCoord2(row);
//		}
//		else
//		{
//			xCoord = BattleGrid.getRowXCoord2(row);
//			endOfBattleGrid = BattleGrid.getRowXCoord1(row)-10;
//		}
		this.animationTicker = 0;
		this.movementAnimationTimer = new Timer(150,new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(animationTicker<=5)
				{
					currentImage = animationImages[animationTicker];
					animationTicker++;
				}
				else
				{
					currentImage = animationImages[0];
					animationTicker = 1;
				}
			}
		});
		fightingAnimationTimer = new Timer(150,new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(animationTicker<=5)
				{
					currentImage = fightingImages[animationTicker];
					animationTicker++;
				}
				else
				{
					currentImage = fightingImages[0];
					animationTicker = 1;
				}
			}
		});



		springBackTimer = new Timer(75,new ActionListener()
		{
			int velocity1 = -35;
			int velocity2 = 35;
			public void actionPerformed(ActionEvent e)
			{
				if(player.getPlayerNumber().equals("playerOne"))
				{
					setInFightBoolean(false);
					if(velocity1<0)
					{
						xCoord+=velocity1;
						velocity1+=2;
						currentlySpringingBack = true;

					}
					else
					{
						currentlySpringingBack = false;
						springBackTimer.stop();
						velocity1 = -35;
						velocity2 = 35;
					}
				}
				else
				{
					setInFightBoolean(false);
					if(velocity2>0)
					{
						xCoord+=velocity2;
						velocity2-=2;
						currentlySpringingBack = true;
					}
					else
					{
						currentlySpringingBack = false;
						springBackTimer.stop();
						velocity1 = -35;
						velocity2 = 35;

					}
				}

			}
		});
		movementAnimationTimer.start();
		currentHealth = DEFAULT_HEALTH_CONSTANT * healthPercentage;
		numPixelsPerMove = (int)(DEFAULT_SPEED_CONSTANT*speedPercentage);
		this.attackPercentage = (int)(DEFAULT_DPS_CONSTANT * attackPercentage);
		this.moneyValue = moneyValue;
		player.spendMoney(moneyValue);
	}
	//METHODS
	public void walk()
	{
		fightingAnimationTimer.stop();
		movementAnimationTimer.start();
	}

	public void fight(Monster victim)
	{
		setInFightBoolean(true);
		int delay = animationTicker;
	//	new Thread(new Song("attackSound.wav")).start(); //play sound
		victim.movementAnimationTimer.stop();
		victim.fightingAnimationTimer.start();
		victim.takeDamage(getAttackPercentage());
		if(victim.getCurrentHealth()<=0)
		{
			inFight = false;
			victim.removeSelfFromBattleGrid();
			fightingAnimationTimer.stop();
			movementAnimationTimer.start();
		}
	}
	public boolean getInFightBoolean()
	{
		return inFight;
	}
	public double getHealth()
	{
		return this.currentHealth;
	}
	public Image getCurrentImage()
	{
		return currentImage;
	}
	public int getXCoord()
	{
		return xCoord;
	}
	public int getRow()
	{
		return row;
	}
	public void notifyWin()
	{
		setInFightBoolean(false);
	}
	public boolean inRangeObstacle(Obstacle obstacle)
	{
		if(Math.abs(getXCoord()-obstacle.getXCoord())<15)
		{
			return true;
		}
		return false;
	}
	public boolean inFightingRange(Monster target)
	{
		if(Math.abs(getXCoord()-target.getXCoord())<=15)
			return true;
		return false;
	}
	public double getCurrentHealth()
	{
		return currentHealth;
	}
	public double getAttackPercentage()
	{
		return attackPercentage;
	}
	public void setInFightBoolean(boolean b)
	{
		this.inFight = b;
		if(b)
		{
			movementAnimationTimer.stop();
			fightingAnimationTimer.start();
		}
		else
		{
			fightingAnimationTimer.stop();
			movementAnimationTimer.start();
		}
	}
	/**
	 * equivalent to the act() method in GridWorld
	 *
	 * Each Monster can only deal damage to one opponent at a time. However, each Monster may take damage from
	 * multiple opponents simultaneously
	 */
	public void encounterObstacle(Obstacle obstacle)
	{
		if(obstacle instanceof FireObstacle)
		{
			takeDamage(((FireObstacle)obstacle).getDamageRate());
			((FireObstacle)obstacle).increaseDamageDealt(10);
			movementAnimationTimer.stop();
			fightingAnimationTimer.start();
			obstacle.makeMove();
		}
		else if(obstacle instanceof SpringObstacle && !inFight )
		{
			((SpringObstacle)(obstacle)).playSpringAnimation();
			this.setInFightBoolean(false);
			//NOTIFY HERE
			if(player.getPlayerNumber().equals("playerOne"))
			{
				Vector<Item> thisRow;
				for(int i = 0;i<MonsterRunner.playerTwoItems.get(row).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
				{
					if(MonsterRunner.playerTwoItems.get(row).get(i) instanceof Monster)
					{
						Monster target = (Monster)MonsterRunner.playerTwoItems.get(row).get(i);
						if(inFightingRange(target))
						{
							target.setInFightBoolean(false);
						}
					}
				}
			}
			else if(player.getPlayerNumber().equals("playerTwo"))
			{
				Vector<Item> thisRow;
				for(int i = 0;i<MonsterRunner.playerOneItems.get(row).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
				{
					if(MonsterRunner.playerOneItems.get(row).get(i) instanceof Monster)
					{
						Monster target = (Monster)MonsterRunner.playerOneItems.get(row).get(i);
						if(inFightingRange(target))
							target.setInFightBoolean(false);
					}
				}
			}
			springBackTimer.start();
		}
		else
		{
			obstacle.makeMove(attackPercentage);
		}
	}
	public void makeMove()
	{
		if(player.getPlayerNumber().equals("playerOne"))
		{
			boolean encounteredObstacle = false;
			for(int i = 0;i<MonsterRunner.playerTwoItems.get(getRow()).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
			{
				Monster target;
				Vector<Item> thisRow = MonsterRunner.playerTwoItems.get(getRow());
				if(thisRow.get(i) instanceof FireObstacle)
				{
					FireObstacle obstacle = (FireObstacle)thisRow.get(i);
					if(inRangeObstacle(obstacle))
					{
						encounterObstacle(obstacle);
						encounteredObstacle = true;
						movementAnimationTimer.stop();
						fightingAnimationTimer.start();
					}
				}
				else if(thisRow.get(i) instanceof SpringObstacle)
				{

					SpringObstacle obstacle = (SpringObstacle)thisRow.get(i);
					if(inRangeObstacle(obstacle) && !currentlySpringingBack && !inFight)
					{
						setInFightBoolean(false);
						encounterObstacle(obstacle);
					}

				}
				else if(thisRow.get(i) instanceof Obstacle)
				{
					Obstacle obstacle = (Obstacle)thisRow.get(i);
					if (inRangeObstacle(obstacle) && !currentlySpringingBack)
					{
						setInFightBoolean(true);
						encounterObstacle(obstacle);
					}
				}
				else if(thisRow.get(i) instanceof Monster)
				{
					target = (Monster)thisRow.get(i);
					if(inFightingRange(target))
						fight(target);
				}
			}
			if(!inFight)
			{
				move();
			}
			if(!encounteredObstacle && !inFight)
				walk();
		}
		else if(player.getPlayerNumber().equals("playerTwo"))
		{
			boolean encounteredObstacle = false;
			for(int i = 0;i<MonsterRunner.playerOneItems.get(getRow()).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
			{
				Monster target;
				Vector<Item> thisRow = MonsterRunner.playerOneItems.get(getRow());
				if(thisRow.get(i) instanceof FireObstacle)
				{
					FireObstacle obstacle = (FireObstacle)thisRow.get(i);
					if(inRangeObstacle(obstacle))
					{
						encounterObstacle(obstacle);
						encounteredObstacle = true;
					}
					if(encounteredObstacle)
					{
						movementAnimationTimer.stop();
						fightingAnimationTimer.start();
					}
				}
				else if(thisRow.get(i) instanceof SpringObstacle)
				{
		//			this.setInFightBoolean(false);
					SpringObstacle obstacle = (SpringObstacle)thisRow.get(i);
					if(inRangeObstacle(obstacle) && !currentlySpringingBack && !inFight)
					{
						this.setInFightBoolean(false);
						setInFightBoolean(false);
						encounterObstacle(obstacle);
					}
				}
				else if(thisRow.get(i) instanceof Obstacle)
				{
					Obstacle obstacle = (Obstacle)thisRow.get(i);
					if (inRangeObstacle(obstacle) && !currentlySpringingBack)
					{
						setInFightBoolean(true);
						encounterObstacle(obstacle);
					}
				}
				else if(thisRow.get(i) instanceof Monster)
				{
					target = (Monster)thisRow.get(i);
					if(inFightingRange(target))
						fight(target);
				}
			}
			if(!inFight)
				move();
			if(!encounteredObstacle && !inFight)
				walk();
		}
	}
	/**
	 * Moves the Monster
	 */
	public void move()
	{
		if(getHealth()<=0)
		{
			removeSelfFromBattleGrid();
			return;
		}
		if (player.getPlayerNumber().equals("playerTwo"))
		{
			if(xCoord<=endOfBattleGrid)
			{
				removeSelfFromBattleGrid();
				opponent.reduceHealth(5);
				opponent.repaint();
				Domination.checkForGameEnd();
				return;
			}

			xCoord -= numPixelsPerMove;

		}
		else
		{
			if(xCoord>=endOfBattleGrid)
			{
				removeSelfFromBattleGrid();
				opponent.reduceHealth(5);
				opponent.repaint();
				Domination.checkForGameEnd();
				return;
			}

			xCoord += numPixelsPerMove;

		}
	}
	public void takeDamage(double damageTaken)
	{
		currentHealth -= damageTaken;
	}
	public double getMoneyValue()
	{
		return this.moneyValue;
	}
	public void removeSelfFromBattleGrid()
	{
		if(inFight)
		{
			if(player.getPlayerNumber().equals("playerOne"))
			{
				for(int i = 0;i<MonsterRunner.playerTwoItems.get(getRow()).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
				{
					if(MonsterRunner.playerTwoItems.get(getRow()).get(i) instanceof Monster)
					{
						Monster target = (Monster)MonsterRunner.playerTwoItems.get(getRow()).get(i);
						if(inFightingRange(target))
						{
							target.setInFightBoolean(false);
							if (!gained)
							{
								opponent.gainMoney(50);
								gained = true;
							}
						}
					}
				}
				gained = false;
			}
			else if(player.getPlayerNumber().equals("playerTwo"))
			{
				for(int i = 0;i<MonsterRunner.playerOneItems.get(getRow()).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
				{
					if(MonsterRunner.playerOneItems.get(getRow()).get(i) instanceof Monster)
					{
						Monster target = (Monster)MonsterRunner.playerOneItems.get(getRow()).get(i);
						if(inFightingRange(target))
						{
							target.setInFightBoolean(false);//not in fight
							if (!gained)
							{
								opponent.gainMoney(50);
								gained = true;
							}
						}
					}
				}
				gained = false;
			}
		}
		if(player.getPlayerNumber().equals("playerOne"))
		{
			for(int i = 0;i<MonsterRunner.playerOneItems.get(getRow()).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
			{
				if(MonsterRunner.playerOneItems.get(getRow()).get(i) instanceof Monster)
				{
					Monster target = (Monster)MonsterRunner.playerOneItems.get(getRow()).get(i);
					if(target==this)
					{
						MonsterRunner.playerOneItems.get(getRow()).remove(i);
						return;
					}
				}
			}
		}
		else/* if(player.getPlayerNumber().equals("playerTwo"))*/
		{
			for(int i = 0;i<MonsterRunner.playerTwoItems.get(getRow()).size();i++)//Monster target: MonsterRunner.playerTwoItems.get(getRow()))
			{
				if(MonsterRunner.playerTwoItems.get(getRow()).get(i) instanceof Monster)
				{
					Monster target = (Monster)MonsterRunner.playerTwoItems.get(getRow()).get(i);
					if(target==this)
					{
						MonsterRunner.playerTwoItems.get(getRow()).remove(i);
						return;
					}
				}
			}
		}
	}
}