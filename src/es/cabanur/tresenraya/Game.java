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
	
	public void setBoard(GameBoard board) {
		this.board = board;
	}
	
	public GameBoard getBoard() {
		return this.board;
	}
	
	void move(Player player, int refRow, int refCol) {
		board.put(player.getColor(), refRow, refCol);

		/* First step: checking if any of the surrounding squares to this move
		 * has another GamePiece of the same color.
		 */
		for (int r = refRow - 1; r <= refRow + 1; r++) {
			for (int c = refCol - 1; c <= refCol + 1; c++) {
				if (r == 0 && c == 0) continue; // Skip the reference Piece.
				try {
					if (board.getSquare(r,c).getColor() == board.getSquare(refRow, refCol).getColor()) {
						/* Second step: check if there's another GamePiece of the
						 * same color in the next square following the same 
						 * direction.
						 */
						int secondPieceRow = refRow + 2 * r;
						int secondPieceCol = refCol + 2 * c;
						if (board.getSquare(secondPieceRow, secondPieceCol).getColor() 
								== board.getSquare(refRow, refCol).getColor()) {
							// We have a winner!
							setWinner(player);
							
						}
					}
				} catch (NullPointerException e) {
					/* Tried to get the piece Color from an empty square. move on.
					 */
					continue;
				}
				
			}
		}
	}
	
}
