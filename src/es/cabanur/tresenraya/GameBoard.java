package es.cabanur.tresenraya;
import java.io.Serializable;


public class GameBoard implements Serializable {
	
	private static final long serialVersionUID = 201410030500L;
	
	/**
	 * The actual gameboard is a 2D Matrix of "squares" where you can {@link #put()}
	 * GamePieces. A "null" square is one which has not yet been played.
	 * 
	 * The Gameboard should be represented in the following way: 
	 * (0,0) (0,1) ... (0,n)
	 * (1,0) (1,1) ... (1,n)
	 * ...
	 * (n,0) (n,1) ... (n,n)
	 */
	private GamePiece[][] squares;
//	private Game game;
	
	/** 
	 * Creates a new GameBoard of {@code height} rows and {@code width} columns.
	 * 
	 * Since these numbers will be directly
	 * used to create an array, if any of the parameters are negative an
	 * unchecked Exception will be thrown.
	 * 
	 * @param height Rows of the Board.
	 * @param width Columns of the Board.
	 */
	public GameBoard(int height, int width) {
		this.squares = new GamePiece[height][width];
//		this.game = game;
	}

	public GamePiece[][] getSquares() {
		return this.squares;
	}
	
	public GamePiece getSquare(int row, int col) {
		if (row >= squares.length) row = squares.length - 1;
		if (row <= 0) row = 0;
		if (col >= squares[row].length) col = squares[row].length - 1;
		if (col <= 0) col = 0;
		return squares[row][col];
	}
	
	/** 
	 * Puts a GamePiece or game token in this GameBoard at the marked position.
	 * 
	 * @param player The Player who puts the Piece
	 * @param row The row where to put it.
	 * @param column The column where to put it.
	 * 
	 * @throws IllegalArgumentException if one tries to put a GamePiece where
	 * another one has already been placed.
	 */
	public void put(GamePieceColor color, int row, int column) {
		
		if (squares[row][column] == null) {
			squares[row][column] = new GamePiece(color);
//			checkMove(player, row, column);
		} else {
			throw new IllegalArgumentException("The square " + row + ", " + column 
					+ "has already been played");
		}
	}
	

}
	


