//CSC150 - Final Exam - Owen O'Connor

package smiley;


//Demonstrates filled shapes.
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawSmiley extends JPanel
{
public void paintComponent( Graphics g )
{
   super.paintComponent( g );

   // draw the face
   g.setColor( Color.YELLOW );
   g.fillOval( 10, 10, 200, 200 );
   
   // draw the eyes, now oval
   g.setColor( Color.BLACK );
   g.fillOval( 55, 50, 30, 50 );
   g.fillOval( 135, 50, 30, 50 );
   
   // draw the mouth
   g.fillOval( 50, 110, 120, 60 );
   
   // "touch up" the mouth into a smile
   g.setColor( Color.YELLOW );
   g.fillRect( 50, 110, 120, 30 );
   g.fillOval( 50, 120, 120, 40 );
   
   // draw the mouth ends
   g.setColor( Color.BLACK );
   g.fillOval( 35, 125, 30, 30 );
   g.fillOval( 155, 125, 30, 30 );
   
   // "touch up" the mouth ends
   g.setColor( Color.YELLOW );
   g.fillOval( 30, 120, 30, 30 );
   g.fillOval( 160, 120, 30, 30 );
  
} // end method paintComponent
} // end class DrawSmiley