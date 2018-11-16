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
import javax.swing.*;

public class Lab4SpiralGeneratorViewer
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      final int FRAME_WIDTH = 400;
      final int FRAME_HEIGHT = 400;
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setTitle("SpiralGeneratorViewer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JComponent component = new JComponent()
      {
         public void paintComponent(Graphics graph)
         {
            SpiralGenerator spiral = new SpiralGenerator();
            spiral.draw(graph);
         }
      };
      frame.add(component);
      frame.setVisible(true);
   }
}
