package shape;

import org.junit.Test;

import java.util.Random;
import static org.junit.Assert.*;

/**
 * Everything except Increment Method is tested in Grid class so this class tests the increment method
 */
public class TestIndexPair
{
	@Test
	public void testIncrement()
	{
		// Get random Point
		Random rand = new Random();
		int randomX = rand.nextInt(-100, 100);
		int randomY = rand.nextInt(-100, 100);

		// Test increment method
		IndexPair indexPair1 = new IndexPair(randomX, randomY);
		assertEquals(new IndexPair(randomX, randomY + 1), indexPair1.increment(Direction.TOP));
		assertEquals(new IndexPair(randomX, randomY - 1), indexPair1.increment(Direction.BOTTOM));
		assertEquals(new IndexPair(randomX - 1, randomY), indexPair1.increment(Direction.LEFT));
		assertEquals(new IndexPair(randomX + 1, randomY), indexPair1.increment(Direction.RIGHT));
	}
}
