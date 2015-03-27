/**
 * A <code>Song</code> represents a real life Song.
 * @author Ayush Mehra
 * @author Vincent Yang
 */
public class Song implements Runnable
{
	//FIELDS
	private EasySound sound;

	//CONSTRUCTORS
	/**
	 * Constructs a new Song
	 * @param fileName the file name of the song that this Song represents and plays.
	 */
	public Song(String fileName)
	{
		sound = new EasySound(fileName);
	}

	//METHODS
	public void play()
	{
		sound.play();
	}
	public void run()
	{
		this.play();
	}
}