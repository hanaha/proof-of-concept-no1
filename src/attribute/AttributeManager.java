package attribute;

import java.util.*;

public class AttributeManager
{
    public void addModifier(AttributeFlatValueModifier modifier)
    {
        if (this.flatValueModifiers.contains(modifier))
        {
            // we do nothing. We don't want same instance stacking over itself.
        }
        else
        {
            this.newAttributeCheck(modifier.getAttributeName());
            this.flatValueModifiers.add(modifier);
            this.increaseAttributeCounter(modifier.getAttributeName());
            this.calculateIntermediaryStepOne();
        }
    }

    private void increaseAttributeCounter(String attributeName)
    {
        int value = this.modifierCounter.get(attributeName);
        this.modifierCounter.put(attributeName, value + 1);
    }

    private void decreaseAttributeCounter(String attributeName)
    {
        int value = this.modifierCounter.get(attributeName);
        this.modifierCounter.put(attributeName, value - 1);

        if (value - 1 == 0)
        {
            this.removeAttribute(attributeName);
        }
    }

    private void removeAttribute(String attributeName)
    {
        this.attributes.remove(attributeName);
    }

    private void newAttributeCheck(String attributeName)
    {
        if (!this.attributes.containsKey(attributeName))
        {
            Attribute attribute = new Attribute(attributeName);
            this.attributes.put(attributeName, attribute);
            this.modifierCounter.put(attributeName, 0);
        }
    }

    private void calculateIntermediaryStepOne()
    {
        this.intermediaryAttributesStepOne = new HashMap<>();

        for (Attribute attribute : attributes.values())
        {
            this.intermediaryAttributesStepOne.put(attribute, attribute.copy());
        }

        for (AttributeFlatValueModifier modifier : this.flatValueModifiers)
        {
            modifier.modify(this.intermediaryAttributesStepOne.get(this.attributes.get(modifier.getAttributeName())));
        }

        this.calculateIntermediaryStepTwo();
    }

    private void calculateIntermediaryStepTwo()
    {
        this.intermediaryAttributesStepTwo = new HashMap<>();

        for (Attribute attribute : intermediaryAttributesStepOne.values())
        {
            this.intermediaryAttributesStepTwo.put(attribute, attribute.copy());
        }

        for (AttributeAdditiveScalingModifier modifier : this.additiveScalingModifiers)
        {
            modifier.modify(this.intermediaryAttributesStepTwo.get(
                    this.intermediaryAttributesStepOne.get(modifier.getAttributeName())));
        }

        this.calculateFinalAttributes();
    }

    private void calculateFinalAttributes()
    {
        this.finalAttributes = new HashMap<>();

        for (Attribute attribute : intermediaryAttributesStepTwo.values())
        {
            this.finalAttributes.put(attribute, attribute.copy());
        }

        for (AttributeMultiplicativeScalingModifier modifier : this.multiplicativeScalingModifiers)
        {
            modifier.modify(this.finalAttributes.get(
                    this.intermediaryAttributesStepTwo.get(modifier.getAttributeName())));
        }
    }

    public void removeModifier(AttributeFlatValueModifier modifier)
    {
        if (this.flatValueModifiers.contains(modifier))
        {
            this.flatValueModifiers.remove(modifier);
            this.decreaseAttributeCounter(modifier.getAttributeName());
            this.calculateIntermediaryStepOne();
        }
        else
        {
            // we don't have anything to remove.
        }
    }

    public void addModifier(AttributeAdditiveScalingModifier modifier)
    {
        this.additiveScalingModifiers.add(modifier);

        if (this.additiveScalingModifiers.contains(modifier))
        {
            // we do nothing. We don't want same instance stacking over itself.
        }
        else
        {
            this.newAttributeCheck(modifier.getAttributeName());
            this.additiveScalingModifiers.add(modifier);
            this.increaseAttributeCounter(modifier.getAttributeName());
            this.calculateIntermediaryStepTwo();
        }
    }

    public void removeModifier(AttributeAdditiveScalingModifier modifier)
    {
        if (this.additiveScalingModifiers.contains(modifier))
        {
            this.additiveScalingModifiers.remove(modifier);
            this.decreaseAttributeCounter(modifier.getAttributeName());
            this.calculateIntermediaryStepTwo();
        }
        else
        {
            // we don't have anything to remove.
        }
    }

    public void addModifier(AttributeMultiplicativeScalingModifier modifier)
    {
        if (this.multiplicativeScalingModifiers.contains(modifier))
        {
            // we do nothing. We don't want same instance stacking over itself.
        }
        else
        {
            this.newAttributeCheck(modifier.getAttributeName());
            this.multiplicativeScalingModifiers.add(modifier);;
            this.increaseAttributeCounter(modifier.getAttributeName());

            Attribute baseAttribute = this.attributes.get(modifier.getAttributeName());
            Attribute copy = null;

            if (this.intermediaryAttributesStepOne.containsKey(baseAttribute))
            {
                copy = this.finalAttributes.get(
                        intermediaryAttributesStepTwo.get(
                                this.intermediaryAttributesStepOne.get(baseAttribute)));
            }
            else
            {
                copy = baseAttribute.copy();
                this.finalAttributes.put(baseAttribute, copy);
            }

            modifier.modify(copy);
        }
    }

    public void removeModifier(AttributeMultiplicativeScalingModifier modifier)
    {
        if (this.multiplicativeScalingModifiers.contains(modifier))
        {
            this.multiplicativeScalingModifiers.remove(modifier);
            this.decreaseAttributeCounter(modifier.getAttributeName());

            Attribute baseAttribute = this.attributes.get(modifier.getAttributeName());
            Attribute copy = null;

            if (this.intermediaryAttributesStepOne.containsKey(baseAttribute))
            {
                copy = this.finalAttributes.get(
                        intermediaryAttributesStepTwo.get(
                                this.intermediaryAttributesStepOne.get(baseAttribute)));
            }
            else
            {
                copy = this.finalAttributes.get(baseAttribute);
            }

            modifier.reverse(copy);
        }
        else
        {
            // we don't have anything to remove.
        }
    }

    public Set<Attribute> getAttributes()
    {
        return (Set<Attribute>) attributes.values();
    }

    public Map<String, Attribute> getFinalAttributes()
    {
        Map<String, Attribute> returnMap = new HashMap<>(this.finalAttributes.size());

        for (Attribute attribute : this.finalAttributes.values())
        {
            returnMap.put(attribute.getName(), attribute);
        }

        return returnMap;
    }

    private Map<String, Attribute> attributes = new HashMap<>();
    private Map<Attribute, Attribute> intermediaryAttributesStepOne = new HashMap<>();
    private Map<Attribute, Attribute> intermediaryAttributesStepTwo = new HashMap<>();
    private Map<Attribute, Attribute> finalAttributes = new HashMap<>();

    private Map<String, Integer> modifierCounter = new HashMap<>();

    private Set<AttributeFlatValueModifier> flatValueModifiers = new HashSet<>();
    private Set<AttributeAdditiveScalingModifier> additiveScalingModifiers = new HashSet<>();
    private Set<AttributeMultiplicativeScalingModifier> multiplicativeScalingModifiers = new HashSet<>();
}
