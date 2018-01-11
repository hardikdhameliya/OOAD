/*
 * This is abstract class for all the pieces, which extends Jlabel
 * 
 * Functions:
 * Piece()
 * void setPosition(int Xposition, int Yposition)
 * int getCurrentXposition()
 * void setCurrentXposition(int Xposition)
 * int getCurrentYposition()
 * void setCurrentYposition(int Yposition)
 * 
 * Abstact methods
 * String getColor();
 * String getPiece();
 * Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y);
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public abstract class Piece extends JLabel{

	//variable to hold current x position and y position
	private int currentXposition;
	private int currentYposition;
	
	//constructor
	public Piece() {
		currentXposition=0;
		currentYposition=0;
	}
		
	//returns current x position
	public int getCurrentXposition() {
		return currentXposition;
	}
	
	//set current x position
	public void setCurrentXposition(int Xposition) {
		currentXposition = Xposition;
	}

	//returns current y position	
	public int getCurrentYposition() {
		return currentYposition;
	}
	
	//set current y position
	public void setCurrentYposition(int Yposition) {
		currentYposition = Yposition;
	}
	
	//abstact method which are defined in sub classes
	public abstract String getColor(); 
	public abstract String getPiece();
	public abstract Map<Integer, ArrayList<Integer>> xyPossibleMovments(int x, int y);
}
