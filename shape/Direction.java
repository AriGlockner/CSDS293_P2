package shape;

public enum Direction
{
	// Directions
	LEFT(true, false),
	RIGHT(true, true),
	BOTTOM(false, false),
	TOP(false, true);

	// If the direction is horizontal (LEFT/RIGHT)
	public final boolean horizontal;
	// If the direction increments the coordinate value (RIGHT/TOP)
	public final boolean increment;

	/**
	 * @param horizontal If the direction LEFT or RIGHT
	 * @param increment  If the direction is RIGHT or TOP
	 */
	Direction(boolean horizontal, boolean increment)
	{
		this.horizontal = horizontal;
		this.increment = increment;
	}

	/**
	 * @param horizontal true if the direction is left or right
	 * @param increment  true if the direction is right or top
	 * @return the direction given horizontal and increment
	 */
	public static Direction getDirection(boolean horizontal, boolean increment)
	{
		if (horizontal)
			if (increment)
				return RIGHT;
			else
				return LEFT;
		if (increment)
			return TOP;
		return BOTTOM;
	}
}
