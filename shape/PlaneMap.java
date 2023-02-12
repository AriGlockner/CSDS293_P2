package shape;

import java.util.*;

/**
 * The PlaneMap class maintains a horizontal and a vertical AxisMap.
 *
 * @param <S>
 */
public final class PlaneMap<S extends Comparable<S>>
{
	// Horizontal AxisMap
	private final AxisMap<S> x;
	// Vertical AxisMap
	private final AxisMap<S> y;

	/**
	 * Instantiates a new PlaneMap which consists of 2 AxisMaps x and y
	 *
	 * @param x Horizontal AxisMap
	 * @param y Vertical AxisMap
	 */
	private PlaneMap(AxisMap<S> x, AxisMap<S> y)
	{
		assert x != null;
		assert y != null;
		this.x = x;
		this.y = y;
	}

	/**
	 * @param value the value to get the index of
	 * @return the index of the given value, or null if either the value is null or the value doesn't have an index
	 * for the x-axis
	 */
	public Optional<Integer> xIndexOf(S value)
	{
		return x.indexOf(value);
	}

	/**
	 * @param value the value to get the index of
	 * @return the index of the given value, or null if either the value is null or the value doesn't have an index
	 * for the y-axis
	 */
	public Optional<Integer> yIndexOf(S value)
	{
		return y.indexOf(value);
	}

	/**
	 * @return the number of points in the index for the x-axis
	 */
	public int xSize()
	{
		return x.size();
	}

	/**
	 * @return the number of points in the index for the y-axis
	 */
	public int ySize()
	{
		return y.size();
	}

	/**
	 * @param value      value to get the index of
	 * @param horizontal true for x-axis, false for y-axis
	 * @return an index if it exists and null otherwise
	 */
	Integer indexOf(S value, boolean horizontal)
	{
		return horizontal ? x.flatIndexOf(value) : y.flatIndexOf(value);
	}

	@Override
	public String toString()
	{
		return x + " " + y;
	}

	/**
	 * @param rectangles Set of rectangles to create a PlaneMap of
	 * @param <S>        Generic type of the Rectangles
	 * @return a new PlaneMap whose axes correspond to the border coordinates that appear explicitly in the Rectangles
	 */
	public static <S extends Comparable<S>> PlaneMap<S> from(Set<Rectangle<S>> rectangles)
	{
		Set<S> horizontal = new LinkedHashSet<>();
		Set<S> vertical = new LinkedHashSet<>();

		rectangles.forEach(rect -> {
			horizontal.addAll(rect.getBorders(Rectangle.HORIZONTAL_BOUNDS).values());
			vertical.addAll(rect.getBorders(Rectangle.VERTICAL_BOUNDS).values());
		});



		return of(horizontal, vertical);
	}

	/**
	 * @param x   x-axis map to create
	 * @param y   y-axis map to create
	 * @param <S> generic type of the axis map
	 * @return a new AxisPlane which consists of 2 AxisMaps
	 */
	public static <S extends Comparable<S>> PlaneMap<S> of(Collection<S> x, Collection<S> y)
	{
		return new PlaneMap<>(AxisMap.from(x), AxisMap.from(y));
	}
}
