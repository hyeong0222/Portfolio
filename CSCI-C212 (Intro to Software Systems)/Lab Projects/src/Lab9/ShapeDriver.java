////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Lab 9
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 20th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab9; 


// These are the imports I used 
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.Timer;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.Point;

/*
 * Driver program for random shape generator app
 */
public class ShapeDriver extends JPanel implements KeyListener, ActionListener {

    // Panel constants
    public final int FRAME_WIDTH = 600;
    public final int FRAME_HEIGHT = 600;
	private ArrayList<Shape> shapes;

    private Random random;
    private Timer timer;

    public ShapeDriver() {
        super(); 

        /* T0-DO: 
         *  - set up JPanel
         *  - initialize any other fields you want to declare and use
         *  - add the KeyListiner 
         */
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setBackground(Color.gray);
        shapes = new ArrayList<Shape>();
        random = new Random();
        
        // the second argument to the Timer Constructor takes an ActionListener // the this key word informs the JVM to look inside this class for
        // the actionPerformed method that must be overridden when
        // ActionListener is implemented
        // Every tick of the clock will now run the actionPerformed method
        timer = new Timer(300/60, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        // call super class paintComponent method
        // background will not be colored otherwise
        super.paintComponent(g);
        
        for (Shape each : shapes) {
        		each.draw(g);
        }

        // TO-DO use the different Shapes draw methods here
        // The draw methods in the different shapes should take 
        // The Graphics object should be passed to the Shapes Draw method
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        /* To-DO: 
         *  - if c, r, e, s, or t is pressed draw a circle, rectangle, ellipse, square 
         *  - or triangle. 
         *  - repaint the JPanel
         */ 
    		
    		int red = random.nextInt(255);
    		int blue = random.nextInt(255);
    		int green = random.nextInt(255);
    		int redBorder = random.nextInt(255);
    		int blueBorder = random.nextInt(255);
    		int greenBorder = random.nextInt(255);
    		int x = random.nextInt(500)+1;
    		int y = random.nextInt(500)+1;
    		int length = random.nextInt(100)+1;
    		int height = random.nextInt(100)+1;
    		
    		// generates a circle when C is pressed on a keyboard
    		switch (e.getKeyCode()) {
    			case KeyEvent.VK_C:
    				Circle circle = new Circle (new Color(red, blue, green), new Color(redBorder, blueBorder, greenBorder), x, y, length);
    				shapes.add(circle);
    				this.repaint();
    				break;
    		}
    		
//    		// generates a rectangle when R is pressed on a keyboard
//    		switch (e.getKeyCode()) {
//    			case KeyEvent.VK_R:
//    				Rectangle rectangle = new Rectangle (new Color(red, blue, green), new Color(redBorder, blueBorder, greenBorder), x, y, length, height);
//    				shapes.add(rectangle);
//    				this.repaint();
//    				break;
//    		}
//    		
//    		// generates an ellipse when E is pressed on a keyboard
//    		switch (e.getKeyCode()) {
//    			case KeyEvent.VK_E:
//    				Ellipse ellipse = new Ellipse (new Color(red, blue, green), new Color(redBorder, blueBorder, greenBorder), x, y, length, height);
//    				shapes.add(ellipse);
//    				this.repaint();
//    				break;
//    		}
//    		
//    		// generates a square when S is pressed on a keyboard
//    		switch (e.getKeyCode()) {
//    			case KeyEvent.VK_S:
//    				Square square = new Square (new Color(red, blue, green), new Color(redBorder, blueBorder, greenBorder), x, y, length);
//    				shapes.add(square);
//    				this.repaint();
//    				break;
//    		}
//    		
//    		// generates a triangle when T is pressed on a keyboard
//    		switch (e.getKeyCode()) {
//    			case KeyEvent.VK_T:
//    				Triangle triangle = new Triangle (new Color(red, blue, green), new Color(redBorder, blueBorder, greenBorder), x, y, length, height);
//    				shapes.add(triangle);
//    				this.repaint();
//    				break;
//    		}
    
    }
    
    // 	Method that must be implemented since the class implements ActionListener
    public double getDistance (Point a, Point b) {
    		double distance = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    		return distance;
    }
    
    public void actionPerformed(ActionEvent e) {		
    		// move each circle
    		// check if circle is in bounds, and bounce off the borders if need be 
    		// check if circle hits another circle of different color,
    		// bounce circles off each other and swap colors
    		// if circle of one color hits a circle of same color, remove smaller circle
    		// call repaint
    		
    		// a copy of ArrayList 'shapes'
    		ArrayList<Shape> tempArray = new ArrayList<Shape>();
    		for (int i = 0; i < shapes.size(); i++) {
    			tempArray.add(i, shapes.get(i));
    		}
    		
    		for (Shape each : shapes) {
    			each.moveLocation();
    			Circle current = (Circle) each;
    			if ((current.getX() + current.getRadius() * 2 >= FRAME_WIDTH) || (current.getX() <= 0)) {
    				current.changeDx();
    			}
    			if ((current.getY() + current.getRadius() * 2 >= FRAME_HEIGHT) || (current.getY() <= 0)) {
    				current.changeDy();
    			}
    			
    			// 
    			for (Shape another : shapes) {
    				Circle current2 = (Circle) another;
    				if (!current.equals(current2)) {
    					if (getDistance(current.getCenter(), current2.getCenter()) <= (current.getRadius() + current2.getRadius())) {
    						if (current.getFillColor().equals(current2.getFillColor())) {
    							Circle smaller = (Circle) another;
    							if (current.getRadius() < current2.getRadius()) {
    								smaller = current;
    							}
    							else smaller = current2;
    							tempArray.remove(smaller);
    						}
    						
    						else {
    						Color temp = current.getFillColor();
    						current.setFillColor(current2.getFillColor());
    						current2.setFillColor(temp);
    						current.changeDx();
    						current.changeDy();
    						current2.changeDx();
    						current2.changeDy();
    						}
    					}
    				}
    			}
    			
    		}
    	    shapes = tempArray;
    		this.repaint();
    }


    // do not need to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyReleased(KeyEvent e) { }
    
    // do not need to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyTyped(KeyEvent e) { }

    // test client
    public static void main(String[] args) {

    }
}