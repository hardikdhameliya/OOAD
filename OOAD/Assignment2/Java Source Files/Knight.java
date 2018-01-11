/*
 * This is Knight class which extends Piece class.
 * It contains imageicon of King and color of piece.
 * 
 * functions:
 * ImageIcon getIcon()
 * String getPiece()
 * Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y)
 * String getColor()
 * 
 */


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Knight extends Piece{

	//variable to store image and color
	private ImageIcon image;
	private String color;
	
	//Constructor
	public Knight(){
		super();
		image=new ImageIcon();
		color=null;
	}
	
	//constructor to set color and image
	public Knight(String cl) {
		//super(Xposition, Yposition,cl);
		
		if(cl.equalsIgnoreCase("white")){
			color="white";
			image= new ImageIcon(Component.whiteKnight.getValue());
			setIcon(image);
		
		}else if(cl.equalsIgnoreCase("black")){
			color="black";
			image= new ImageIcon(Component.blackKnight.getValue());
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
		return "Knight";
	}
	
	//Gives possible movement of Knight
	@Override
	public Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y) {
		Map<Integer, ArrayList<Integer>> xyMap=new HashMap<Integer,ArrayList<Integer>>();
		ArrayList<Integer> yMoves=new ArrayList<Integer>();
		
		yMoves.add(y+1);
		yMoves.add(y-1);
		
		if(xyMap.containsKey(x+2)){
			xyMap.get(x+2).addAll(yMoves);
		}else{
			xyMap.put(x+2, yMoves);
		}
		
		yMoves=new ArrayList<Integer>();
		yMoves.add(y+1);
		yMoves.add(y-1);
		
		if(xyMap.containsKey(x-2)){
			xyMap.get(x-2).addAll(yMoves);
		}else{
			xyMap.put(x-2, yMoves);
		}
		
		
		yMoves=new ArrayList<Integer>();
		yMoves.add(y+2);
		yMoves.add(y-2);
		
		if(xyMap.containsKey(x+1)){
			xyMap.get(x+1).addAll(yMoves);
		}else{
			xyMap.put(x+1, yMoves);
		}
		
		yMoves=new ArrayList<Integer>();
		yMoves.add(y+2);
		yMoves.add(y-2);
		
		if(xyMap.containsKey(x-1)){
			xyMap.get(x-1).addAll(yMoves);
		}else{
			xyMap.put(x-1, yMoves);
		}
		
		return xyMap;
	}
	
	
	//returns color of knight
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	

}
