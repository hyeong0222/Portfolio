package Lab4;
////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 4 Part 1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 5th
//
//
//////////////////////////////////////////////////////////////////////////////////

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

/**
   This program generates a spiral.
*/
public class SpiralGenerator
{
   /**
      This method draws the spiral on the frame.
      @param g the Graphics context
   */
   public void draw(Graphics g)
   {
	   int x = 200;
	   int y = 200;
	   int increase = 10;
	   
	   // The for-loop would continue 50 times to draw a spiral shape.
	   for (int counter = 0; counter < 50; counter++) {
		   // The x coordinate would increase by the 'increase' value
		   if (counter % 4 == 0) {
			   g.drawLine(x, y, x + increase, y);
			   x += increase;
		   }
		   // The y coordinate would decrease by the 'increase' value
		   if (counter % 4 == 1) {
			   g.drawLine(x, y, x, y - increase);
			   y -= increase;
		   }
		   // The x coordinate would decrease by the 'increase' value
		   if (counter % 4 == 2) {
			   g.drawLine(x, y, x - increase, y);
			   x -= increase;
		   }
		   // The y coordinate would increase by the 'increase' value
		   if (counter % 4 == 3) {
			   g.drawLine(x, y, x, y + increase);
			   y += increase;
		   }
		   increase += 5;

	   }
   }
}
