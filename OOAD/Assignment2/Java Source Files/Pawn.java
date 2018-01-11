/*
 * This is Pawn class which extends Piece class.
 * It contains imageicon of Pawn and color of piece.
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
import java.util.Map;

import javax.swing.ImageIcon;

public class Pawn extends Piece{

	//variable to store image and color
	private ImageIcon image;
	private String color;
	
	//constructor
	public Pawn(){
		super();
		image=new ImageIcon();
		color=null;
	}
	
	//constructor to set color and image
	public Pawn(String cl) {
		
		if(cl.equalsIgnoreCase("white")){
			color="white";
			image= new ImageIcon(Component.whitePawn.getValue());
			setIcon(image);
		
		}else if(cl.equalsIgnoreCase("black")){
			color="black";
			image= new ImageIcon(Component.blackPawn.getValue());
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
		return "Pawn";
	}
	
	
	//Gives possible movement of Pawn
	@Override
	public Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y) {
		Map<Integer, ArrayList<Integer>> xyMap=new HashMap<Integer,ArrayList<Integer>>();
		ArrayList<Integer> yMoves=new ArrayList<Integer>();
		
		yMoves.add(y+1);
		yMoves.add(y-1);
		
		if(xyMap.containsKey(x)){
			xyMap.get(x).addAll(yMoves);
		}else{
			xyMap.put(x, yMoves);
		}
		
		if(y==RowNumber.secondRow.ordinal()||y==RowNumber.seventhRow.ordinal()){
			yMoves=new ArrayList<Integer>();
			yMoves.add(y+2);
			yMoves.add(y-2);
			if(xyMap.containsKey(x)){
				xyMap.get(x).addAll(yMoves);
			}else{
				xyMap.put(x, yMoves);
			}
		}
				
		return xyMap;
	}
	
	
	//returns color of pawn
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	
	

}
