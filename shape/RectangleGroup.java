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

	private final List<T> xSet;
	private final List<T> ySet;

	//
	private final boolean isConnected;

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

		// Helper method to create the MatrixGrid
		xSet = planeMap.getHorizontalKeys().stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		ySet = planeMap.getVerticalKeys().stream().sorted().collect(Collectors.toCollection(ArrayList::new));

		// Create the matrix grid
		matrixGrid = createMatrixGrid();

		// Set isOverlapping
		isOverlapping = matrixGrid.values().stream().max(Long::compareTo).orElse(0L) > 1L;

		// Set isConnected
		isConnected = calculateIsConnected();
	}

	/**
	 * @return a new MatrixGrid from the fields set and planeMap
	 */
	private NavigableMap<IndexPair, Long> createMatrixGrid()
	{
		assert set != null : "The Set of Rectangles is null";
		assert planeMap != null : "ThePlaneMap is null";

		NavigableMap<IndexPair, Long> matrixGrid = new TreeMap<>();

		// Set all every entry in matrixGrid as <IndexPair, 0L>
		IntStream.range(0, planeMap.xSize()).forEach(x ->
				IntStream.range(0, planeMap.ySize()).forEach(y -> matrixGrid.put(new IndexPair(x, y), 0L)));

		// Update the Matrix Grid
		// For each Rectangle in the set
		set.forEach(rectangle -> {
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

		return matrixGrid;
	}

	/**
	 * @return a set of all connected IndexPairs
	 */
	private Set<IndexPair> component(IndexPair start, Set<IndexPair> current, NavigableMap<IndexPair, Long> grid)
	{
		// Add the start point
		current.add(start);

		// For each direction
		Rectangle.ALL_BOUNDS.forEach(direction -> addComponentNeighbors(start.increment(direction), current, grid));

		// Return the set of all connected IndexPairs
		return current;
	}

	/**
	 * Helper method for component class that adds the neighbors of the component start in the component method
	 *
	 * @param next    IndexPair to add if applicable
	 * @param current set of current neighbors
	 * @param grid    the grid of IndexPairs
	 */
	private void addComponentNeighbors(IndexPair next, Set<IndexPair> current, NavigableMap<IndexPair, Long> grid)
	{
		// If the IndexPair next can be added to the set
		if (grid.getOrDefault(next, 0L) > 0 && !current.contains(next))
			// Add next to the set
			component(next, current, grid);
	}

	/**
	 * Helper method that calculates if the rectangles are connected
	 *
	 * @return if the rectangles are connected or not
	 */
	private boolean calculateIsConnected()
	{
		assert set.size() > 0 : "The set of Rectangles is empty.";

		// Get the 1st rectangle in the set
		Rectangle<T> first = set.stream().findFirst().orElseThrow(NullPointerException::new);

		// Get the values of the 1st rectangle
		T horizontal = first.getBorder(Direction.LEFT);
		T vertical = first.getBorder(Direction.BOTTOM);

		// Get the IndexPair associated with the Rectangle
		IndexPair startingIndexPair = new IndexPair(planeMap.indexOf(horizontal, true),
				planeMap.indexOf(vertical, false));

		// Return if the number of connected points on the grid is equal to the total number of points on the grid
		return matrixGrid.size() == component(startingIndexPair, new LinkedHashSet<>(), matrixGrid).size();
	}

	/**
	 * @return the set of Rectangles
	 */
	public Set<Rectangle<T>> getSet()
	{
		return Collections.unmodifiableSet(set);
	}

	/**
	 * @return the planeMap
	 */
	public PlaneMap<T> getPlaneMap()
	{
		return planeMap;
	}

	/**
	 * @return matrix grid
	 */
	public NavigableMap<IndexPair, Long> getMatrixGrid()
	{
		return Collections.unmodifiableNavigableMap(matrixGrid);
	}

	/**
	 * @return tbe boolean isOverlapping which determines whether multiple rectangles overlap
	 */
	public boolean isOverlapping()
	{
		return isOverlapping;
	}

	/**
	 * @return true if 2 or more Rectangles are connected and false otherwise
	 */
	public boolean isConnected()
	{
		return isConnected;
	}

	/**
	 * @return the Matrix Grid as a String
	 */
	@Override
	public String toString()
	{
		return getMatrixGrid().toString();
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
