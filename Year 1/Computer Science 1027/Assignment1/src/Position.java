/**
 * An object of class Position represents the position of a square of the grid
 * 
 * 
 * 
 * @author Yeonsil Choi
 */
public class Position {
	/**
	 * position of objects (snake, apple, scissor, rock)
	 */

	private int positionRow, positionColumn;

	/**
	 * constructor creates a position in row and column
	 * 
	 * @param row
	 * @param col
	 */
	public Position(int row, int col) {
		positionRow = row;
		positionColumn = col;

	}

	/**
	 * 
	 * @return the position of row
	 */

	public int getRow() {
		return positionRow;
	}

	/**
	 * 
	 * @return the position of column
	 */

	public int getCol() {
		return positionColumn;
	}

	/**
	 * 
	 * @param newRow to set new position of row
	 */

	public void setRow(int newRow) {
		positionRow = newRow;
	}

	/**
	 * 
	 * @param newCol to set new position of column
	 */

	public void setCol(int newCol) {
		positionColumn = newCol;
	}

	/**
	 * 
	 * @param otherPosition to compare each positions
	 * @return boolean to check each positions are same or not
	 */

	public boolean equals(Position otherPosition) {
		if (positionRow == otherPosition.positionRow && positionColumn == otherPosition.positionColumn) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * public static void main(String[] args) { Position p1 = new Position(1, 2);
	 * Position p2 = new Position(1, 1);
	 * 
	 * System.out.print(p1.equals(p2));
	 * 
	 * }
	 */

}
