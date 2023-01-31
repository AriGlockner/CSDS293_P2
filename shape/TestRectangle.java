package shape;

import org.junit.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * This class tests the Rectangle class
 *
 * @author ari
 */
public class TestRectangle
{

	// TODO:
	// check Rectangle.of(1, 2, 1, 2);
	@Test
	public void testRectangle() throws RectangleException
	{
		Rectangle<Integer> rectangle = Rectangle.of(1, 2, 1, 2);

		/*
		Rectangle<Integer> r = Rectangle.of(1, 2, 3, 4);
		Rectangle<Integer> copy = Rectangle.copyOf(r);

		Assert.assertEquals(r, copy);
		r = Rectangle.of(1, 2, 4, 5);
		Assert.assertNotEquals(r, copy);

		Assert.assertThrows(new RectangleException(), Rectangle.of(4, 3, 2, 1));
		 */
	}
}
