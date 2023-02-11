package shape;

import org.junit.Assert;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Captures the abstraction of a RectangleSet along with its properties
 *
 * @author ari
 */
final class RectangleGroup<T extends Comparable<T>>
{
	private final Set<Rectangle<T>> set;
	private final PlaneMap<T> planeMap;
	private NavigableMap<IndexPair, Long> matrixGrid; // final
	private boolean isOverlapping; // final

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

		// Add
		System.out.println(planeMap.xSize() + " " + planeMap.ySize());
		System.out.println(planeMap);
		rectangles.forEach(rectangle -> {
			//System.out.println("H: " + planeMap.indexOf(rectangle.getBorder(Direction.LEFT), true));
			//System.out.println("H: " + planeMap.indexOf(rectangle.getBorder(Direction.RIGHT), true));
			//System.out.println("V: " + rectangle.getBorder(Direction.BOTTOM) + " " + planeMap.indexOf(rectangle.getBorder(Direction.BOTTOM), false));
			System.out.println("V: " + rectangle.getBorder(Direction.TOP) + " " + planeMap.indexOf(rectangle.getBorder(Direction.TOP), false));

			/*
			System.out.println(rectangle);
			int leftIndex = planeMap.indexOf(rectangle.getBorder(Direction.LEFT), true);
			int rightIndex = planeMap.indexOf(rectangle.getBorder(Direction.RIGHT), true);
			int bottomIndex = planeMap.indexOf(rectangle.getBorder(Direction.BOTTOM), false);
			int topIndex = planeMap.indexOf(rectangle.getBorder(Direction.TOP), false);

			System.out.println(leftIndex);
			System.out.println(rightIndex);
			System.out.println(bottomIndex);
			System.out.println(topIndex + "\n");
			 */
		});
		/*
		rectangles.forEach(rectangle -> {
			int leftIndex = planeMap.indexOf(rectangle.getBorder(Direction.LEFT), true);
			int rightIndex = planeMap.indexOf(rectangle.getBorder(Direction.LEFT), true);
			int topIndex = planeMap.indexOf(rectangle.getBorder(Direction.TOP), false);
			int bottomIndex = planeMap.indexOf(rectangle.getBorder(Direction.BOTTOM), false);

			IntStream.range(planeMap.indexOf(rectangle.getBorder(Direction.LEFT), true),
					planeMap.indexOf(rectangle.getBorder(Direction.RIGHT), true)).forEach(x -> {
				IntStream.range(planeMap.indexOf(rectangle.getBorder(Direction.TOP), false),
						planeMap.indexOf(rectangle.getBorder(Direction.BOTTOM), false)).forEach(y -> {
					matrixGrid.replace(new IndexPair(x, y), matrixGrid.get(new IndexPair(x, y)) + 1);
				});
			});
		});
		 */

		rectangles.forEach(System.out::println);

		/*
		The indexes in the corresponding AxisMap.
		From the indexes in the corresponding PlaneMap
		 */


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

	@Override
	public String toString()
	{
		return matrixGrid.toString();
	}

	static<T extends Comparable<T>> RectangleGroup<T> from(Set<Rectangle<T>> rectangles)
	{
		return new RectangleGroup<>(rectangles);
	}

	public static void main(String[] args)
	{

		Set<Rectangle<Integer>> sets = new LinkedHashSet<>();
		sets.add(Rectangle.of(1, 2, 1, 2));
		sets.add(Rectangle.of(4, 5, 2, 8));
		sets.add(Rectangle.of(-5, 3, 0, 1));

		RectangleGroup<Integer> rectangleGroup = RectangleGroup.from(sets);
		PlaneMap<Integer> planeMap = rectangleGroup.getPlaneMap();
		System.out.println(rectangleGroup);
		/*
		System.out.println(planeMap);
		System.out.println(planeMap.xSize());
		System.out.println(planeMap.ySize());
		*/

		/*
		NavigableMap<Integer, Integer> map = new TreeMap<>();

		for (int i = 0; i <= 5; i++)
			map.put(i, 0);
		System.out.println(map);

		map.replace(3, map.get(3) + 1);
		map.replace(3, map.get(3) + 1);

		System.out.println(map);

		final boolean[] repeat = {false};
		map.values().forEach(value -> {
			if (value > 1)
			{
				repeat[0] = true;
			}
		});

		//System.out.println(repeat[0]);

		 */
	}
}
