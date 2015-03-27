import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A <code>BoltMonster</code> is a <code>Monster</code>, but faster,
 * weaker, and cheaper.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class BoltMonster extends Monster implements Item, Movable
{
	//FIELDS
	//CONSTRUCTORS
	/**
	 * Constructs a BoltMonster
	 * @param player the player who owns the BoltMonster
	 * @param opponent the player against the player who owns the BoltMonster
	 * @param animationImages the set of images that represent the BoltMonster
	 * @param row the row in which the BoltMonster resides
	 */
	protected BoltMonster(Player player, Player opponent,Image[] animationImages, Image[] fightingImages,int row)
	{
		super(player,opponent,animationImages,fightingImages, 1.6, 0.75, 0.5, 50, row);
		player.updateStat("numBoltMonstersPurchased");
	}
}