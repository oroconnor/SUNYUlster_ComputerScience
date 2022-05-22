package pacman;

import java.awt.Image;

public abstract class PacMan {
	private int _x, _y, _dx, _dy, _viewx, _viewy, _reqdx, _reqdy;
	private Image _pacman;
	private Image[] _up, _down, _left, _right;
	private ProxyBoard _board;
	private boolean _dead;
	private boolean _isEnergized;
	
	public PacMan(ProxyBoard board){_board = board; _dead = false;}
	public abstract boolean isPlayer1();
	public abstract void init();
	public abstract int move();
	public boolean isDead(){return _dead;}
	public boolean isPlayer1Dead(){return _board.isPlayer1Dead();}
	public boolean isPlayer2Dead(){return _board.isPlayer2Dead();}
	public boolean inBlock(){return getX() % Constant.BLOCK_SIZE == 0 && getY() % Constant.BLOCK_SIZE == 0;}
	public int getX(){return _x;}
	public int getY(){return _y;}
	public int getRow(){return _y/Constant.BLOCK_SIZE;}
	public int getCol(){return _x/Constant.BLOCK_SIZE;}
	public int getdx(){return _dx;}
	public int getdy(){return _dy;}
	public int getPlayer1X(){return _board.getPlayer1X();}
	public int getPlayer1Y(){return _board.getPlayer1Y();}
	public int getPlayer2X(){return _board.getPlayer2X();}
	public int getPlayer2Y(){return _board.getPlayer2Y();}
	public int getPLayer1Row(){return getPlayer1Y()/Constant.BLOCK_SIZE;}
	public int getPLayer1Col(){return getPlayer1X()/Constant.BLOCK_SIZE;}
	public int getPLayer2Row(){return getPlayer2Y()/Constant.BLOCK_SIZE;}
	public int getPLayer2Col(){return getPlayer2X()/Constant.BLOCK_SIZE;}
	public int getViewx(){return _viewx;}
	public int getViewy(){return _viewy;}
	public int getReqdx(){return _reqdx;}
	public int getReqdy(){return _reqdy;}
	public Image getPacman(){return _pacman;}
	public Image[] getUp(){return _up;}
	public Image[] getDown(){return _down;}
	public Image[] getLeft(){return _left;}
	public Image[] getRight(){return _right;}
	public int[][] getMap(){return _board.getMap();}
	public Ghost[] getGhosts(){return _board.getGhosts();}
	public boolean isEnergized(){return _isEnergized;}
	public void setDead(boolean dead){_dead = dead;}
	public void setEnergized(boolean isEnergized){_isEnergized = isEnergized;}
	public void setX(int x){_x = x;}
	public void setY(int y){_y = y;}
	public void setdx(int dx){_dx = dx;}
	public void setdy(int dy){_dy = dy;}
	public void setViewX(int viewx){_viewx = viewx;}
	public void setViewY(int viewy){_viewy = viewy;}
	public void setReqdx(int reqdx){_reqdx = reqdx; _reqdy = 0;}
	public void setReqdy(int reqdy){_reqdy = reqdy; _reqdx = 0;}
	public void setImages(Image pacman, Image[] up, Image[] down, Image[] left, Image[] right){_pacman = pacman; _up = up; _down = down; _left = left; _right = right;}
}
