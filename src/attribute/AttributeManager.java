package attribute;

import java.util.*;


public class AttributeManager
{
	//
	// Add Modifiers
	//


	public void addModifier(AttributeFlatValueModifier modifier)
	{
		String attributeName = modifier.getAttributeName();
		this.newAttributeCheck(attributeName);

		if (!this.flatModifierMap.get(attributeName).contains(modifier))
		{
			// add the modifier
			this.flatModifierMap.get(attributeName).add(modifier);
			this.increaseAttributeCounter(attributeName);

			// propagate attribute changes
			this.propagate(PROPAGATE_FLAT_MODIFIER_CHANGE, attributeName);
		}
	}

	public void addModifier(AttributeAdditiveScalingModifier modifier)
	{
		String attributeName = modifier.getAttributeName();
		this.newAttributeCheck(attributeName);

		if (!this.additiveScalingModifierMap.get(attributeName).contains(modifier))
		{
			// add the modifier
			this.additiveScalingModifierMap.get(attributeName).add(modifier);
			this.increaseAttributeCounter(attributeName);

			// propagate attribute changes
			this.propagate(PROPAGATE_ADDING_ADDITIVE_SCALING, attributeName);
		}
	}

	public void addModifier(AttributeMultiplicativeScalingModifier modifier)
	{
		String attributeName = modifier.getAttributeName();
		this.newAttributeCheck(attributeName);

		if (!this.multiplicativeScalingModifierMap.get(attributeName).contains(modifier))
		{
			// add the modifier
			this.multiplicativeScalingModifierMap.get(attributeName).add(modifier);
			this.increaseAttributeCounter(attributeName);

			// Apply modifier, as multiplicative scaling is lsat step.
			modifier.modify(this.finalAttributes.get(attributeName));

			// propagate attribute changes
			// this.propagate(PROPAGATE_ADDING_MULTIPLICATIVE_SCALING, attributeName);
		}
	}


	//
	// Remove Modifiers
	//


	public void removeModifier(AttributeFlatValueModifier modifier)
	{
		String attributeName = modifier.getAttributeName();

		if (this.flatModifierMap.get(attributeName).contains(modifier))
		{
			this.flatModifierMap.get(attributeName).remove(modifier);
			this.propagate(PROPAGATE_FLAT_MODIFIER_CHANGE, attributeName);
			this.decreaseAttributeCounter(attributeName);
		}
	}


	public void removeModifier(AttributeAdditiveScalingModifier modifier)
	{
		String attributeName = modifier.getAttributeName();

		if (this.additiveScalingModifierMap.get(attributeName).contains(modifier))
		{
			this.additiveScalingModifierMap.get(attributeName).remove(modifier);
			this.propagate(PROPAGATE_FLAT_MODIFIER_CHANGE, attributeName);
			this.decreaseAttributeCounter(attributeName);
		}
	}


	public void removeModifier(AttributeMultiplicativeScalingModifier modifier)
	{
		String attributeName = modifier.getAttributeName();
		// we assume that the modifier has been added in the first place.

		if (this.multiplicativeScalingModifierMap.get(attributeName).contains(modifier))
		{
			this.multiplicativeScalingModifierMap.get(attributeName).remove(modifier);
			this.decreaseAttributeCounter(modifier.getAttributeName());

			Attribute attribute = this.finalAttributes.get(attributeName);

			modifier.reverse(attribute);
		}
		else
		{
			// we don't have anything to remove.
		}
	}

	//
	// Propagating attribute changes
	//

	private void propagate(String stepName, String attributeName)
	{
		switch (stepName)
		{
			case PROPAGATE_FLAT_MODIFIER_CHANGE:
				this.calculateIntermediaryStepOne(attributeName);
			case PROPAGATE_ADDING_ADDITIVE_SCALING:
				this.calculateIntermediaryStepTwo(attributeName);
			case PROPAGATE_ADDING_MULTIPLICATIVE_SCALING:
				this.calculateFinalAttributes(attributeName);
		}
	}

	private void calculateIntermediaryStepOne(String attributeName)
	{
		Attribute attribute = this.attributes.get(attributeName);
		Attribute copy = attribute.copy();

		this.intermediaryAttributesStepOne.put(attributeName, copy);

		for (AttributeFlatValueModifier modifier : this.flatModifierMap.get(attributeName))
		{
			modifier.modify(copy);
		}
	}


	private void calculateIntermediaryStepTwo(String attributeName)
	{
		Attribute attribute = this.intermediaryAttributesStepOne.get(attributeName);
		Attribute copy = attribute.copy();

		this.intermediaryAttributesStepTwo.put(attributeName, copy);

		for (AttributeAdditiveScalingModifier modifier :
				this.additiveScalingModifierMap.get(attributeName))
		{
			modifier.modify(copy);
		}
	}


	private void calculateFinalAttributes(String attributeName)
	{
		Attribute attribute = this.intermediaryAttributesStepTwo.get(attributeName);
		Attribute copy = attribute.copy();

		this.finalAttributes.put(attributeName, copy);

		for (AttributeMultiplicativeScalingModifier modifier :
				this.multiplicativeScalingModifierMap.get(attributeName))
		{
			modifier.modify(copy);
		}
	}

	//
	// Getters
	//


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

	//
	// Other methods
	//


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
		this.intermediaryAttributesStepOne.remove(attributeName);
		this.intermediaryAttributesStepTwo.remove(attributeName);
		this.finalAttributes.remove(attributeName);

		this.flatModifierMap.remove(attributeName);
		this.additiveScalingModifierMap.remove(attributeName);
		this.multiplicativeScalingModifierMap.remove(attributeName);
	}

	private void newAttributeCheck(String attributeName)
	{
		if (!this.attributes.containsKey(attributeName))
		{
			Attribute attribute = new Attribute(attributeName);
			this.attributes.put(attributeName, attribute);
			this.intermediaryAttributesStepOne.put(attributeName, attribute.copy());
			this.intermediaryAttributesStepTwo.put(attributeName, attribute.copy());
			this.finalAttributes.put(attributeName, attribute.copy());
			this.modifierCounter.put(attributeName, 0);

			this.flatModifierMap.put(attributeName, new HashSet<>());
			this.additiveScalingModifierMap.put(attributeName, new HashSet<>());
			this.multiplicativeScalingModifierMap.put(attributeName, new HashSet<>());
		}
	}

	//
	// Fields
	//

	private Map<String, Attribute> attributes = new HashMap<>();
	private Map<String, Attribute> intermediaryAttributesStepOne = new HashMap<>();
	private Map<String, Attribute> intermediaryAttributesStepTwo = new HashMap<>();
	private Map<String, Attribute> finalAttributes = new HashMap<>();

	private Map<String, Integer> modifierCounter = new HashMap<>();

	private Map<String, Set<AttributeFlatValueModifier>> flatModifierMap = new HashMap<>();
	private Map<String, Set<AttributeAdditiveScalingModifier>> additiveScalingModifierMap = new HashMap<>();
	private Map<String, Set<AttributeMultiplicativeScalingModifier>> multiplicativeScalingModifierMap = new HashMap<>();

	private static final String PROPAGATE_FLAT_MODIFIER_CHANGE = "PROPAGATE_FLAT_MODIFIER_CHANGE";
	private static final String PROPAGATE_ADDING_ADDITIVE_SCALING = "PROPAGATE_ADDING_ADDITIVE_SCALING";
	private static final String PROPAGATE_ADDING_MULTIPLICATIVE_SCALING = "PROPAGATE_ADDING_MULTIPLICATIVE_SCALING";
}
