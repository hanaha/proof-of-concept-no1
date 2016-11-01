package attribute;

public class AttributeFlatValueModifier extends AttributeModifier
{
    public AttributeFlatValueModifier(String attributeName, double fixedFlatDelta)
    {
        super(attributeName);
        this.fixedFlatDelta = fixedFlatDelta;
    }

    public AttributeFlatValueModifier(String attributeName, double minFlatDelta, double maxFlatDelta)
    {
        super(attributeName);
        this.minFlatDelta = minFlatDelta;
        this.maxFlatDelta = maxFlatDelta;
    }
    public double getFixedFlatModificationDelta()
    {
        return this.fixedFlatDelta;
    }

    public double getMinFlatDelta()
    {
        return minFlatDelta;
    }

    public double getMaxFlatDelta()
    {
        return maxFlatDelta;
    }

    public void modify(Attribute attribute)
    {
        if (this.fixedFlatDelta != 0)
        {
            attribute.modifyFixedFlat(fixedFlatDelta);
        }
        else
        {
            attribute.modifyMinFlat(minFlatDelta);
            attribute.modifyMaxFlat(maxFlatDelta);
        }
    }

    private double fixedFlatDelta = 0;
    private double minFlatDelta = 0;
    private double maxFlatDelta = 0;
}
