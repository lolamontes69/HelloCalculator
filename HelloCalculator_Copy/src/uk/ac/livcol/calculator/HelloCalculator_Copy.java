/**
 * Filename:    HelloCalculator_Copy.java
 * Author:      Andrew Laing
 * Email:       parisianconnections@gmail.com
 * Date:        4 Feb 2018
 * Description: 
 */
package uk.ac.livcol.calculator;

import javax.swing.SwingUtilities;


public class HelloCalculator_Copy {
	public static void main(String[] args) 
	{
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new CalculatorGUI();
            }
        });

        System.out.println("A Swing GUI should be visible");
	}

}
