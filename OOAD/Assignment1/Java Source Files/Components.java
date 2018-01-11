import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.sun.xml.internal.ws.assembler.dev.ServerTubelineAssemblyContext;

public class Components {

	private final String BLACK_BISHOP_FILE = "C:/black_bishop.png";
	private final String BLACK_KING_FILE = "C:/black_king.png";
	private final String BLACK_KNIGHT_FILE = "C:/black_knight.png";
	private final String BLACK_PAWN_FILE = "C:/black_pawn.png";
	private final String BLACK_QUEEN_FILE = "C:/black_queen.png";
	private final String BLACK_ROOK_FILE = "C:/black_rook.png";

	private ImageIcon black_bishop;
	private ImageIcon black_king;
	private ImageIcon black_knight;
	private ImageIcon black_pawn;
	private ImageIcon black_queen;
	private ImageIcon black_rook;

	private final String WHITE_BISHOP_FILE = "C:/white_bishop.png";
	private final String WHITE_KING_FILE = "C:/white_king.png";
	private final String WHITE_KNIGHT_FILE = "C:/white_knight.png";
	private final String WHITE_PAWN_FILE = "C:/white_pawn.png";
	private final String WHITE_QUEEN_FILE = "C:/white_queen.png";
	private final String WHITE_ROOK_FILE = "C:/white_rook.png";

	private ImageIcon white_bishop;
	private ImageIcon white_king;
	private ImageIcon white_knight;
	private ImageIcon white_pawn;
	private ImageIcon white_queen;
	private ImageIcon white_rook;

	public Components() {

		// Set the Black pieces
		setBlack_bishop();
		setBlack_king();
		setBlack_knight();
		setBlack_queen();
		setBlack_rook();
		setBlack_pawn();

		// Set the White pieces
		setWhite_bishop();
		setWhite_king();
		setWhite_knight();
		setWhite_queen();
		setWhite_rook();
		setWhite_pawn();

	}

	public ImageIcon getBlack_bishop() {
		return black_bishop;
	}

	public void setBlack_bishop() {

		black_bishop = new ImageIcon(BLACK_BISHOP_FILE);

	}

	public ImageIcon getBlack_king() {
		return black_king;
	}

	public void setBlack_king() {

		black_king = new ImageIcon(BLACK_KING_FILE);

	}

	public ImageIcon getBlack_knight() {
		return black_knight;
	}

	public void setBlack_knight() {

		black_knight = new ImageIcon(BLACK_KNIGHT_FILE);

	}

	public ImageIcon getBlack_pawn() {
		return black_pawn;
	}

	public void setBlack_pawn() {

		black_pawn = new ImageIcon(BLACK_PAWN_FILE);

	}

	public ImageIcon getBlack_queen() {
		return black_queen;
	}

	public void setBlack_queen() {

		black_queen = new ImageIcon(BLACK_QUEEN_FILE);

	}

	public ImageIcon getBlack_rook() {
		return black_rook;
	}

	public void setBlack_rook() {

		black_rook = new ImageIcon(BLACK_ROOK_FILE);

	}

	// White componenets

	public ImageIcon getWhite_bishop() {
		return white_bishop;
	}

	public void setWhite_bishop() {

		white_bishop = new ImageIcon(WHITE_BISHOP_FILE);

	}

	public ImageIcon getWhite_king() {
		return white_king;
	}

	public void setWhite_king() {

		white_king = new ImageIcon(WHITE_KING_FILE);

	}

	public ImageIcon getWhite_knight() {
		return white_knight;
	}

	public void setWhite_knight() {

		white_knight = new ImageIcon(WHITE_KNIGHT_FILE);

	}

	public ImageIcon getWhite_pawn() {
		return white_pawn;
	}

	public void setWhite_pawn() {

		white_pawn = new ImageIcon(WHITE_PAWN_FILE);

	}

	public ImageIcon getWhite_queen() {
		return white_queen;
	}

	public void setWhite_queen() {

		white_queen = new ImageIcon(WHITE_QUEEN_FILE);

	}

	public ImageIcon getWhite_rook() {
		return white_rook;
	}

	public void setWhite_rook() {

		white_rook = new ImageIcon(WHITE_ROOK_FILE);

	}

}
