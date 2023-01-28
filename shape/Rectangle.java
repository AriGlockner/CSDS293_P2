package shape;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Set;

public final class Rectangle<T extends Comparable<T>>
{
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

	public static <T extends Comparable<T>> Rectangle<T> of(T left, T right, T bottom, T top)
	{
		// Verify that nothing is null
		RectangleException.verifyNonNull(Set.of(left, right, top, bottom));

		// Verify the bounds
		RectangleException.verifyBounds(left, right);
		RectangleException.verifyBounds(bottom, top);

		// Create and return a new Rectangle
		EnumMap<Direction, T> map = new EnumMap<>(Direction.class);
		map.put(Direction.LEFT, left);
		map.put(Direction.RIGHT, right);
		map.put(Direction.BOTTOM, bottom);
		map.put(Direction.TOP, top);
		return new Rectangle<>(map);
	}

	/**
	 * @param rectangle the rectangle to duplicate
	 * @param <T>       Generic type of the position of each side of the Rectangle
	 * @return a copy of its rectangle argument using a copy of the argument's borders
	 */
	public static <T extends Comparable<T>> Rectangle<T> copyOf(Rectangle<T> rectangle)
	{
		return new Rectangle<>(rectangle.getBorders(Set.of(Direction.LEFT, Direction.RIGHT, Direction.TOP, Direction.BOTTOM)));
	}
}