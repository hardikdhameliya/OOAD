/*
 * This class extends Jpanel to create chessboard.
 * It also sets the backgroud color, pieces on particular square, coordinate of sqaure etc.
 * 
 * Functions:
 * Square()
 * void setBackGroundColor(String color)
 * void setIcon(Piece mypiece)
 * Piece getCurrentPiece()
 * void setNewIcon(Icon newIcon)
 * void setText(String st)
 * void setPosition(int x, int y)
 * int getXPosition()
 * int getYPosition() 
 * void highlight()
 * Map<Integer, ArrayList<Integer>> getPossibleMoves()
 * boolean isClicked()
 * void notClicked()
 * String getPieceColor()
 * String getPiece()
 * void setClicked()
 */

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Square extends JPanel{

	//variables for x position, y position, background color, button
	private JButton button;
	private int xPosition;
	private int yPosition;
	private Piece piece;
	private String color;
	private boolean isIcon;
	
	//variable for posible movement of piece on the square
	private Map<Integer, ArrayList<Integer>> xyMovment;
	
	//variable to check whether button is clicked or not
	private boolean clicked;
	private boolean firsttime;
	
	
	//constuctor
	public Square(){
		super();
		
		firsttime=true;
		xyMovment=new HashMap<Integer, ArrayList<Integer>>();
		button=new JButton();
		setLayout(new GridLayout());
		color=null;
		isIcon=false;
		add(button);
	}
	
	
	//set the background color
	public void setBackGroundColor(String color){
		if(color.equalsIgnoreCase("white")){
			button.setBackground(Color.WHITE);
		}else{
			button.setBackground(Color.green);
		}
		
	}
	
	//set the icon
	public void setIcon(Piece mypiece){
		piece=mypiece;
		if(piece!=null){
			isIcon=true;
			button.setIcon(piece.getIcon());
			//button.setText("x "+ xPosition+" y "+yPosition);
		}else{
			isIcon=false;
			button.setIcon(null);
			
		}
		
	}
	
	//returns piece
	public Piece getCurrentPiece(){
		return piece;
	}
	
	//change the icon
	public void setNewIcon(Icon newIcon){
		button.setIcon(newIcon);
	}
	
	//write on the square
	public void setText(String st){
		button.setText(st);
	}
	
	//set the position of square
	public void setPosition(int x, int y){
		
		if(x>=ColumnNumber.firstColumn.ordinal() && x<=ColumnNumber.eightColumn.ordinal()){
			xPosition=x;
		}
		if(y>=RowNumber.firstRow.ordinal() && y<=RowNumber.eightRow.ordinal()){
			yPosition=y;
		}
		
	}
	
	//returns x position of sqaure
	public int getXPosition(){
		return xPosition;
	}
	
	//returns y position of sqaure
	public int getYPosition(){
		return yPosition;
	}
	
	//keep track that whether button is pressed to highlight the possible moves or not
	public void highlight(){
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			//if(!clicked){	
				
				firsttime=true;
				clicked=true;
				if(piece!=null){
					xyMovment=piece.xyPossibleMovments(xPosition, yPosition);
				}
			}
		});
		
	}
	
	//returns possible moves
	public Map<Integer, ArrayList<Integer>> getPossibleMoves(){
		return xyMovment;
		
	}
	
	//returns whether button is clicked or not
	public boolean isClicked() {
		return clicked;
	}
	
	//reset the flag
	public void notClicked(){
		clicked=false;
	}
	
	//returns whether button is clicked for first time
	public boolean isFirstClick(){
		return firsttime;
	}
	
	
	//returns color of piece
	public String getPieceColor(){
		
		if(xPosition>=ColumnNumber.firstColumn.ordinal() && xPosition<=ColumnNumber.eightColumn.ordinal() && xPosition>=RowNumber.firstRow.ordinal() && xPosition<=RowNumber.eightRow.ordinal()){
			
			if(isIcon){
				return piece.getColor();
			}else{
				return null;
			}
		}else{
			return null;
		}
		
		
	}
	
	
	//returns piece on the square
	public String getPiece(){
		if(piece!=null){
			return piece.getPiece();
		}else{
			return null;
		}
		
	}
	
	//set the clicked flag
	public void setClicked(){
		clicked=true;
	}
}
