package attribute;

public class AttributeMultiplicativeScalingModifier extends AttributeModifier
{
    public AttributeMultiplicativeScalingModifier(String attributeName, double coefficient)
    {
        super(attributeName);
        this.coefficient = coefficient;
    }

    public double getCoefficient()
    {
        return this.coefficient;
    }

    public void modify(Attribute attribute)
    {
        attribute.modifyBaseScalingMultiplicativelly(this.coefficient);
    }

    public void reverse(Attribute attribute)
    {
        attribute.modifyBaseScalingMultiplicativelly(1 / this.coefficient);
    }

    private double coefficient;

}
