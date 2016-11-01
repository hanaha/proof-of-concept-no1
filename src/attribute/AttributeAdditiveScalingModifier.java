package attribute;

public class AttributeAdditiveScalingModifier extends AttributeModifier
{
    public AttributeAdditiveScalingModifier(String attributeName, double delta)
    {
        super(attributeName);
        this.delta = delta;
    }

    public double getDelta()
    {
        return this.delta;
    }

    public void modify(Attribute attribute)
    {
        attribute.modifyBaseScalingAdditively(this.delta);
    }

    private double delta;
}
