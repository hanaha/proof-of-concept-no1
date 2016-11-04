package attribute;

public class Attribute
{
    public Attribute(String name)
    {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinFlat()
    {
        return Utils.roundBigDecimal(minFlat * baseScaling, 6);
    }

    public void modifyMinFlat(double delta)
    {
        this.minFlat += delta;
    }

    public double getMaxFlat()
    {
        return Utils.roundBigDecimal(maxFlat * baseScaling, 6);
    }

    public void modifyMaxFlat(double delta)
    {
        this.maxFlat += delta;
    }

    public double getFixedFlat()
    {
        return Utils.roundBigDecimal(fixedFlat * baseScaling, 6);
    }

    public void modifyFixedFlat(double delta)
    {
        this.fixedFlat += delta;
    }

    public double getBaseScaling() {
        return baseScaling;
    }

    public void modifyBaseScalingAdditively(double delta)
    {
        this.baseScaling += delta;
    }

    public void modifyBaseScalingMultiplicativelly(double coefficient)
    {
        this.baseScaling *= coefficient;
    }

    public Attribute copy()
    {
        Attribute copyAttribute = new Attribute(this.name);
        copyAttribute.minFlat = this.minFlat;
        copyAttribute.maxFlat = this.maxFlat;
        copyAttribute.fixedFlat = this.fixedFlat;
        copyAttribute.baseScaling = this.baseScaling;

        return copyAttribute ;
    }

    @Override
    public String toString() {
        return this.getName() + " ; " +
                this.getFixedFlat() + " [" +
                this.getMinFlat() + " - " +
                this.getMaxFlat() + "] " +
                " scaling: " + this.baseScaling;
    }

    private String name;

    private double minFlat = 0;
    private double maxFlat = 0;
    private double fixedFlat = 0;
    private double baseScaling = 1;
}
