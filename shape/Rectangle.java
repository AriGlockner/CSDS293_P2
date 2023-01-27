package shape;

import java.util.EnumMap;

public final class Rectangle<T extends Comparable<T>> {

	enum Direction {
		left, right, top, bottom
	}

	private final EnumMap<Direction, T> borders;

	public Rectangle(T x1, T y1, T x2, T y2)
	{
		// Initialize borders
		borders = new EnumMap<>(Direction.class);

		// Set borders
		if (x1.compareTo(x2) < 1)
		{
			borders.put(Direction.left, x2);
			borders.put(Direction.right, x1);
		}
		else{
			borders.put(Direction.left, x1);
			borders.put(Direction.right, x2);
		}

	}

	void getBorder(Direction direction)
	{

	}

	EnumMap<Direction, T> getBorders()
	{
		return borders;
	}

	public static void main(String[] args)
	{

	}
}