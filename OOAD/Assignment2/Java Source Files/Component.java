/*
 * Enmum class for components
 */
public enum Component {

	whiteBishop("c:/white_bishop.png"),
	blackBishop("c:/black_bishop.png"),
	
	whiteKing("c:/white_king.png"),
	blackKing("c:/black_king.png"),
	
	whiteKnight("c:/white_knight.png"),
	blackKnight("c:/black_knight.png"),
	
	whitePawn("c:/white_pawn.png"),
	blackPawn("c:/black_pawn.png"),
	
	whiteQueen("c:/white_queen.png"),
	blackQueen("c:/black_queen.png"),
	
	whiteRook("c:/white_rook.png"),
	blackRook("c:/black_rook.png");
	
	
	//store location of image in value
	private String location;
	
    private Component(String value) {
       location = value;
    }

    public String getValue() {
       return location;
    }
}
