package attribute;

public abstract class AttributeModifier
{
    public AttributeModifier(String attributeName)
    {
        this.attributeName = attributeName;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    private String attributeName;
}
