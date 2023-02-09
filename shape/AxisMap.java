package shape;

import java.util.*;
import java.util.stream.IntStream;

/**
 * The purpose of the AxisMap class is to maintain the explicit coordinates in sorted order. The AxisMap has a private
 * Map<S, Integer> (where S is the generic coordinate type) that associates to each coordinate value its index among
 * coordinates. For example, if the only coordinates are 2 and 5, then the index of 2 is 0 and the index of 5 is 1.
 *
 * @author ari
 */
public final class AxisMap<S>
{
	// TODO: Make all Generics extend Comparable

	// The AxisMap that stores the coordinate as the key and the index as the value
	private final Map<S, Integer> index;

	/**
	 * Creates a new AxisMap. An AxisMap can only be instantiated from one of its Factory methods
	 *
	 * @param index the AxisMap
	 */
	private AxisMap(Map<S, Integer> index)
	{
		assert index != null;
		this.index = index;
	}

	/**
	 * @param value value to get the index of
	 * @return the index of the given value, or null if the value does not have an index
	 */
	Integer flatIndexOf(S value)
	{
		return index.getOrDefault(value, null);
	}

	/**
	 * @return the number of points in the index
	 */
	public int size()
	{
		return index.size();
	}

	/**
	 * @param value the value to get the index of
	 * @return the index of the given value, or null if either the value is null or the value doesn't have an index
	 */
	public Optional<Integer> indexOf(S value)
	{
		return Optional.ofNullable(index.get(value));
	}

	/**
	 * @param coordinates list of coordinates to put into the map
	 * @param <T>         generic type of the coordinates
	 * @return a new AxisMap starting from the given coordinates
	 */
	static <T> AxisMap<T> from(Collection<T> coordinates)
	{
		assert (coordinates != null) : "The coordinates are null";

		// Create a map
		Map<T, Integer> map = new HashMap<>(coordinates.size());

		// Convert the collection into an ArrayList
		List<T> cords = new ArrayList<>(coordinates);

		// Map Each coordinate to the index it is associated with
		IntStream.range(0, coordinates.size()).forEach(count -> map.put(cords.get(count), count));

		return new AxisMap<>(map);
	}

	/**
	 * @return the Axis Map as a string
	 */
	@Override
	public String toString()
	{
		return index.toString();
	}
}
