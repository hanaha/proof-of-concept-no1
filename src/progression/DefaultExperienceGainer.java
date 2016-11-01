package progression;

import java.util.Map;


public class DefaultExperienceGainer extends DefaultLevelGainer implements GainsExperience
{
	DefaultExperienceGainer(LevelProgressionFunctor levelProgressionFunctor)
	{
		this.levelProgression = levelProgressionFunctor.generateLevelProgression();
	}

	@Override
	public int getExperience()
	{
		return getExperience();
	}


	@Override
	public void addExperience(int experience)
	{
		this.setExperience(this.experience + experience);
	}


	@Override
	public void subtractExperience(int experience)
	{
		this.setExperience(this.experience - experience);
	}


	@Override
	public void setExperience(int experience)
	{
		if (experience < 0)
		{
			this.experience = 0;
		}
		else
		{
			this.experience = experience;
		}
	}


	@Override
	public void modifyByCoefficient(double coefficient)
	{
		this.experience = (int) Math.floor((this.experience * coefficient) + 0.5);
	}


	@Override
	public int getExperienceLimit()
	{
		return experienceLimit;
	}


	@Override
	public void setExperienceLimit(int limit)
	{
		this.setExperienceLimit(limit);
	}


	@Override
	public double getExperienceEfficiency()
	{
		return this.experienceEfficiency;
	}


	@Override
	public void setExperienceEfficiency(double efficiency)
	{
		this.experienceEfficiency = efficiency;
	}


	private int experienceLimit = 0;

	private int experience = 0;

	private double experienceEfficiency = 1.0;

	private Map<Integer, Integer> levelProgression;
}
