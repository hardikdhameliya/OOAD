/*
 * This is Bishop class which extends Piece class.
 * It contains imageicon of Bishop and color of piece.
 * 
 * functions:
 * ImageIcon getIcon()
 * String getPiece()
 * Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y)
 * String getColor()
 * 
 */



import java.awt.Color;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

public class Bishop extends Piece {

	//variable to store image and color
	private ImageIcon image;
	private String color;
	
	//constructor
	public Bishop() {
		super();
		image = new ImageIcon();
		color=null;
	}

	//constructor to set color and image
	public Bishop(String cl) {
		
		if (cl.equalsIgnoreCase("white")) {
			color="white";
			//image = new ImageIcon("c:/white_bishop.png");
			image = new ImageIcon(Component.whiteBishop.getValue());
			setIcon(image);
		} else if (cl.equalsIgnoreCase("black")) {
			color="black";
			image = new ImageIcon(Component.blackBishop.getValue());
			setIcon(image);

		}
	}

	
	//returns imageicon
	public ImageIcon getIcon() {
		return image;
	}

	//returns piece as String
	@Override
	public String getPiece() {
		// TODO Auto-generated method stub
		return "Bishop";
	}

	//Gives possible movement of Bishop
	@Override
	public Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y) {

		Map<Integer, ArrayList<Integer>> xyMap = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> yMoves = new ArrayList<Integer>();
		int leftMoves = x - ColumnNumber.firstColumn.ordinal();
		int rightMoves = ColumnNumber.eightColumn.ordinal() - x;
		int upMoves = y - RowNumber.firstRow.ordinal();
		int downMoves = RowNumber.eightRow.ordinal() - y;

		List<Integer> xLeftMoves = new ArrayList<>();
		List<Integer> xRightMoves = new ArrayList<>();
		List<Integer> yUpMoves = new ArrayList<>();
		List<Integer> yUpMoves1 = new ArrayList<>();
		List<Integer> yDownMoves = new ArrayList<>();
		List<Integer> yDownMoves1 = new ArrayList<>();

		for (int i = leftMoves - 1; i >= 0; i--) {
			xLeftMoves.add(i);
		}

		for (int i = leftMoves + 1; i <= ColumnNumber.eightColumn.ordinal(); i++) {
			xRightMoves.add(i);
		}

		for (int i = upMoves - 1; i >= 0; i--) {
			yUpMoves.add(i);
			yUpMoves1.add(i);
		}

		for (int i = upMoves + 1; i <= RowNumber.eightRow.ordinal(); i++) {
			yDownMoves.add(i);
			yDownMoves1.add(i);
		}

		// Diagonal Moves
		for (Integer xx : xLeftMoves) {
			for (Integer yy : yDownMoves) {
				yMoves = new ArrayList<Integer>();
				yMoves.add(yy);
				if (xyMap.containsKey(xx)) {
					xyMap.get(xx).addAll(yMoves);
				} else {
					xyMap.put(xx, yMoves);
				}
				yDownMoves.remove(yy);
				break;
			}
		}
		
		for (Integer xx : xRightMoves) {
			for (Integer yy : yUpMoves) {
				yMoves = new ArrayList<Integer>();
				yMoves.add(yy);
				if (xyMap.containsKey(xx)) {
					xyMap.get(xx).addAll(yMoves);
				} else {
					xyMap.put(xx, yMoves);
				}
				yUpMoves.remove(yy);
				break;
			}
		}

		for (Integer xx : xLeftMoves) {
			for (Integer yy : yUpMoves1) {
				yMoves = new ArrayList<Integer>();
				yMoves.add(yy);
				if (xyMap.containsKey(xx)) {
					xyMap.get(xx).addAll(yMoves);
				} else {
					xyMap.put(xx, yMoves);
				}
				yUpMoves1.remove(yy);
				break;
			}
		}

		for (Integer xx : xRightMoves) {
			for (Integer yy : yDownMoves1) {
				yMoves = new ArrayList<Integer>();
				yMoves.add(yy);
				if (xyMap.containsKey(xx)) {
					xyMap.get(xx).addAll(yMoves);
				} else {
					xyMap.put(xx, yMoves);
				}
				yDownMoves1.remove(yy);
				break;
			}
		}

		return xyMap;

	}

	//returns color of bishop
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}

}
