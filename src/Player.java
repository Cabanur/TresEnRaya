import java.io.Serializable;


public class Player implements Serializable {
	
	private static final long serialVersionUID = 201410030500L;
	private String name;
	private GamePieceColor color;
	private GameBoard board;

	public Player(String name, Game game, GamePieceColor color) {
		this.name = name;
		this.color = color;
		this.board = game.getBoard();
	}

	public GamePieceColor getColor() {
		return color;
	}

	public String getName() {
		return this.name;
	}
	
	public void join(Game game) {
		game.addPlayer(this);
	}
	
	public void move(int row, int column) {
		board.put(this, row, column);
		
	}
	
	

}
