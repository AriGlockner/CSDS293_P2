package shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public final class Grid implements Iterable<IndexPair>
{
	private final List<IndexPair> grid;
	private final Rectangle<Integer> rectangle;

	private Grid(Rectangle<Integer> rectangle)
	{
		assert rectangle != null : "Rectangle is null";

		this.rectangle = rectangle;

		// TODO: Remove???
		// Create a new Grid
		grid = new ArrayList<>();

		// For each possible x value, add each possible y value to the grid
		IntStream.range(rectangle.getBorder(Direction.LEFT), rectangle.getBorder(Direction.RIGHT)).forEach(x -> {
			// Add each possible index pair for each x to the grid
			grid.addAll(IntStream.range(rectangle.getBorder(Direction.BOTTOM),
					// Get a list of each possible index pair for each x
					rectangle.getBorder(Direction.TOP)).mapToObj(y -> new IndexPair(x, y)).toList());
		});
	}

	/**
	 * @param rectangle creates a grid containing all possible Index Pairs from within the rectangle specified here
	 * @return a new Grid from its specified rectangle
	 */
	public static Grid from(Rectangle<Integer> rectangle)
	{
		return new Grid(rectangle);
	}

	/**
	 * @return a new Grid Iterator
	 */
	@Override
	public Iterator<IndexPair> iterator()
	{
		return new GridIterator(this);
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (IndexPair pair : this)
			stringBuilder.append(pair).append(" ");
		return stringBuilder.substring(0, Math.max(0, stringBuilder.length() - 1));
	}

	public static class GridIterator implements Iterator<IndexPair>
	{
		// Stores the current grid
		private final List<IndexPair> grid;
		// Stores the current index in the grid
		int index;

		private GridIterator(Grid grid)
		{
			this.grid = grid.grid;
		}

		/**
		 * @return true if there is an IndexPair after this one and false otherwise
		 */
		@Override
		public boolean hasNext()
		{
			return grid.size() > index;
		}

		/**
		 * @return the current element in the grid and increments the index of the Grid
		 */
		@Override
		public IndexPair next()
		{
			return grid.get(index++);
		}
	}

	public static void main(String[] args)
	{
		Grid grid = Grid.from(Rectangle.of(1, 4, 1, 5));
		System.out.println(grid);
	}
}
