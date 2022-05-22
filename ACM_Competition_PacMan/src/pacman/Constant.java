package pacman;

import java.awt.Color;

	interface Constant {
	    public static final Color DOT_COLOR = Color.YELLOW;
	    public static final Color MAZE_COLOR = Color.BLUE;
	    public static final Color ENERGIZER_COLOR = Color.PINK;
	    public static final byte BLOCK_SIZE = 24;
	    public static final byte NUMBER_OF_BLOCKS = 28;
	    public static final byte PACMAN_ANIM_DELAY = 2;
	    public static final byte PACMAN_ANIM_COUNT = 4;
	    public static final byte PACMAN_SPEED = 6;
	    public static final byte VALID_SPEEDS[] = {1, 2, 3, 4};
	    public static final byte NUMBER_OF_GHOSTS = 4;
	    public static final int ENERGIZE_TIME = 5000;
	    public static final int MAX_FRUIT_TIME = 10000;
	    public static final byte UP = 1;
	    public static final byte DOWN = 2;
	    public static final byte LEFT = 3;
	    public static final byte RIGHT = 4;
	    public static final byte CONTINUE = 5;
	    public static final byte WALL = 1;
	    public static final byte COIN = 2;
	    public static final byte PATH = 0;
	    
    
}
