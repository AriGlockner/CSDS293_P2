package shape;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Set;

public final class Rectangle<T extends Comparable<T>> {
	// the borders of the Rectangle
	private final EnumMap<Direction, T> borders;

	/**
	 * Instantiates a new Rectangle
	 *
	 * @param borders the borders of the Rectangle
	 */
	private Rectangle(EnumMap<Direction, T> borders)
	{
		this.borders = borders;
	}

	/**
	 * @param direction any direction left, right, top, or bottom to get the boarder of
	 * @return the border corresponding with the given direction
	 */
	T getBorder(Direction direction)
	{
		return borders.get(direction);
	}

	/**
	 * @param directions any of the directions left, right, top, bottom to get the boarder of
	 * @return the border corresponding with the given directions
	 */
	EnumMap<Direction, T> getBorders(Collection<Direction> directions)
	{
		// Create a new EnumMap
		EnumMap<Direction, T> b = new EnumMap<>(Direction.class);

		// Add each Border specified to the EnumMap
		for (Direction d : directions)
			if (!b.containsKey(d))
				b.put(d, getBorder(d));

		// Return the new EnumMap
		return b;
	}

	public static<T extends Comparable<T>> Rectangle<T> of(T left, T right, T bottom, T top) throws RectangleException
	{
		// TODO: Continue
		Set<Object> set = Set.of(left, right, top, bottom);
		if (set.contains(null))
			RectangleException.verifyNonNull(set);
		return null;
	}

	public static<T extends Comparable<T>> Rectangle<T> copyOf(Rectangle<T> rectangle) throws RectangleException
	{
		//Rectangle<T> r = new Rectangle<>(rectangle.getBorders());
		return null; //return r;
	}

	public static void main(String[] args)
	{

	}
}