package progression;

/**
 * Default level gainer is an entity that can gain and lose levels, but cannot
 * have a negative level.
 */
public class DefaultLevelGainer implements GainsLevels
{
	@Override
	public int getLevel()
	{
		return this.level;
	}


	@Override
	public void gainLevel()
	{
		this.level++;
	}


	@Override
	public void loseLevel()
	{
		this.setLevel(this.getLevel() - 1);
	}


	/**
	 * Level cannot be negative, it is set to zero, if new value is given negative.
	 * @param level
	 */
	@Override
	public void setLevel(int level)
	{
		if (level < 0)
		{
			this.level = 0;
		}
		else
		{
			this.level = level;
		}
	}

	private int level = 0;
}
