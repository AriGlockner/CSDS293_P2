package shape;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Set;

/**
 * The building block of the polygon module is a public final class Rectangle. A rectangle is defined by its borders
 * and contains a private final EnumMap<Direction, T> borders variable, which maps each border to its coordinates
 *
 * @param <T> Generic type used for each border's coordinates
 *
 * @author ari
 */
public final class Rectangle<T extends Comparable<T>>
{
	// the borders of the Rectangle
	private final EnumMap<Direction, T> borders;

	// Direction Bounds of the Rectangle
	static final Set<Direction> HORIZONTAL_BOUNDS = Set.of(Direction.LEFT, Direction.RIGHT);
	static final Set<Direction> VERTICAL_BOUNDS = Set.of(Direction.BOTTOM, Direction.TOP);
	static final Set<Direction> ALL_BOUNDS = Set.of(Direction.LEFT, Direction.RIGHT, Direction.BOTTOM, Direction.TOP);

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

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Rectangle<?>)
			return getBorders(ALL_BOUNDS).equals(((Rectangle<?>) o).getBorders(ALL_BOUNDS));
		return false;
	}

	public static <T extends Comparable<T>> Rectangle<T> of(T left, T right, T bottom, T top)
	{
		// Verify that nothing is null
		RectangleException.verifyNonNull(Set.of(left, right, bottom, top));

		// Verify the bounds
		RectangleException.verifyBounds(left, right);
		RectangleException.verifyBounds(bottom, top);

		// Create and return a new Rectangle
		EnumMap<Direction, T> map = new EnumMap<>(Direction.class);
		map.put(Direction.LEFT, left);
		map.put(Direction.RIGHT, right);
		map.put(Direction.BOTTOM, bottom);
		map.put(Direction.TOP, top);

		// Return the Rectangle after it is determined that no bounds are neither null nor invalid
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