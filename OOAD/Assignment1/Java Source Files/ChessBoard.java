import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class ChessBoard {

	private final String INPUT_FILE = "e:/Byrne_v_Fischer.txt";
	private final int KINGCASTLING = 1111;
	private final int QUEENCASTLING = 5555;
	private final int EOF = 9999;
	private final int TOTAL_COLUMN = 8;
	private final int TOTAL_ROW = 8;

	private String[] black_move;
	private String[] white_move;
	private int lineNumber;
	private boolean whiteMoveFlag;

	private JFrame mychessBoard;
	private JButton[][] chessBoardSquares;
	private JButton prevButton;
	private JButton nextButton;
	private JPanel chessGamePanel;
	private JPanel buttonPanel;
	private JPanel myBoardPanel;

	private LinkedList<Icon> previousMoves;
	private LinkedList<Integer> column;
	private LinkedList<Integer> rows;

	private Components comp;

	public ChessBoard() {

		lineNumber = 0;
		whiteMoveFlag = true;

		black_move = new String[3];
		white_move = new String[3];

		previousMoves = new LinkedList<Icon>();
		column = new LinkedList<Integer>();
		rows = new LinkedList<Integer>();

		mychessBoard = new JFrame("Chess Game");
		chessBoardSquares = new JButton[TOTAL_COLUMN][TOTAL_ROW];
		prevButton = new JButton("<-Prev");
		nextButton = new JButton("Next->");
		chessGamePanel = new JPanel(new GridBagLayout());
		buttonPanel = new JPanel();
		myBoardPanel = new JPanel();

		comp = new Components();

	}

	/*
	 * This function will initialize gui including chess-board, place all the
	 * components and set the game to begin
	 */
	public void chessGui() {

		mychessBoard.setVisible(true);
		// mychessBoard.setLocationRelativeTo(null);
		mychessBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 2.0;
		gbc.weighty = 2.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;

		GridLayout chessLayout = new GridLayout(0, TOTAL_ROW);
		myBoardPanel.setLayout(chessLayout);
		chessGamePanel.add(myBoardPanel, gbc);

		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {

				String mystring = Integer.toString(ii + jj);

				JButton tempButton = new JButton();

				if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
					tempButton.setBackground(Color.WHITE);
					tempButton.setText(mystring);

				} else {
					tempButton.setBackground(Color.GREEN);
					tempButton.setText(mystring);
				}

				if (ii == 0) {
					switch (jj) {
					case 0:
						tempButton.setIcon(comp.getBlack_rook());
						break;
					case 1:
						tempButton.setIcon(comp.getBlack_knight());
						break;
					case 2:
						tempButton.setIcon(comp.getBlack_bishop());
						break;
					case 3:
						tempButton.setIcon(comp.getBlack_queen());
						break;
					case 4:
						tempButton.setIcon(comp.getBlack_king());
						break;
					case 5:
						tempButton.setIcon(comp.getBlack_bishop());
						break;
					case 6:
						tempButton.setIcon(comp.getBlack_knight());
						break;
					case 7:
						tempButton.setIcon(comp.getBlack_rook());
						break;
					}
				}

				if (ii == 1) {
					tempButton.setIcon(comp.getBlack_pawn());
				}

				if (ii == 7) {
					switch (jj) {
					case 0:
						tempButton.setIcon(comp.getWhite_rook());
						break;
					case 1:
						tempButton.setIcon(comp.getWhite_knight());
						break;
					case 2:
						tempButton.setIcon(comp.getWhite_bishop());
						break;
					case 3:
						tempButton.setIcon(comp.getWhite_queen());
						break;
					case 4:
						tempButton.setIcon(comp.getWhite_king());
						break;
					case 5:
						tempButton.setIcon(comp.getWhite_bishop());
						break;
					case 6:
						tempButton.setIcon(comp.getWhite_knight());
						break;
					case 7:
						tempButton.setIcon(comp.getWhite_rook());
						break;
					}
				}

				if (ii == 6) {
					tempButton.setIcon(comp.getWhite_pawn());
				}

				chessBoardSquares[ii][jj] = tempButton;
				myBoardPanel.add(chessBoardSquares[ii][jj]);
			}

		}

		GridBagLayout buttonlayout = new GridBagLayout();
		buttonPanel.setLayout(buttonlayout);
		buttonPanel.add(prevButton);

		prevButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (lineNumber > 0) {
					whiteMoveFlag = reversePositions(whiteMoveFlag);
					if (whiteMoveFlag) {
						lineNumber--;
					}

				}
			}
		});
		buttonPanel.add(nextButton);
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lineNumber >= 0) {

					ImageIcon myIcon = new ImageIcon();

					// whiteMoveflag true means next turn is for White and if it
					// is false than next turn is for Black.
					if (whiteMoveFlag) {
						int i = 0;
						if (readFile(lineNumber, "next") != EOF) {
							lineNumber++;
							System.out.println("linenumber is" + lineNumber);

							for (String st : white_move) {
								if (st != null) {
									if (st.equals("O")) {
										i++;
									} else {
										myIcon = checkForPromotion(st, whiteMoveFlag);
									}
								}
							}
							if (i == 3) {
								queenSideCastling("white");
							} else if (i == 2) {
								kingSideCastling("white");
							} else if (myIcon != null) {
								pawmPromotion(white_move, myIcon, whiteMoveFlag);
							} else {
								whiteMoveFlag = nextPositions(white_move, whiteMoveFlag);
							}
							whiteMoveFlag = false;
						}
					} else {
						int i = 0;
						for (String st : black_move) {
							if (st != null) {
								if (st.equals("O")) {
									i++;
								} else {
									myIcon = checkForPromotion(st, whiteMoveFlag);
								}
							}
						}
						if (i == 3) {
							queenSideCastling("black");
						} else if (i == 2) {
							kingSideCastling("black");
						} else if (myIcon != null) {
							pawmPromotion(black_move, myIcon, whiteMoveFlag);
						} else {
							whiteMoveFlag = nextPositions(black_move, whiteMoveFlag);
						}
						whiteMoveFlag = true;
					}

					System.out.println(white_move[0] + white_move[1] + black_move[0] + black_move[1]);
				}

			}
		});

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		chessGamePanel.add(buttonPanel, gbc);

		mychessBoard.add(chessGamePanel);

		mychessBoard.pack();
	}

	/*
	 * Check whether the movement of pawn is pawn promotion or not. Inputs to
	 * function is moves given in the input file and side of the
	 * board(black/white)
	 */

	public ImageIcon checkForPromotion(String inputString, boolean whiteMove) {

		ImageIcon promotedIcon = new ImageIcon();

		if (whiteMove) {
			switch (inputString) {
			case "Q":
				promotedIcon = comp.getWhite_queen();
				break;
			case "N":
				promotedIcon = comp.getWhite_knight();
				break;
			case "B":
				promotedIcon = comp.getWhite_bishop();
				break;
			case "R":
				promotedIcon = comp.getWhite_rook();
				break;
			default:
				promotedIcon = null;
			}

		} else {
			switch (inputString) {
			case "Q":
				promotedIcon = comp.getBlack_queen();
				break;
			case "N":
				promotedIcon = comp.getBlack_knight();
				break;
			case "B":
				promotedIcon = comp.getBlack_bishop();
				break;
			case "R":
				promotedIcon = comp.getBlack_rook();
				break;
			default:
				promotedIcon = null;
			}

		}
		return promotedIcon;
	}

	/*
	 * This function will move components(White/Black) to next position. It will
	 * called when next button is pressed Input to function is string containing
	 * the moves and position-flag which indicate whose turn is next
	 */
	public boolean nextPositions(String[] compMove, boolean positionFlag) {

		char[] charArray = compMove[0].toCharArray();
		int previous_row = Character.getNumericValue(charArray[0]) - 10;
		int previous_column = 7 - (Character.getNumericValue(charArray[1]) - 1);

		charArray = compMove[1].toCharArray();
		int next_row = Character.getNumericValue(charArray[0]) - 10;
		int next_column = 7 - (Character.getNumericValue(charArray[1]) - 1);

		JButton previous_Jbutton = new JButton();
		JButton next_Jbutton = new JButton();

		previousMoves.addLast(chessBoardSquares[previous_column][previous_row].getIcon());
		previous_Jbutton = chessBoardSquares[previous_column][previous_row];
		column.addLast(previous_column);
		rows.addLast(previous_row);

		System.out.println("added" + previous_Jbutton.getIcon() + " column" + previous_column + " rows" + previous_row);

		previousMoves.addLast(chessBoardSquares[next_column][next_row].getIcon());
		next_Jbutton = chessBoardSquares[next_column][next_row];
		column.addLast(next_column);
		rows.addLast(next_row);

		System.out.println("added" + previousMoves.getLast() + " column" + next_column + " rows" + next_row);

		next_Jbutton.setIcon(previous_Jbutton.getIcon());
		System.out.println("last icon" + previousMoves.getLast());

		previous_Jbutton.setIcon(null);

		return !positionFlag;

	}

	/*
	 * This function will promot the pawn to either queen, bishoop, rook or
	 * knight. Input to function is string of moves, Image in which pawn will
	 * convert to and positionflag indicating whose turn is next
	 */
	public boolean pawmPromotion(String[] compMoves, ImageIcon myIcon, boolean positionFlag) {

		char[] charArray = compMoves[0].toCharArray();
		int previous_row = Character.getNumericValue(charArray[0]) - 10;
		int previous_column = 7 - (Character.getNumericValue(charArray[1]) - 1);

		charArray = compMoves[1].toCharArray();
		int next_row = Character.getNumericValue(charArray[0]) - 10;
		int next_column = 7 - (Character.getNumericValue(charArray[1]) - 1);

		JButton previous_Jbutton = new JButton();
		JButton next_Jbutton = new JButton();

		previousMoves.addLast(chessBoardSquares[previous_column][previous_row].getIcon());
		previous_Jbutton = chessBoardSquares[previous_column][previous_row];
		column.addLast(previous_column);
		rows.addLast(previous_row);

		System.out.println("added" + previous_Jbutton.getIcon() + " column" + previous_column + " rows" + previous_row);

		previousMoves.addLast(chessBoardSquares[next_column][next_row].getIcon());
		next_Jbutton = chessBoardSquares[next_column][next_row];
		column.addLast(next_column);
		rows.addLast(next_row);

		System.out.println("added" + previousMoves.getLast() + " column" + next_column + " rows" + next_row);

		next_Jbutton.setIcon(myIcon);
		System.out.println("last icon" + previousMoves.getLast());

		previous_Jbutton.setIcon(null);

		return !positionFlag;

	}

	/*
	 * This function will do backward move when prev button presses and will
	 * take position flag indicating whose turn is next
	 */
	public boolean reversePositions(boolean positionFlag) {

		JButton previous_Jbutton = new JButton();
		JButton next_Jbutton = new JButton();

		int previous_row;
		int previous_column;
		int next_row;
		int next_column;

		previous_row = rows.getLast();

		if (previous_row != KINGCASTLING && previous_row != QUEENCASTLING) {
			previous_column = column.getLast();
			rows.removeLast();
			column.removeLast();

			next_row = rows.getLast();
			next_column = column.getLast();
			rows.removeLast();
			column.removeLast();

			previous_Jbutton.setIcon(previousMoves.getLast());
			previousMoves.removeLast();
			System.out.println("Removed  " + previous_Jbutton.getIcon() + " previous_column" + previous_column
					+ " previous_rows" + previous_row);
			chessBoardSquares[previous_column][previous_row].setIcon(previous_Jbutton.getIcon());

			next_Jbutton.setIcon(previousMoves.getLast());
			previousMoves.removeLast();
			System.out.println(
					"Removed " + next_Jbutton.getIcon() + " next_column" + next_column + " next_rows" + next_row);
			chessBoardSquares[next_column][next_row].setIcon(next_Jbutton.getIcon());

		} else {
			rows.removeLast();
			previous_row = rows.getLast();
			previous_column = column.getLast();
			rows.removeLast();
			column.removeLast();

			next_row = rows.getLast();
			next_column = column.getLast();
			rows.removeLast();
			column.removeLast();

			previous_Jbutton.setIcon(previousMoves.getLast());
			previousMoves.removeLast();
			System.out.println("Removed  " + previous_Jbutton.getIcon() + " previous_column" + previous_column
					+ " previous_rows" + previous_row);
			chessBoardSquares[previous_column][previous_row].setIcon(previous_Jbutton.getIcon());

			next_Jbutton.setIcon(previousMoves.getLast());
			previousMoves.removeLast();
			System.out.println(
					"Removed " + next_Jbutton.getIcon() + " next_column" + next_column + " next_rows" + next_row);
			chessBoardSquares[next_column][next_row].setIcon(next_Jbutton.getIcon());

			previous_row = rows.getLast();
			previous_column = column.getLast();
			rows.removeLast();
			column.removeLast();

			next_row = rows.getLast();
			next_column = column.getLast();
			rows.removeLast();
			column.removeLast();

			previous_Jbutton.setIcon(previousMoves.getLast());
			previousMoves.removeLast();
			System.out.println("Removed  " + previous_Jbutton.getIcon() + " previous_column" + previous_column
					+ " previous_rows" + previous_row);
			chessBoardSquares[previous_column][previous_row].setIcon(previous_Jbutton.getIcon());
			// System.out.println("last
			// icon"+previousMoves.getLast().getIcon());

			next_Jbutton.setIcon(previousMoves.getLast());
			previousMoves.removeLast();
			System.out.println(
					"Removed " + next_Jbutton.getIcon() + " next_column" + next_column + " next_rows" + next_row);
			chessBoardSquares[next_column][next_row].setIcon(next_Jbutton.getIcon());

		}

		return !positionFlag;

	}

	/*
	 * This function will do queen side castling and take side(white or black)
	 * as input parameter
	 */
	public void queenSideCastling(String side) {

		int kingColumnNumber = 0;
		int rookColumnNumber = 0;
		int compRowNumber = 0;
		JButton previous_Jbutton = new JButton();
		JButton next_Jbutton = new JButton();

		if (side == "white") {
			compRowNumber = 7;
			kingColumnNumber = 4;
			rookColumnNumber = 0;

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber];
			previousMoves.addLast(previous_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + kingColumnNumber);

			previousMoves.addLast(chessBoardSquares[compRowNumber][kingColumnNumber - 2].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber - 2);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + (kingColumnNumber - 2));

			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber];

			previousMoves.addLast(next_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber);
			System.out.println("added" + previousMoves.getLast());

			previousMoves.addLast(chessBoardSquares[compRowNumber][rookColumnNumber + 3].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber + 3);
			System.out.println("added" + previousMoves.getLast());

			chessBoardSquares[compRowNumber][kingColumnNumber].setIcon(null);
			chessBoardSquares[compRowNumber][rookColumnNumber].setIcon(null);

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber - 2];
			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber + 3];

			next_Jbutton.setIcon(comp.getWhite_rook());
			previous_Jbutton.setIcon(comp.getWhite_king());

		} else if (side == "black") {

			compRowNumber = 0;
			kingColumnNumber = 4;
			rookColumnNumber = 0;

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber];
			previousMoves.addLast(previous_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber);

			previousMoves.addLast(chessBoardSquares[compRowNumber][kingColumnNumber - 2].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber - 2);

			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber];
			previousMoves.addLast(next_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber);

			previousMoves.addLast(chessBoardSquares[compRowNumber][rookColumnNumber + 3].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber + 3);

			chessBoardSquares[compRowNumber][kingColumnNumber].setIcon(null);
			chessBoardSquares[compRowNumber][rookColumnNumber].setIcon(null);

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber - 2];
			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber + 3];

			next_Jbutton.setIcon(comp.getBlack_rook());
			previous_Jbutton.setIcon(comp.getBlack_king());

		}

		rows.addLast(QUEENCASTLING);
	}

	/*
	 * This function will do queen side castling and take side(white or black)
	 * as input parameter
	 */

	public void kingSideCastling(String side) {
		int kingColumnNumber = 0;
		int rookColumnNumber = 0;
		int compRowNumber = 0;
		JButton previous_Jbutton = new JButton();
		JButton next_Jbutton = new JButton();

		if (side == "white") {
			compRowNumber = 7;
			kingColumnNumber = 4;
			rookColumnNumber = 7;

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber];
			previousMoves.addLast(previous_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + kingColumnNumber);

			previousMoves.addLast(chessBoardSquares[compRowNumber][kingColumnNumber + 2].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber + 2);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + (kingColumnNumber + 2));

			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber];
			previousMoves.addLast(next_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + (rookColumnNumber));

			previousMoves.addLast(chessBoardSquares[compRowNumber][kingColumnNumber - 2].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber - 2);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + (rookColumnNumber - 2));

			chessBoardSquares[compRowNumber][kingColumnNumber].setIcon(null);
			chessBoardSquares[compRowNumber][rookColumnNumber].setIcon(null);

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber + 2];
			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber - 2];

			next_Jbutton.setIcon(comp.getWhite_rook());
			previous_Jbutton.setIcon(comp.getWhite_king());

		} else if (side == "black") {

			compRowNumber = 0;
			kingColumnNumber = 4;
			rookColumnNumber = 7;

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber];
			previousMoves.addLast(previous_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + kingColumnNumber);

			previousMoves.addLast(chessBoardSquares[compRowNumber][kingColumnNumber + 2].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(kingColumnNumber + 2);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + (kingColumnNumber + 2));

			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber];
			previousMoves.addLast(next_Jbutton.getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + rookColumnNumber);

			previousMoves.addLast(chessBoardSquares[compRowNumber][rookColumnNumber - 2].getIcon());
			column.addLast(compRowNumber);
			rows.addLast(rookColumnNumber - 2);
			System.out.println("added" + previousMoves.getLast() + " " + compRowNumber + " " + (rookColumnNumber - 2));

			chessBoardSquares[compRowNumber][kingColumnNumber].setIcon(null);
			chessBoardSquares[compRowNumber][rookColumnNumber].setIcon(null);

			previous_Jbutton = chessBoardSquares[compRowNumber][kingColumnNumber + 2];
			next_Jbutton = chessBoardSquares[compRowNumber][rookColumnNumber - 2];

			next_Jbutton.setIcon(comp.getBlack_rook());
			previous_Jbutton.setIcon(comp.getBlack_king());

		}

		rows.addLast(KINGCASTLING);
	}

	/* This function will return Jframe of current frame */
	public JFrame chessboard() {
		return mychessBoard;
	}

	/* This function will initialize gui */
	public void playGame() {

		chessGui();
	}

	/*
	 * This function will read the file from given linunumber and will return
	 * currentlinenumber or End of File indication
	 */
	public int readFile(int lineNumber, String sequence) {
		BufferedReader br = null;
		String line = null;
		String spacesplitby = " ";
		String dashsplitby = "\\W+";
		int currentLineNumber = 0;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
		} catch (IOException e) {
			// Exception in case file not found, just carry on..
		}

		try {

			while ((line = br.readLine()) != null) {
				int index = 0;
				if (line.length() != 0) {
					if (currentLineNumber == lineNumber) {

						String[] totalMoves = line.split(spacesplitby);
						String[] move = totalMoves[0].split(dashsplitby);

						for (String string : move) {
							white_move[index] = string;
							index++;
						}

						String[] newMove = totalMoves[1].split(dashsplitby);
						index = 0;

						for (String newstring : newMove) {
							black_move[index] = newstring;
							index++;
						}
						break;
					}
					currentLineNumber++;

				}
			}
			System.out.println("last line" + currentLineNumber);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (line == null) {
			return EOF;
		}
		return currentLineNumber;
	}

}
