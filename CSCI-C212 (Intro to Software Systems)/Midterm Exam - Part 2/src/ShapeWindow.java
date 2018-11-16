////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Midterm Exam 2 - Part 2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 22nd
//
//
//////////////////////////////////////////////////////////////////////////////////

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

/*
 * Main application for random shape generator app
 */
public class ShapeWindow extends JFrame {
	
    JPanel shapeDriver;
  
    public ShapeWindow() {
        super();
        // TO-DO: set up the frame
        shapeDriver = (JPanel) new ShapeDriver();
        this.setTitle("Hexagon");
        this.getContentPane().add(shapeDriver);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // run main application 
        JFrame shapeWindow = new ShapeWindow();
    }
}
