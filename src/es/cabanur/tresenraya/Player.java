package es.cabanur.tresenraya;
import java.io.Serializable;


public class Player implements Serializable {
	
	private static final long serialVersionUID = 201410030500L;
	private String name;
	private GamePieceColor color;
	private Game game;
	
	public Player(String name, GamePieceColor color) {
		this.name = name;
		this.color = color;
	}

	public GamePieceColor getColor() {
		return color;
	}

	public String getName() {
		return this.name;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void move(int row, int column) {
		game.move(this, row, column);
		
	}
	
	public String toString() {
		return this.name;
	}
	
}
