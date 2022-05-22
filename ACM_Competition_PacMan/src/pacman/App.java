package pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ACM Programming Competition - PacMan
 * 
 * @author Victor Chen
 * @since February 18, 2013
 */

public class App extends JFrame{
	private Board _game;

  public App(){	  
	  JPanel infoPanel = new JPanel(new GridLayout(0,1));
	  infoPanel.setBackground(Color.BLACK);
	  
//	  JLabel title0 = new JLabel("UCONN");
//	  title0.setForeground(Color.WHITE);
//	  title0.setHorizontalAlignment(JLabel.CENTER);
//	  infoPanel.add(title0);
//		
//	  JLabel title1 = new JLabel("ACM");
//	  title1.setForeground(Color.WHITE);
//	  title1.setHorizontalAlignment(JLabel.CENTER);
//	  infoPanel.add(title1);
//		
//	  JLabel title2 = new JLabel("PROGRAMMING");
//	  title2.setForeground(Color.WHITE);
//	  title2.setHorizontalAlignment(JLabel.CENTER);
//	  infoPanel.add(title2);
//		
//	  JLabel title3 = new JLabel("COMPETITION");
//	  title3.setForeground(Color.WHITE);
//	  title3.setHorizontalAlignment(JLabel.CENTER);
//	  infoPanel.add(title3);
	  
	  JLabel title4 = new JLabel("PACMAN");
	  title4.setForeground(Color.WHITE);
	  title4.setHorizontalAlignment(JLabel.CENTER);
	  infoPanel.add(title4);
		
	  JLabel wins1 = new JLabel("0");
//	  JLabel wins2 = new JLabel("0");
	  JLabel score1 = new JLabel("0");
//	  JLabel score2 = new JLabel("0");
	  wins1.setHorizontalAlignment(JLabel.CENTER);
//	  wins2.setHorizontalAlignment(JLabel.CENTER);
	  score1.setHorizontalAlignment(JLabel.CENTER);
//	  score2.setHorizontalAlignment(JLabel.CENTER);
	  wins1.setFont(new Font(wins1.getName(), Font.PLAIN, 30));
//	  wins2.setFont(new Font(wins2.getName(), Font.PLAIN, 30));
	  score1.setFont(new Font(wins1.getName(), Font.PLAIN, 30));
//	  score2.setFont(new Font(wins2.getName(), Font.PLAIN, 30));
	  wins1.setForeground(Color.WHITE);
//	  wins2.setForeground(Color.WHITE);
	  score1.setForeground(Color.WHITE);
//	  score2.setForeground(Color.WHITE);
	  
	  JLabel winTitle1 = new JLabel("Player 1");
//	  JLabel winTitle2 = new JLabel("Player 2");
	  winTitle1.setHorizontalAlignment(JLabel.CENTER);
//	  winTitle2.setHorizontalAlignment(JLabel.CENTER);
	  winTitle1.setForeground(Color.YELLOW);
//	  winTitle2.setForeground(Color.CYAN);
	  infoPanel.add(winTitle1);
	  infoPanel.add(wins1);
//	  infoPanel.add(winTitle2);
//	  infoPanel.add(wins2);
	  
	  JLabel scoreTitle1 = new JLabel("Player 1's Score: ");
//	  JLabel scoreTitle2 = new JLabel("Player 2's Score: ");
	  scoreTitle1.setForeground(Color.YELLOW);
//	  scoreTitle2.setForeground(Color.CYAN);
	  infoPanel.add(scoreTitle1);
	  infoPanel.add(score1);
//	  infoPanel.add(scoreTitle2);
//	  infoPanel.add(score2);
		
	  _game = new Board(wins1, score1);
	  add(_game);
	  add(infoPanel, BorderLayout.EAST);
	  
	  JButton go = new JButton("Go");
	  go.setActionCommand("Go");
	  go.addActionListener(_game);
	  infoPanel.add(go);
		
	  JButton restart = new JButton("Restart");
	  restart.setActionCommand("Restart");
	  restart.addActionListener(_game);
	  infoPanel.add(restart);
	  
	  setTitle("CSC 150 - Owen O'Connor - PACMAN");
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setSize(780, 710);
	  setResizable(false);
	  setLocationRelativeTo(null);
	  setVisible(true);
  }

  public static void main(String[] args) {
      new App();
  }
}