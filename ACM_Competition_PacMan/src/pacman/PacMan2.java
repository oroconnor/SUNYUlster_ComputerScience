package pacman;

/** 
 * 
 * Possible methods to use:
 * 		- public int[][] getMap()
 * 			This method returns a 2d array of int which is the entire map.
 * 			What you can do with this is:
 * 				int[][] map = getMap();
 * 				if (map[2][3] == Constant.COIN)		//This checks if row 2 column 3 has a coin
 * 				if (map[2][3] == Constant.WALL)		//This checks if row 2 column 3 is a wall
 * 				if (map[2][3] == Constant.PATH		//This checks if row 2 column 3 is a path
 * 
 * 		- public int getPlayer1X(), public int getPlayer1Y()
 * 			These methods return the x and y coordinate of Player 1 (yellow) in terms
 * 			of pixels. For example:
 * 				int x = getPlayer1X();
 * 				int y = getPlayer1Y();
 * 
 *		- public int getPlayer2X(), public int getPlayer2Y()
 * 			These methods return the x and y coordinate of Player 2 (teal) in terms
 * 			of pixels. For example:
 * 				int x = getPlayer2X();
 * 				int y = getPlayer2Y();
 * 
 * 		- public int getPLayer1Row(), public int getPLayer1Col()
 * 			This method returns the current location of Player 1 (yellow) in terms
 * 			of the index to the 2d map. Warning: it is possible that the pacman is in between
 * 			2 blocks, in which case, this method will round down to the nearest block.
 * 				int row = getPLayer1Row();
 * 				int col = getPLayer1Col();
 * 				int[][] map = getMap();	// map[row][col] = Player1 location
 * 
 * 		- public int getPLayer2Row(), public int getPLayer2Col()
 * 			This method returns the current location of Player 2 (teal) in terms
 * 			of the index to the 2d map. Warning: it is possible that the pacman is in between
 * 			2 blocks, in which case, this method will round down to the nearest block.
 * 				int row = getPLayer2Row();
 * 				int col = getPLayer2Col();
 * 				int[][] map = getMap();	// map[row][col] = Player2 location
 * 
 * 		- public boolean isPlayer1Dead()
 * 			This method returns true if player 1 (yellow) is dead. False otherwise.
 * 				boolean test = isPlayer1Dead();
 * 		
 * 		- public boolean isPlayer2Dead()
 * 			This method returns true if player 2 (teal) is dead. False otherwise.
 * 				boolean test = isPlayer2Dead();
 * 
 * 		- public boolean inBlock()
 * 			This method returns true if the pacman is inside a particular block. False
 * 			if the pacman is in between two blocks.
 * 
 * 		- public Ghost[] getGhosts()
 * 			This method returns an array of ghosts. For each ghost, you can get its location and direction.
 * 				Ghost[] ghosts = getGhosts();
 * 				for (int i=0; i<ghosts.length; i++){
 * 					int x = ghosts[i].getx();		// get the x coordinate position of the ghost
 * 					int y = ghosts[i].gety();		// get the y coordinate position of the ghost
 * 					int row = ghosts[i].getRow();	// get the row index to the 2d map
 * 					int col = ghosts[i].getCol();	// get the column index to the 2d map
 * 					int dx = ghosts[i].getdx();		// 1 if ghost is going right, -1 if ghost is going left, 0 if neither
 * 					int dy = ghosts[i].getdy();		// 1 if ghost is going down, -1 if ghost is going up, 0 if neither
 * 				}
 * 
 */

public class PacMan2 extends PacMan{
	private int _prevDir;
	
	/**
	 * Constructor: This gets called from the start of each round.
	 */
	public void init(){
		_prevDir = Constant.DOWN;
	}
	
	/**
	 * @return Returns the direction that the pacman will move on its next turn.
	 * Possible return directions:
	 * 		return Constant.UP; 		// or return 1;
	 * 		return Constant.DOWN;		// or return 2;
	 * 		return Constant.LEFT;		// or return 3;
	 * 		return Constant.RIGHT;		// or return 4;
	 * 		return Constant.CONTINUE	// or return 5;
	 * 
	 * Return Constant.CONTINUE if you want the pacman to continue moving at its current direction.
	 * If you return UP, and UP is not a valid direction (will hit a wall), then the pacman will continue moving
	 * at its current direction instead.
	 */
	public int move(){
		if (inBlock()) {
			return (int) ((Math.random()*4)+1);
		}else{
			return Constant.CONTINUE;
		}
	}
	
	//DO NOT edit the following:
	public PacMan2(ProxyBoard board){super(board);}
	public boolean isPlayer1(){return false;}
}
