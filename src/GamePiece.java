
public class GamePiece {
	private GamePieceColor color;
	
	public GamePiece(GamePieceColor color) {
		this.color = color;
	}
	
	public GamePieceColor getColor() {
		return color;
	}
	@Override
	public String toString() {
		String ret;
		switch (color) {
		case BLACK: {
			ret = "X";
			break;
		}
		case WHITE: {
			ret = "O";
			break;
		}
		default : {
			ret = "?";
		}
		}
		return ret;
		
	}
	
}
