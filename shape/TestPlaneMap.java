package shape;

import org.junit.*;

import java.util.*;
import java.util.stream.*;

import static org.junit.Assert.*;

/**
 * This class tests the PlaneMap class
 */
public class TestPlaneMap
{
	/**
	 * This method tests the PlaneMap class
	 */
	@Test
	public void testPlaneMap()
	{
		// Create PlaneMap
		Collection<Integer> integerCollection = Stream.of(0, 1, 2, 3, 4, 5, 6)
				.collect(Collectors.toCollection(ArrayList::new));
		PlaneMap<Integer> planeMap = PlaneMap.of(integerCollection, integerCollection);

		// indexOf, xIndexOf, and yIndexOf methods
		IntStream.range(0, 7).forEach(i -> {
			// Test x/y indexOf
			assertEquals(Optional.of(i), planeMap.xIndexOf(i));
			assertEquals(Optional.of(i), planeMap.yIndexOf(i));
			// Test indexOf
			assertEquals((Integer) i, planeMap.indexOf(i, true));
			assertEquals((Integer) i, planeMap.indexOf(i, false));
		});

		// size
		assertEquals(7, planeMap.xSize());
		assertEquals(7, planeMap.ySize());

		// Of
		assertEquals("[3.141592653589793, 3.3333333333333335, 2.718281828459045," +
				" 5.0, 0.7071067811865476]", Stream.of(Math.PI, 10.0 / 3.0, Math.E, 5.0, Math.cos(Math.PI / 4.0))
				.collect(Collectors.toCollection(ArrayList::new)).toString());

		assertEquals("[foo, bar, , foobar]", Stream.of("foo", "bar", "", "foobar")
				.collect(Collectors.toCollection(ArrayList::new)).toString());

		// From
		// Have to use LinkedHashSet and add elements in to retain the order of the Rectangles which is necessary for toString
		Set<Rectangle<Integer>> sets = new LinkedHashSet<>();
		sets.add(Rectangle.of(1, 2, 1, 2));
		sets.add(Rectangle.of(4, 5, 2, 8));
		sets.add(Rectangle.of(-5, 3, 0, 1));
		Assert.assertEquals("{1=0, 2=1, 3=5, 4=2, -5=4, 5=3} {8=3, 0=4, 1=5, 2=2}", PlaneMap.from(sets).toString());
	}
}