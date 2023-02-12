package shape;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Captures the abstraction of a RectangleSet along with its properties
 *
 * @author ari
 */
final class RectangleGroup<T extends Comparable<T>>
{
	// Set of all Rectangles
	private final Set<Rectangle<T>> set;
	// The map containing the Rectangles and their indices
	private final PlaneMap<T> planeMap;
	// The Grid containing the number of Rectangles at a specified IndexPair
	private final NavigableMap<IndexPair, Long> matrixGrid;
	// True if any point on the grid contains more than 1 rectangle
	private final boolean isOverlapping;

	/**
	 * This constructor takes in a Set of Rectangles, stores the set of Rectangles, a PlaneMap from the
	 * Rectangles, a matrixGrid keeping track of the number of Rectangles at each particular point, and a
	 * boolean determining if any of the Rectangles Overlap.
	 *
	 * @param rectangles set of Rectangles
	 */
	private RectangleGroup(Set<Rectangle<T>> rectangles)
	{
		assert rectangles != null : "The set of rectangles is null";
		assert rectangles.size() > 0 : "The set of rectangles is empty";

		set = rectangles;
		planeMap = PlaneMap.from(rectangles);

		matrixGrid = new TreeMap<>();

		// Set all every entry in matrixGrid as <IndexPair, 0L>
		IntStream.range(0, planeMap.xSize()).forEach(x ->
				IntStream.range(0, planeMap.ySize()).forEach(y -> matrixGrid.put(new IndexPair(x, y), 0L)));

		// Get a list of the keys used
		List<T> xSet = planeMap.getHorizontalKeys().stream().sorted()
				.collect(Collectors.toCollection(ArrayList::new));
		List<T> ySet = planeMap.getVerticalKeys().stream().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

		// Update the Matrix Grid
		// For each Rectangle in the set
		rectangles.forEach(rectangle -> {
			// Get Horizontal Indices
			IntStream.range(xSet.indexOf(rectangle.getBorder(Direction.LEFT)),
					1 + xSet.indexOf(rectangle.getBorder(Direction.RIGHT))).forEach(x -> {
				// Get Vertical Indices
				IntStream.range(ySet.indexOf(rectangle.getBorder(Direction.BOTTOM)),
						1 + ySet.indexOf(rectangle.getBorder(Direction.TOP))).forEach(y -> {
					// Increment the IndexPair in the Matrix Grid
					matrixGrid.replace(new IndexPair(x, y), matrixGrid.get(new IndexPair(x, y)) + 1L);
				});
			});
		});

		// Set isOverlapping
		isOverlapping = matrixGrid.values().stream().max(Long::compareTo).orElse(0L) > 1L;
	}

	/**
	 * @return the set of Rectangles
	 */
	public Set<Rectangle<T>> getSet()
	{
		return set;
	}

	/**
	 * @return the planeMap
	 */
	public PlaneMap<T> getPlaneMap()
	{
		return planeMap;
	}

	/**
	 * @return tbe boolean isOverlapping which determines whether multiple rectangles overlap
	 */
	public boolean isOverlapping()
	{
		return isOverlapping;
	}

	/**
	 * @return the Matrix Grid as a String
	 */
	@Override
	public String toString()
	{
		return matrixGrid.toString();
	}

	/**
	 * @param rectangles Set of Rectangles to create a Group of
	 * @param <T>        Generic type of the Rectangle
	 * @return a new RectangleGroup
	 */
	static <T extends Comparable<T>> RectangleGroup<T> from(Set<Rectangle<T>> rectangles)
	{
		return new RectangleGroup<>(rectangles);
	}
}
