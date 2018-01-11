/*This class extends Jframe and put together pieces and Hint button to prepare final board.
 * It also contains chessHint class which implements Runnable interface.
 * 
 * Functions:
 * ChessRun()
 * calculateOptimulMovesForWhite()
 * calculateOptimulMovesForBlack()
 * checkForHint()
 * letsplay()
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.util.resources.cldr.ur.CurrencyNames_ur;

public class ChessRun extends JFrame {

	//Objects for class board and HintButton
	private Board board ;
	private ButtonHint hintbutton;
	
	private Square[][] square;
	private JPanel chessGamePanel;
	
	//variables keep track of moves
	private Map<Integer, ArrayList<Integer>> actualMoves ;
	
	//shared containers among the threads
	private ArrayList<Map<Integer, ArrayList<Integer>>> totalMoves = new ArrayList<>();
	private ArrayList<Integer> totalXposition = new ArrayList<>();
	private ArrayList<Integer> totalYposition = new ArrayList<>();
		
	//constructor
	public ChessRun() {
		setVisible(true);
		pack();
		board = new Board();
		actualMoves = new HashMap<Integer, ArrayList<Integer>>();
		hintbutton = new ButtonHint();
		square = new Square[8][8];
		chessGamePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 2.0;
		gbc.weighty = 2.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		chessGamePanel.add(board, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		chessGamePanel.add(hintbutton, gbc);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(chessGamePanel);

	}

	//ChessHint class which implements Runnable interface
	public class ChessHint implements Runnable {

		private int currentX;
		private int currentY;
		
		//constructor
		public ChessHint(int x, int y) {
			// TODO Auto-generated constructor stub
			currentX = x;
			currentY = y;
		}

		//run method which collect the moves and put in to shared container.
		@Override
		public void run() {
			Moves.lock();
			actualMoves = board.actualMoves(currentX, currentY, true);
			totalMoves.add(actualMoves);
			totalXposition.add(currentX);
			totalYposition.add(currentY);
			Moves.unlock();
			
		}
	}

	//This function will calculate optimum moves for white pieces
	private void calculateOptimulMovesForWhite(){
		int ii;
		int xPosition;
		int yPosition;
		boolean capture=false;
		int xValues;
		int xMax=0;
		int yMax=0;
		int xDistance;
		int yDistance;
		String currentColor;
		ArrayList<MoveInHint> listMoves=new ArrayList<MoveInHint>();
		MoveInHint mov=new MoveInHint();
		Map<Integer, ArrayList<Integer>> dummyMoves = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> yMoves = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> optimizedMoves = new HashMap<Integer, ArrayList<Integer>>();
		
		
		Map<Integer, ArrayList<Integer>> movesFirstTie = new HashMap<Integer, ArrayList<Integer>>();
		for (ii = 0; ii < totalMoves.size(); ii++) {
			movesFirstTie=totalMoves.get(ii);
			xPosition=totalXposition.get(ii);
			yPosition=totalYposition.get(ii); 
			for (Map.Entry<Integer, ArrayList<Integer>> listEntry : movesFirstTie.entrySet()) {
				xValues = listEntry.getKey();
				yMoves = new ArrayList<Integer>();
				// iterating over a list
				for (int yValues : listEntry.getValue()) {
					mov=new MoveInHint();
					xMax=xValues;
					yMax=yValues;
					currentColor=square[yMax][xMax].getPieceColor();
					if(currentColor!=null){
						if(!currentColor.equalsIgnoreCase("white")){
							capture=true;
						}
					}else{
						capture=false;
					}
					if(xMax>xPosition){
						xDistance=xMax-xPosition;
					}else{
						xDistance=0;
					}
					if(yMax<yPosition){
						yDistance=yPosition-yMax;
					}else{
						yDistance=0;
					}
					mov.setMaxX(xMax);
					mov.setMaxY(yMax);
					mov.setCapture(capture);
					mov.setxDistance(xDistance);
					mov.setyDistance(yDistance);
					mov.setxPosition(xPosition);
					mov.setyPosition(yPosition);
					
					listMoves.add(mov);
				}
			}
				
		}
		
		listMoves.sort((MoveInHint h1, MoveInHint h2) -> {
		    int compare = Boolean.compare(h2.isCapture(), h1.isCapture());
		    if (compare == 0) {
		        compare = Integer.compare(h2.getMaxX(), h1.getMaxX());
		    }
		    if (compare == 0) {
		        compare = Integer.compare(h1.getMaxY(), h2.getMaxY());
		    }
		    if (compare == 0) {
		        compare = Integer.compare(h2.getxDistance(), h1.getxDistance());
		    }
		    if (compare == 0) {
		        compare = Integer.compare(h2.getyDistance(), h1.getyDistance());
		    }
		    return compare;
		});
		if(!listMoves.isEmpty()){
			//System.out.println(listMoves.get(0));
			yMoves = new ArrayList<Integer>();
			xValues=listMoves.get(0).getMaxX();
			yMoves.add(listMoves.get(0).getMaxY());
			xPosition=listMoves.get(0).getxPosition();
			yPosition=listMoves.get(0).getyPosition();
			
			optimizedMoves.put(xValues, yMoves);
			
			board.setSquare(xPosition, yPosition);
			board.setOptimizedMoves(optimizedMoves);
			board.dohighlight(optimizedMoves, true);
		}else{
			//System.out.println("list is empty");
		}
		
		totalXposition = new ArrayList<>();
		totalYposition = new ArrayList<>();
		totalMoves = new ArrayList<>();
	}
		

	//This function will calculate optimum moves for black pieces
	private void calculateOptimulMovesForBlack(){
		int ii;
		int xPosition;
		int yPosition;
		boolean capture=false;
		int xValues;
		int xMax=0;
		int yMax=0;
		int xDistance;
		int yDistance;
		String currentColor;
		ArrayList<MoveInHint> listMoves=new ArrayList<MoveInHint>();
		MoveInHint mov=new MoveInHint();
		Map<Integer, ArrayList<Integer>> dummyMoves = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> yMoves = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> optimizedMoves = new HashMap<Integer, ArrayList<Integer>>();
		
		
		Map<Integer, ArrayList<Integer>> movesFirstTie = new HashMap<Integer, ArrayList<Integer>>();
		for (ii = 0; ii < totalMoves.size(); ii++) {
			movesFirstTie=totalMoves.get(ii);
			xPosition=totalXposition.get(ii);
			yPosition=totalYposition.get(ii); 
			for (Map.Entry<Integer, ArrayList<Integer>> listEntry : movesFirstTie.entrySet()) {
				xValues = listEntry.getKey();
				yMoves = new ArrayList<Integer>();
				// iterating over a list
				for (int yValues : listEntry.getValue()) {
					mov=new MoveInHint();
					xMax=xValues;
					yMax=yValues;
					currentColor=square[yMax][xMax].getPieceColor();
					if(currentColor!=null){
						if(!currentColor.equalsIgnoreCase("black")){
							capture=true;
						}
					}else{
						capture=false;
					}
					if(xMax<xPosition){
						xDistance=xPosition-xMax;
					}else{
						xDistance=0;
					}
					if(yMax>yPosition){
						yDistance=yMax-yPosition;
					}else{
						yDistance=0;
					}
					mov.setMaxX(xMax);
					mov.setMaxY(yMax);
					mov.setCapture(capture);
					mov.setxDistance(xDistance);
					mov.setyDistance(yDistance);
					mov.setxPosition(xPosition);
					mov.setyPosition(yPosition);
					
					listMoves.add(mov);
				}
			}
				
		}
		
		listMoves.sort((MoveInHint h1, MoveInHint h2) -> {
		    int compare = Boolean.compare(h2.isCapture(), h1.isCapture());
		    if (compare == 0) {
		        compare = Integer.compare(h1.getMaxX(), h2.getMaxX());
		    }
		    if (compare == 0) {
		        compare = Integer.compare(h2.getMaxY(), h1.getMaxY());
		    }
		    if (compare == 0) {
		        compare = Integer.compare(h2.getxDistance(), h1.getxDistance());
		    }
		    if (compare == 0) {
		        compare = Integer.compare(h2.getyDistance(), h1.getyDistance());
		    }
		    return compare;
		});
		if(!listMoves.isEmpty()){
			//System.out.println(listMoves.get(0));
			yMoves = new ArrayList<Integer>();
			xValues=listMoves.get(0).getMaxX();
			yMoves.add(listMoves.get(0).getMaxY());
			xPosition=listMoves.get(0).getxPosition();
			yPosition=listMoves.get(0).getyPosition();
			
			optimizedMoves.put(xValues, yMoves);
			
			board.setSquare(xPosition, yPosition);
			board.setOptimizedMoves(optimizedMoves);
			board.dohighlight(optimizedMoves, true);
		}else{
			//System.out.println("list is empty");
		}
		
		totalXposition = new ArrayList<>();
		totalYposition = new ArrayList<>();
		totalMoves = new ArrayList<>();
			
	}
	
	
	//It will check whether hint button pressed or not and generate thread accordingly. 
	public void checkForHint() {
		String color;
		int currentXposition;
		int currentYposition;
		ArrayList<Thread> threadList = new ArrayList<>();
		if (hintbutton.checkButtonClicked()) {
			square = board.getCurrentPosition();
			hintbutton.unClickButton();
			if (board.isWhiteTurn()) {
				for (RowNumber rowNumber : RowNumber.values()) {
					for (ColumnNumber columnNumber : ColumnNumber.values()) {
						currentXposition = columnNumber.ordinal();
						currentYposition = rowNumber.ordinal();
						 color=square[currentYposition][currentXposition].getPieceColor();
						if (color != null) {
							if (color.equalsIgnoreCase("white")) {
								Thread counterThread = new Thread(new ChessHint(currentXposition, currentYposition));
								threadList.add(counterThread);
								counterThread.start();

							} else {
								continue;
							}
						}

					}
				}
				
				for (Thread thread : threadList) {
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}else if(board.isBlackTurn()){
				for (RowNumber rowNumber : RowNumber.values()) {
					for (ColumnNumber columnNumber : ColumnNumber.values()) {
						currentXposition = columnNumber.ordinal();
						currentYposition = rowNumber.ordinal();
						 color=square[currentYposition][currentXposition].getPieceColor();
						//piece = square[currentYposition][currentXposition].getPiece();
						if (color != null) {
							if (color.equalsIgnoreCase("black")) {
								Thread counterThread = new Thread(new ChessHint(currentXposition, currentYposition));
								threadList.add(counterThread);
								counterThread.start();

							} else {
								continue;
							}
						}

					}
				}
				for (Thread thread : threadList) {
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}


			if(board.isWhiteTurn()){
				calculateOptimulMovesForWhite();
			}else if(board.isBlackTurn()){
				calculateOptimulMovesForBlack();
			}
		}
	}

	//This function will run the game and call checkforhint function
	public void letsplay() {
		
		hintbutton.buttonClicked();

		while (true) {
			board.startGame();
			checkForHint();
		}
	}

}
