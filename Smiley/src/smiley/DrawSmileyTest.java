//CSC150 - Final Exam - Owen O'Connor

package smiley;

//Test application that displays a smiley face.
import javax.swing.JFrame;

public class DrawSmileyTest
{
public static void main( String[] args )
{
   DrawSmiley panel = new DrawSmiley();      
   JFrame application = new JFrame();
   
   application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   application.add( panel );
   application.setSize( 230, 250 );      
   application.setVisible( true );
} // end main
} // end class DrawSmileyTest


