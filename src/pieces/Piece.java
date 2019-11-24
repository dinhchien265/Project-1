package pieces;
import game.*;

import javax.swing.*;

import java.util.ArrayList;

public abstract class Piece {
	public static final boolean WHITE = true, BLACK = false;
	protected boolean color;
	protected int value;
	public ImageIcon imageIcon;
	
	public boolean getColor() {
		return color;
	}

	public Piece(boolean color) {
		this.color = color;
		value = 0;
	}
	
	public int getValue() {
		return value;
	}
	
	public abstract Piece clone();
	
	public abstract ArrayList<Move> getMoves(Board b, int x, int y);
	public abstract ArrayList<Move> getOpenFire(Board b,int x,int y);

	static public boolean valid(int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7)
			return false;
		else
			return true;
	}
}
