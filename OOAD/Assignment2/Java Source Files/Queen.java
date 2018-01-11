/*
 * This is Queen class which extends Piece class.
 * It contains imageicon of Queen and color of piece.
 * 
 * public functions:
 * getIcon()
 * getPiece()
 * xyPossibleMovments(int x, int y)
 * getColor()
 * 
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

public class Queen extends Piece{

	//variable to store image and color
	private ImageIcon image;
	private String color;
	
	//Constructor
	public Queen(){
		super();
		image=new ImageIcon();
		color=null;
		
	}
	
	//constructor to set color and image
	public Queen(String cl) {
		//super(Xposition, Yposition,cl);
		
		if(cl.equalsIgnoreCase("white")){
			color="white";
			image= new ImageIcon(Component.whiteQueen.getValue());
			setIcon(image);
		
		}else if(cl.equalsIgnoreCase("black")){
			color="black";
			image= new ImageIcon(Component.blackQueen.getValue());
			setIcon(image);
		
		}
	}
	
	
	//returns imageicon
	public ImageIcon getIcon(){
		return image;
	}
	
	//returns piece as String
	@Override
	public String getPiece() {
		// TODO Auto-generated method stub
		return "Queen";
	}
	
	//Gives possible movement of Queen
	@Override
	public Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y) {
		Map<Integer, ArrayList<Integer>> xyMap=new HashMap<Integer,ArrayList<Integer>>();
		ArrayList<Integer> yMoves=new ArrayList<Integer>();
		int leftMoves= x-ColumnNumber.firstColumn.ordinal();
		int rightMoves=ColumnNumber.eightColumn.ordinal()-x;
		int upMoves=y-RowNumber.firstRow.ordinal();
		int downMoves=RowNumber.eightRow.ordinal()-y;
		
		List<Integer> xLeftMoves=new ArrayList<>();
		List<Integer> xRightMoves=new ArrayList<>();
		List<Integer> yUpMoves=new ArrayList<>();
		List<Integer> yUpMoves1=new ArrayList<>();
		List<Integer> yDownMoves=new ArrayList<>();
		List<Integer> yDownMoves1=new ArrayList<>();
		
		
		for (int i = leftMoves-1; i >= 0; i--) {
			xLeftMoves.add(i);
		}
		
		for (int i = leftMoves+1; i <= ColumnNumber.eightColumn.ordinal(); i++) {
			xRightMoves.add(i);
		}
		
		for (int i = upMoves-1; i >=0; i--) {
			yUpMoves.add(i);
			yUpMoves1.add(i);
		}
		
		for (int i = upMoves+1; i <= RowNumber.eightRow.ordinal(); i++) {
			yDownMoves.add(i);
			yDownMoves1.add(i);
		}
		
		// Horizontal Moves 
		yMoves=new ArrayList<Integer>();
		for (ColumnNumber columnNumber : ColumnNumber.values()) {
			yMoves=new ArrayList<Integer>();
			if(x!=columnNumber.ordinal()){
				//System.out.println("xmoves "+ columnNumber.ordinal()+" ymoves "+y);
				yMoves.add(y);
				if(xyMap!=null){
					if(xyMap.containsKey(columnNumber.ordinal())){
						xyMap.get(columnNumber.ordinal()).addAll(yMoves);
					}else{
						xyMap.put(columnNumber.ordinal(), yMoves);
					}
				}
			}
		
		}
		
		
		//Vertical Moves
		yMoves=new ArrayList<Integer>();
		for (RowNumber rowNumber : RowNumber.values()) {
			if(y!=rowNumber.ordinal()){
				yMoves.add(rowNumber.ordinal());
				//System.out.println("xmoves "+ x+ " ymoves "+ rowNumber.ordinal());
			}
			
		}
		
		if(xyMap.containsKey(x)){
			xyMap.get(x).addAll(yMoves);
		}else{
			xyMap.put(x, yMoves);
		}
		
		// Diagonal Moves
		for (Integer xx : xLeftMoves) {
			for (Integer yy : yDownMoves) {
				yMoves=new ArrayList<Integer>();
				yMoves.add(yy);
				if(xyMap.containsKey(xx)){
					xyMap.get(xx).addAll(yMoves);
				}else{
					xyMap.put(xx, yMoves);
				}
				yDownMoves.remove(yy);
				break;
			}	
		}
		
		
		for (Integer xx : xRightMoves) {
			for (Integer yy : yUpMoves) {
				yMoves=new ArrayList<Integer>();
				yMoves.add(yy);
				if(xyMap.containsKey(xx)){
					xyMap.get(xx).addAll(yMoves);
				}else{
					xyMap.put(xx, yMoves);
				}
				yUpMoves.remove(yy);
				break;
			}	
		}
		
		for (Integer xx : xLeftMoves) {
			for (Integer yy : yUpMoves1) {
				yMoves=new ArrayList<Integer>();
				yMoves.add(yy);
				if(xyMap.containsKey(xx)){
					xyMap.get(xx).addAll(yMoves);
				}else{
					xyMap.put(xx, yMoves);
				}
				yUpMoves1.remove(yy);
				break;
			}	
		}
		
		for (Integer xx : xRightMoves) {
			for (Integer yy : yDownMoves1) {
				yMoves=new ArrayList<Integer>();
				yMoves.add(yy);
				if(xyMap.containsKey(xx)){
					xyMap.get(xx).addAll(yMoves);
				}else{
					xyMap.put(xx, yMoves);
				}
				yDownMoves1.remove(yy);
				break;
			}	
		}
		
		
		return xyMap;
	}
	
	//returns color of Queen
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	

}
