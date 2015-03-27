import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * A <code>Main</code> starts a Domination game.
 */
public class Main
{
	public static void main(String[] args)
	{
		Player playerOne = new Player("playerOne");
		Player playerTwo = new Player("playerTwo");
		BattleGrid battleGrid = new BattleGrid();
		Domination domination = new Domination(playerOne,playerTwo,battleGrid);

		domination.setResizable(false);
		domination.setUndecorated(true);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		gd.setFullScreenWindow(domination);

		domination.setVisible(true);


	}
}
