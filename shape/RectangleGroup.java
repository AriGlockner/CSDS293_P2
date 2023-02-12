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
		System.out.println(planeMap);

		matrixGrid = new TreeMap<>();

		// Set all every entry in matrixGrid as <IndexPair, 0L>
		IntStream.range(0, planeMap.xSize()).forEach(x ->
				IntStream.range(0, planeMap.ySize()).forEach(y -> matrixGrid.put(new IndexPair(x, y), 0L)));




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
		sets.add(Rectangle.of(1, 2, 1, 2));

		RectangleGroup<Integer> rectangleGroup = RectangleGroup.from(sets);
		Assert.assertEquals("{1=0, 2=1} {1=0, 2=1}", rectangleGroup.planeMap.toString());
	}
}
