/*
 * This class used to keep track of moves of pieces.
 * When Hint button pressed, this moves are sorted in order to find best optimum moves.
 * 
 * Functions:
 * MoveInHint()
 * public MoveInHint(String n, boolean cap, int greaterX, int greaterY, int distanceX, 
						int distanceY, int x, int y) 
	getters and setter for each attributes.
 */

public class MoveInHint {
	
	private String name;
	private boolean capture;
	private int maxX;
	private int maxY;
	private int xDistance;
	private int yDistance;
	private int xPosition;
	private int yPosition;
	
	public MoveInHint(){
		
	}
	
	//Set the name of piece
	public String getName() {
		return name;
	}
	
	//returns name of piece
	public void setName(String n) {
		name = n;
	}
	
	//returns whether piece is captured or not
	public boolean isCapture() {
		return capture;
	}
	
	//set the piece as captured piece
	public void setCapture(boolean cap) {
		capture = cap;
	}
	
	//returns maximum horizontal possible position of piece
	public int getMaxX() {
		return maxX;
	}
	
	//set maximum horizontal possible position of piece
	public void setMaxX(int x) {
		maxX = x;
	}
	
	//returns maximum vertical possible position of piece
	public int getMaxY() {
		return maxY;
	}
	
	//set maximum vertical possible position of piece
	public void setMaxY(int y) {
		maxY = y;
	}
	
	//returns maximum possible horizontal distance of piece
	public int getxDistance() {
		return xDistance;
	}
	
	//set maximum possible horizontal distance of piece
	public void setxDistance(int x) {
		xDistance = x;
	}
	
	//returns maximum possible vertical distance of piece
	public int getyDistance() {
		return yDistance;
	}
	
	//set maximum possible vertical distance of piece
	public void setyDistance(int y) {
		yDistance = y;
	}
	
	//returns current x position of piece
	public int getxPosition() {
		return xPosition;
	}
	
	//set current x position of piece
	public void setxPosition(int x) {
		xPosition = x;
	}
	
	//returns current y position of piece
	public int getyPosition() {
		return yPosition;
	}
	
	//set current y position of piece
	public void setyPosition(int y) {
		yPosition = y;
	}
	
	
	@Override
	public String toString() {
		return "MoveInHint [name=" + name + ", capture=" + capture + ", maxX=" + maxX + ", maxY=" + maxY
				+ ", xDistance=" + xDistance + ", yDistance=" + yDistance + ", xPosition=" + xPosition + ", yPosition="
				+ yPosition + "]";
	}
	
	

	
}
