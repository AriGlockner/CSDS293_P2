package shape;

import org.junit.*;

/**
 * This class tests the Rectangle class
 *
 * @author ari
 */
public class TestRectangle
{

	@Test
	public void testRectangle()
	{
		// Test Rectangle.of and Rectangle.copyOf
		Rectangle<Integer> rectangle = Rectangle.of(1, 2, 1, 2);
		Rectangle<Integer> copy = Rectangle.copyOf(rectangle);
		Assert.assertEquals(rectangle, copy);

		//

	}

	/**
	 * Tests the verifyNotNull method in the RectangleException class
	 */
	@Test
	public void testVerifyNotNull()
	{
		Exception exception = Assert.assertThrows(NullPointerException.class, () -> Rectangle.of(1, null, 2, 1));
		Assert.assertEquals("Cannot invoke \"Object.hashCode()\" because \"pe\" is null", exception.getMessage());
		exception = Assert.assertThrows(NullPointerException.class, () -> Rectangle.of(null, null, null, null));
		Assert.assertEquals("Cannot invoke \"Object.hashCode()\" because \"pe\" is null", exception.getMessage());
		exception = Assert.assertThrows(NullPointerException.class, () -> Rectangle.of(null, 8, 2, 1));
		Assert.assertEquals("Cannot invoke \"Object.hashCode()\" because \"pe\" is null", exception.getMessage());
	}

	/**
	 * Tests the verifyBounds method in the RectangleException class
	 */
	@Test
	public void testVerifyBounds()
	{
		Rectangle<Integer> rectangle = Rectangle.of(1, 2, 3, 4);

		Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> Rectangle.of(1, 2, 2, 1));
		Assert.assertEquals("shape.RectangleException", exception.getMessage());
		exception = Assert.assertThrows(IllegalArgumentException.class, () -> Rectangle.of(1, 0, 0, 1));
		Assert.assertEquals("shape.RectangleException", exception.getMessage());
		exception = Assert.assertThrows(IllegalArgumentException.class, () -> Rectangle.of(2, 2, 2, 2));
		Assert.assertEquals("shape.RectangleException", exception.getMessage());
	}
}
