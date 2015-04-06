package es.cabanur.tresenraya;


public class TestBench {

	public static void main(String[] args) {
		Game game = new Game();
		
		
		
		game.addPlayer(new Player("Pau", GamePieceColor.BLACK));
		game.addPlayer(new Player("Juan",GamePieceColor.WHITE));
		
		int row, col;
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		
		while (game.hasWinner() == false) {
			for (Player p : game.getPlayers()) {
				System.out.println(game.getBoard());
				System.out.print(p.getName() + ", it's your turn. Select row and "
						+ "column to place a piece in: ");
				row = stdin.nextInt();
				col = stdin.nextInt();
				p.move(row, col);
				System.out.println();
			}
		}
		
		stdin.close();
		
	}

}
