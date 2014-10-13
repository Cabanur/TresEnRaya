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
	private Game game;
	
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
	public GameBoard(int height, int width, Game game) {
		this.squares = new GamePiece[height][width];
		this.game = game;
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
	public void put(Player player, int row, int column) {
		
		if (squares[row][column] == null) {
			squares[row][column] = new GamePiece(player.getColor());
			checkMove(player, row, column);
		} else {
			throw new IllegalArgumentException("The square " + row + ", " + column 
					+ "has already been played");
		}
	}
	
	/** Check if a move (given by the position of the newly placed GamePiece)
	 * gives a winner.
	 * 
	 * @param refRow Row where the newly placed GamePiece is
	 * @param refCol Column where the newly Placed GamePiece is
	 */
	private void checkMove(Player player, int refRow, int refCol) {
		/* First step: checking if any of the surrounding squares to this move
		 * has another GamePiece of the same color.
		 */
		for (int r = refRow - 1; r <= refRow + 1; r++) {
			for (int c = refCol - 1; c <= refCol + 1; c++) {
				if (r == 0 && c == 0) continue; // Skip the reference Piece.
				if (squares[r][c].getColor() == squares[refRow][refCol].getColor()) {
					/* Second step: check if there's another GamePiece of the
					 * same color in the next square following the same 
					 * direction.
					 */
					int secondPieceRow = refRow + 2 * r;
					int secondPieceCol = refCol + 2 * c;
					if (squares[secondPieceRow][secondPieceCol].getColor() 
							== squares[refRow][refCol].getColor()) {
						// We have a winner!
						game.setWinner(player);
						
					}
				}
				
			}
		}
	}
	

}
