package shape;

import org.junit.*;

import java.util.*;
import java.util.stream.*;

import static org.junit.Assert.*;

/**
 * This class tests the AxisMap class
 *
 * @author ari
 */
public class TestAxisMap
{
	/**
	 * This method tests the Axis Map class
	 */
	@Test
	public void testAxisMap()
	{
		// Create map
		AxisMap<Integer> axisMap = AxisMap.from(Stream.of(0, 1, 2, 3, 4, 5)
				.collect(Collectors.toCollection(ArrayList::new)));

		// Check Map
		assertEquals("{0=0, 1=1, 2=2, 3=3, 4=4, 5=5}", axisMap.toString());

		// Check IndexOf
		IntStream.range(0, 6).forEach(i -> assertEquals(Optional.of(i), axisMap.indexOf(i)));
		assertEquals(Optional.empty(), axisMap.indexOf(99999));

		// Check flat index of
		IntStream.range(0, 6).forEach(i -> assertEquals((Integer) i, axisMap.flatIndexOf(i)));

		// Check size
		assertEquals(6, axisMap.size());

		// Check from method
		AxisMap<Integer> axisMap2 = AxisMap.from(Stream.of(2, 1, 4)
				.collect(Collectors.toCollection(ArrayList::new)));
		assertEquals("{4=2, 1=0, 2=1}", axisMap2.toString());

		Assert.assertEquals("{the=6, eat=3, bear=0, not=4, does=1, duck=2}",
				AxisMap.from(Stream.of("the", "duck", "does", "not", "eat", "the", "bear")
						.collect(Collectors.toCollection(ArrayList::new))).toString());
	}
}
