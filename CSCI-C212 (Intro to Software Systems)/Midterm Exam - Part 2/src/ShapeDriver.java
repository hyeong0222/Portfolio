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

// These are the imports I used 
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Dimension;

import javax.swing.Timer;


import java.awt.Point;
import java.awt.Polygon;


/*
 * Driver program for random shape generator app
 */
public class ShapeDriver extends JPanel implements MouseListener, ActionListener {

    // Panel constants
    public final int FRAME_WIDTH = 800;
    public final int FRAME_HEIGHT = 800;
	private ArrayList<Shape> hexagons;
	private int[] xPoints;
	private int[] yPoints;
    private Random random;
    private Timer timer;
    private int clicked;
    
    public ShapeDriver() {
        super(); 

        /* T0-DO: 
         *  - set up JPanel
         *  - initialize any other fields you want to declare and use
         *  - add the KeyListiner 
         */
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setFocusable(true);
        this.addMouseListener(this);
        this.setBackground(Color.gray);
        hexagons = new ArrayList<Shape>();
        random = new Random();
		this.xPoints = new int[] {200, 600, 800, 600, 200, 0};
		this.yPoints = new int[] {0, 0, 400, 800, 800, 400};
		this.clicked = 1;
		

        
        // the second argument to the Timer Constructor takes an ActionListener // the this key word informs the JVM to look inside this class for
        // the actionPerformed method that must be overridden when
        // ActionListener is implemented
        // Every tick of the clock will now run the actionPerformed method
        timer = new Timer(300/60, this);
        timer.start();
        
        int red = random.nextInt(255);
		int blue = random.nextInt(255);
		int green = random.nextInt(255);
		Color randomC = new Color (red, blue, green);
		
		Shape hexagon = new Hexagon (randomC, Color.black, xPoints, yPoints);
		hexagons.add(hexagon);
		

    }

    @Override
    public void paintComponent(Graphics g) {
        // call super class paintComponent method
        // background will not be colored otherwise
        super.paintComponent(g);
        
    	
        for (Shape each : hexagons) {
       		each.draw(g);
		}

        // TO-DO use the different Shapes draw methods here
        // The draw methods in the different shapes should take 
        // The Graphics object should be passed to the Shapes Draw method
		
    }
    
    // This method detects a click action on the JFrame, and reduces the size of a hexagon to 4 smaller ones.
    public void mousePressed(MouseEvent event) {
    		int x = event.getX();
    		int y = event.getY();
    	
    	 	int red = random.nextInt(255);
 		int blue = random.nextInt(255);
 		int green = random.nextInt(255);
    		Color randomC = new Color (red, green, blue);
    		clicked++;
    		
    		
    		int nOfHexagon = (int)Math.pow(clicked, 2);
    		
    		
    		for (int i = 1; i < nOfHexagon; i++) {
    			if (i == 1) {
    				for (int a = 0; a < xPoints.length; a++ ) {
    	    				xPoints[a] = xPoints[a] / 2;
    	    			}
    	    			for (int b = 0; b < yPoints.length; b++) {
    	    				yPoints[b] = yPoints[b] / 2;
    	    			}
    				hexagons.set(0, new Hexagon (randomC, Color.black, xPoints, yPoints));
    			}
    			
    			else if (i == 2) {
    				for (int a = 0; a < xPoints.length; a++ ) {
    					xPoints[a] = xPoints[a] + 400;
    				}
    				for (int b = 0; b < yPoints.length; b++) {
    					yPoints[b] = yPoints[b];
    				}
    				hexagons.add(new Hexagon (randomC, Color.black, xPoints, yPoints));
			}
    				
    			else if (i == 3) {
    				for (int a = 0; a < xPoints.length; a++ ) {
    					xPoints[a] = xPoints[a] - 400;
    				}
    				for (int b = 0; b < yPoints.length; b++) {
    					yPoints[b] = yPoints[b] + 400;
  				}
    				hexagons.add(new Hexagon (randomC, Color.black, xPoints, yPoints));
    			}
    			
    			else if (i == 4) {
    				for (int a = 0; a < xPoints.length; a++ ) {
    					xPoints[a] = xPoints[a] + 400;
    				}
    				for (int b = 0; b < yPoints.length; b++) {
    					yPoints[b] = yPoints[b];
    				}
    				hexagons.add(new Hexagon (randomC, Color.black, xPoints, yPoints));
    			}
        		repaint();

		}
    	}
    
    public void mouseReleased(MouseEvent event) { 
    		final int delay = 1000;
    		
    		ActionListener listener = new ShapeDriver();
    		
//    		Timer t = new Timer(delay, listener);
//    		
//    		t.start();
    		
    		for (int i = 0; i < hexagons.size(); i++) {
    			hexagons.get(i).moveLocation();
    			
//    			if ((hexagons.get(i).getLargest(xPoints) > FRAME_WIDTH) || (hexagons.get(i).getSmallest(yPoints) < 0)) {
//    				
//    			}
    				
    		}
//    			each.moveLocation();
//    			Hexagon current = (Hexagon) each;
//  
//    			if (;)) >= FRAME_WIDTH) || (current.getX() <= 0)) {
//    				current.changeDx();
//    			}
//    			if ((current.getY() + current.getRadius() * 2 >= FRAME_HEIGHT) || (current.getY() <= 0)) {
//    				current.changeDy();
//    			}
//    		}
    		
    	
    }
    public void mouseClicked(MouseEvent event) { }
    public void mouseEntered(MouseEvent event) { }
    public void mouseExited(MouseEvent event) { }
    
    public void actionPerformed(ActionEvent e) {
    	
    }
    
    // test client
    public static void main(String[] args) {

    }
}