/* This class is for Hint button and related functionlity.
 * When hint button is clicked, it will set the flag.
 * 
 * 
 * Function
 * ButtonHint()
 * void buttonClicked()
 * boolean checkButtonClicked()
 * void unClickButton()
 * 
 */

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonHint extends JPanel{

	//hint button related variables
	private JButton hintButton;
	private boolean hintButtonClicked;
	
	//constructor
	public ButtonHint() {
		super();
		hintButton = new JButton("Hint");
		hintButtonClicked=false;
		setLayout(new GridBagLayout());
		add(hintButton);
	}
	
	//set the flag when hint button is clicked
	public void buttonClicked(){
		hintButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hintButtonClicked=true;
				//System.out.println("hint clikced");
			}
		});
	}
	
	//Returns whether hint button is clicked or not
	public boolean checkButtonClicked(){
		return hintButtonClicked;
	}
	
	//Reset the flag
	public void unClickButton(){
		hintButtonClicked=false;
	}
	
}
