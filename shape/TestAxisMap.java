package shape;

import org.junit.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
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
		assertEquals("{4=2, 1=1, 2=0}", axisMap2.toString());

		Assert.assertEquals("{the=5, eat=4, not=3, bear=6, duck=1, does=2}",
				AxisMap.from(Stream.of("the", "duck", "does", "not", "eat", "the", "bear")
						.collect(Collectors.toCollection(ArrayList::new))).toString());
	}
}
