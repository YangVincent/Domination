import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
/**
 * An <code>Obstacle</code> is an <code>Item</code> that cannot move but has the ability of hurting or slowing opponents' progresses against
 * the owning <code>Player</code>.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class Obstacle implements Item
{
	Image[] animatedObstacle;
	Player player, opponent;
	int row, col, cost, damageDealt, damagingCapability, damageRate, damageReceived, animationTicker, currentHealth;
	Timer animationTimer;
	/**
	 * Constructs a new Obstacle
	 * @param player the Player that owns the Obstacle
	 * @param opponent the Player against the Player that owns the Obstacle.
	 * @param animatedObstacle the set of images that represent an Obstacle in its normal state.
	 * @param row the row in which this Obstacle resides
	 * @param col the column in which this Obstacle resides.
	 */
	public Obstacle(Player player,Player opponent,Image[] animatedObstacle,int row, int col)
	{
		this.player = player;
		this.opponent = opponent;
		this.row = row;
		this.col = col;
		this.animatedObstacle = animatedObstacle;
		this.cost = 50;
		this.player.spendMoney(cost);
		this.player.updateStat("numObstaclesPurchased");
		this.damageDealt = 0;
		this.damagingCapability = 500;
		this.damageRate = 0;
		this.damageReceived = 0;
		this.currentHealth = 2000;
		this.animationTicker = 0;
		this.animationTimer = new Timer(150,new ActionListener() {
			public void actionPerformed(ActionEvent e){}});
	}
	public void makeMove(double damage)
	{
		this.currentHealth -= damage;
		if (this.currentHealth<=0)
			this.removeSelfFromBattleGrid();
	}
	public void makeMove(){}
	public Image getCurrentImage(){return animatedObstacle[0];}
	public void removeSelfFromBattleGrid()
	{
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
						target.notifyWin();
					}
				}
			}
			thisRow = MonsterRunner.playerOneItems.get(row);
			for(int i = 0;i<thisRow.size();i++)
			{
				if(thisRow.get(i)==this)
				{
					thisRow.remove(i);
					BattleGrid.toggleOccupiedGridLocation(row, col);
					return;
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
						target.notifyWin();
				}
			}
			thisRow = MonsterRunner.playerTwoItems.get(row);
			for(int i = 0;i<thisRow.size();i++)
			{
				if(thisRow.get(i)==this)
				{
					thisRow.remove(i);
					BattleGrid.toggleOccupiedGridLocation(row,17-col);
					return;
				}
			}
		}
	}
	public int getXCoord()
	{
		if(player.getPlayerNumber().equals("playerOne"))
		{
			if (row==0)
			{
				return BattleGrid.blueX[0]+BattleGrid.incrementerX0*col-11;
			}
			else if(row==1)
			{
				return  BattleGrid.blueX[1]+BattleGrid.incrementerX1*col-11;
			}
			else if(row==2)
			{
				return  BattleGrid.blueX[2]+BattleGrid.incrementerX2*col-11;
			}
			else if(row==3)
			{
				return BattleGrid.blueX[3]+BattleGrid.incrementerX3*col-12;
			}
			else if(row==4)
			{
				return BattleGrid.blueX[4]+BattleGrid.incrementerX4*col-12;
			}
			else if(row==5)
			{
				return BattleGrid.blueX[5]+BattleGrid.incrementerX5*col-12;
			}
			else if(row==6)
			{
				return BattleGrid.blueX[6]+BattleGrid.incrementerX6*col-13;
			}
			else if(row==7)
			{
				return BattleGrid.blueX[7]+BattleGrid.incrementerX7*col-13;
			}
			else if(row==8)
			{
				return BattleGrid.blueX[8]+BattleGrid.incrementerX8*col-13;
			}
			else if(row==9)
			{
				return BattleGrid.blueX[9]+BattleGrid.incrementerX9*col-13;
			}
			else if(row==10)
			{
				return BattleGrid.blueX[10]+BattleGrid.incrementerX10*col-14;
			}
			else if(row==11)
			{
				return BattleGrid.blueX[11]+BattleGrid.incrementerX11*col-14;
			}
			else if(row==12)
			{
				return BattleGrid.blueX[12]+BattleGrid.incrementerX12*col-14;
			}
		}
		else if(player.getPlayerNumber().equals("playerTwo"))
		{
			if (row==0)
			{
				return  BattleGrid.redX[0]-BattleGrid.incrementerX0*col-7;
			}
			else if(row==1)
			{
				return  BattleGrid.redX[1]-BattleGrid.incrementerX1*col-14;
			}
			else if(row==2)
			{
				return  BattleGrid.redX[2]-BattleGrid.incrementerX2*col-14;
			}
			else if(row==3)
			{
				return BattleGrid.redX[3]-BattleGrid.incrementerX3*col-12;
			}
			else if(row==4)
			{
				return BattleGrid.redX[4]-BattleGrid.incrementerX4*col-12;
			}
			else if(row==5)
			{
				return BattleGrid.redX[5]-BattleGrid.incrementerX5*col-12;
			}
			else if(row==6)
			{
				return BattleGrid.redX[6]-BattleGrid.incrementerX6*col-13;
			}
			else if(row==7)
			{
				return BattleGrid.redX[7]-BattleGrid.incrementerX7*col-13;
			}
			else if(row==8)
			{
				return BattleGrid.redX[8]-BattleGrid.incrementerX8*col-13;
			}
			else if(row==9)
			{
				return BattleGrid.redX[9]-BattleGrid.incrementerX9*col-13;
			}
			else if(row==10)
			{
				return BattleGrid.redX[10]-BattleGrid.incrementerX10*col-14;
			}
			else if(row==11)
			{
				return BattleGrid.redX[11]-BattleGrid.incrementerX11*col-14;
			}
			else if(row==12)
			{
				return BattleGrid.redX[12]-BattleGrid.incrementerX12*col-14;
			}
		}
		return -1;
	}
	public boolean inFightingRange(Monster target)
	{
		if(Math.abs(getXCoord()-target.getXCoord())<=15)
			return true;
		return false;
	}
}