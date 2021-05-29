import java.util.ArrayList;
import java.util.List;

/**
 * represents the board game where the snake moves around eating apples
 * 
 * 
 * 
 * @author Yeonsil Choi
 *
 */
public class BoardGame {

	private int board_length;
	private int board_width;
	private Snake theSnake;
	private String[][] matrix;
	/**
	 * Array list for saving information from the text file
	 */
	private List<String> list = new ArrayList<String>();

	/**
	 * the constructor for the file
	 * 
	 * @param boardFile is a file name which contains information to run game
	 */
	public BoardGame(String boardFile) {
		String line;

		// to check how many lines there is in text file
		// for creating groups of matrixes
		int countNum = 0;

		MyFileReader filereader = new MyFileReader(boardFile);

		// skipping first 2 lines
		for (int i = 1; i <= 2; i++) {
			filereader.readInt();
		}

		// read and store rest of lines into arraylist
		while (filereader.endOfFile() == false) {
			line = filereader.readString();
			// String valArray[] = new String[countNum + 1];
			// valArray[countNum] = line;
			this.list.add(line);
			countNum++;
		}

		// assigned first two elements in arraylist to board_length and board_width
		this.board_length = Integer.parseInt(this.list.get(0));
		this.board_width = Integer.parseInt(this.list.get(1));

		// create a 2D array of type
		this.matrix = new String[board_width][board_length];

		// all entries of the array are initialized to contain "empty"
		for (int i = 0; i < board_width; i++) {
			for (int j = 0; j < board_length; j++) {
				this.matrix[i][j] = "empty";
			}
		}

		// create new object of the class Snake using second and third elements in
		// arraylist
		theSnake = new Snake(Integer.parseInt(this.list.get(2)), Integer.parseInt(this.list.get(3)));

		// create groups of matrixes
		for (int i = 4; i < countNum; i++) {
			this.matrix[Integer.parseInt(this.list.get(i))][Integer.parseInt(this.list.get(i + 1))] = this.list
					.get(i + 2);
			i = i + 2;
		}

	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return the string stored in matrix[row][col]
	 */
	public String getObject(int row, int col) {
		return this.matrix[row][col];
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param newObject store new object in matrix[row][col]
	 */
	public void setObject(int row, int col, String newObject) {
		this.matrix[row][col] = newObject;

	}

	/**
	 * 
	 * @return theSnake
	 */
	public Snake getSnake() {
		return this.theSnake;
	}

	/**
	 * stores the value of newSnake in instance variable theSnake
	 * 
	 * @param newSnake
	 */
	public void setSnake(Snake newSnake) {
		this.theSnake = newSnake;
	}

	/**
	 * 
	 * @return board_length
	 */
	public int getLength() {
		return this.board_length;
	}

	/**
	 * 
	 * @return board_width
	 */
	public int getWidth() {
		return this.board_width;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return matrix[row][col]
	 */
	public String getType(int row, int col) {
		return this.matrix[row][col];
	}

	/*
	 * public static void main(String[] args) { BoardGame board_game = new
	 * BoardGame("testboard.txt"); // System.out.print(board_game.getObject(1, 1));
	 * // board_game.setObject(1, 2, "apple"); // board_game.getSnake(); //
	 * System.out.print(board_game.getSnake());
	 * System.out.print(board_game.getType(1, 1));
	 * 
	 * }
	 */

}