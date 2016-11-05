package inventory;

import java.util.function.Predicate;


/**
 * Created by Anna on 05/11/2016.
 *
 * This could come in handy if we have to handle items in bulk (e.g. arrows or other stackable items)
 */
public class ItemPredicate<t> implements Predicate<t>
{
	@Override
	public boolean test(t t)
	{
		return false;
	}


	@Override
	public Predicate<t> and(Predicate<? super t> other)
	{
		return null;
	}


	@Override
	public Predicate<t> negate()
	{
		return null;
	}


	@Override
	public Predicate<t> or(Predicate<? super t> other)
	{
		return null;
	}
}
