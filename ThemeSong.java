import java.util.*;
/**
 * A <code>ThemeSong</code> represents the ThemeSong of the Domination game.
 */
public class ThemeSong extends Song implements Runnable
{
	Domination domination;
	/**
	 * Constructs a new ThemeSong.
	 * @param domination the Domination frame that plays this ThemeSong.
	 */
	public ThemeSong(Domination domination)
	{
		super("FightingThemeSong.wav");
		this.domination = domination;
	}
	public void run()
	{
		super.play();
		domination.restartMusic();
	}
}