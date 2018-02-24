public class Cell
{
	int row;
	int column;
  boolean tomato;
	boolean used;
	double [][] distances;

	public Cell(int givenRow, int givenColumn,
							boolean givenTomato, boolean givenUsed)
	{
		row = givenRow;
		column = givenColumn;
		tomato = givenTomato;
		used = givenUsed;
	}
}
