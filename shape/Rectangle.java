package shape;

import java.util.Collection;
import java.util.EnumMap;

public final class Rectangle<T extends Comparable<T>> {

	enum Direction {
		left, right, top, bottom
	}

	private final EnumMap<Direction, T> borders;

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

	public static<T extends Comparable<T>> Rectangle<T> of() throws RectangleException
	{

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