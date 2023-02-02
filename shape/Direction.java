package shape;

/**
 * This enum represents the keys for the EnumMaps that maps to the coordinates for the Rectangle.
 *
 * @author ari
 */
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
}
