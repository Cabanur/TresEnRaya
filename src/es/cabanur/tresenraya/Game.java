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
		for (int secondRow = refRow - 1; secondRow <= refRow + 1; secondRow++) {
			// when scanning for a row out of range, ignore.
			if (secondRow < 0 || secondRow >= board.getSquares().length) continue; 
			
			for (int secondCol = refCol - 1; secondCol <= refCol + 1; secondCol++) {
				// when scanning for a column out of range, ignore.
				if (secondCol < 0 || secondCol >= board.getSquares()[secondRow].length) continue;
				
				if (secondRow == refRow && secondCol == refCol) continue; // Skip the reference Piece.
				try {
					if (board.getSquare(secondRow,secondCol).getColor() == board.getSquare(refRow, refCol).getColor()) {
						/* Second step: check if there's another GamePiece of the
						 * same color in the next square following the same 
						 * direction.
						 */
						int thirdPieceRow = 2 * secondRow - refRow;
						
						int thirdPieceCol = 2 * secondCol - refCol;
						if (board.getSquare(thirdPieceRow, thirdPieceCol).getColor() 
								== board.getSquare(refRow, refCol).getColor()
								|| board.getSquare(thirdPieceRow * -1, thirdPieceCol * -1).getColor()
								== board.getSquare(refRow, refCol).getColor()) {
							// We have a winner!
							setWinner(player);
							break;
							
						}
					}
				} catch (NullPointerException e) {
					/* Tried to get the piece Color from an empty square. move on.
					 */
					System.err.println("Tried to get a piece from a non-valid or empty square (" 
							+ secondRow + ", " + secondCol + ").");
					continue;
				}
				
			}
		}
	}
	
}
