import java.util.ArrayList;


public class TestBench {

	public static void main(String[] args) {
		Game game = new Game();
		GameBoard board = new GameBoard(3,3, game);
		
		
		ArrayList<Player> players = new ArrayList<>(2);
		
		players.add(new Player("Pau", game, GamePieceColor.BLACK));
		players.add(new Player("Juan", game, GamePieceColor.WHITE));
		
		Player p;
		int row, col;
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		
		while (!game.hasWinner()) {
			p = players.iterator().next();
			System.out.print(p.getName() + ", it's your turn. Select row and column to place a " 
					+ "piece in: ");
			row = stdin.nextInt();
			col = stdin.nextInt();
			p.move(row, col);
		}
		
		
		
	}

}
