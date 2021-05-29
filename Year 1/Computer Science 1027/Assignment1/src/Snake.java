/**
 * stores the information about the snake as it moves around the board
 * 
 * 
 * 
 * @author Yeonsil Choi
 * 
 *
 */
public class Snake {
	/**
	 * the number of grid squares occupied by the snake
	 */
	private int snakeLength;
	/**
	 * the grid squares occupied by the snake will be stored in this array
	 */
	private Position[] snakeBody;

	/**
	 * constructor initializes snakeLengh and Position object create Position object
	 * storing the values of row and col Position object is sotred in the first
	 * entry of array snakeBody
	 * 
	 * @param row
	 * @param col
	 */
	public Snake(int row, int col) {
		this.snakeLength = 1;
		this.snakeBody = new Position[5];

		this.snakeBody[0] = new Position(row, col);
	}

	/**
	 * 
	 * @return the value of instance variable snakeLength
	 */
	public int getLength() {
		return this.snakeLength;
	}

	/**
	 * 
	 * @param index
	 * @return the Position object stored in snakeBody[index]
	 */
	public Position getPosition(int index) {

		if (index < 0 || index >= snakeLength) {
			return null;
		} else {
			return this.snakeBody[index];
		}
	}

	/**
	 * decreases the value of snakeLength by 1
	 */
	public void shrink() {
		this.snakeLength = this.snakeLength - 1;
	}

	/**
	 * 
	 * @param pos
	 * @return boolean to check if pos is in array snakeBody
	 */
	public boolean snakePosition(Position pos) {
		/*
		 * for (Position p : this.snakeBody) { if (p.equals(pos)) { return true; }
		 * break; } return false;
		 */

		for (int i = 0; i < this.snakeBody.length; i++) {
			// to avoid NullPointerException
			if (this.snakeBody[i] != null) {
				if (pos.equals(this.snakeBody[i])) {
					return true;
				}
			}
			// break;

		}
		return false;

	}

	/**
	 * 
	 * @param direction (right, left, up, down)
	 * @return the new position of the head of the snake when the snake moves in the
	 *         direction specified by the parameter
	 */
	public Position newHeadPosition(String direction) {

		// get snakeBody[0] row and column
		int n_row = snakeBody[0].getRow();
		int n_col = snakeBody[0].getCol();

		// -> prof's solution
		// Position head = new Position(snakeBody[0].getRow(), snakeBody[0].getCol());

		// for calculating depends on directions
		Position up = new Position(n_row - 1, n_col);
		Position down = new Position(n_row + 1, n_col);
		Position left = new Position(n_row, n_col - 1);
		Position right = new Position(n_row, n_col + 1);
		if (direction == "up") {
			return up;
		} else if (direction == "down") {
			return down;
		} else if (direction == "left") {
			return left;
		} else if (direction == "right") {
			return right;
		}
		return snakeBody[0];

	}

	/**
	 * moves the snake in the specified direction
	 * 
	 * @param direction
	 */
	public void moveSnake(String direction) {

		// get an updated position for snake head
		Position new_p = newHeadPosition(direction);

		// to shift elements in array, create a new Position with same length of
		// snakeBody
		Position[] pos = new Position[snakeBody.length];

		// for loop to shift all elements
		for (int i = snakeBody.length - 2; i >= 0; i--) {
			pos[i + 1] = snakeBody[i];
			// to check how many null in an array for avoiding an error is occurred
			// when the length of snake is 1

			/*
			 * int nullCount = 0;
			 * 
			 * // count null and if there are 3 nulls, change pos[1] to null // to not to
			 * store the position where the 1 length of snake was for (int j = 1; j <
			 * snakeBody.length; j++) { if (pos[j] == null) { nullCount++; } } if (nullCount
			 * == 3) { pos[1] = pos[0]; pos[1] = null; }
			 */

			if (snakeLength == 1) {
				pos[1] = pos[0];
				pos[1] = null;
			}

		}

		// switch all elements
		snakeBody = pos;

		// set updated position for snake head
		snakeBody[0] = new_p;
	}

	/**
	 * increases the length of the snake by 1 and moves the snake's head in the
	 * direction specified
	 * 
	 * @param direction
	 */
	public void grow(String direction) {
		// get a new position depending on directions
		Position new_p = newHeadPosition(direction);

		// if snakeLength and snakeBody.length are the same, increase array size
		if (this.snakeLength == this.snakeBody.length) {
			increaseArraySize();
		}

		// shift all values form index 0 to index snakeLength-1
		for (int i = snakeBody.length - 1; i > 0; i--) {
			// Position[] pos = new Position[5];
			// pos[i] = snakeBody[i - 1];
			snakeBody[i] = snakeBody[i - 1];
			// snakeBody = pos;
		}
		snakeBody[0] = new_p;

		// increase snakeLength
		snakeLength++;

	}

	/**
	 * doubles the size of array snakeBody preserving the information that was
	 * stored in it
	 */
	private void increaseArraySize() {
		// get length of snakeBody
		int len = this.snakeBody.length;
		// create new Position array of doubled size
		Position[] new_p = new Position[len * 2];
		// copies an array from the specified source array
		System.arraycopy(snakeBody, 0, new_p, 0, snakeBody.length);
		// snakeBody is now assigned to new Position array
		this.snakeBody = new_p;

	}

}
