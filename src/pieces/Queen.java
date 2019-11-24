package pieces;

import java.util.ArrayList;

import game.Board;
import game.Move;


public class Queen extends Piece {
	Rook rook;
	Bishop bishop;


	public Queen(boolean color) {
		super(color);
		value = 9;
		rook=new Rook(color);
		bishop=new Bishop(color);
	}

	public String toString() {
		if(color == Piece.WHITE)
			return "Q";
		else
			return "q";
	}
	
	public Queen clone() {
		return new Queen(color);
	}
	
	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		moves.addAll(rook.getMoves(b,x,y));
		moves.addAll(bishop.getMoves(b,x,y));
		return moves;
	}

	@Override
	public ArrayList<Move> getOpenFire(Board b, int x, int y) {
		ArrayList<Move> openFire=new ArrayList<>();
		openFire.addAll(rook.getOpenFire(b,x,y));
		openFire.addAll(bishop.getOpenFire(b,x,y));
		return openFire;
	}
}
