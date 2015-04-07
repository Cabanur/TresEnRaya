package es.cabanur.tresenraya;
import java.io.Serializable;


public class Game implements Serializable {

	private static final long serialVersionUID = 201410030500L;
	private Player[] players;
	private Player winner;
	private GameBoard board;
	
	public Game() {
		players = new Player[2];
		board = new GameBoard(3,3);
		
	}
	
	public void addPlayer(Player newPlayer) {
		boolean added = false;
//		for (Player p : players) {
//			if (p == null && added == false) {
//				p = newPlayer;
//				p.setGame(this);
//				added = true;
//			}
//		}
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null && added == false) {
				players[i] = newPlayer;
				players[i].setGame(this);
				added = true;
			}
		}
		if ( added == false ) {
			throw new IndexOutOfBoundsException("Couldn't add the player " 
					+ newPlayer.getName() + ", this game is full");
		}
	}
	
	public Player getPlayer(int index) {
		return players[index];
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public boolean hasWinner() {
		if (winner == null) return false;
		else return true;
	
	}

	private void setWinner(Player player) {
		this.winner = player;
		
	}
	
	public Player getWinner() {
		return this.winner;
	}
	
	public void setBoard(GameBoard board) {
		this.board = board;
	}
	
	public GameBoard getBoard() {
		return this.board;
	}
	
	void move(Player player, int refRow, int refCol) {
		// ref stands for reference, since it's the reference piece against which we'll be checking others.
		
		board.put(player.getColor(), refRow, refCol);

		/* First step: checking if any of the surrounding squares to this move
		 * has another GamePiece of the same color.
		 */
		for (int rowDiff = - 1; rowDiff <= 1; rowDiff++) {
			int secondRow = refRow + rowDiff;
			// when scanning for a row out of range, ignore.
			if ((secondRow < 0) || (secondRow >= board.getSquares().length)) continue;
			
			for (int colDiff = -1; colDiff <= 1; colDiff++) {
				int secondCol = refCol + colDiff;
				// when scanning for a column out of range, ignore.
				if (secondCol < 0 || secondCol >= board.getSquares()[secondRow].length) continue;
				
				// Skip the reference Piece.
				if (rowDiff == 0 && colDiff == 0) continue; 
				
				/* Getting here means board.getSquare(secondRow, secondCol) gives back a valid 
				 * square which may contain nothing (null) or a valid piece. When this valid
				 * piece is of the same color as the reference, it's now a matching piece.
				 */
				
				GamePieceColor playerColor = player.getColor();
				GamePiece checkedPiece;
				GamePieceColor checkedPieceColor;
				try {
					checkedPiece = board.getSquare(secondRow, secondCol);
					checkedPieceColor = checkedPiece.getColor();
				} catch (IllegalArgumentException e) {
					System.err.println("Exception when trying to get the second piece".toString() + e);
					continue;
				}
				if (checkedPieceColor == playerColor) {
					/* Once a matching piece is found, we need to check the next square in the 
					 * same direction and the previous square in the opposite direction.
					 */
					int thirdPieceRow = secondRow + rowDiff; // One step ahead in the same direction as second.
					int thirdPieceCol = secondCol + colDiff;

					int otherThirdPieceRow = secondRow - 2 * rowDiff; // Two steps in the opposite direction as second
					int otherThirdPieceCol = secondCol - 2 * colDiff;
					
					GamePiece thirdPiece;
					GamePiece otherThirdPiece;
					
					try {
						thirdPiece = board.getSquare(thirdPieceRow, thirdPieceCol);
						otherThirdPiece = board.getSquare(otherThirdPieceRow, otherThirdPieceCol);
					} catch (Exception e) {
						System.err.println("Exception when trying to get possible third pieces.\n" + e);
						continue;
					}
					
					if ( (thirdPiece.getColor() == playerColor)
							|| (otherThirdPiece.getColor() == playerColor) ) {
						// We have a winner!
						setWinner(player);
						return;
					
					}
					
				}
			}
		}
	}
	
	
	
}
