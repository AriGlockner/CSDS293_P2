package shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Grid implements Iterable<IndexPair>
{
	private final List<IndexPair> grid;

	private Grid(Rectangle<Integer> rectangle)
	{
		grid = new ArrayList<>();

		// TODO: Use streams
		for (int i = rectangle.getBorder(Direction.LEFT); i < rectangle.getBorder(Direction.RIGHT); i++)
			for (int j = rectangle.getBorder(Direction.BOTTOM); j < rectangle.getBorder(Direction.TOP); j++)
				grid.add(new IndexPair(i, j));
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
