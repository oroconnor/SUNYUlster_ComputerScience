package pacman;

public class ProxyBoard {
	private Board _board;
	
	public ProxyBoard(Board board){_board = board;}
	public int[][] getMap(){return _board.proxyMap();}
	public Ghost[] getGhosts(){return _board.getGhosts();}
	public int getPlayer1X(){return _board.getPlayer1X();}
	public int getPlayer1Y(){return _board.getPlayer1Y();}
	public int getPlayer2X(){return _board.getPlayer2X();}
	public int getPlayer2Y(){return _board.getPlayer2Y();}
	public boolean isPlayer1Dead(){return _board.isPlayer1Dead();}
	public boolean isPlayer2Dead(){return _board.isPlayer2Dead();}
}
