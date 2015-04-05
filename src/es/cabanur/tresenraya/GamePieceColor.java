package es.cabanur.tresenraya;

public enum GamePieceColor {
	BLACK,
	WHITE;
	
	public String toString() {
		String s;
		switch (this) {
		case BLACK: 
			s = "X";
			break;
		case WHITE:
			s = "O";
			break;
		default:
			s = "?";
				
		}
		return s;
		
	}

}
