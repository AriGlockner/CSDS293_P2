package shape;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.*;

public class TestPlaneMap
{
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
				" 5.0, 0.7071067811865476]", Stream.of(Math.PI, 10.0/3.0, Math.E, 5.0, Math.cos(Math.PI / 4.0))
				.collect(Collectors.toCollection(ArrayList::new)).toString());

		assertEquals("[foo, bar, , foobar]", Stream.of("foo", "bar", "", "foobar")
				.collect(Collectors.toCollection(ArrayList::new)).toString());

		// From
		Assert.assertEquals("{1=4, 2=5, 3=1, -5=0, 4=2, 5=3} {0=0, 8=3, 1=4, 2=5}", PlaneMap.from(Set.of(
				Rectangle.of(1, 2, 1, 2),
				Rectangle.of(4, 5, 2, 8),
				Rectangle.of(-5, 3, 0, 1))).toString());
	}
}
