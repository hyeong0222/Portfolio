////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Lab 8
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 6th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab8;

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
        this.setTitle("Draw Shapes");
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
