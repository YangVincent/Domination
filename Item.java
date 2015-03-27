
import javax.swing.*;
import java.awt.*;
/**
 * An <code>Item</code> is an Object that can be placed on a <code>BattleGrid</code>.
 */
public interface Item
{
	void makeMove();
	void removeSelfFromBattleGrid();
	Image getCurrentImage();
	int getXCoord();
}
