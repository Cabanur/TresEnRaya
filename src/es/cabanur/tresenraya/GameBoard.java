package es.cabanur.tresenraya;
import java.io.Serializable;

/** A GameBoard is a collection of {@link #squares} where you can
 * {@link #put} {@link GamePiece}s.
 * 
 * @author Cabanur
 *
 */
public class GameBoard implements Serializable {
	
	private static final long serialVersionUID = 201505072200L;
	
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
	
	/** 
	 * Creates a new GameBoard of {@code height} rows and {@code width} columns
	 * and fills it with {@link NoPiece}s.
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
		for (int row = 0; row < squares.length; row++) {
			for (int col = 0; col < squares[row].length; col++) {
				squares[row][col] = new NoPiece();
				
			}
		}
	}

	/**
	 * @return the entire Board in it's current state.
	 */
	public GamePiece[][] getSquares() {
		return this.squares;
	}
	
	/**
	 * Returns the GamePiece requested. 
	 * 
	 * @param row Row requested
	 * @param col Column requested
	 * @return The GamePiece in the requested position.
	 * @throws IllegalArgumentException if row or col is out of bound.
	 */
	public GamePiece getSquare(int row, int col) {
	
		if (row < 0 || row >= squares.length) throw new IndexOutOfBoundsException("That column doesn't exist!");
		if (col < 0 || col >= squares[row].length) throw new IndexOutOfBoundsException("That row doesn't exist!");
		
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
		
		if (squares[row][column] instanceof NoPiece) {
			squares[row][column] = new GamePiece(color);
		} else {
			throw new IllegalArgumentException("The square " + row + ", " + column 
					+ " has already been played");
		}
	}
	
	/**
	 * Prints this Gameboard with spaces and nice things, like this: 
	 * 
	 * <pre>
	 * +-------+
	 * | X O X |
	 * | O X O |
	 * | X 0 X |
	 * +-------+
	 * </pre>
	 * Such beautiful. Much wow.
	 * 
	 */
	public String toString() {
		
		String ret = "+-------+\n";
		
		for (GamePiece[] rows : squares) {
			ret += "| ";
			for (GamePiece piece : rows) {
				if (piece == null) ret += " ";
				else ret += piece;
				ret += " ";
			}
			
			ret += "|\n";
		}
		
		ret += "+-------+";
		return ret;
	}

}
	


