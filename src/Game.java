import java.io.Serializable;


public class Game implements Serializable {

	private static final long serialVersionUID = 201410030500L;
	private Player[] players;
	private Player winner;
	
	public Game() {
		players = new Player[2];
		
	}
	
	public void addPlayer(Player newPlayer) {
		boolean added = false;
		for (Player p : players) {
			if (p == null) {
				p = newPlayer;
				added = true;
			}
		}
		if ( !added ) {
			throw new IndexOutOfBoundsException("Couldn't add the player " 
					+ newPlayer.getName() + ", this game is full");
		}
	}

	public boolean hasWinner() {
		if (winner == null) return false;
		else return true;
	
	}

	public void setWinner(Player player) {
		this.winner = player;
		
	}
	
}
