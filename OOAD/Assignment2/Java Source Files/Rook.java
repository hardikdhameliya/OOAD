/*
 * This is Rook class which extends Piece class.
 * It contains imageicon of Rook and color of piece.
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;
import java.util.List;

public class Rook extends Piece{

	//variable to store image and color
	private ImageIcon image;
	private String color;
	
	
	//Constructor
	public Rook(){
		super();
		image=new ImageIcon();
		color=null;
	}
	
	//constructor to set color and image
	public Rook(String cl) {
		
		if(cl.equalsIgnoreCase("white")){
			color="white";
			image= new ImageIcon(Component.whiteRook.getValue());
			setIcon(image);
		
		}else if(cl.equalsIgnoreCase("black")){
			color="black";
			image= new ImageIcon(Component.blackRook.getValue());
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
		return "Rook";
	}
	
	//Gives possible movement of Rook
	public Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y){
		
		Map<Integer, ArrayList<Integer>> xyMap=new HashMap<Integer,ArrayList<Integer>>();
		ArrayList<Integer> yMoves=new ArrayList<Integer>();
		
		

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
				}else{
					xyMap.put(columnNumber.ordinal(), yMoves);
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
				
		return xyMap;
		
	}
	
	//returns color of Rook
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	

}
