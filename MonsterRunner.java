import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * A <code>MonsterRunner</code> moves each <code>Monster</code> that is currently on the <code>BattleGrid</code>
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class MonsterRunner implements Runnable
{
	//FIELDS
	public static Vector<Vector<Item>> playerOneItems;
	public static Vector<Vector<Item>> playerTwoItems;
	BattleGrid battleGrid;

	//CONSTRUCTORS
	public MonsterRunner(BattleGrid battleGrid)
	{
		this.battleGrid = battleGrid;
		playerOneItems = new Vector<Vector<Item>>();
		playerTwoItems = new Vector<Vector<Item>>();
		for(int i = 0;i<13;i++)
		{
			playerOneItems.add(new Vector<Item>());
			playerTwoItems.add(new Vector<Item>());
		}
	}

	//METHODS
	public void run()
	{
		while(true)
		{
			long startTime = System.currentTimeMillis();

			for(int i = 0;i<13;i++)
			{
				Vector<Item> playerOneRow = playerOneItems.get(i);
				Vector<Item> playerTwoRow = playerTwoItems.get(i);
				for(int x = 0; x<playerOneRow.size();x++)//Monster monster: playerOneItems.get(i))
				{
					playerOneRow.get(x).makeMove();//monster.makeMove();
					battleGrid.repaint();
				}
				for(int x = 0; x<playerTwoRow.size();x++)
				{
					playerTwoRow.get(x).makeMove();
				}
			}

			long endTime = System.currentTimeMillis();

			int waitTime = 100 - (int)(endTime - startTime);

			if(waitTime>0)
			{
				try {Domination.monsterThread.sleep(waitTime);}
				catch (InterruptedException e){}
			}
		}
	}
	public static void addPlayerOneItem(int row, Item item)
	{
		playerOneItems.get(row).add(item);
	}
	public static void addPlayerTwoItem(int row, Item item)
	{
		playerTwoItems.get(row).add(item);
	}
}