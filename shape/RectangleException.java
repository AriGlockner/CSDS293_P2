package shape;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * This
 *
 * @author ari
 */
public final class RectangleException extends Exception implements Serializable
{
	public enum Error
	{
		NULL_POINTERS, INVALID_BOUNDS
	}

	// TODO: Comment
	// Type of Error
	private final Error error;
	//
	private final Set<Object> indexes;
	// Bounds
	private final Object lesserBound;
	private final Object greaterBound;

	//
	private static final long SerialVersionUID = 239L;

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


	// TODO: Check what the type should be in the indexes set

	/**
	 * The RectangleException has a public static verify NonNull
	 * method whose argument is a vararg Object array and that, if any of
	 * the arguments is null, throws a IllegalArgumentException whose
	 * cause is a descriptive RectangleException.
	 *
	 * @param indexes
	 */
	// TODO: Adjust parmeter to (Object...args)
	public static void verifyNonNull(Object...indexes)
	{
		for (Object obj : indexes)
		{
			System.out.println(obj);
			if (obj == null)
				throw new IllegalArgumentException(new RectangleException(Collections.singleton(indexes)));
		}
	}

	// TODO: serialVersionUID
}
