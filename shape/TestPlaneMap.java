package shape;

import org.junit.*;
import java.util.*;

public class TestPlaneMap
{
	@Test
	public void testPlaneMap()
	{
		PlaneMap<Integer> planeMap = PlaneMap.of(Set.of(0, 2, 3), Set.of(0, 1, 2, 3));

		System.out.println(planeMap);
		Assert.assertEquals("{0=0, 1=2, 2=3} {0=0, 1=1, 2=2, 3=3}", planeMap.toString());

	}
}
