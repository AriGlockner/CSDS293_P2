package shape;

import java.util.Comparator;
import java.util.Set;

public final class RectangleException extends Exception
{
	public enum Error {
		NULL_POINTERS, INVALID_BOUNDS
	}
	// Type of Error
	private final Error error;
	private final Set<Object> indexes;
	private final Object lesserBound;
	private final Object greaterBound;

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
	 * @param error type of error
	 * @param lesserBound smaller bound of Rectangle
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
	public static<S extends Comparator<S>> void verifyBounds(S lesserBound, S greaterBound)
	{
		if (lesserBound.compare(lesserBound, greaterBound) >= 0)
			throw new IllegalArgumentException(new RectangleException(Error.INVALID_BOUNDS, lesserBound, greaterBound));
	}

	/**
	 * The RectangleException has a public static verify NonNull
	 * method whose argument is a vararg Object array and that, if any of
	 * the arguments is null, throws a IllegalArgumentException whose
	 * cause is a descriptive RectangleException.
	 *
	 * @param indexes
	 */
	public static<T> void verifyNonNull(Set<Object> indexes)
	{
		for (Object obj : indexes)
			if (obj == null)
				throw new IllegalArgumentException(new RectangleException(indexes));
	}
}
