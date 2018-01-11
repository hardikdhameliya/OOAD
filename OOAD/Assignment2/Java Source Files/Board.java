/*This is board class contains board related things.
 * It handles movement of components, keep track of turns, highlight possible moves of components.
 * 
 * Functions:
 * Board()
 * Square[][] getCurrentPosition()
 * void prepareBlankBoard()
 * void setPieces()
 * void setPieces()
 * setSquare(int x, int y)
 * void startGame()
 * void setOptimizedMoves(Map<Integer, ArrayList<Integer>> dummyMoves)
 * Map<Integer, ArrayList<Integer>> getOptimizedMoves()
 * boolean isOptimizedMovesSet()
 * void moveComponent(int currentXposition,int currentYposition,int newXposition,int newYposition )
 * boolean isValidMove(Map<Integer, ArrayList<Integer>> possibleMoves, int currentXPoistion, int currentYPosition)
 * dohighlight(Map<Integer, ArrayList<Integer>> possibleMoves, boolean hl)
 * Map<Integer, ArrayList<Integer>> actualMoves(int currentXposition, int currentYposition, boolean hl)
 *boolean checkYposition(int y)
 * 
*/

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderWidths;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;


public class Board extends JPanel{

	//Hashmap variables to store possible moves, actual moves
	private Map<Integer, ArrayList<Integer>> actualMoves= new HashMap<Integer, ArrayList<Integer>>();
	private Map<Integer, ArrayList<Integer>> optimizedMoves= new HashMap<Integer, ArrayList<Integer>>();
	
	//variables for keep track of highlighted square, it's current position, which sides turn.
	private final int totalXposition=8;
	private final int totalYposition=8;
	
	private boolean isOptimizedMoves;
	private int currentXposition;
	private int currentYposition;
	private String currentColor;
	private int highlightedXposition;
	private int highlightedYposition;
	private boolean ishighlight;
	private Square[][] square;
	private boolean whiteTurn;
	private boolean blackTurn;
	
	//All the pieces
	private Rook[] whiteRook;
	private Rook[] blackRook;
	
	private Bishop[] whiteBishop;
	private Bishop[] blackBishop;
	
	private Knight[] whiteKnight;
	private Knight[] blackKnight;
	
	private King whiteKing;
	private King blackKing;
	
	private Queen whiteQueen;
	private Queen blackQueen;
	
	private Pawn[] whitePawn;
	private Pawn[] blackPawn;
	
	//border to be set when highlighted
	private LineBorder thickBorder;
	
	
	public Board()  {
		super();
		
		actualMoves= new HashMap<Integer, ArrayList<Integer>>();
		optimizedMoves= new HashMap<Integer, ArrayList<Integer>>();
		
		// Intialization of variables 
		currentXposition=0;
		currentYposition=0;
		highlightedXposition=0;
		highlightedYposition=0;
		ishighlight=false;
		square=new Square[totalXposition][totalYposition];
		whiteTurn=true;
		blackTurn=false;
		
		whiteRook= new Rook[2];
		blackRook= new Rook[2];
		whiteBishop= new Bishop[2];
		blackBishop= new Bishop[2];
		
		whiteKnight= new Knight[2];
		blackKnight= new Knight[2];
		whiteKing= new King();
		blackKing= new King();
		
		whiteQueen= new Queen();
		blackQueen= new Queen();
		
		whitePawn=new Pawn[8];
		blackPawn=new Pawn[8];
		
		thickBorder = new LineBorder(Color.black, 3);
		
		prepareBlankBoard();
		setPieces();
		setLayout(new GridLayout(0, 8));
		
		//Adding the squares in the Main Panel
		for (int i = 0; i < square.length; i++)
		{
			for(int j = 0; j < square[i].length; j++)
			{
				add(square[i][j]);
				//add(new JButton("button"+i+j));
			
			}
		}
	}
	
	//returns current position of sqaure
	public Square[][] getCurrentPosition(){
		return square.clone();
	}
	
	//prepare blank board
	private void prepareBlankBoard()
	{
		//Initialize and set the color of each square
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if((i + j) % 2 == 0 )
				{
					square[i][j] = new Square();
					square[i][j].setPosition(j, i);
					square[i][j].setBackGroundColor("white");
					square[i][j].highlight();
					
				}
				else
				{
					square[i][j] = new Square();
					square[i][j].setPosition(j, i);
					square[i][j].setBackGroundColor("green");
					square[i][j].highlight();
					
				}	
			}
		}
		
	}
	
	//places piece to square
	private void setPieces(){
		//Initialize Pawn
		
		for(int i = 0; i < 8; i++)
		{
			blackPawn[i] = new Pawn("black");
			whitePawn[i] = new Pawn("white");
		}
		
		//initialize the pieces;
		for(int i =0; i < 2; i++)
		{
			blackBishop[i] = new Bishop("black");
			whiteBishop[i] = new Bishop("white");
			
			blackKnight[i] = new Knight("black");
			whiteKnight[i] = new Knight("white");
			
			blackRook[i] = new Rook("black");
			whiteRook[i] = new Rook("white");
		}
		blackQueen = new Queen("black");
		whiteQueen = new Queen("white");
			
		blackKing = new King("black");
		whiteKing = new King("white");
		
		
		square[RowNumber.firstRow.ordinal()][ColumnNumber.firstColumn.ordinal()].setIcon(blackRook[0]);
		square[RowNumber.firstRow.ordinal()][ColumnNumber.eightColumn.ordinal()].setIcon(blackRook[1]);
		
		square[RowNumber.firstRow.ordinal()][ColumnNumber.secondColumn.ordinal()].setIcon(blackKnight[0]);
		square[RowNumber.firstRow.ordinal()][ColumnNumber.seventhColumn.ordinal()].setIcon(blackKnight[1]);

		square[RowNumber.firstRow.ordinal()][ColumnNumber.thirdColumn.ordinal()].setIcon(blackBishop[0]);
		square[RowNumber.firstRow.ordinal()][ColumnNumber.sixthColumn.ordinal()].setIcon(blackBishop[1]);

		square[RowNumber.firstRow.ordinal()][ColumnNumber.forthColumn.ordinal()].setIcon(blackQueen);
		
		square[RowNumber.firstRow.ordinal()][ColumnNumber.fifthColumn.ordinal()].setIcon(blackKing);
		
		int i=0;
		for (ColumnNumber columnNumber : ColumnNumber.values()) {
			square[RowNumber.secondRow.ordinal()][columnNumber.ordinal()].setIcon(blackPawn[i++]);
			
		}
		
		square[RowNumber.eightRow.ordinal()][ColumnNumber.firstColumn.ordinal()].setIcon(whiteRook[0]);
		square[RowNumber.eightRow.ordinal()][ColumnNumber.eightColumn.ordinal()].setIcon(whiteRook[1]);
		
		square[RowNumber.eightRow.ordinal()][ColumnNumber.secondColumn.ordinal()].setIcon(whiteKnight[0]);
		square[RowNumber.eightRow.ordinal()][ColumnNumber.seventhColumn.ordinal()].setIcon(whiteKnight[1]);

		square[RowNumber.eightRow.ordinal()][ColumnNumber.thirdColumn.ordinal()].setIcon(whiteBishop[0]);
		square[RowNumber.eightRow.ordinal()][ColumnNumber.sixthColumn.ordinal()].setIcon(whiteBishop[1]);

		square[RowNumber.eightRow.ordinal()][ColumnNumber.forthColumn.ordinal()].setIcon(whiteQueen);
		
		square[RowNumber.eightRow.ordinal()][ColumnNumber.fifthColumn.ordinal()].setIcon(whiteKing);
		
		i=0;
		for (ColumnNumber columnNumber : ColumnNumber.values()) {
			square[RowNumber.seventhRow.ordinal()][columnNumber.ordinal()].setIcon(whitePawn[i++]);
			//square[RowNumber.seventhRow.ordinal()][columnNumber.ordinal()].setBorder(thickBorder);
		}
	}
	
	//set square as clicked
	public void setSquare(int x, int y){
		square[y][x].setClicked();
	}
	
	
	//This function will keep track of turns, whether any piece selected to move.
	public void startGame(){
		
		for(RowNumber rowNumber:RowNumber.values()){
			for (ColumnNumber columnNumber : ColumnNumber.values()) {
				if(square[rowNumber.ordinal()][columnNumber.ordinal()].isClicked()){
					currentXposition=columnNumber.ordinal();
					currentYposition=rowNumber.ordinal();
					square[currentYposition][currentXposition].notClicked();
					
					if(whiteTurn){
						currentColor=square[currentYposition][currentXposition].getPieceColor();
						if(currentColor!=null){
							if(currentColor.equalsIgnoreCase("white")){
								if(!ishighlight){
									ishighlight=true;
									highlightedXposition=currentXposition;
									highlightedYposition=currentYposition;
									if(isOptimizedMoves){
										actualMoves=getOptimizedMoves();
									}else{
										actualMoves=actualMoves(highlightedXposition, highlightedYposition,true);
									}
									
									dohighlight(actualMoves, true);
								}else{
									dehighlight();
									ishighlight=false;
								}
							}else if (currentColor.equalsIgnoreCase("black")){
									if(ishighlight){
									ishighlight=false;
									if(!isValidMove(actualMoves, currentXposition, currentYposition)){
										//System.out.println("is not valid move man!");
										dehighlight();
									 }else{
										//System.out.println("is valid move man!");
										whiteTurn=false;
										blackTurn=true;
										moveComponent(highlightedXposition, highlightedYposition, currentXposition, currentYposition);
										dehighlight();
										//System.out.println(square[highlightedYposition][highlightedXposition].getPiece());
										//System.out.println(square[currentYposition][currentYposition].getPiece());
									}
								}
							}
						}else{
								ishighlight=false;
								dehighlight();
								if(!isValidMove(actualMoves, currentXposition, currentYposition)){
									//System.out.println("is not valid move man!");
									dehighlight();
								}else{
									//System.out.println("is valid move man!");
									whiteTurn=false;
									blackTurn=true;
									moveComponent(highlightedXposition, highlightedYposition, currentXposition, currentYposition);
									dehighlight();
									//System.out.println(square[highlightedYposition][highlightedXposition].getPiece());
									//System.out.println(square[currentYposition][currentYposition].getPiece());
								}
						}
					}else if(blackTurn){
						currentColor=square[currentYposition][currentXposition].getPieceColor();
						if(currentColor!=null){
							if(currentColor.equalsIgnoreCase("black")){
								if(!ishighlight){
									ishighlight=true;
									highlightedXposition=currentXposition;
									highlightedYposition=currentYposition;
									if(isOptimizedMoves){
										actualMoves=getOptimizedMoves();
									}else{
										actualMoves=actualMoves(highlightedXposition, highlightedYposition,true);
									}
									
									dohighlight(actualMoves, true);
								}else{
									dehighlight();
									ishighlight=false;
								}
							}else if(currentColor.equalsIgnoreCase("white")) {
								if(ishighlight){
									ishighlight=false;
									if(!isValidMove(actualMoves, currentXposition, currentYposition)){
										//System.out.println("is not valid move man!");
										dehighlight();
									}else{
										//System.out.println("is valid move man!");
										whiteTurn=true;
										blackTurn=false;
										moveComponent(highlightedXposition, highlightedYposition, currentXposition, currentYposition);
										dehighlight();
										//System.out.println(square[highlightedYposition][highlightedXposition].getPiece());
										//System.out.println(square[currentYposition][currentYposition].getPiece());
									}
								}
							}
						}else{
								ishighlight=false;
								dehighlight();
								if(!isValidMove(actualMoves, currentXposition, currentYposition)){
									//System.out.println("is not valid move man!");
									dehighlight();
								 }else{
									//System.out.println("is valid move man!");
									whiteTurn=true;
									blackTurn=false;
									moveComponent(highlightedXposition, highlightedYposition, currentXposition, currentYposition);
									dehighlight();
									//System.out.println(square[highlightedYposition][highlightedXposition].getPiece());
									//System.out.println(square[currentYposition][currentYposition].getPiece());
								}
						}
					}
					
					}	
				}
			}
	}
	
	//This sets optimized moves of component in case hint button clicked
	public void setOptimizedMoves(Map<Integer, ArrayList<Integer>> dummyMoves){
		optimizedMoves=dummyMoves;
		isOptimizedMoves=true;
	}
	
	//Returns optimized moves
	private Map<Integer, ArrayList<Integer>> getOptimizedMoves(){
		isOptimizedMoves=false;
		return optimizedMoves;
	}
	
	//Returns whether optimized moves set or not
	public boolean isOptimizedMovesSet(){
		return isOptimizedMoves;
	}
	
	//Move the component from current position to new position
	private void moveComponent(int currentXposition, 
							  int currentYposition,
							  int newXposition, 
							  int newYposition ){
		String currentPiece;
		Piece newPiece=null;
		Icon newPieceIcon;
		String color;
		
		currentPiece=square[currentYposition][currentXposition].getPiece();
		color=square[currentYposition][currentXposition].getPieceColor();
		switch (currentPiece) {
		
		case "Rook":
			newPiece=new Rook(color);
			break;
		case "Bishop" :
			newPiece= new Bishop(color);
			break;
		case "King":
			newPiece=new King(color);
			break;
		case "Pawn":
			newPiece=new Pawn(color);
			break;
		case "Knight":
			newPiece=new Knight(color);
			break;
		case "Queen":
			newPiece=new Queen(color);
			break;
		}
		
		square[newYposition][newXposition].setIcon(newPiece);
		square[currentYposition][currentXposition].setIcon(null);
	}
	
	//Check whether new move is valid or not
	private boolean isValidMove(Map<Integer, ArrayList<Integer>> possibleMoves, 
							   int currentXPoistion, int currentYPosition){
		int xValues;
		boolean isValid=false;
		for(Map.Entry<Integer, ArrayList<Integer>> listEntry : possibleMoves.entrySet()){
			xValues=listEntry.getKey();
			// iterating over a list
			if(currentXPoistion!=xValues){
				continue;
			}
			for(int yValues : listEntry.getValue()){
				if(currentYPosition!=yValues){
					continue;
				}else{
					isValid=true;
				}
        	}
		}
		return isValid;
	}
	
	//Highlight the possible moves
	public void dohighlight(Map<Integer, ArrayList<Integer>> possibleMoves, boolean hl){
		
		int xValues;
		
		for(Map.Entry<Integer, ArrayList<Integer>> listEntry : possibleMoves.entrySet()){
			xValues=listEntry.getKey();
			// iterating over a list
			for(int yValues : listEntry.getValue()){
				if(hl){
					square[yValues][xValues].setBorder(thickBorder);
				}else{
					square[yValues][xValues].setBorder(BorderFactory.createEmptyBorder());
				}
        	}
		}
	}
	
	//Dehighlight the moves
	private void dehighlight(){
		for (RowNumber rowNumber : RowNumber.values()) {
			for (ColumnNumber columnNumber : ColumnNumber.values()) {
				square[rowNumber.ordinal()][columnNumber.ordinal()].setBorder(BorderFactory.createEmptyBorder());
				//square[RowNumber.seventhRow.ordinal()][columnNumber.ordinal()].setBorder(thickBorder);
			}
		}
		
	}
	
	//Returns whether next turn is for white
	public boolean isWhiteTurn(){
		return whiteTurn;
	}
	
	//Returns whether next turn is for black
	public boolean isBlackTurn(){
		return blackTurn;
	}
	
	//Returns actual moves
	public Map<Integer, ArrayList<Integer>> actualMoves(int currentXposition, 
														int currentYposition, boolean hl){
		
		Map<Integer, ArrayList<Integer>> possibleMoves= new HashMap<Integer, ArrayList<Integer>>();
		Map<Integer, ArrayList<Integer>> actualMoves= new HashMap<Integer, ArrayList<Integer>>();
		int xValues;
		ArrayList<Integer> upperMoves=new ArrayList<Integer>();
		ArrayList<Integer> downMoves=new ArrayList<Integer>();
		ArrayList<Integer> rightMoves=new ArrayList<Integer>();
		ArrayList<Integer> leftMoves=new ArrayList<Integer>();
		
		ArrayList<Integer> upperLeftMoves=new ArrayList<Integer>();
		ArrayList<Integer> downRightMoves=new ArrayList<Integer>();
		ArrayList<Integer> upperRightMoves=new ArrayList<Integer>();
		ArrayList<Integer> downLeftMoves=new ArrayList<Integer>();
		
		int currentY=0;
		int currentX=0;
		boolean upperLeftFinish=false;
		boolean downLeftFinish=false;
		boolean upRightFinish=false;
		boolean downRightFinish=false;
		
		boolean upFinish=false;
		boolean downFinish=false;
		boolean rightFinish=false;
		boolean leftFinish=false;
		int pawnMove=0;
		
		String piece=square[currentYposition][currentXposition].getPiece();
		String newMoveColor;
		possibleMoves=square[currentYposition][currentXposition].getPossibleMoves();
		
		String currentColor=square[currentYposition][currentXposition].getPieceColor();
		
		if(piece==null){
			piece="No Piece";
		}
		
		switch (piece) {
		case "Knight":
			possibleMoves=new HashMap<Integer,ArrayList<Integer>>();
			ArrayList<Integer> yMoves=new ArrayList<Integer>();
			
			yMoves.add(currentYposition+1);
			yMoves.add(currentYposition-1);
			
			if(possibleMoves.containsKey(currentXposition+2)){
				possibleMoves.get(currentXposition+2).addAll(yMoves);
			}else{
				possibleMoves.put(currentXposition+2, yMoves);
			}
			
			yMoves=new ArrayList<Integer>();
			yMoves.add(currentYposition+1);
			yMoves.add(currentYposition-1);
			
			if(possibleMoves.containsKey(currentXposition-2)){
				possibleMoves.get(currentXposition-2).addAll(yMoves);
			}else{
				possibleMoves.put(currentXposition-2, yMoves);
			}
			
			
			yMoves=new ArrayList<Integer>();
			yMoves.add(currentYposition+2);
			yMoves.add(currentYposition-2);
			
			if(possibleMoves.containsKey(currentXposition+1)){
				possibleMoves.get(currentXposition+1).addAll(yMoves);
			}else{
				possibleMoves.put(currentXposition+1, yMoves);
			}
			
			yMoves=new ArrayList<Integer>();
			yMoves.add(currentYposition+2);
			yMoves.add(currentYposition-2);
			
			if(possibleMoves.containsKey(currentXposition-1)){
				possibleMoves.get(currentXposition-1).addAll(yMoves);
			}else{
				possibleMoves.put(currentXposition-1, yMoves);
			}
		
			
			for(Map.Entry<Integer, ArrayList<Integer>> listEntry : possibleMoves.entrySet()){
				xValues=listEntry.getKey();
				downMoves=new ArrayList<Integer>();
				if(ColumnNumber.firstColumn.ordinal() > xValues || xValues > ColumnNumber.eightColumn.ordinal()){
					continue;
				}
				// iterating over a list
				for(int yValues : listEntry.getValue()){
					if(ColumnNumber.firstColumn.ordinal() <= xValues && xValues <= ColumnNumber.eightColumn.ordinal() && RowNumber.firstRow.ordinal()<=yValues && yValues<=RowNumber.eightRow.ordinal()){
						newMoveColor=square[yValues][xValues].getPieceColor();
						if(newMoveColor!=null){
							if(!currentColor.equalsIgnoreCase(newMoveColor)){
								downMoves.add(yValues);
							}
						}else{
							downMoves.add(yValues);
						}
					}
				}
				if (actualMoves.containsKey(xValues)) {
					actualMoves.get(xValues).addAll(downMoves);
				} else {
					actualMoves.put(xValues, downMoves);
				}
			}
			break;
		case "Rook":
			
			currentColor=square[currentYposition][currentXposition].getPieceColor();
			//up
			currentY=currentYposition-1;
				while(currentY>=RowNumber.firstRow.ordinal()){
					newMoveColor=square[currentY][currentXposition].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							upperMoves.add(currentY);
							upFinish=true;
						}else{
							upFinish=true;
						}
					}else{
						upperMoves.add(currentY);
					}
					currentY--;
					
					if(upFinish){
						break;
					}
				}
				
				if (actualMoves.containsKey(currentXposition)) {
					actualMoves.get(currentXposition).addAll(upperMoves);
				} else {
					actualMoves.put(currentXposition, upperMoves);
				}
				
				//down
				currentY=currentYposition+1;
				while(currentY<=RowNumber.eightRow.ordinal()){
					newMoveColor=square[currentY][currentXposition].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							downMoves.add(currentY);
							downFinish=true;
						}else{
							downFinish=true;
						}
					}else{
						downMoves.add(currentY);
					}
					currentY++;
						
					if(downFinish){
						break;
					}
				}
					
				if (actualMoves.containsKey(currentXposition)) {
					actualMoves.get(currentXposition).addAll(downMoves);
				} else {
					actualMoves.put(currentXposition, downMoves);
				}
				
				
				//Left moves
				currentY=currentYposition;
				for(int ii=currentXposition-1; ii>=0;ii--){
					newMoveColor=square[currentY][ii].getPieceColor();
					leftMoves=new ArrayList<Integer>();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							leftMoves.add(currentY);
							leftFinish=true;
						}else{
							leftFinish=true;
						}
					}else{
						leftMoves.add(currentY);
					}
					
					if (actualMoves.containsKey(ii)) {
						actualMoves.get(ii).addAll(leftMoves);
					} else {
						actualMoves.put(ii, leftMoves);
					}
					
					if(leftFinish){
						break;
					}
				}
					
				//Right moves
				currentY=currentYposition;
				for(int ii=currentXposition+1; ii<=ColumnNumber.eightColumn.ordinal();ii++){
					newMoveColor=square[currentY][ii].getPieceColor();
					rightMoves=new ArrayList<Integer>();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							rightMoves.add(currentY);
							rightFinish=true;
						}else{
							rightFinish=true;
						}
					}else{
						rightMoves.add(currentY);
					}
					
					if (actualMoves.containsKey(ii)) {
						actualMoves.get(ii).addAll(rightMoves);
					} else {
						actualMoves.put(ii, rightMoves);
					}
					
					if(rightFinish){
						break;
					}
				}
		
			break;
		
		case "Bishop":
			currentColor=square[currentYposition][currentXposition].getPieceColor();
			//left up
			currentY=currentYposition-1;
			for(int ii=currentXposition-1; ii>=0;ii--){
				while(currentY>=RowNumber.firstRow.ordinal()){
					upperLeftMoves=new ArrayList<Integer>();
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							upperLeftMoves.add(currentY);
							upperLeftFinish=true;
						}else{
							upperLeftFinish=true;
						}
					}else{
						upperLeftMoves.add(currentY);
					}
					currentY--;
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(upperLeftMoves);
				} else {
					actualMoves.put(ii, upperLeftMoves);
				}
				if(upperLeftFinish || currentY<0){
					break;
				}
			}	
			
					
			//left down
			currentY=currentYposition+1;
			for(int ii=currentXposition-1; ii>=0;ii--){
				while(currentY<=RowNumber.eightRow.ordinal()){
					downLeftMoves=new ArrayList<Integer>();
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							downLeftMoves.add(currentY);
							downLeftFinish=true;
						}else{
							downLeftFinish=true;
						}
					}else{
						downLeftMoves.add(currentY);
					}
					currentY++;
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(downLeftMoves);
				} else {
					actualMoves.put(ii, downLeftMoves);
				}
				if(downLeftFinish|| currentY>7){
					break;
				}
			}	
			
			
			//right up
			currentY=currentYposition-1;
			for(int ii=currentXposition+1; ii<=ColumnNumber.eightColumn.ordinal();ii++){
				upperRightMoves=new ArrayList<Integer>();
				while(currentY>=RowNumber.firstRow.ordinal()){
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							upperRightMoves.add(currentY);
							upRightFinish=true;
						}else{
							upRightFinish=true;
						}
					}else{
						upperRightMoves.add(currentY);
					}
					currentY--;
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(upperRightMoves);
				} else {
					actualMoves.put(ii, upperRightMoves);
				}
				if(upRightFinish|| currentY<0){
					break;
				}
			}
			
			
			//Right down
			currentY=currentYposition+1;
			for(int ii=currentXposition+1; ii<=7;ii++){
				while(currentY<=RowNumber.eightRow.ordinal()){
					downRightMoves=new ArrayList<Integer>();
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							downRightMoves.add(currentY);
							downRightFinish=true;
						}else{
							downRightFinish=true;
						}
					}else{
						downRightMoves.add(currentY);
					}
					currentY++;
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(downRightMoves);
				} else {
					actualMoves.put(ii, downRightMoves);
				}
				if(downRightFinish|| currentY>7){
					break;
				}
			}
			break;

		case "Queen":
			currentColor=square[currentYposition][currentXposition].getPieceColor();
			//up
			currentY=currentYposition-1;
				while(currentY>=RowNumber.firstRow.ordinal()){
					newMoveColor=square[currentY][currentXposition].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							upperMoves.add(currentY);
							upFinish=true;
						}else{
							upFinish=true;
						}
					}else{
						upperMoves.add(currentY);
					}
					currentY--;
					
					if(upFinish){
						break;
					}
				}
				
				if (actualMoves.containsKey(currentXposition)) {
					actualMoves.get(currentXposition).addAll(upperMoves);
				} else {
					actualMoves.put(currentXposition, upperMoves);
				}
				
				//down
				currentY=currentYposition+1;
				while(currentY<=RowNumber.eightRow.ordinal()){
					newMoveColor=square[currentY][currentXposition].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							downMoves.add(currentY);
							downFinish=true;
						}else{
							downFinish=true;
						}
					}else{
						downMoves.add(currentY);
					}
					currentY++;
						
					if(downFinish){
						break;
					}
				}
					
				if (actualMoves.containsKey(currentXposition)) {
					actualMoves.get(currentXposition).addAll(downMoves);
				} else {
					actualMoves.put(currentXposition, downMoves);
				}
				
				
				//Left moves
				currentY=currentYposition;
				for(int ii=currentXposition-1; ii>=0;ii--){
					newMoveColor=square[currentY][ii].getPieceColor();
					leftMoves=new ArrayList<Integer>();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							leftMoves.add(currentY);
							leftFinish=true;
						}else{
							leftFinish=true;
						}
					}else{
						leftMoves.add(currentY);
					}
					
					if (actualMoves.containsKey(ii)) {
						actualMoves.get(ii).addAll(leftMoves);
					} else {
						actualMoves.put(ii, leftMoves);
					}
					
					if(leftFinish){
						break;
					}
				}
					
				//Right moves
				currentY=currentYposition;
				for(int ii=currentXposition+1; ii<=ColumnNumber.eightColumn.ordinal();ii++){
					newMoveColor=square[currentY][ii].getPieceColor();
					rightMoves=new ArrayList<Integer>();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							rightMoves.add(currentY);
							rightFinish=true;
						}else{
							rightFinish=true;
						}
					}else{
						rightMoves.add(currentY);
					}
					
					if (actualMoves.containsKey(ii)) {
						actualMoves.get(ii).addAll(rightMoves);
					} else {
						actualMoves.put(ii, rightMoves);
					}
					
					if(rightFinish){
						break;
					}
				}
				
			//left up
			currentY=currentYposition-1;
			for(int ii=currentXposition-1; ii>=0;ii--){
				while(currentY>=RowNumber.firstRow.ordinal()){
					upperLeftMoves=new ArrayList<Integer>();
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							upperLeftMoves.add(currentY);
							upperLeftFinish=true;
						}else{
							upperLeftFinish=true;
						}
					}else{
						upperLeftMoves.add(currentY);
					}
					currentY--;
					
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(upperLeftMoves);
				} else {
					actualMoves.put(ii, upperLeftMoves);
				}
				if(upperLeftFinish || currentY < 0){
					break;
				}
			}	
			
					
			//left down
			currentY=currentYposition+1;
			for(int ii=currentXposition-1; ii>=0;ii--){
				while(currentY<=RowNumber.eightRow.ordinal()){
					downLeftMoves=new ArrayList<Integer>();
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							downLeftMoves.add(currentY);
							downLeftFinish=true;
						}else{
							downLeftFinish=true;
						}
					}else{
						downLeftMoves.add(currentY);
					}
					currentY++;
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(downLeftMoves);
				} else {
					actualMoves.put(ii, downLeftMoves);
				}
				if(downLeftFinish || currentY>7){
					break;
				}
			}	
			
			
			//right up
			currentY=currentYposition-1;
			for(int ii=currentXposition+1; ii<=ColumnNumber.eightColumn.ordinal();ii++){
				upperRightMoves=new ArrayList<Integer>();
				while(currentY>=RowNumber.firstRow.ordinal()){
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							upperRightMoves.add(currentY);
							upRightFinish=true;
						}else{
							upRightFinish=true;
						}
					}else{
						upperRightMoves.add(currentY);
					}
					currentY--;
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(upperRightMoves);
				} else {
					actualMoves.put(ii, upperRightMoves);
				}
				if(upRightFinish || currentY<0){
					break;
				}
			}
			
			
			//Right down
			currentY=currentYposition+1;
			for(int ii=currentXposition+1; ii<=7;ii++){
				while(currentY<=RowNumber.eightRow.ordinal()){
					downRightMoves=new ArrayList<Integer>();
					newMoveColor=square[currentY][ii].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							downRightMoves.add(currentY);
							downRightFinish=true;
						}else{
							downRightFinish=true;
						}
					}else{
						downRightMoves.add(currentY);
					}
					currentY++;
					break;
				}
				
				
				if (actualMoves.containsKey(ii)) {
					actualMoves.get(ii).addAll(downRightMoves);
				} else {
					actualMoves.put(ii, downRightMoves);
				}
				if(downRightFinish || currentY > 7){
					break;
				}
			}

			break;
		case "Pawn":
			currentX=currentXposition;
			if(currentColor.equalsIgnoreCase("white")){
				currentY=currentYposition-1;
				if(!checkYposition(currentY)){
					break;
				}
				if(currentYposition==RowNumber.seventhRow.ordinal()){
					pawnMove=2;
				}else{
					pawnMove=1;
				}
				
				while(pawnMove>0){
					newMoveColor=square[currentY][currentXposition].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							//upperMoves.add(currentY);
							pawnMove=-1;
						}else{
							pawnMove=-1;
						}
					}else{
						upperMoves.add(currentY);
						}
					pawnMove--;
					currentY--;
					if(!checkYposition(currentY)){
						break;
					}
				}
				
				if (actualMoves.containsKey(currentXposition)) {
					actualMoves.get(currentXposition).addAll(upperMoves);
				}else{
					actualMoves.put(currentXposition, upperMoves);
				}
				
				currentY=currentYposition-1;
				if(!checkYposition(currentY)){
					break;
				}
				
				if(currentXposition>ColumnNumber.firstColumn.ordinal() && currentXposition<ColumnNumber.eightColumn.ordinal()){
					pawnMove=3;
					currentX=currentXposition-1;
				}else if(currentXposition==ColumnNumber.firstColumn.ordinal()){
					pawnMove=2;
					currentX=currentXposition;
				}else if(currentXposition==ColumnNumber.eightColumn.ordinal()){
					pawnMove=2;
					currentX=currentXposition-1;
				}
				
				while(pawnMove>0){
					newMoveColor=square[currentY][currentX].getPieceColor();
					upperMoves=new ArrayList<Integer>();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							if(currentX!=currentXposition){
								upperMoves.add(currentY);
							}
						}
					}
					
					if (actualMoves.containsKey(currentX)) {
						actualMoves.get(currentX).addAll(upperMoves);
					}else{
						actualMoves.put(currentX, upperMoves);
					}
					pawnMove--;
					currentX++;
					
					if(!checkYposition(currentY)){
						break;
					}
				}
			
			}else if(currentColor.equalsIgnoreCase("black")){
				currentY=currentYposition+1;
				if(!checkYposition(currentY)){
					break;
				}
				if(currentYposition==RowNumber.secondRow.ordinal()){
					pawnMove=2;
				}else{
					pawnMove=1;
				}
				
				while(pawnMove>0){
					newMoveColor=square[currentY][currentXposition].getPieceColor();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
						//	downMoves.add(currentY);
							pawnMove=-1;
						}else{
							pawnMove=-1;
						}
					}else{
						downMoves.add(currentY);
						}
					pawnMove--;
					currentY++;
					if(!checkYposition(currentY)){
						break;
					}
				}
				
				if (actualMoves.containsKey(currentXposition)) {
					actualMoves.get(currentXposition).addAll(downMoves);
				}else{
					actualMoves.put(currentXposition, downMoves);
				}
				
				
				
				currentY=currentYposition+1;
				if(!checkYposition(currentY)){
					break;
				}
				
				if(currentXposition>ColumnNumber.firstColumn.ordinal() && currentXposition<ColumnNumber.eightColumn.ordinal()){
					pawnMove=3;
					currentX=currentXposition-1;
				}else if(currentXposition==ColumnNumber.firstColumn.ordinal()){
					pawnMove=2;
					currentX=currentXposition;
				}else if(currentXposition==ColumnNumber.eightColumn.ordinal()){
					pawnMove=2;
					currentX=currentXposition-1;
				}
				
				
				while(pawnMove>0){
					newMoveColor=square[currentY][currentX].getPieceColor();
					downMoves=new ArrayList<Integer>();
					if(newMoveColor!=null){
						if(!currentColor.equalsIgnoreCase(newMoveColor)){
							if(currentX!=currentXposition){
								downMoves.add(currentY);
							}
						}
					}
					
					if (actualMoves.containsKey(currentX)) {
						actualMoves.get(currentX).addAll(downMoves);
					}else{
						actualMoves.put(currentX, downMoves);
					}
					pawnMove--;
					currentX++;
					if(!checkYposition(currentY)){
						break;
					}
				}
			}
			
			break;
		case "King":
			
			possibleMoves=new HashMap<Integer,ArrayList<Integer>>();
			yMoves=new ArrayList<Integer>();
			
			yMoves.add(currentYposition);
			yMoves.add(currentYposition+1);
			yMoves.add(currentYposition-1);
			
			if(possibleMoves.containsKey(currentXposition+1)){
				possibleMoves.get(currentXposition+1).addAll(yMoves);
			}else{
				possibleMoves.put(currentXposition+1, yMoves);
			}
			
			if(possibleMoves.containsKey(currentXposition-1)){
				possibleMoves.get(currentXposition-1).addAll(yMoves);
			}else{
				possibleMoves.put(currentXposition-1, yMoves);
			}
			
			yMoves=new ArrayList<Integer>();
			yMoves.add(currentYposition+1);
			yMoves.add(currentYposition-1);
			
			if(possibleMoves.containsKey(currentXposition)){
				possibleMoves.get(currentXposition).addAll(yMoves);
			}else{
				possibleMoves.put(currentXposition, yMoves);
			}
		
			for(Map.Entry<Integer, ArrayList<Integer>> listEntry : possibleMoves.entrySet()){
				xValues=listEntry.getKey();
				downMoves=new ArrayList<Integer>();
				if(ColumnNumber.firstColumn.ordinal() > xValues || xValues > ColumnNumber.eightColumn.ordinal()){
					continue;
				}
				// iterating over a list
				for(int yValues : listEntry.getValue()){
					if(ColumnNumber.firstColumn.ordinal() <= xValues && xValues <= ColumnNumber.eightColumn.ordinal() && RowNumber.firstRow.ordinal()<=yValues && yValues<=RowNumber.eightRow.ordinal()){
						newMoveColor=square[yValues][xValues].getPieceColor();
						if(newMoveColor!=null){
							if(!currentColor.equalsIgnoreCase(newMoveColor)){
								downMoves.add(yValues);
							}
						}else{
							downMoves.add(yValues);
						}
					}
				}
				if (actualMoves.containsKey(xValues)) {
					actualMoves.get(xValues).addAll(downMoves);
				} else {
					actualMoves.put(xValues, downMoves);
				}
			}
			
			break;
		
		default :
			//System.out.println("nothing is there man");	
		}
		return actualMoves;
	}	
	
	
	//check the yposition is valid or not
	private boolean checkYposition(int y){
		
		if(y>=RowNumber.firstRow.ordinal()&&y<=RowNumber.eightRow.ordinal()){
			return true;
		}else{
			return false;
		}
	}
	
		
}	