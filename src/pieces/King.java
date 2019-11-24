package pieces;

import java.util.ArrayList;

import game.Board;
import game.Move;


public class King extends Piece {
	boolean hasMoved = false;


	public King(boolean color) {
		super(color);
		value = 0;
	}
	
	public King(boolean color, boolean hasMoved) {
		super(color);
		this.hasMoved = hasMoved;
		value = 10000;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public King clone() {
		return new King(color, hasMoved);
	}
	
	public String toString() {
		if(color == Piece.WHITE)
			return "K";
		else
			return "k";
	}


	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();

		if(valid(x, y+1) && 
				(!b.getTile(x, y+1).isOccupied() || 
						(b.getTile(x, y+1).isOccupied() && b.getTile(x, y+1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x,y+1));

		if(valid(x+1, y+1) && 
				(!b.getTile(x+1, y+1).isOccupied() || 
						(b.getTile(x+1, y+1).isOccupied() && b.getTile(x+1, y+1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x+1,y+1));

		if(valid(x+1,y) && 
				(!b.getTile(x+1,y).isOccupied() || 
						(b.getTile(x+1,y).isOccupied() && b.getTile(x+1,y).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x+1,y));

		if(valid(x+1,y-1) && 
				(!b.getTile(x+1,y-1).isOccupied() || 
						(b.getTile(x+1,y-1).isOccupied() && b.getTile(x+1,y-1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x+1,y-1));

		if(valid(x,y-1) && 
				(!b.getTile(x,y-1).isOccupied() || 
						(b.getTile(x,y-1).isOccupied() && b.getTile(x,y-1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x,y-1));

		if(valid(x-1,y-1) && 
				(!b.getTile(x-1,y-1).isOccupied() || 
						(b.getTile(x-1,y-1).isOccupied() && b.getTile(x-1,y-1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x-1,y-1));

		if(valid(x-1,y) && 
				(!b.getTile(x-1,y).isOccupied() || 
						(b.getTile(x-1,y).isOccupied() && b.getTile(x-1,y).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x-1,y));

		if(valid(x-1,y+1) && 
				(!b.getTile(x-1,y+1).isOccupied() || 
						(b.getTile(x-1,y+1).isOccupied() && b.getTile(x-1,y+1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x-1,y+1));

		// Nhap thanh
		if(color == Piece.WHITE) {
			if(!hasMoved && x == Board.e && y == 1-1) {
				if(!b.getTile(Board.f, 1-1).isOccupied() &&
						!isonopenfire(b,Board.f,1-1)&&
						!b.getTile(Board.g, 1-1).isOccupied() &&
						!isonopenfire(b,Board.g,1-1)&&
						!isonopenfire(b,x,y)&&
						b.getTile(Board.h, 1-1).isOccupied() &&
						b.getTile(Board.h, 1-1).getPiece().toString().equals("R") &&
						!((Rook)b.getTile(Board.h,1-1).getPiece()).isHasMoved()
				)
					moves.add(new Move(x,y,x+2,y));
				if(!b.getTile(Board.b, 1-1).isOccupied() &&
						!b.getTile(Board.c, 1-1).isOccupied() &&
						!isonopenfire(b,Board.c,1-1)&&
						!b.getTile(Board.d, 1-1).isOccupied() &&
						!isonopenfire(b,Board.d,1-1)&&
						!isonopenfire(b,x,y)&&
						b.getTile(Board.a, 1-1).isOccupied() &&
						b.getTile(Board.a, 1-1).getPiece().toString().equals("R")&&
						!((Rook)b.getTile(Board.a,1-1).getPiece()).isHasMoved()
				)
					moves.add(new Move(x,y,x-2,y));
						
			}
		}
		else {
			if(!hasMoved && x == Board.e && y == 8-1) {
				if(!b.getTile(Board.f, 8-1).isOccupied() &&
						!isonopenfire(b,Board.f,8-1)&&
						!b.getTile(Board.g, 8-1).isOccupied() &&
						!isonopenfire(b,Board.g,8-1)&&
						!isonopenfire(b,x,y)&&
						b.getTile(Board.h, 8-1).isOccupied() &&
						b.getTile(Board.h, 8-1).getPiece().toString().equals("r") &&
						!((Rook)b.getTile(Board.h,8-1).getPiece()).isHasMoved()
				)
					moves.add(new Move(x,y,x+2,y));
				if(!b.getTile(Board.b, 8-1).isOccupied() &&
						!b.getTile(Board.c, 8-1).isOccupied() &&
						!isonopenfire(b,Board.c,8-1)&&
						!b.getTile(Board.d, 8-1).isOccupied() &&
						!isonopenfire(b,Board.d,8-1)&&
						!isonopenfire(b,x,y)&&
						b.getTile(Board.a, 8-1).isOccupied() &&
						b.getTile(Board.a, 8-1).getPiece().toString().equals("r")&&
						!((Rook)b.getTile(Board.a,8-1).getPiece()).isHasMoved()
				)
					moves.add(new Move(x,y,x-2,y));

			}

		}

		return moves;
	}
	private boolean isonopenfire(Board b,int x,int y){
		ArrayList<Move> opponentMoves =b.getMovesWithoutCastling(!color);
		for (int i = 0; i < opponentMoves.size(); i++) {
			if(opponentMoves.get(i).getX2()==x&&opponentMoves.get(i).getY2()==y)
				return true;
		}
		return false;
	}

	@Override
	public ArrayList<Move> getOpenFire(Board b, int x, int y) {
		return null;
	}
}
