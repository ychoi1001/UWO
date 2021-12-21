/***
 * This class implements all the auxiliary methods needed by the algorithm that
 * plays the board game.
 * 
 * @author Yeonsil Choi (251126837)
 *
 */
public class Evaluate {
	// instance variables
	private char[][] gameBoard;
	private int tiles;
	// private int rows;
	// private int cols;
	private int levels;

	/***
	 * constructor
	 * 
	 * @param boardRows
	 * @param boardColumns
	 * @param tilesNeeded
	 * @param maxLevels
	 */
	public Evaluate(int boardRows, int boardColumns, int tilesNeeded, int maxLevels) {
		this.tiles = tilesNeeded;
		// this.rows = boardRows;
		// this.cols = boardColumns;
		this.levels = maxLevels;
		this.gameBoard = new char[boardRows][boardColumns];

		// initialize gameBoard
		// every entry of gameBoard stores the character 'g'
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = 'g';
			}
		}
	}

	/***
	 * 
	 * @return dic which is an empty Dictionary of the size that I have selected
	 */
	public Dictionary createDictionary() {
		Dictionary dic = new Dictionary(37);
		return dic;
	}

	/***
	 * 
	 * @param dict
	 * @return
	 */
	public Data repeatedConfig(Dictionary dict) {
		String str = "";
		// to represent the content of the 2D array gameBoard as a string
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				str += gameBoard[i][j];
			}
		}

		// avoid exception
		try {
			// check whether this string is in dict
			if (str.equals(dict.get(str).getKey())) {
				return dict.get(str);
			}
		} catch (Exception e) {
			// System.out.print(e);
		}

		// if the string is not in dict, return null
		return null;
	}

	/***
	 * represents the content of the 2D array gameBoard as a string and then it
	 * creates an object of the class Data storing this string, score, and level
	 * this Data object is stored in dict
	 * 
	 * @param dict
	 * @param score
	 * @param level
	 */
	public void insertConfig(Dictionary dict, int score, int level) {
		// to represent the content of the 2D array gameBoard as a string
		String str = "";
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				str += gameBoard[i][j];
			}
		}

		// create data object storing str, score, level
		Data data = new Data(str, score, level);

		// avoid exception
		try {
			dict.put(data);
		} catch (Exception e) {
			// System.out.print(e);
		}

	}

	/***
	 * stores symbol in gameBoard[row][col]
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void storePlay(int row, int col, char symbol) {
		this.gameBoard[row][col] = symbol;

	}

	/***
	 * returns true if gameBoard[row][col] is 'g' otherwise it returns false
	 * 
	 * @param row
	 * @param col
	 * @return boolean value
	 */
	public boolean squareIsEmpty(int row, int col) {
		if (this.gameBoard[row][col] == 'g') {
			return true;
		}
		return false;
	}

	/***
	 * returns true if gameBoard[row][col] is 'o' otherwise it returns false
	 * 
	 * @param row
	 * @param col
	 * @return boolean value
	 */
	public boolean tileOfComputer(int row, int col) {
		if (this.gameBoard[row][col] == 'o') {
			return true;
		}

		return false;

	}

	/***
	 * returns true if gameBoard[row][col] is 'b' otherwise it returns false
	 * 
	 * @param row
	 * @param col
	 * @return boolean value
	 */
	public boolean tileOfHuman(int row, int col) {
		if (this.gameBoard[row][col] == 'b') {
			return true;
		}
		return false;

	}

	/***
	 * returns true if there are the required number of adjacent tiles of type
	 * symbol in the same row, column, or diagonal of gameBoard
	 * 
	 * @param symbol
	 * @return boolean value
	 */
	public boolean wins(char symbol) {

		// 1. Check the row

		//to check if we have the required number of adjacent tiles of type symbol in the same row
		//use nested for loops for traversing every elements in 2D array
		int count = 0; 
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				//check if gameBoard[row][col] == symbol
				if (gameBoard[row][col] == symbol) {
					count = 1;

					//use for loop because I only need the required number of adjacent tiles
					for (int j = 1; j < this.tiles; j++) {

						//to avoid ArrayIndexOutOfBoundsException
						if ((col + j) <= gameBoard[row].length - 1) {

							//I already checked gameBoard[row][col] == symbol
							//Therefore, I need to check if the adjacent tiles in the same row are also symbols
							if (gameBoard[row][col] == gameBoard[row][col + j]) {
								count++; // if it is also symbol, update count

								//check count so we only can check the number of adjacent tiles of type symbol
								//if count reaches the required number of adjacent tiles, return true
								if (count == this.tiles) {
									return true;
								}
							}
						}
					}

				}
			}
		}

		// 2. Check the column
		// use the same logic as shown above
		// to check if we have the required number of adjacent tiles of type symbol in the same column
		// use nested for loops for traversing every elements in 2D array
		count = 0;
		for (int col = 0; col < gameBoard[0].length; col++) {
			for (int row = 0; row < gameBoard.length; row++) {
				//check if gameBoard[row][col] == symbol
				if (gameBoard[row][col] == symbol) {
					count = 1;

					//use for loop because I only need the required number of adjacent tiles
					for (int j = 1; j < this.tiles; j++) {
						//to avoid ArrayIndexOutOfBoundsException
						if ((row + j) <= gameBoard.length - 1) {

							//I already checked gameBoard[row][col] == symbol
							//Therefore, I need to check if the adjacent tiles in the same column are also symbols
							if (gameBoard[row][col] == gameBoard[row + j][col]) {
								count++; // if it is also symbol, update count

								//check count so we only can check the number of adjacent tiles of type symbol
								//if count reaches the required number of adjacent tiles, return true
								if (count == this.tiles) {
								
									return true;
								}
							}
						}
					}
				}

			}
		}

		// 3. Check the right side of diagonals (from left to right)
		// use the same logic as shown above
		// to check if we have the required number of adjacent tiles of type symbol in the same right side of diagonal
		count = 0;
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				//check if gameBoard[row][col] == symbol
				if (gameBoard[row][col] == symbol) {
					count = 1;

					//use for loop because I only need the required number of adjacent tiles
					for (int j = 1; j < this.tiles; j++) {
						
						//to avoid ArrayIndexOutOfBoundsException
						if ((row + j) <= gameBoard.length - 1 && (col + j) <= gameBoard[row].length - 1) {

							//I already checked gameBoard[row][col] == symbol
							//Therefore, I need to check if the adjacent tiles in the same right side of diagonal
							//are also symbols
							if (gameBoard[row][col] == gameBoard[row + j][col + j]) {
								count++; // if it is also symbol, update count

								//check if count reaches the number of adjacent tiles of type symbol
								//if count reaches the required number of adjacent tiles, return true
								if (count == this.tiles) {
									return true;
								}
							}
						}
					}
				}

			}
		}

		//4. Check the left side of diagonals (from right to left)
		// use the same logic as shown above
		// to check if we have the required number of adjacent tiles of type symbol in the same left side of diagonal
		count = 0;
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				//check if gameBoard[row][col] == symbol
				if (gameBoard[row][col] == symbol) {
					count = 1;

					//use for loop because I only need the required number of adjacent tiles
					for (int j = 1; j < this.tiles; j++) {
						
						//to avoid ArrayIndexOutOfBoundsException
						if ((row + j) <= gameBoard.length - 1 && (col - j) >= 0) {

							//I already checked gameBoard[row][col] == symbol
							//Therefore, I need to check if the adjacent tiles in the same left side of diagonal
							//are also symbols
							if (gameBoard[row][col] == gameBoard[row + j][col - j]) {
								count++; // if it is also symbol, update count

								//check if count reaches the number of adjacent tiles of type symbol
								//if count reaches the required number of adjacent tiles, return true
								if (count == this.tiles) {
									return true;
								}
							}
						}
					}
				}

			}
		}

		return false;

	}

	/***
	 * returns true if there are no empty position left in gameBoard
	 * @return boolean value
	 */
	public boolean isDraw() {
		for (int row = 0; row < this.gameBoard.length; row++) {
			for (int col = 0; col < this.gameBoard[row].length; col++) {
				if (this.gameBoard[row][col] == 'g') {
					return false;
				}
			}

		}
		return true;
	}

	/***
	 * 
	 * @return int value
	 */
	public int evalBoard() {
		if (wins('o') == true) {
			return 3; //if the computer has won, return 3
		} else if (wins('b') == true) {
			return 0; //if the human player has won, return 0
		} else if (isDraw() == true) {
			return 2; //if the game is a draw, return 2
		} else {
			return 1; //if the game is still undecided, return 1
		}
	}

}
