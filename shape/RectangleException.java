package shape;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

/**
 * This class are the exceptions for the Rectangle class
 *
 * @author ari
 */
public final class RectangleException extends Exception
{
	public enum Error
	{
		NULL_POINTERS, INVALID_BOUNDS
	}

	// Type of Error
	private final Error error;
	// Indices of the Rectangle that are problematic
	private final Set<Object> indexes;
	// Bounds of the Rectangle
	private final Object lesserBound;
	private final Object greaterBound;

	// Serial ID
	@Serial
	private static final long serialVersionUID = 239L;

	/**
	 * Sets  the  value  of  error  from  its  argument,  and  the  other
	 * private variables to null. This constructor is useful when only
	 * the error code is relevant for diagnostics
	 *
	 * @param error type of error
	 */
	RectangleException(Error error)
	{
		this.error = error;
		indexes = null;
		lesserBound = greaterBound = null;
	}

	/**
	 * Sets the error to NULL_POINTERS, the indexes to the
	 * arguments,  and  the  other  private  variables  to  null.  This
	 * constructor is useful when reporting multiple erroneous null
	 * values.
	 */
	RectangleException(Set<Object> indexes)
	{
		error = Error.NULL_POINTERS;
		this.indexes = indexes;
		lesserBound = greaterBound = null;
	}

	/**
	 * Sets  the  error,  lesserBound,  and  greaterBound  to  the
	 * argument values, and the indexes to null. This constructor is
	 * useful when the bounds are erroneous.
	 *
	 * @param error        type of error
	 * @param lesserBound  smaller bound of Rectangle
	 * @param greaterBound larger bound of Rectangle
	 */
	RectangleException(Error error, Object lesserBound, Object greaterBound)
	{
		this.error = error;
		indexes = null;
		this.lesserBound = lesserBound;
		this.greaterBound = greaterBound;
	}

	/**
	 * The RectangleException has a public static verifyBounds(S
	 * lesserBound, S greaterBound) that checks if the lesserBound is
	 * greater than or equal to the greaterBound, in which case it throws
	 * an IllegalArgumentException whose cause is a descriptive
	 * RectangleException.
	 */
	public static <T extends Comparable<T>> void verifyBounds(T lesserBound, T greaterBound)
	{
		if (lesserBound.compareTo(greaterBound) >= 0)
			throw new IllegalArgumentException(new RectangleException(Error.INVALID_BOUNDS, lesserBound, greaterBound));
	}

	/**
	 * The RectangleException has a public static verify NonNull
	 * method whose argument is a vararg Object array and that, if any of
	 * the arguments is null, throws a IllegalArgumentException whose
	 * cause is a descriptive RectangleException.
	 *
	 * @param indices indices of the Rectangle
	 */
	public static void verifyNonNull(Object... indices)
	{
		Set<Object> exceptions = new HashSet<>();
		Set<Integer> errors = new HashSet<>();

		for (int i = 0; i < indices.length; i++)
			if (indices[i] == null)
			{
				exceptions.add(null);
				errors.add(i);
			}

		if (exceptions.size() > 0)
			throw new IllegalArgumentException("Indices " + errors + " are null", new RectangleException(exceptions));
	}
}
