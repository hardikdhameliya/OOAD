/*This class contains main method and has object of ChessRun class
 * It will call letsplay method to start the game.
 * 
 */

import java.util.*;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class ChessGame {

	
	public static void main(String[] args) {
		 ChessRun myChessGame=new ChessRun();
		 
		 myChessGame.checkForHint();	
		 myChessGame.letsplay();
		 
		 
	}
}
