package pacman;

public class Ghost {
	private int _x, _y, _dx, _dy, _speed;
	private boolean _dead;

	public Ghost(int x, int y, int dx, int dy, int speed){
		_x = x;
		_y = y;
		_dx = dx;
		_dy = dy;
		_speed = speed;
		_dead = false;
	}
	
	public int getx(){return _x;}
	public int gety(){return _y;}
	public int getRow(){return _y/Constant.BLOCK_SIZE;}
	public int getCol(){return _x/Constant.BLOCK_SIZE;}
	public int getdx(){return _dx;}
	public int getdy(){return _dy;}
	public int getSpeed(){return _speed;}
	public boolean isDead() {return _dead;}
	public void setX(int x){_x = x;}
	public void setY(int y){_y = y;}
	public void setdx(int dx){_dx = dx;}
	public void setdy(int dy){_dy = dy;}
	public void setSpeed(int speed){_speed = speed;}
	public void setDead(boolean dead){_dead = dead;}
}
