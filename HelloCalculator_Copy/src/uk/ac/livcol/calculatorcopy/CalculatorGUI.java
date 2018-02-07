/**
 * Filename:    CalculatorGUI.java
 * Author:      Andrew Laing
 * Email:       parisianconnections@gmail.com
 * Date:        4 Feb 2018
 * Description: 
 */
package uk.ac.livcol.calculatorcopy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class CalculatorGUI implements ActionListener, KeyListener
{
	private double calculationResult;
	private double lastNumberEntered;
	private double memoryValue;
	
	private String currentOperation;
	private String lastPressed;
	
	private DecimalFormat resultFormat = new DecimalFormat("#");
	
	private JTextField displayField;
	
    public CalculatorGUI()
    {
    	resultFormat.setMaximumFractionDigits(4);
    	calculationResult = 0.0;
    	lastNumberEntered = 0.0;
    	memoryValue = 0.0;
    	currentOperation = "None";
    	lastPressed = "None";
    	
		JFrame frm = new JFrame("Calculator");
		setFrameAttributes(frm);
		
		JPanel paddingPanel = new JPanel();
		setPaddingPanelAttributes(paddingPanel);
		frm.getContentPane().add(paddingPanel);
		
		/* Create the display panel */
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(UIManager.getColor("control"));
		displayPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		paddingPanel.add(displayPanel);
		displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addDisplayField(displayPanel);
		
		/* Create the button panels */
		JPanel buttonPanel_0 = new JPanel();
		setButtonPanelAttributes(buttonPanel_0);
		paddingPanel.add(buttonPanel_0);
		
		addButton(buttonPanel_0, "MC", Font.PLAIN, 18);
		addButton(buttonPanel_0, "MR", Font.PLAIN, 18);
		addButton(buttonPanel_0, "M-", Font.PLAIN, 18);
		addButton(buttonPanel_0, "M+", Font.PLAIN, 18);
		
		
		JPanel buttonPanel_1 = new JPanel();
		setButtonPanelAttributes(buttonPanel_1);
		paddingPanel.add(buttonPanel_1);
		
		addButton(buttonPanel_1, "C", Font.PLAIN, 18);
		addButton(buttonPanel_1, "-/+", Font.PLAIN, 16);
		addButton(buttonPanel_1, "%", Font.PLAIN, 18);
		addButton(buttonPanel_1, "SQRT", Font.PLAIN, 14);
		
		JPanel buttonPanel_2 = new JPanel();
		setButtonPanelAttributes(buttonPanel_2);
		paddingPanel.add(buttonPanel_2);

		addButton(buttonPanel_2, "7", Font.PLAIN, 28);
		addButton(buttonPanel_2, "8", Font.PLAIN, 28);
		addButton(buttonPanel_2, "9", Font.PLAIN, 28);
		addButton(buttonPanel_2, "/", Font.PLAIN, 18);
		
		JPanel buttonPanel_3 = new JPanel();
		setButtonPanelAttributes(buttonPanel_3);
		paddingPanel.add(buttonPanel_3);

		addButton(buttonPanel_3, "4", Font.PLAIN, 28);
		addButton(buttonPanel_3, "5", Font.PLAIN, 28);
		addButton(buttonPanel_3, "6", Font.PLAIN, 28);
		addButton(buttonPanel_3, "X", Font.PLAIN, 18);
		
		JPanel buttonPanel_4 = new JPanel();
		setButtonPanelAttributes(buttonPanel_4);
		paddingPanel.add(buttonPanel_4);
	    
		addButton(buttonPanel_4, "1", Font.PLAIN, 28);
		addButton(buttonPanel_4, "2", Font.PLAIN, 28);		
		addButton(buttonPanel_4, "3", Font.PLAIN, 28);
		addButton(buttonPanel_4, "-", Font.PLAIN, 18);
		
		JPanel buttonPanel_5 = new JPanel();
		setButtonPanelAttributes(buttonPanel_5);
		paddingPanel.add(buttonPanel_5);
		
		addButton(buttonPanel_5, "0", Font.PLAIN, 28);
		addButton(buttonPanel_5, ".", Font.BOLD, 18);
		addButton(buttonPanel_5, "=", Font.PLAIN, 18);
		addButton(buttonPanel_5, "+", Font.PLAIN, 18);
		
		/* Display the frame */
		frm.setVisible(true);
    }

    
    /**
     * The method addDisplayField adds the calculator's display 
     * into a JPanel.
     * @param panel A panel to place the display field into.
     */
    private void addDisplayField(JPanel panel)
    {	
		displayField = new JTextField();
		displayField.setEnabled(true);
		displayField.setEditable(false);
		displayField.setColumns(11);
		displayField.setMargin(new Insets(1,3,1,3));
		displayField.setBackground(Color.WHITE);
		displayField.setFont(new Font("Dialog", Font.PLAIN, 24));
		displayField.setHorizontalAlignment(SwingConstants.RIGHT);
		displayField.setText("0");
		displayField.addKeyListener(this);
		
		panel.add(displayField);
    }
    
    
    /**
     * The method setButtonPanelAttributes some attributes 
     * of the JPanel passed to it.
     * @param panel A panel to set attributes for.
     */
    private void setButtonPanelAttributes(JPanel panel) 
    {
		panel.setBackground(UIManager.getColor("control"));
		panel.setLayout(new GridLayout(0, 4, 5, 0));
		panel.addKeyListener(this);
	}
    
    
    /**
     * The method setFrameAttributes some attributes 
     * of the JFrame passed to it.
     * @param frame A frame to set attributes for.
     */
    private void setFrameAttributes(JFrame frame) 
    {
		frame.setBackground(UIManager.getColor("control"));
		frame.setResizable(false);
		frame.setSize(270, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 1, 5, 0));
		frame.addKeyListener(this);
	}
    
    
    /**
     * The method setPaddingPanelAttributes some attributes 
     * of the JPanel passed to it.
     * @param panel A panel to set attributes for.
     */
    private void setPaddingPanelAttributes(JPanel panel)
    {
		panel.setLayout(new GridLayout(7, 0, 10, 5));
		panel.setBackground(UIManager.getColor("control"));
		panel.setBorder(new EmptyBorder(15, 15, 25, 15));
        panel.addKeyListener(this);
    }
    
    
    /**
     * The method addButton adds a JButton to the JPanel passed to it.
     * @param panel A JPanel to add a button to.
     * @param text The text to add to the button.
     * @param fontWeight The weight of the button's text font.
     * @param fontSize The size of the button's font.
     */
    private void addButton(JPanel panel, String text, int fontWeight, int fontSize) 
    {
		JButton btn = new JButton(text);
		btn.setBackground(SystemColor.info);
		btn.setMargin(new Insets(1,1,1,1));   /* Reduce internal padding */
		btn.setFont(new Font("Dialog", fontWeight, fontSize));
		btn.addActionListener(this);
		btn.addKeyListener(this);
		
		panel.add(btn);
	}
    
    
    /**
     * The method clearContents clears the contents of the display field.
     */
    private void clearContents()
    {
    	displayField.setText("0");
    	lastNumberEntered = 0.0;
    	calculationResult = 0.0;
    	lastPressed = "None";
    	currentOperation = "None";
    }
    
    
    /**
     * The method addToDisplayField adds a character to the
     * end of the display field.
     * @param n A character to add to the display field.
     */
    private void addToDisplayField(String n)
    {
    	String newValue = displayField.getText() + n;
    	displayField.setText(newValue);
    }
    
    
    /**
     * The method getDisplayContentsAsDouble returns the contents of
     * the display field as a double.
     * @return The contents of the display field as a double.
     */
    private double getDisplayContentsAsDouble() {
		double contents = 0.0;
		
		try {
			contents = Double.parseDouble(displayField.getText());
		} 
		catch (Exception e) { ; }
		
		return contents;
	}
    
    
    /**
     * The method toggleSign negates the number currently displayed.
     */
    private void toggleSign()
    {
    	double currentlyDisplayed = getDisplayContentsAsDouble();
    	if(currentlyDisplayed==0.0)
    		return;
    	else if(currentlyDisplayed < 0)
    		currentlyDisplayed = Math.abs(currentlyDisplayed);
    	else {
			currentlyDisplayed = currentlyDisplayed * -1.0;
		}
    	
    	displayField.setText(resultFormat.format(currentlyDisplayed));
    	
    	/* This behaviour mimics the Windows calculator */
        if (!lastPressed.equals("Number")) {
			lastNumberEntered = currentlyDisplayed;
		}
    }
    
    
    /**
     * The method doSquareRoot calculates and shows the square root
     * of the current number in the display area.
     */
    private void doSquareRoot()
    {
    	double currentlyDisplayed = getDisplayContentsAsDouble();
    	if(currentlyDisplayed < 0.0) {
     		displayField.setText("Math Domain Error");
     		lastPressed = "None";
     		return;
    	}
        
        currentlyDisplayed = Math.sqrt(currentlyDisplayed);
        displayField.setText(resultFormat.format(currentlyDisplayed));
    }
    
    
    
    /**
     * The method doPercentage calculates and adds the percentage
     * of the current calculation result to the display area.
     */
    private void doPercentage()
    {
    	double currentlyDisplayed = getDisplayContentsAsDouble();
        
    	double percentage = calculationResult * (currentlyDisplayed / 100.0);
    	
        displayField.setText(resultFormat.format(percentage));
    }
    
    
    
    /**
     * The method doEqualsoperation returns the adds of the current
     * operation to the display.
     */
    private void doEqualsOperation()
    {
    	if(lastPressed.equals("Number"))
    	    	 lastNumberEntered = getDisplayContentsAsDouble();
        
        switch(currentOperation)
        {
            case "/":
             	if(lastNumberEntered == 0.0)
             	{
             		displayField.setText("Cannot divide by 0");
             		lastPressed = "None";
             		return;
             	}
             	calculationResult = calculationResult / lastNumberEntered;
         	    break;
            case "X":
             	calculationResult = calculationResult * lastNumberEntered;
         	    break;
            case "+":
             	calculationResult = calculationResult + lastNumberEntered;
         	    break;
            case "-":
             	calculationResult = calculationResult - lastNumberEntered;
         	    break;
            default:
             	calculationResult = lastNumberEntered;
             	return;
         }
         
     	 displayField.setText(resultFormat.format(calculationResult));
     	 
     	 lastPressed = "Equals";
    }
    
    
    /* Only partially implemented - needs % added */
    private void performCalculation(String operatorString)
    {
    	if(operatorString.equals("=")) {
    		if(currentOperation.equals("None"))
    			return;
    		else {
    			doEqualsOperation();
				return;
			}
    	}
        if (!lastPressed.equals("Number")) {
			return;
		}
        
        lastNumberEntered = getDisplayContentsAsDouble();
        
        switch(currentOperation)
        {
            case "/":
            	if(lastNumberEntered == 0.0)
            	{
            		displayField.setText("Cannot divide by 0");
            		lastPressed = "None";
            		return;
            	}
            	calculationResult = calculationResult / lastNumberEntered;
        	    break;
            case "X":
            	calculationResult = calculationResult * lastNumberEntered;
        	    break;
            case "+":
            	calculationResult = calculationResult + lastNumberEntered;
        	    break;
            case "-":
            	calculationResult = calculationResult - lastNumberEntered;
        	    break;
            default:
            	calculationResult = lastNumberEntered;
            	lastPressed = "Operator";
            	return;
        }
    		
        lastPressed = "Operator";
        
    	displayField.setText(resultFormat.format(calculationResult));
    } 
    
    

    
    /**
     * The method deleteEndCharacter deletes the end character
     * from the text shown in the display field.
     */
    private void deleteEndCharacter() 
    {
		String test = displayField.getText();
		
	    if (test != null) {
	    	if (test.length() > 0) {
				test = test.substring(0, test.length() - 1);
	            displayField.setText(test);
			}
	    }
	}
    
    
    /**
     * The method doNumberPress adds a number to
     * the display field.
     * @param input A number character to add to the display field.
     */
    private void doNumberPress(String input)
    {
		if(!lastPressed.equals("Number")) {
			displayField.setText("");
		}
		lastPressed = "Number";
		addToDisplayField(input);
    }
    
    
    /**
     * The method doDecimalPointPress adds a decimal point to the
     * display. If no numbers have been entered previously, it
     * adds a zero before the point.
     */
    private void doDecimalPointPress()
    {
		if(!lastPressed.equals("Number")) {
			displayField.setText("");
		}
		lastPressed = "Number";
		
		String test = displayField.getText();
		if (test.contains(".")) {
			return;
		}
		else if (test.length()==0) {
			addToDisplayField("0.");
		}
		else {
			addToDisplayField(".");
		}
    }
    
    
    /**
     * The method actionPerformed deals with button press events.
     */
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		String pressString;
		pressString = ae.getActionCommand();
		
		switch(pressString)
		{
			case "9": case "8": case "7": case "6": case "5":
			case "4": case "3": case "2": case "1": case "0":
				doNumberPress(pressString);
				break;
			case ".":
				doDecimalPointPress();
				break;

			case "/": case "X": case "-": case "+": case "=":
				performCalculation(pressString);
				
				if(lastPressed.equals("None")) {
					currentOperation = "None";
					calculationResult = 0.0;
					lastNumberEntered = 0.0;
				}
				else if(!pressString.equals("="))
				{
					currentOperation = pressString;
				}
    				
				break;
			case "%":
				doPercentage();
				break;
			case "-/+":
				toggleSign();
				break;
			case "SQRT":
				doSquareRoot();
				if(lastPressed.equals("None")) {
					currentOperation = "None";
					calculationResult = 0.0;
					lastNumberEntered = 0.0;
				}
				break;
			case "C":
				clearContents();
				break;
			case "MC":
				memoryValue = 0.0;
				break;
			case "MR":
				displayField.setText(resultFormat.format(memoryValue));
				lastPressed = "Number";
				break;
			case "M-":
				memoryValue = memoryValue - getDisplayContentsAsDouble();
				break;
			case "M+":
				memoryValue = memoryValue + getDisplayContentsAsDouble();
				break;
			default:
				break;
		}
	}


	/**
	 * The method keyPressed deals with key press events.
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		char input;
		
		/* If the user has pressed the escape key quit the application */
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		/* Use the ENTER press as the '=' key */
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        	input = '=';
        else
    		input = e.getKeyChar();
				
		if(input == '*')
			input = 'X';
        
		if(Character.isDigit(input)) 
		{
			doNumberPress("" + input);
		}
		else if (input == '.') 
		{
			doDecimalPointPress();
		}
		else if (input == '\b') 
		{
			deleteEndCharacter();
		}
		else if (input == 'c' || input == 'C') 
		{
			clearContents();
		}
		else 
		{
			switch(input)
			{
				case '/': case 'X': case '-': case '+': case '=':
					performCalculation("" + input);
					
					if(lastPressed.equals("None")) {
						currentOperation = "None";
						calculationResult = 0.0;
						lastNumberEntered = 0.0;
					} 
					else if(input != '=')
					{
						currentOperation = "" + input;
					}
	    				
					break;
				case '%':
					doPercentage();
					break;
				default:
					break;
			}
		}
	}


	/**
	 * Required by KeyListener interface
	 */
	@Override
	public void keyReleased(KeyEvent e) { ; }


	/**
	 * Required by KeyListener interface
	 */
	@Override
	public void keyTyped(KeyEvent e) { ; }
}
