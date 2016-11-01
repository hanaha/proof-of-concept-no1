package attribute;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;


public class AttributeManagerTest {
    @Test
    public void addFlatModifier() throws Exception
    {
        AttributeFlatValueModifier modifier =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 5);
        AttributeFlatValueModifier modifier2 =
                new AttributeFlatValueModifier(ATTRIBUTE_BETA, 10, 20);

        this.attributeManager.addModifier(modifier);
        this.attributeManager.addModifier(modifier2);

        Map<String, Attribute> attributes = this.attributeManager.getFinalAttributes();

        assertTrue(attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue(attributes.get(ATTRIBUTE_BETA).getMinFlat() == 10);
        assertTrue(attributes.get(ATTRIBUTE_BETA).getMaxFlat() == 20);
    }

    @Test
    public void removeFlatModifier() throws Exception
    {

    }

    @Test
    public void addAdditiveScalingModifier() throws Exception
    {

    }

    @Test
    public void removeAdditiveScalingModifier1() throws Exception
    {

    }

    @Test
    public void addMultiplicativeScalingModifier() throws Exception
    {

    }

    @Test
    public void removeMultiplicativeScalingModifier2() throws Exception
    {

    }

    @Before
    public void initializeManager()
    {
        this.attributeManager = new AttributeManager();
    }

    private static final String ATTRIBUTE_ALPHA = "attribute_alpha";
    private static final String ATTRIBUTE_BETA = "attribute_beta";
    private static final String ATTRIBUTE_GAMMA = "attribute_gamma";

    private AttributeManager attributeManager;
}