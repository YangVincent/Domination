
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * A <code>BattleGrid</code> is a grid on which two <code>Player</code>s fight.
 * It holds <code>Monster</code>s and <code>Obstacle</code>s.
 * @author Ayush Mehra
 * @author Vincent Yang
 * fool
 */
public class BattleGrid extends JPanel
{
	//FIELDS
	private int markerRow1, markerCol1, markerRow2, markerCol2;
	private static boolean[][] occupiedGridLocations;
	Image background, grid;
	public static Image[] blueMonsterAnimation = new Image[6];
	public static Image[] blueMonsterFightAnimation = new Image[6];
	public static Image[] redMonsterAnimation = new Image[6];
	public static Image[] redMonsterFightAnimation = new Image[6];
	public static Image[] blueFireAnimation = new Image[8];
	public static Image[] redFireAnimation = new Image[8];
	public static Image[] blueBoltMonsterAnimation = new Image[6];
	public static Image[] redBoltMonsterAnimation = new Image[6];
	public static Image[] blueBoltMonsterFightAnimation = new Image[6];
	public static Image[] redBoltMonsterFightAnimation = new Image[6];
	public static Image[] blueJuggernautMonsterAnimation = new Image[6];
	public static Image[] redJuggernautMonsterAnimation = new Image[6];
	public static Image[] blueJuggernautMonsterFightAnimation = new Image[6];
	public static Image[] redJuggernautMonsterFightAnimation = new Image[6];
	public static Image[] blueDisappearMonster = new Image[8];
	public static Image[] redDisappearMonster = new Image[8];
	public static Image[] blueSpringObstacle = new Image[9];
	public static Image[] redSpringObstacle = new Image[9];
	public static Image[] obstacleAnimation = {Toolkit.getDefaultToolkit().getImage("obstacle.png")};
	Timer animationTimer;
	int animationTicker = 0;
	Image blueFireImage, redFireImage, blueMarker, redMarker;
	Graphics2D g2;
	Monster blueMonster, redMonster;
	public static int[] blueX = {263,256,248,240,232,224,214,204,195,185,176,162,152};
	private int[] incrementerX = {44,45,46,47,48,49,50,51,52,53,54,56,57};
	public static int incrementerX0 = 44;
	public static int incrementerX1 = 45;
	public static int incrementerX2 = 46;
	public static int incrementerX3 = 47;
	public static int incrementerX4 = 48;
	public static int incrementerX5 = 49;
	public static int incrementerX6 = 50;
	public static int incrementerX7 = 51;
	public static int incrementerX8 = 52;
	public static int incrementerX9 = 53;
	public static int incrementerX10 = 54;
	public static int incrementerX11 = 56;
	public static int incrementerX12 = 57;
	public static int[] redX;
	private int[] Y = {25,55,85,120,153,190,228,267,310,352,396,445,495};
//	int Y0 = 25;
//	int Y1 = 55;
//	int Y2 = 85;
//	int Y3 = 120;
//	int Y4 = 153;
//	int Y5 = 190;
//	int Y6 = 228;
//	int Y7 = 267;
//	int Y8 = 310;
//	int Y9 = 352;
//	int Y10 = 396;
//	int Y11 = 445;
//	int Y12 = 495;
	Thread monsterThread;

	//CONSTRUCTORS

	/**
	 * Constructs a default BattleGrid.
	 */
	public BattleGrid()
	{
		background = Toolkit.getDefaultToolkit().getImage("background.gif");
		grid = Toolkit.getDefaultToolkit().getImage("grid2.png");
		occupiedGridLocations = new boolean[13][18];
		for(int row = 0;row<13;row++)
		{
			for(int col = 0;col<18;col++)
			{
				occupiedGridLocations[row][col] = false;
			}
		}
		blueMarker = Toolkit.getDefaultToolkit().getImage("blueMarker.png");
		redMarker = Toolkit.getDefaultToolkit().getImage("redMarker.png");
		markerRow1 = 0;
		markerCol1 = 0;
		markerRow2 = 0;
		markerCol2 = 0;
		redX = new int[13];
		for(int i = 0;i<blueX.length;i++)
		{
			redX[i] = 1280-blueX[i]-6;
		}
		for(int i = 1;i<=6;i++)
		{
			blueMonsterAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/BlueMonster/blue"+i+".png");
			redMonsterAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/RedMonster/red"+i+".png");
			blueMonsterFightAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/BlueFightingMonster/blueFight"+i+".png");
			redMonsterFightAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/RedFightingMonster/redFight"+i+".png");
			blueBoltMonsterAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/BlueBoltMonster/blue"+i+".png");
			redBoltMonsterAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/RedBoltMonster/red"+i+".png");
			blueBoltMonsterFightAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/BlueFightingBoltMonster/blue"+i+".png");
			redBoltMonsterFightAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/RedFightingBoltMonster/red"+i+".png");
			blueJuggernautMonsterAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/BlueJuggernautMonster/blue"+i+".png");
			blueJuggernautMonsterFightAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/BlueFightingJuggernautMonster/blue"+i+".png");
			redJuggernautMonsterAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/RedJuggernautMonster/red"+i+".png");
			redJuggernautMonsterFightAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/RedFightingJuggernautMonster/red"+i+".png");
		}
		for(int i = 1;i<=8;i++)
		{
			blueFireAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/Fire/BlueFire/blueFire"+i+".png");
			redFireAnimation[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/Fire/RedFire/redFire"+i+".png");
			blueDisappearMonster[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/BlueMonsterReachEnd/blue"+i+".png");
			redDisappearMonster[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/RedMonsterReachEnd/red"+i+".png");
		}
		for(int i = 1;i<=9;i++)
		{
			blueSpringObstacle[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/SpringObstacle/BlueSpring/spring"+i+".png");
			redSpringObstacle[i-1] = Toolkit.getDefaultToolkit().getImage("Animations/SpringObstacle/RedSpring/spring"+i+".png");
		}
		animationTimer = new Timer(150,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(animationTicker!=7)
				{
					blueFireImage = blueFireAnimation[animationTicker];
					redFireImage = redFireAnimation[animationTicker];
					animationTicker++;
					repaint();
				}
				else
				{
					blueFireImage = blueFireAnimation[7];
					redFireImage = redFireAnimation[7];
					animationTicker = 0;
					repaint();
				}
			}
		});
		animationTimer.start();
	}

	//METHODS

	/**
	 * Paints the BattleGrid
	 */
	public void paint(Graphics g)
	{
		g2 = (Graphics2D)g;
		g2.scale(Domination.ratioX,Domination.ratioY);
		double imageScale = (Domination.screenHeight*0.666666666666)/grid.getHeight(this);
		int imageScaledHeight = (int)(grid.getHeight(this)*imageScale);
		int imageScaledWidth = (int)(grid.getWidth(this)*2*imageScale);
		g2.drawImage(background,0,0,1280, 1600/3+10,this);
		g2.drawImage(grid,116,10,1056,533,this);
		for(int i = 0;i<13;i++)
		{
			for(int x = 0;x<MonsterRunner.playerOneItems.get(i).size();x++)
			{
				Item item = MonsterRunner.playerOneItems.get(i).get(x);
				if(item instanceof Monster)
					g2.drawImage(item.getCurrentImage(), item.getXCoord()-15, getRowYCoord(i)-25,38,44, this);
				else if(item instanceof FireObstacle)
					g2.drawImage(item.getCurrentImage(), item.getXCoord(), getRowYCoord(i)-10,34,30, this);
				else if(item instanceof SpringObstacle)
					g2.drawImage(item.getCurrentImage(), item.getXCoord(), getRowYCoord(i)-10,34,30, this);
				else if(item instanceof Obstacle)
					g2.drawImage(item.getCurrentImage(), item.getXCoord(), getRowYCoord(i)-10,34,30, this);
				else if(item instanceof PowerUp)
				{
					if(((PowerUp) item).toDisplay())
					{
						g2.drawImage(item.getCurrentImage(), item.getXCoord(), ((PowerUp) item).getYCoord(),100,120, this);
					}
				}
			}
			for(int x = 0;x<MonsterRunner.playerTwoItems.get(i).size();x++)
			{
				Item item = MonsterRunner.playerTwoItems.get(i).get(x);
				if(item instanceof Monster)
					g2.drawImage(item.getCurrentImage(), item.getXCoord(), getRowYCoord(i)-25,38,44, this);
				else if(item instanceof FireObstacle)
					g2.drawImage(item.getCurrentImage(), item.getXCoord(), getRowYCoord(i)-10,34,30, this);
				else if(item instanceof SpringObstacle)
					g2.drawImage(item.getCurrentImage(), item.getXCoord(), getRowYCoord(i)-10,34,30, this);
				else if(item instanceof Obstacle)
					g2.drawImage(item.getCurrentImage(), item.getXCoord(), getRowYCoord(i)-10,34,30, this);
				else if(item instanceof PowerUp)
				{
					if(((PowerUp) item).toDisplay())
					{
						g2.drawImage(item.getCurrentImage(), item.getXCoord(), ((PowerUp) item).getYCoord(),100,120, this);
					}
				}
			}
		}
		int screenResolutionScale = 1280/Domination.screenWidth;
		int row = 0;
		int col = 0;
		g.setColor(Color.CYAN);
		//player one marker updater
		if (markerRow1==0)
		{
			g2.drawImage(blueMarker, blueX[0]+incrementerX[0]*markerCol1-11, Y[0]-9, 32, 24, this);
		}
		else if(markerRow1==1)
		{
			g2.drawImage(blueMarker, blueX[1]+incrementerX[1]*markerCol1-11, Y[1]-7, 32, 24, this);
		}
		else if(markerRow1==2)
		{
			g2.drawImage(blueMarker, blueX[2]+incrementerX[2]*markerCol1-11, Y[2]-6, 32, 24, this);
		}
		else if(markerRow1==3)
		{
			g2.drawImage(blueMarker,blueX[3]+incrementerX[3]*markerCol1-12, Y[3]-8, 33, 26,this);
		}
		else if(markerRow1==4)
		{
			g2.drawImage(blueMarker,blueX[4]+incrementerX[4]*markerCol1-12, Y[4]-6, 33, 26,this);
		}
		else if(markerRow1==5)
		{
			g2.drawImage(blueMarker,blueX[5]+incrementerX[5]*markerCol1-12, Y[5]-6, 33, 26,this);
		}
		else if(markerRow1==6)
		{
			g2.drawImage(blueMarker,blueX[6]+incrementerX[6]*markerCol1-13, Y[6]-7, 34, 27,this);
		}
		else if(markerRow1==7)
		{
			g2.drawImage(blueMarker,blueX[7]+incrementerX[7]*markerCol1-13, Y[7]-8, 36, 31,this);
		}
		else if(markerRow1==8)
		{
			g2.drawImage(blueMarker,blueX[8]+incrementerX[8]*markerCol1-13, Y[8]-10, 36, 31,this);
		}
		else if(markerRow1==9)
		{
			g2.drawImage(blueMarker,blueX[9]+incrementerX[9]*markerCol1-13, Y[9]-10, 36, 31,this);
		}
		else if(markerRow1==10)
		{
			g2.drawImage(blueMarker,blueX[10]+incrementerX[10]*markerCol1-14, Y[10]-10, 37, 33,this);
		}
		else if(markerRow1==11)
		{
			g2.drawImage(blueMarker,blueX[11]+incrementerX[11]*markerCol1-14, Y[11]-10, 38, 34,this);
		}
		else if(markerRow1==12)
		{
			g2.drawImage(blueMarker,blueX[12]+incrementerX[12]*markerCol1-14, Y[12]-10, 38, 34,this);
		}
		g.setColor(Color.RED);
		if (markerRow2==0)
		{
			g2.drawImage(redMarker, redX[0]-incrementerX[0]*markerCol2-14, Y[0]-9, 32, 24, this);
		}
		else if(markerRow2==1)
		{
			g2.drawImage(redMarker, redX[1]-incrementerX[1]*markerCol2-14, Y[1]-7, 32, 24, this);
		}
		else if(markerRow2==2)
		{
			g2.drawImage(redMarker, redX[2]-incrementerX[2]*markerCol2-14, Y[2]-6, 32, 24, this);
		}
		else if(markerRow2==3)
		{
			g2.drawImage(redMarker,redX[3]-incrementerX[3]*markerCol2-12, Y[3]-8, 33, 26,this);
		}
		else if(markerRow2==4)
		{
			g2.drawImage(redMarker,redX[4]-incrementerX[4]*markerCol2-12, Y[4]-6, 33, 26,this);
		}
		else if(markerRow2==5)
		{
			g2.drawImage(redMarker,redX[5]-incrementerX[5]*markerCol2-12, Y[5]-6, 33, 26,this);
		}
		else if(markerRow2==6)
		{
			g2.drawImage(redMarker,redX[6]-incrementerX[6]*markerCol2-13, Y[6]-7, 34, 27,this);
		}
		else if(markerRow2==7)
		{
			g2.drawImage(redMarker,redX[7]-incrementerX[7]*markerCol2-13, Y[7]-8, 36, 31,this);
		}
		else if(markerRow2==8)
		{
			g2.drawImage(redMarker,redX[8]-incrementerX[8]*markerCol2-13, Y[8]-10, 36, 31,this);
		}
		else if(markerRow2==9)
		{
			g2.drawImage(redMarker,redX[9]-incrementerX[9]*markerCol2-13, Y[9]-10, 36, 31,this);
		}
		else if(markerRow2==10)
		{
			g2.drawImage(redMarker,redX[10]-incrementerX[10]*markerCol2-14, Y[10]-10, 37, 33,this);
		}
		else if(markerRow2==11)
		{
			g2.drawImage(redMarker,redX[11]-incrementerX[11]*markerCol2-14, Y[11]-10, 38, 34,this);
		}
		else if(markerRow2==12)
		{
			g2.drawImage(redMarker,redX[12]-incrementerX[12]*markerCol2-14, Y[12]-10, 38, 34,this);
		}
	}
	/**
	 * Checks of a certain location on the BattleGrid is open
	 */
	public static boolean isOpenLocation(int row, int col)
	{
		return !occupiedGridLocations[row][col];
//		if(occupiedGridLocations[row][col])
//			return false;
//		return true;
	}
	/**
	 * Changes the availability of a certain location on the BattleGrid
	 */
	public static void toggleOccupiedGridLocation(int row, int col)
	{
		occupiedGridLocations[row][col] = !occupiedGridLocations[row][col];
//		boolean current = occupiedGridLocations[row][col];
//		if(current)
//			occupiedGridLocations[row][col] = false;
//		else
//			occupiedGridLocations[row][col] = true;
	}
	/**
	 * Updates the 1st Player's Marker's row and column coordinates in the 2D array
	 */
	public void playerOneMoveMarker(String direction)
	{
		if(direction.equals("DOWN"))
		{
			if(markerRow1!=12)
			{
				markerRow1++;
			}
		}
		else if(direction.equals("UP"))
		{
			if(markerRow1!=0)
			{
				markerRow1--;
			}
		}
		else if(direction.equals("LEFT"))
		{
			if(markerCol1!=0)
			{
				markerCol1--;
			}
		}
		else if(direction.equals("RIGHT"))
		{
			if(markerCol1!=8)
			{
				markerCol1++;
			}
		}
		else if(direction.equals("RESET"))
		{
			markerCol1=0;
			markerRow1=0;
		}
		repaint();
	}
	/**
	 * Updates the 2nd Player's Marker's row and column coordinates in the 2D array
	 */
	public void playerTwoMoveMarker(String direction)
	{
		if(direction.equals("DOWN") && markerRow2 != 12)
		{
			markerRow2++;
		}
		else if(direction.equals("UP") && markerRow2!=0)
		{
			markerRow2--;
		}
		else if(direction.equals("LEFT") && markerCol2 != 8)
		{
			markerCol2++;
		}
		else if(direction.equals("RIGHT") && markerCol2 != 0)
		{
			markerCol2--;
		}
		else if(direction.equals("RESET"))
		{
			markerRow2=0;
			markerCol2=0;
		}
		repaint();
	}
	/**
	 * @return The row of the 1st Player's Marker.
	 */
	public int getMarkerRow1()
	{
		return markerRow1;
	}
	/**
	 * @return The column of the 1st Player's Marker.
	 */
	public int getMarkerCol1()
	{
		return markerCol1;
	}
	/**
	 * @return The row of the 2nd Player's Marker.
	 */
	public int getMarkerRow2()
	{
		return markerRow2;
	}
	/**
	 * @return The column of the 2nd Player's Marker.
	 */
	public int getMarkerCol2()
	{
		return markerCol2;
	}
	/**
	 * @return The y-coordinate of a row
	 */
	public int getRowYCoord(int row)
	{
		return Y[row];
	}
	public static int getRowXCoord1(int row)
	{
		return blueX[row];
	}
	public static int getRowXCoord2(int row)
	{
		return redX[row];
	}

	public void resetOpenGridLocations()
	{
		for(int row = 0;row<13;row++)
		{
			for(int col = 0;col<18;col++)
			{
				occupiedGridLocations[row][col] = false;
			}
		}
	}
}