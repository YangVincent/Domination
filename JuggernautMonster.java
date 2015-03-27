import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;

/**
 * A <code>JuggernautMonster</code> is a special type of Monster that is super strong and expensive, but slow.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class JuggernautMonster extends Monster implements Movable, Item
{
	//FIELDS

	//CONSTRUCTORS
	/**
	 * Constructs a new JuggernautMonster.
	 * @param player the player who owns this JuggernautMonster.
	 * @param opponent the player against the player who owns this JuggernautMonster.
	 * @param animationImages the set of images that represent this JuggernautMonster in the normal state.
	 * @param fightingImages the set of images that represent this JuggernautMonster when it is fighting.
	 * @param row the row in which this JuggernautMonster resides.
	 */
	public JuggernautMonster(Player player, Player opponent,Image[] animationImages, Image[] fightingImages, int row)
	{
		super(player, opponent,animationImages, fightingImages,0.6, 2.0, 1.5, 100,row);
		player.updateStat("numJuggernautMonstersPurchased");
	}

	//METHODS
}