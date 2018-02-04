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
	
	private String currentOperation;
	private String lastPressed;
	private DecimalFormat resultFormat = new DecimalFormat("#0.00");
	
	private JTextField displayField;
	
    public CalculatorGUI()
    {
    	calculationResult = 0.0;
    	lastNumberEntered = 0.0;
    	currentOperation = "None";
    	lastPressed = "None";
    	
		JFrame frm = new JFrame("Calculator");
		setFrameAttributes(frm);
		
		JPanel paddingPanel = new JPanel();
		setPaddingPanelAttributes(paddingPanel);
		frm.getContentPane().add(paddingPanel);
		
		/* Create the display panel */
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(Color.PINK);
		displayPanel.setBorder(new EmptyBorder(10, 0, 5, 0));
		paddingPanel.add(displayPanel);
		displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addDisplayField(displayPanel);
		
		/* Create the button panels */
		JPanel buttonPanel_1 = new JPanel();
		setButtonPanelAttributes(buttonPanel_1);
		paddingPanel.add(buttonPanel_1);
		
		addButton(buttonPanel_1, "C", Font.PLAIN, 18);
		addButton(buttonPanel_1, "-/+", Font.PLAIN, 11);
		addButton(buttonPanel_1, "%", Font.PLAIN, 18);
		addButton(buttonPanel_1, "SQRT", Font.PLAIN, 8);
		
		JPanel buttonPanel_2 = new JPanel();
		setButtonPanelAttributes(buttonPanel_2);
		paddingPanel.add(buttonPanel_2);

		addButton(buttonPanel_2, "7", Font.PLAIN, 18);
		addButton(buttonPanel_2, "8", Font.PLAIN, 18);
		addButton(buttonPanel_2, "9", Font.PLAIN, 18);
		addButton(buttonPanel_2, "/", Font.PLAIN, 18);
		
		JPanel buttonPanel_3 = new JPanel();
		setButtonPanelAttributes(buttonPanel_3);
		paddingPanel.add(buttonPanel_3);

		addButton(buttonPanel_3, "4", Font.PLAIN, 18);
		addButton(buttonPanel_3, "5", Font.PLAIN, 18);
		addButton(buttonPanel_3, "6", Font.PLAIN, 18);
		addButton(buttonPanel_3, "X", Font.PLAIN, 18);
		
		JPanel buttonPanel_4 = new JPanel();
		setButtonPanelAttributes(buttonPanel_4);
		paddingPanel.add(buttonPanel_4);
	    
		addButton(buttonPanel_4, "1", Font.PLAIN, 18);
		addButton(buttonPanel_4, "2", Font.PLAIN, 18);		
		addButton(buttonPanel_4, "3", Font.PLAIN, 18);
		addButton(buttonPanel_4, "-", Font.PLAIN, 18);
		
		JPanel buttonPanel_5 = new JPanel();
		setButtonPanelAttributes(buttonPanel_5);
		paddingPanel.add(buttonPanel_5);
		
		addButton(buttonPanel_5, "0", Font.PLAIN, 18);
		addButton(buttonPanel_5, ".", Font.PLAIN, 18);
		addButton(buttonPanel_5, "=", Font.PLAIN, 18);
		addButton(buttonPanel_5, "+", Font.PLAIN, 18);
		
		/* Display the frame */
		frm.setVisible(true);
    }

    
    private void addDisplayField(JPanel panel)
    {
		displayField = new JTextField();
		displayField.setEnabled(true);
		displayField.setEditable(false);
		displayField.setColumns(11);
		displayField.setBackground(Color.WHITE);
		displayField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		displayField.setHorizontalAlignment(SwingConstants.RIGHT);
		displayField.setText("");
		displayField.addKeyListener(this);
		
		panel.add(displayField);
    }
    
    
    private void setButtonPanelAttributes(JPanel panel) 
    {
		panel.setBackground(Color.PINK);
		panel.setLayout(new GridLayout(0, 4, 5, 0));
		panel.addKeyListener(this);
	}
    
    
    private void setFrameAttributes(JFrame frame) 
    {
		frame.setBackground(Color.PINK);
		frame.setResizable(false);
		frame.setSize(270, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 1, 5, 0));
		frame.addKeyListener(this);
	}
    
    private void setPaddingPanelAttributes(JPanel panel)
    {
		panel.setLayout(new GridLayout(6, 0, 10, 5));
		panel.setBackground(Color.PINK);
		panel.setBorder(new EmptyBorder(15, 15, 25, 15));
        panel.addKeyListener(this);
    }
    
    
    private void addButton(JPanel panel, String text, int fontWeight, int fontSize) 
    {
		JButton btn = new JButton(text);
		btn.setBackground(new Color(204, 204, 255));
		btn.setFont(new Font("Tahoma", fontWeight, fontSize));
		btn.addActionListener(this);
		btn.addKeyListener(this);
		
		panel.add(btn);
	}
    
    
    private void clearContents()
    {
    	displayField.setText("");
    	lastNumberEntered = 0.0;
    	calculationResult = 0.0;
    	currentOperation = "None";
    }
    
    
    private void addToDisplayField(String n)
    {
    	String newValue = displayField.getText() + n;
    	displayField.setText(newValue);
    }
    
    
    private double getDisplayContents() {
		double contents = 0;
		
		try {
			contents = Double.parseDouble(displayField.getText());
		} catch (Exception e) {
			contents = 0.0;
		}
		
		return contents;
	}
    
    
    
    /* To do
     * get the contents of the field and perform the operation then
     * display the result 
     */
    private void performCalculation(String operatorString)
    {
    	if(operatorString.equals("=")) {
    		if(currentOperation.equals("None"))
    			return;
    		else {
				return;
			}
    	}
        if (!lastPressed.equals("Number")) {
			return;
		}
        
        switch(currentOperation)
        {
            case "/":
            	lastNumberEntered = getDisplayContents();

            	if(lastNumberEntered == 0.0)
            	{
            		displayField.setText("Cannot divide by 0");
            		lastPressed = "None";
            		return;
            	}
            	
            	calculationResult = calculationResult / lastNumberEntered;
            	lastPressed = "Operator";
        	    break;
            case "X":
            	lastNumberEntered = getDisplayContents();
            	calculationResult = calculationResult * lastNumberEntered;
            	lastPressed = "Operator";
        	    break;
            case "+":
            	lastNumberEntered = getDisplayContents();
            	calculationResult = calculationResult + lastNumberEntered;
            	lastPressed = "Operator";
        	    break;
            case "-":
            	lastNumberEntered = getDisplayContents();
            	calculationResult = calculationResult - lastNumberEntered;
            	lastPressed = "Operator";
        	    break;
            default:
            	lastNumberEntered = getDisplayContents();
            	calculationResult = lastNumberEntered;
            	lastPressed = "Operator";
            	return;
        }
    		
    	displayField.setText(resultFormat.format(calculationResult));
    } 
    
    
    private void addDecimalPoint()
    {
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
    
    
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		String pressString;
		pressString = ae.getActionCommand();
		
		switch(pressString)
		{
			case "9": case "8": case "7": case "6": case "5":
			case "4": case "3": case "2": case "1": case "0":
				if(!lastPressed.equals("Number")) {
					displayField.setText("");
				}
				lastPressed = "Number";
				addToDisplayField(pressString);
				break;
			case ".":
				if(!lastPressed.equals("Number")) {
					displayField.setText("");
				}
				lastPressed = "Number";
				addDecimalPoint();
				break;
			/* To do
			 * For the operations 
             * get current
             * get input value
             * perform op on sum
             * set current to operator
			 * */
			case "/":
			case "X":
			case "-":
			case "+":
				performCalculation(pressString);
				
				if(lastPressed.equals("None")) {
					currentOperation = "None";
					calculationResult = 0.0;
					lastNumberEntered = 0.0;
				}
				else {
					currentOperation = pressString;
				}
    				
				break;
			case "%":
				break;
			case "=":
				break;
			case "-/+":
				break;
			case "SQRT":
				break;
			case "C":
				clearContents();
				break;
			default:
				break;
		}
	}


	@Override
	public void keyPressed(KeyEvent e) 
	{
		/* If the user has pressed the escape key quit the application */
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		char input = e.getKeyChar();
				
		if(Character.isDigit(input)) {
			if(!lastPressed.equals("Number")) {
				displayField.setText("");
			}
			lastPressed = "Number";
			addToDisplayField("" + input);  /* This converts it to the String required by the method */
		}
		else if (input == '.') 
		{
			if(!lastPressed.equals("Number")) {
				displayField.setText("");
			}
			
			lastPressed = "Number";
			addDecimalPoint();
		}
		else if (input == '\b') 
		{
			deleteEndCharacter();
		}
		else if (input == 'c' || input == 'C') 
		{
			clearContents();
		}
		else {
			if(input == '*') {
				input = 'X';
			}
				
			
			switch(input)
			{
			case '/':
			case 'X':
			case '-':
			case '+':
				performCalculation("" + input);
				
				if(lastPressed.equals("None")) {
					currentOperation = "None";
					calculationResult = 0.0;
					lastNumberEntered = 0.0;
				}
				else {
					currentOperation = "" + input;
				}
    				
				break;
			case '%':
				break;
			case '=':
				break;
			default:
				break;
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}
}
