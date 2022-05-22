package pacman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	 	private Timer _timer;
	    private Timer _EnergizerTimer;
	    private Timer _FruitTimer;
	    private boolean _ingame;
	    private int _pacmanAnimCount;
	    private int _pacmanAnimDir;
	    private int _pacmanAnimPos;
	    private int[] _dx, _dy;
	    private Ghost[] _ghosts;
	    private Image _ghost;
	    private Image _ScaredGhost;
	    private Image _cherry;
	    private boolean _drawCherry;
	    private PacMan _player1;
	    private PacMan _player2;
	    private int _player1Score, _player2Score, _player1Wins, _player2Wins;
	    private JLabel _wins1, _wins2, _score1, _score2;
	    private ProxyBoard _proxyBoard;
	    private Clip _clip;
	    private short[][] _copyMap;
		private final short _map[][] = {
			{ 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,  2,  2, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,  6},
			{ 5, 19, 26, 26, 26, 26, 18, 26, 18, 18, 18, 26, 22,  1,  4, 19, 26, 26, 26, 18, 26, 18, 26, 18, 18, 26, 22,  5},
			{ 5, 21,  0,  0,  0,  0, 21,  7, 17, 16, 20,  0, 21,  0,  0, 21,  0,  0,  0, 21,  0, 21,  0, 17, 20,  0, 21,  5},
			{ 5, 37,  0, 27, 26, 26, 20,  5, 17, 16, 20,  0, 21,  0,  0, 21,  0, 23,  0, 21,  0, 21,  0, 25, 28,  0, 37,  5},
			{ 5, 21,  0, 10, 10,  0, 21,  5, 17, 16, 20,  0, 21,  0,  0, 21,  0, 21,  0, 21,  0, 21,  0,  0,  0,  0, 21,  5},
			{ 5, 17, 18, 18, 22,  5, 21,  5, 17, 16, 20,  0, 17, 26, 26, 20,  0, 21,  0, 21,  0, 17, 18, 22,  0, 19, 20,  5},
			{ 5, 17, 24, 24, 28,  1, 21,  0, 25, 24, 28,  0, 21,  0,  0, 21,  0, 21,  0, 29,  0, 17, 24, 28,  0, 17, 20,  5},
			{ 5, 21,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0, 21,  0,  0, 21,  0, 21,  0,  0,  0, 21,  0,  0,  0, 17, 20,  5},
			{ 5, 25, 26, 26, 26, 26, 16, 26, 26, 26, 26, 26, 20,  0,  0, 17, 26, 24, 26, 26, 26, 16, 26, 26, 26, 24, 20,  5},
			{ 9, 10, 10, 10, 10,  6, 21,  0,  0,  0,  0,  0, 21,  0,  0, 21,  0,  0,  0,  0,  0, 21,  3, 10, 10, 10, 10, 12},
			{ 0,  0,  0,  0,  0,  5, 21,  0,  0,  0,  0,  0, 21,  0,  0, 21,  0,  0,  0,  0,  0, 21,  5,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0,  5, 21,  0,  0, 19, 26, 26, 24, 18, 18, 24, 26, 26, 22,  0,  0, 21,  5,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0,  5, 21,  0,  0, 21,  0,  0,  0,  1,  4,  0,  0,  0, 21,  0,  0, 21,  5,  0,  0,  0,  0,  0},
			{10, 10, 10, 10, 10, 12, 21,  0,  0, 21,  0,  3,  2,  0,  0,  2,  6,  0, 21,  0,  0, 21,  9, 10, 10, 10, 10, 10},
			{27, 26, 26, 26, 26, 26, 16, 26, 26,  4,  0,  1,  0,  0,  0,  0,  4,  0,  1, 26, 26, 16, 26, 26, 26, 26, 26, 30},
			{10, 10, 10, 10, 10,  6, 21,  0,  0, 21,  0,  9,  8,  8,  8,  8, 12,  0, 21,  0,  0, 21,  3, 10, 10, 10, 10, 10},
			{ 0,  0,  0,  0,  0,  5, 21,  0,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0,  0, 21,  5,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0,  5, 21,  0,  0, 17, 26, 26, 26, 74, 10, 26, 26, 26, 20,  0,  0, 21,  5,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0,  5, 21,  0,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0,  0, 21,  5,  0,  0,  0,  0,  0},
			{ 3, 10, 10, 10, 10, 12, 21,  0,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0,  0, 21,  9, 10, 10, 10, 10,  6},
			{ 5, 19, 26, 26, 26, 26, 16, 26, 26, 24, 26, 26, 22,  0,  0, 19, 26, 26, 24, 26, 26, 16, 26, 26, 26, 26, 22,  5},
			{ 5, 21,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0, 21,  0,  0, 21,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  5},
			{ 5, 25, 26, 26, 22,  0, 17, 26, 18, 26, 26, 26, 24, 26, 26, 24, 26, 26, 26, 18, 26, 20,  0, 19, 26, 26, 28,  5},
			{ 1,  0,  0,  0, 21,  0, 21,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0, 21,  0, 21,  0,  0,  0,  4},
			{ 5, 19, 26, 26, 24, 26, 28,  0, 25, 26, 26, 26, 22,  0,  0, 19, 26, 26, 26, 28,  0, 25, 26, 24, 26, 26, 22,  5},
			{ 5, 37,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 37,  5},
			{ 5, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 24, 26, 26, 24, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 28,  5},
			{ 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12}
		};
	private final int _basicMap[][] = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1},
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
			{ 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		};

    public Board(JLabel wins1, JLabel score1) {
    	_wins1 = wins1;
//    	_wins2 = wins2;
    	_score1 = score1;
//    	_score2 = score2;
    	playIntroSound();
    	init();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        _timer = new Timer(40, this);
        _timer.setActionCommand("Timer");
        _timer.start();
        
    }
    
    public void init(){
    	_proxyBoard = new ProxyBoard(this);
        _copyMap = new short[Constant.NUMBER_OF_BLOCKS][Constant.NUMBER_OF_BLOCKS];
        _ghosts = new Ghost[Constant.NUMBER_OF_GHOSTS];
        _pacmanAnimDir = 1;
        _pacmanAnimPos = 0;
        _pacmanAnimCount = Constant.PACMAN_ANIM_DELAY;
        _dx = new int[4];
        _dy = new int[4];
        _ingame = false;
        
    }

    public void addNotify() {
        super.addNotify();
        GameInit();
    }

    public void DoAnim() {
        _pacmanAnimCount--;
        if (_pacmanAnimCount <= 0) {
            _pacmanAnimCount = Constant.PACMAN_ANIM_DELAY;
            _pacmanAnimPos = _pacmanAnimPos + _pacmanAnimDir;
            if (_pacmanAnimPos == (Constant.PACMAN_ANIM_COUNT - 1) || _pacmanAnimPos == 0)
                _pacmanAnimDir = -_pacmanAnimDir;
        }
    }

    public void PlayGame(Graphics2D g2d) {
    	if (!_player1.isDead()){
        	//getDirection(_player1);	// Comment this line to control player 1 using arrow keys
            MovePacMan(_player1);
            DrawPacMan(g2d, _player1);
    	}
    	_player2.setDead(true);
    	if (!_player2.isDead()){
        	//getDirection(_player2);
            MovePacMan(_player2);
            DrawPacMan(g2d, _player2);
    	}
    	
        checkFruit();
        checkEnergizer();
    	
    	// Check if player collected a coin
    	checkCoin();
    	
        if (_player1.isDead() && _player2.isDead()){
        	if (_player1Score > _player2Score){
        		_player1Wins++;
        		_wins1.setText(Integer.toString(_player1Wins));
        	}else if (_player2Score > _player1Score){
            	_player2Wins++;
            	_wins2.setText(Integer.toString(_player2Wins));
        	}
        	_timer.stop();
        	_clip.stop();
        	
        	// Loop game
        	//_timer.start();
    		//_ingame = true;
  		  	//GameInit();
        }
        

        moveGhosts(g2d);
        CheckMaze();
    }
    
    public void checkCoin(){
    	int col1 = _player1.getCol();
        int row1 = _player1.getRow();
        int col2 = _player2.getCol();
        int row2 = _player2.getRow();
        
        if (col1 == col2 && row1 == row2){	// Both players competing for the same coin, give the coin to both players
        	short ch = _copyMap[row1][col1];
        	if ((ch & 16) != 0) {
	            _copyMap[row1][col1] = (short)(ch & 15);
            	_player1Score++;
            	_player2Score++;
            	_score1.setText(Integer.toString(_player1Score));
            	_score2.setText(Integer.toString(_player2Score));
	        }
        }else{
	        short ch1 = _copyMap[row1][col1];
	        short ch2 = _copyMap[row2][col2];
	        if ((ch1 & 16) != 0) {
	            _copyMap[row1][col1] = (short)(ch1 & 15);
            	_player1Score++;
            	_score1.setText(Integer.toString(_player1Score));
	        }
	        if ((ch2 & 16) != 0) {
	            _copyMap[row2][col2] = (short)(ch2 & 15);
            	_player2Score++;
            	_score2.setText(Integer.toString(_player2Score));
	        }
        }
    }
    
    
	

    public void checkEnergizer(){
    	int col1 = _player1.getCol();
        int row1 = _player1.getRow();
        int col2 = _player2.getCol();
        int row2 = _player2.getRow();
        
        if (col1 == col2 && row1 == row2){	// Both players competing for the same coin, give the coin to both players
        	short ch = _copyMap[row1][col1];
        	if ((ch & 32) != 0) {
	            _copyMap[row1][col1] = (short)(ch & 31);
            	//_player1Score++;
            	//_player2Score++;
            	//_score1.setText(Integer.toString(_player1Score));
            	//_score2.setText(Integer.toString(_player2Score));
	        }
        }else{ // player1 hits the energizer
	        short ch1 = _copyMap[row1][col1];
	        short ch2 = _copyMap[row2][col2];
	        if ((ch1 & 32) != 0) {
	            _copyMap[row1][col1] = (short)(ch1 & 31);
	            _player1.setEnergized(true);
	            _EnergizerTimer = new Timer(Constant.ENERGIZE_TIME, this); // set timer for 6 seconds
	            _EnergizerTimer.setActionCommand("DeEnergize");
	            _EnergizerTimer.start();
            	//_player1Score++;
            	//_score1.setText(Integer.toString(_player1Score));
	        }
	        if ((ch2 & 32) != 0) {
	            _copyMap[row2][col2] = (short)(ch2 & 15);
            	_player2Score++;
            	_score2.setText(Integer.toString(_player2Score));
	        }
        }
    }
    
    public void checkFruit(){
    	int col1 = _player1.getCol();
        int row1 = _player1.getRow();
        int col2 = _player2.getCol();
        int row2 = _player2.getRow();
        
        if (col1 == col2 && row1 == row2){	// Both players competing for the same coin, give the coin to both players
        	short ch = _copyMap[row1][col1];
        	if ((ch & 32) != 0) {
	            _copyMap[row1][col1] = (short)(ch & 31);
            	//_player1Score++;
            	//_player2Score++;
            	//_score1.setText(Integer.toString(_player1Score));
            	//_score2.setText(Integer.toString(_player2Score));
	        }
        }else{ // player1 hits the cherry
	        short ch1 = _copyMap[row1][col1];
	        short ch2 = _copyMap[row2][col2];
	        if ((ch1 & 64) != 0) {
	        	if (_drawCherry) {
	        	   _player1Score+=100;
	               _drawCherry = false;
	        	}  
	            //_EnergizerTimer = new Timer(Constant.ENERGIZE_TIME, this); // set timer for 6 seconds
	            //_EnergizerTimer.setActionCommand("DeEnergize");
	            //_EnergizerTimer.start();
            	//_player1Score++;
            	//_score1.setText(Integer.toString(_player1Score));
	        }
	        if ((ch2 & 32) != 0) {
	            _copyMap[row2][col2] = (short)(ch2 & 15);
            	_player2Score++;
            	_score2.setText(Integer.toString(_player2Score));
	        }
        }
    }
    
    public void getDirection(PacMan player){
    	int direction = player.move();
    	switch (direction){
    		case Constant.UP:
    			player.setReqdy(-1);
    			break;
    		case Constant.DOWN:
    			player.setReqdy(1);
    			break;
    		case Constant.LEFT:
    			player.setReqdx(-1);
    			break;
    		case Constant.RIGHT:
    			player.setReqdx(1);
    	}
    }

    public void CheckMaze() {
    	// Check to see if there are any dots left
        boolean finished = true;
        for (int i=0; i<Constant.NUMBER_OF_BLOCKS; i++){
        	for (int j=0; j<Constant.NUMBER_OF_BLOCKS; j++)
        	if ((_copyMap[i][j] & 16) != 0){
                finished = false;
                break;
        	}
        }

        // Win if there are no dots
        if (finished) {
            _timer.stop();
        }
    }

    public void moveGhosts(Graphics2D g2d) {
        for (int i = 0; i < Constant.NUMBER_OF_GHOSTS; i++) {
        	if (_ghosts[i].isDead()) 
        		continue;
            if (_ghosts[i].getx() % Constant.BLOCK_SIZE == 0 && _ghosts[i].gety() % Constant.BLOCK_SIZE == 0) {
                int posx = _ghosts[i].getx() / Constant.BLOCK_SIZE;
                int posy = _ghosts[i].gety() / Constant.BLOCK_SIZE;

                int count = 0;
                // Switch direction when it hits a wall
                if ((_copyMap[posy][posx] & 1) == 0 && _ghosts[i].getdx() != 1) {
                    _dx[count] = -1;
                    _dy[count] = 0;
                    count++;
                }
                if ((_copyMap[posy][posx] & 2) == 0 && _ghosts[i].getdy() != 1) {
                    _dx[count] = 0;
                    _dy[count] = -1;
                    count++;
                }
                if ((_copyMap[posy][posx] & 4) == 0 && _ghosts[i].getdx() != -1) {
                    _dx[count] = 1;
                    _dy[count] = 0;
                    count++;
                }
                if ((_copyMap[posy][posx] & 8) == 0 && _ghosts[i].getdy() != -1) {
                    _dx[count] = 0;
                    _dy[count] = 1;
                    count++;
                }
                
                // Wrap around left and right
                if (posx == 27 && posy == 14 && _ghosts[i].getdx() == 1){
                	_ghosts[i].setX(0);
                	_ghosts[i].setY(14*Constant.BLOCK_SIZE);
                }else if (posx == 0 && posy == 14 && _ghosts[i].getdx() == -1){
                	_ghosts[i].setX(27*Constant.BLOCK_SIZE);
                	_ghosts[i].setY(14*Constant.BLOCK_SIZE);
                }else{		// No walls hit
	                if (count == 0) {
	                    if ((_copyMap[posy][posx] & 15) == 15) {
	                        _ghosts[i].setdx(0);
	                        _ghosts[i].setdy(0);
	                    } else {
	                        _ghosts[i].setdx(-_ghosts[i].getdx());
	                        _ghosts[i].setdy(-_ghosts[i].getdy());
	                    }
	                } else {
	                    count = (int)(Math.random() * count);
	                    if (count > 3)
	                        count = 3;
	                    _ghosts[i].setdx(_dx[count]);
	                    _ghosts[i].setdy(_dy[count]);
	                }
                }

            }
            _ghosts[i].setX(_ghosts[i].getx() + _ghosts[i].getdx() * _ghosts[i].getSpeed());
            _ghosts[i].setY(_ghosts[i].gety() + _ghosts[i].getdy() * _ghosts[i].getSpeed());
            // if pac man is energized the ghosts get very scared!
            if (_player1.isEnergized()==false)            
               g2d.drawImage(_ghost, _ghosts[i].getx() + 1, _ghosts[i].gety() + 1, this);
            else
               g2d.drawImage(_ScaredGhost, _ghosts[i].getx() + 1, _ghosts[i].gety() + 1, this);
            
            // Check for collision with PacMan
            if (_player1.getX() > (_ghosts[i].getx() - 12) && _player1.getX() < (_ghosts[i].getx() + 12) &&
            		_player1.getY() > (_ghosts[i].gety() - 12) && _player1.getY() < (_ghosts[i].gety() + 12) &&
            		_ingame && !_player1.isDead()) {
            			if (_player1.isEnergized() == false) {
            				_player1.setDead(true);
            				playEatingGhostSound();
            				}
            			else
            			{
            				_ghosts[i].setDead(true);
            			}
            }
            
            if (_player2.getX() > (_ghosts[i].getx() - 12) && _player2.getX() < (_ghosts[i].getx() + 12) &&
            		_player2.getY() > (_ghosts[i].gety() - 12) && _player2.getY() < (_ghosts[i].gety() + 12) &&
                    _ingame && !_player2.isDead()) {
            			_player2.setDead(true);
            			playDeathSound();
            }
        }
    }

    public void MovePacMan(PacMan player) {
    	// Check if direction changed in between blocks from left to right or up to down
    	if (player.getReqdx() == -player.getdx() && player.getReqdy() == -player.getdy()) {
    		player.setdx(player.getReqdx());
            player.setdy(player.getReqdy());
            player.setViewX(player.getReqdx());
            player.setViewY(player.getReqdy());
        }
        
        if (player.getX() % Constant.BLOCK_SIZE == 0 && player.getY() % Constant.BLOCK_SIZE == 0) {
            int col = player.getCol();
            int row = player.getRow();
            short ch = _copyMap[row][col];
            
            // Wrap around left and right
            if (col == 27 && row == 14 && player.getReqdx() == 1){
            	player.setX(0);
            	player.setY(14*Constant.BLOCK_SIZE);
            }else if (col == 0 && row == 14 && player.getReqdx() == -1){
            	player.setX(27*Constant.BLOCK_SIZE);
            	player.setY(14*Constant.BLOCK_SIZE);
            }

            // Change direction if no walls
            if (player.getReqdx() != 0 || player.getReqdy() != 0) {
                if (!((player.getReqdx() == -1 && player.getReqdy() == 0 && (ch & 1) != 0) ||
                      (player.getReqdx() == 1 && player.getReqdy() == 0 && (ch & 4) != 0) ||
                      (player.getReqdx() == 0 && player.getReqdy() == -1 && (ch & 2) != 0) ||
                      (player.getReqdx() == 0 && player.getReqdy() == 1 && (ch & 8) != 0))) {
                    player.setdx(player.getReqdx());
                    player.setdy(player.getReqdy());
                    player.setViewX(player.getReqdx());
                    player.setViewY(player.getReqdy());
                }
            }

            // Check for standstill
            if ((player.getdx() == -1 && player.getdy() == 0 && (ch & 1) != 0) ||
                (player.getdx() == 1 && player.getdy() == 0 && (ch & 4) != 0) ||
                (player.getdx() == 0 && player.getdy() == -1 && (ch & 2) != 0) ||
                (player.getdx() == 0 && player.getdy() == 1 && (ch & 8) != 0)) {
            	player.setdx(0);
            	player.setdy(0);
            }
        }
        
        // Move to new location
        player.setX(player.getX() + Constant.PACMAN_SPEED * player.getdx());
        player.setY(player.getY() + Constant.PACMAN_SPEED * player.getdy());
    }

    public void DrawPacMan(Graphics2D g2d, PacMan player) {
        if (player.getViewx() == -1)
            DrawPacManLeft(g2d, player);
        else if (player.getViewx() == 1)
            DrawPacManRight(g2d, player);
        else if (player.getViewy() == -1)
            DrawPacManUp(g2d, player);
        else
            DrawPacManDown(g2d, player);
    }

    public void DrawPacManUp(Graphics2D g2d, PacMan player) {
    	Image[] image = player.getUp();
        switch (_pacmanAnimPos) {
	        case 1:
	            g2d.drawImage(image[0], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 2:
	            g2d.drawImage(image[1], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 3:
	            g2d.drawImage(image[2], player.getX() + 1, player.getY() + 1, this);
	            break;
	        default:
	            g2d.drawImage(player.getPacman(), player.getX() + 1, player.getY() + 1, this);
	            break;
        }
    }

    public void DrawPacManDown(Graphics2D g2d, PacMan player) {
    	Image[] image = player.getDown();
        switch (_pacmanAnimPos) {
	        case 1:
	            g2d.drawImage(image[0], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 2:
	            g2d.drawImage(image[1], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 3:
	            g2d.drawImage(image[2], player.getX() + 1, player.getY() + 1, this);
	            break;
	        default:
	            g2d.drawImage(player.getPacman(), player.getX() + 1, player.getY() + 1, this);
	            break;
        }
    }

    public void DrawPacManLeft(Graphics2D g2d, PacMan player) {
    	Image[] image = player.getLeft();
        switch (_pacmanAnimPos) {
	        case 1:
	            g2d.drawImage(image[0], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 2:
	            g2d.drawImage(image[1], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 3:
	            g2d.drawImage(image[2], player.getX() + 1, player.getY() + 1, this);
	            break;
	        default:
	            g2d.drawImage(player.getPacman(), player.getX() + 1, player.getY() + 1, this);
	            break;
        }
    }

    public void DrawPacManRight(Graphics2D g2d, PacMan player) {
    	Image[] image = player.getRight();
        switch (_pacmanAnimPos) {
	        case 1:
	            g2d.drawImage(image[0], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 2:
	            g2d.drawImage(image[1], player.getX() + 1, player.getY() + 1, this);
	            break;
	        case 3:
	            g2d.drawImage(image[2], player.getX() + 1, player.getY() + 1, this);
	            break;
	        default:
	            g2d.drawImage(player.getPacman(), player.getX() + 1, player.getY() + 1, this);
	            break;
        }
    }

    public void DrawMaze(Graphics2D g2d) {
    	for (int i=0; i<_copyMap.length; i++){
    		for (int j=0; j<_copyMap.length; j++){
    			g2d.setColor(Constant.MAZE_COLOR);
                g2d.setStroke(new BasicStroke(2));
                int row = j*Constant.BLOCK_SIZE;
                int col = i*Constant.BLOCK_SIZE;
                
        	    // draws left
                if ((_copyMap[i][j] & 1) != 0){
                    g2d.drawLine(row, col, row, col+Constant.BLOCK_SIZE);
                }
                
                // draws top
                if ((_copyMap[i][j] & 2) != 0){
                    g2d.drawLine(row, i*Constant.BLOCK_SIZE, row+Constant.BLOCK_SIZE, col);
                }
                
                // draws right
                if ((_copyMap[i][j] & 4) != 0) {
                    g2d.drawLine(row+Constant.BLOCK_SIZE, col, row+Constant.BLOCK_SIZE, col+Constant.BLOCK_SIZE);
                }
                
                // draws bottom
                if ((_copyMap[i][j] & 8) != 0) {
                    g2d.drawLine(row, col+Constant.BLOCK_SIZE, row+Constant.BLOCK_SIZE, col+Constant.BLOCK_SIZE);
                }
                
                // draws point
                if ((_copyMap[i][j] & 16) != 0) {
                    g2d.setColor(Constant.DOT_COLOR);
                    g2d.fillRect(row+11, col+11, 3, 3);
                }    
                    
                // draws energizer
                if ((_copyMap[i][j] & 32) != 0) {
                    g2d.setColor(Constant.ENERGIZER_COLOR);
                    g2d.fillOval(row+3, col+3, 15, 15);
                }
                
                // draws fruit
                if ((_copyMap[i][j] & 64) != 0) {
                	if (_drawCherry) 
                	   g2d.drawImage(_cherry, row+12, col, this);
                    //g2d.setColor(Constant.ENERGIZER_COLOR);
                    //g2d.fillOval(row+3, col+3, 15, 15);
                	
                }
               
                }
    		}
    	}
   

    public void GameInit() {
    	_player1Score = 0;
 //   	_player2Score = 0;
    	_score1.setText("0");
//    	_score2.setText("0");
        copyMap();
        createGhosts();
        createPlayers();
    	GetImages();
    }
    
    public void clearScore(){
    	_player1Score = 0;
 //   	_player2Score = 0;
    	_player1Wins = 0;
 //   	_player2Wins = 0;
    	_score1.setText("0");
 //   	_score2.setText("0");
    	_wins1.setText("0");
 //   	_wins2.setText("0");
    }
    
    public void copyMap(){
    	for (int i = 0; i < _map.length; i++){
        	for (int j=0; j<_map.length; j++){
        		_copyMap[i][j] = _map[i][j];
        	}
        }
    }
    
    public void createGhosts(){
    	int dx = 1;
        int loc = 15 * Constant.BLOCK_SIZE;
        for (int i = 0; i < Constant.NUMBER_OF_GHOSTS; i++) {
            int random = (int)(Math.random() * 4);
            int speed = Constant.VALID_SPEEDS[random];
            _ghosts[i] = new Ghost(loc, loc, dx, 0, speed);
            dx = -dx;
        }
    }
    
    public void createPlayers(){
        _player1 = new PacMan1(_proxyBoard);
        _player1.init();
        _player1.setX(9*Constant.BLOCK_SIZE);
        _player1.setY(14*Constant.BLOCK_SIZE);
        _player1.setdx(0);
        _player1.setdy(0);
        _player1.setViewX(-1);
        _player1.setViewY(0);
        _player1.setReqdx(0);
        
        _player2 = new PacMan2(_proxyBoard);
        _player2.init();
        _player2.setX(18*Constant.BLOCK_SIZE);
        _player2.setY(14*Constant.BLOCK_SIZE);
        _player2.setdx(0);
        _player2.setdy(0);
        _player2.setViewX(-1);
        _player2.setViewY(0);
        _player2.setReqdx(0);
    }

    public void GetImages(){
    	// Image for ghosts
    	_ghost = new ImageIcon(Board.class.getResource("/pacpix/ghost.png")).getImage();
    	_ScaredGhost = new ImageIcon(Board.class.getResource("/pacpix/scaredghost.png")).getImage();
    	_cherry = new ImageIcon(Board.class.getResource("/pacpix/cherry.png")).getImage();
    	
    	// Image for player 1's PacMan
    	Image pacman1 = new ImageIcon(Board.class.getResource("/pacpix/p1pacman.png")).getImage();
    	
    	// Images for player 1 going up
    	Image[] up1 = new Image[3];
    	up1[0] = new ImageIcon(Board.class.getResource("/pacpix/p1up1.png")).getImage();
    	up1[1] = new ImageIcon(Board.class.getResource("/pacpix/p1up2.png")).getImage();
    	up1[2] = new ImageIcon(Board.class.getResource("/pacpix/p1up3.png")).getImage();
    	
    	// Images for player 1 going down
    	Image[] down1 = new Image[3];
    	down1[0] = new ImageIcon(Board.class.getResource("/pacpix/p1down1.png")).getImage();
    	down1[1] = new ImageIcon(Board.class.getResource("/pacpix/p1down2.png")).getImage(); 
    	down1[2] = new ImageIcon(Board.class.getResource("/pacpix/p1down3.png")).getImage();
    	
    	// Images for player 1 going left
    	Image[] left1 = new Image[3];
    	left1[0] = new ImageIcon(Board.class.getResource("/pacpix/p1left1.png")).getImage();
    	left1[1] = new ImageIcon(Board.class.getResource("/pacpix/p1left2.png")).getImage();
    	left1[2] = new ImageIcon(Board.class.getResource("/pacpix/p1left3.png")).getImage();
    	
    	// Images for player 1 going right
    	Image[] right1 = new Image[3];
    	right1[0] = new ImageIcon(Board.class.getResource("/pacpix/p1right1.png")).getImage();
    	right1[1] = new ImageIcon(Board.class.getResource("/pacpix/p1right2.png")).getImage();
    	right1[2] = new ImageIcon(Board.class.getResource("/pacpix/p1right3.png")).getImage();
    	
    	// Set player 1's images
    	_player1.setImages(pacman1, up1, down1, left1, right1);
    	
    	// Image for player 2's PacMan
    	Image pacman2 = new ImageIcon(Board.class.getResource("/pacpix/p2pacman.png")).getImage();
    	
    	// Images for player 2 going up
    	Image[] up2 = new Image[3];
    	up2[0] = new ImageIcon(Board.class.getResource("/pacpix/p2up1.png")).getImage();
    	up2[1] = new ImageIcon(Board.class.getResource("/pacpix/p2up2.png")).getImage();
    	up2[2] = new ImageIcon(Board.class.getResource("/pacpix/p2up3.png")).getImage();
    	
    	// Images for player 2 going down
    	Image[] down2 = new Image[3];
    	down2[0] = new ImageIcon(Board.class.getResource("/pacpix/p2down1.png")).getImage();
    	down2[1] = new ImageIcon(Board.class.getResource("/pacpix/p2down2.png")).getImage(); 
    	down2[2] = new ImageIcon(Board.class.getResource("/pacpix/p2down3.png")).getImage();
    	
    	// Images for player 2 going left
    	Image[] left2 = new Image[3];
    	left2[0] = new ImageIcon(Board.class.getResource("/pacpix/p2left1.png")).getImage();
    	left2[1] = new ImageIcon(Board.class.getResource("/pacpix/p2left2.png")).getImage();
    	left2[2] = new ImageIcon(Board.class.getResource("/pacpix/p2left3.png")).getImage();
    	
    	// Images for player 2 going right
    	Image[] right2 = new Image[3];
    	right2[0] = new ImageIcon(Board.class.getResource("/pacpix/p2right1.png")).getImage();
    	right2[1] = new ImageIcon(Board.class.getResource("/pacpix/p2right2.png")).getImage();
    	right2[2] = new ImageIcon(Board.class.getResource("/pacpix/p2right3.png")).getImage();
    	
    	// Set player 2's images
    	_player2.setImages(pacman2, up2, down2, left2, right2);
    }
    
    public void playSound(){
        try{
        	URL url = this.getClass().getClassLoader().getResource("pacman/waka.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            _clip = AudioSystem.getClip();
            _clip.open(audioInputStream);
            _clip.setLoopPoints(4000, -1);
            _clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    
    public void playDeathSound(){
        try{
        	URL url = this.getClass().getClassLoader().getResource("pacman/death.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch(Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    
    public void playIntroSound(){
        try{
        	URL url = this.getClass().getClassLoader().getResource("pacman/pacman_beginning.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch(Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    
    public void playEatingGhostSound(){
        try{
        	URL url = this.getClass().getClassLoader().getResource("pacman/pacman_eatghost.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch(Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    
    public int[][] proxyMap(){
    	int[][] proxyMap = new int[_copyMap.length][_copyMap.length];
    	for (int i=0; i<_basicMap.length; i++){
    		for (int j=0; j<_basicMap.length; j++){
    			proxyMap[i][j] = _basicMap[i][j];
    		}
    	}
    	
    	for (int i=0; i<_copyMap.length; i++){
    		for (int j=0; j<_copyMap.length; j++){
    			if ((_copyMap[i][j] & 16) != 0){
    				proxyMap[i][j] = Constant.COIN;
    			}else if(_basicMap[i][j] == 0){
    				proxyMap[i][j] = Constant.PATH;
    			}
    		}
    	}
    	return proxyMap;
    }
    
    public Ghost[] getGhosts(){return _ghosts;}
    public int getPlayer1X(){return _player1.getX();}
    public int getPlayer1Y(){return _player1.getY();}
    public int getPlayer2X(){return _player2.getX();}
    public int getPlayer2Y(){return _player2.getY();}
    public boolean isPlayer1Dead(){return _player1.isDead();}
    public boolean isPlayer2Dead(){return _player2.isDead();}

    public void paint(Graphics g){
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;

    	g2d.setColor(Color.BLACK);
    	g2d.fillRect(0, 0, 700, 700);

    	DrawMaze(g2d);
    	DoAnim();
    	if (_ingame)
    		PlayGame(g2d);
    	Toolkit.getDefaultToolkit().sync();
    	g.dispose();
    }
    
    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand().equals("Go")){
    		_timer.start();
    		if (_clip != null) _clip.stop();
    		playSound();
    		_ingame = true;
  		  	GameInit();
  	        //Start the fruit timer
  	        _drawCherry = false;
  	        int fruitTime = (int)(Math.random() * Constant.MAX_FRUIT_TIME);
  	        _FruitTimer = new Timer(fruitTime, this); // set timer for random between 0 and 10 seconds
  	        _FruitTimer.setActionCommand("DrawFruit");
  	        _FruitTimer.start();
    	}else if(e.getActionCommand().equals("Restart")){
    		_timer.start();
    		if (_clip != null) _clip.stop();
    		_ingame = false;
    		clearScore();
    		GameInit();
    	}else if(e.getActionCommand().equals("Timer")){
    		requestFocus();
    		repaint();
    	}
    	// pac man's energizer timer went off
    	else if(e.getActionCommand().equals("DeEnergize")){
    	   _player1.setEnergized(false); // pac man is no longer energized
    	   
    	}else if(e.getActionCommand().equals("DrawFruit")){
     	   _drawCherry = true; // draw the cherry
    	}
    }

    private class TAdapter extends KeyAdapter {
    	public void keyPressed(KeyEvent e) {
    		int key = e.getKeyCode();
    		if (_ingame){
    			if (key == KeyEvent.VK_LEFT){
    				_player1.setReqdx(-1);
    			}else if (key == KeyEvent.VK_RIGHT){
    				_player1.setReqdx(1);
    			}else if (key == KeyEvent.VK_UP){
    				_player1.setReqdy(-1);
    			}else if (key == KeyEvent.VK_DOWN){
    				_player1.setReqdy(1);
    			}else if (key == KeyEvent.VK_ESCAPE && _timer.isRunning()){
    				_ingame = false;
    			}
    		}
    	}
    }
}