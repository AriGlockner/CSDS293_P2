package shape;

import org.junit.*;

import java.util.*;
import java.util.stream.*;

import static org.junit.Assert.*;

/**
 * This class tests the Grid class
 *
 * @author ari
 */
public class TestGrid
{
	/**
	 * Tests the Grid class
	 */
	@Test
	public void testGrid()
	{
		// Test from
		Grid grid = Grid.from(Rectangle.of(1, 4, 1, 5));
		assertEquals("(1, 1) (1, 2) (1, 3) (1, 4) (2, 1) (2, 2) (2, 3) (2, 4) (3, 1) (3, 2) (3, 3) (3, 4)", grid.toString());
		assertEquals("(-2, 0) (-1, 0)", Grid.from(Rectangle.of(-2, 0, 0, 1)).toString());

		// Test Iterating through Grid (which is done in the Grid Constructor class)
		List<IndexPair> list = new ArrayList<>();
		IntStream.range(1, 4).forEach(x -> IntStream.range(1, 5).forEach(y -> list.add(new IndexPair(x, y))));
		assertEquals(list, grid.getGridAsList());

		// Test the getGridAsRectangle Class
		assertEquals(Rectangle.of(1, 4, 1, 5), grid.getGridAsRectangle());
	}
}
