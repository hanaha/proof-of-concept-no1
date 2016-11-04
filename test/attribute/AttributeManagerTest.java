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

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue("Beta min flat value should be 10", attributes.get(ATTRIBUTE_BETA).getMinFlat() == 10);
        assertTrue("Beta max flat value should be 20", attributes.get(ATTRIBUTE_BETA).getMaxFlat() == 20);
    }

    @Test
    public void removeFlatModifier() throws Exception
    {
        AttributeFlatValueModifier modifier =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 5);

        this.attributeManager.addModifier(modifier);

        Map<String, Attribute> attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);

        this.attributeManager.removeModifier(modifier);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("There should be no attributes in the manager", attributes.size() == 0);
    }

    @Test
    public void addAndRemoveAdditiveScalingModifier() throws Exception
    {
        AttributeFlatValueModifier alphaFixedFlat =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 5);
        AttributeFlatValueModifier alphaBracketFlat =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 10, 20);
        AttributeAdditiveScalingModifier alphaAdditiveScaling =
                new AttributeAdditiveScalingModifier(ATTRIBUTE_ALPHA, .5);

        this.attributeManager.addModifier(alphaFixedFlat);
        this.attributeManager.addModifier(alphaBracketFlat);

        Map<String, Attribute> attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue("Alpha min flat value should be 10", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 10);
        assertTrue("Alpha max flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 20);

        this.attributeManager.addModifier(alphaAdditiveScaling);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Scaled alpha fixed flat value should be 7.5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 7.5);
        assertTrue("Scaled alpha min flat value should be 15", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 15);
        assertTrue("Scaled alpha max flat value should be 30", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 30);

        this.attributeManager.removeModifier(alphaAdditiveScaling);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue("Alpha min flat value should be 10", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 10);
        assertTrue("Alpha max flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 20);
    }

    @Test
    public void addAndRemoveMultiplicativeScalingModifier() throws Exception
    {
        AttributeFlatValueModifier alphaFixedFlat =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 5);
        AttributeFlatValueModifier alphaBracketFlat =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 10, 20);
        AttributeMultiplicativeScalingModifier alphaMultiplicativeScaling =
                new AttributeMultiplicativeScalingModifier(ATTRIBUTE_ALPHA, 1.5);

        this.attributeManager.addModifier(alphaFixedFlat);
        this.attributeManager.addModifier(alphaBracketFlat);

        Map<String, Attribute> attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue("Alpha min flat value should be 10", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 10);
        assertTrue("Alpha max flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 20);

        this.attributeManager.removeModifier(alphaMultiplicativeScaling);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue("Alpha min flat value should be 10", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 10);
        assertTrue("Alpha max flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 20);
    }


    @Test
    public void addAllModifiers() throws Exception
    {
        AttributeFlatValueModifier alphaFixedFlat =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 5);
        AttributeFlatValueModifier alphaBracketFlat =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 10, 20);
        AttributeMultiplicativeScalingModifier alphaMultiplicativeScaling =
                new AttributeMultiplicativeScalingModifier(ATTRIBUTE_ALPHA, 1.5);
        AttributeAdditiveScalingModifier alphaAdditiveScaling =
                new AttributeAdditiveScalingModifier(ATTRIBUTE_ALPHA, .6);

        this.attributeManager.addModifier(alphaFixedFlat);
        this.attributeManager.addModifier(alphaBracketFlat);

        Map<String, Attribute> attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue("Alpha min flat value should be 10", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 10);
        assertTrue("Alpha max flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 20);

        this.attributeManager.addModifier(alphaMultiplicativeScaling);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Scaled alpha fixed flat value should be 7.5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 7.5);
        assertTrue("Scaled alpha min flat value should be 15", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 15);
        assertTrue("Scaled alpha max flat value should be 30", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 30);


        this.attributeManager.addModifier(alphaAdditiveScaling);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Scaled alpha fixed flat value should be 12", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 12);
        assertTrue("Scaled alpha min flat value should be 24", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 24);
        assertTrue("Scaled alpha max flat value should be 48", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 48);
    }


    @Test
    public void stackingModifiers() throws Exception
    {
        AttributeFlatValueModifier modifier =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 5);
        AttributeFlatValueModifier modifier2 =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 10, 20);

        AttributeFlatValueModifier modifier3 =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 5);
        AttributeFlatValueModifier modifier4 =
                new AttributeFlatValueModifier(ATTRIBUTE_ALPHA, 10, 20);

        this.attributeManager.addModifier(modifier);
        this.attributeManager.addModifier(modifier2);

        this.attributeManager.addModifier(modifier);
        this.attributeManager.addModifier(modifier2);

        Map<String, Attribute> attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 5", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 5);
        assertTrue("Alpha min flat value should be 10", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 10);
        assertTrue("Alpha max flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 20);

        this.attributeManager.addModifier(modifier3);
        this.attributeManager.addModifier(modifier4);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 10", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 10);
        assertTrue("Alpha min flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 20);
        assertTrue("Alpha max flat value should be 40", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 40);

        AttributeAdditiveScalingModifier alphaAdditiveScaling =
                new AttributeAdditiveScalingModifier(ATTRIBUTE_ALPHA, .5);
        AttributeAdditiveScalingModifier alphaAdditiveScaling2 =
                new AttributeAdditiveScalingModifier(ATTRIBUTE_ALPHA, .5);

        this.attributeManager.addModifier(alphaAdditiveScaling);
        this.attributeManager.addModifier(alphaAdditiveScaling);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 15", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 15);
        assertTrue("Alpha min flat value should be 30", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 30);
        assertTrue("Alpha max flat value should be 60", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 60);

        this.attributeManager.addModifier(alphaAdditiveScaling2);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 20", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 20);
        assertTrue("Alpha min flat value should be 40", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 40);
        assertTrue("Alpha max flat value should be 80", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 80);

        AttributeMultiplicativeScalingModifier alphaMultiplicativeScaling =
                new AttributeMultiplicativeScalingModifier(ATTRIBUTE_ALPHA, 2);
        AttributeMultiplicativeScalingModifier alphaMultiplicativeScaling2 =
                new AttributeMultiplicativeScalingModifier(ATTRIBUTE_ALPHA, 2);


        this.attributeManager.addModifier(alphaMultiplicativeScaling);
        this.attributeManager.addModifier(alphaMultiplicativeScaling);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 40", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 40);
        assertTrue("Alpha min flat value should be 80", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 80);
        assertTrue("Alpha max flat value should be 160", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 160);

        this.attributeManager.addModifier(alphaMultiplicativeScaling2);

        attributes = this.attributeManager.getFinalAttributes();

        assertTrue("Alpha fixed flat value should be 80", attributes.get(ATTRIBUTE_ALPHA).getFixedFlat() == 80);
        assertTrue("Alpha min flat value should be 160", attributes.get(ATTRIBUTE_ALPHA).getMinFlat() == 160);
        assertTrue("Alpha max flat value should be 320", attributes.get(ATTRIBUTE_ALPHA).getMaxFlat() == 320);
    }



    @Before
    public void initializeManager()
    {
        this.attributeManager = new AttributeManager();
    }

    private static final String ATTRIBUTE_ALPHA = "attribute_alpha";
    private static final String ATTRIBUTE_BETA = "attribute_beta";

    private AttributeManager attributeManager;
}