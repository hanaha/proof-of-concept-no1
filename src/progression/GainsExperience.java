package progression;

public interface GainsExperience extends GainsLevels
{
	int level = 0;

	public int getExperience();
	public void addExperience(int experience);
	public void subtractExperience(int experience);
	public void setExperience(int experience);
	public void modifyByCoefficient(double coefficient);
	public int getExperienceLimit();
	public void setExperienceLimit(int limit);
	public double getExperienceEfficiency();
	public void setExperienceEfficiency(double efficiency);
}
