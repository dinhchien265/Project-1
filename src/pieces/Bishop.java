package pieces;
import game.*;
import java.util.ArrayList;



public class Bishop extends Piece {

	public Bishop(boolean color) {
		super(color);
		value = 3;
	}
	
	public String toString() {
		if(color == Piece.WHITE)
			return "B";
		else
			return "b";
	}
	
	public Bishop clone() {
		return new Bishop(color);
	}

	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();
		

		for(int i = 1; i < 8; i++) {
			if(valid(x+i, y+i)) {
				if(b.getTile(x+i, y+i).isOccupied()) {
					if(b.getTile(x+i, y+i).getPiece().color != color)
						moves.add(new Move(x,y,x+i,y+i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x+i,y+i));	
			}
		}

		for(int i = 1; i < 8; i++) {
			if(valid(x-i, y+i)) {
				if(b.getTile(x-i, y+i).isOccupied()) {
					if(b.getTile(x-i, y+i).getPiece().color != color)
						moves.add(new Move(x,y,x-i,y+i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x-i,y+i));	
			}
		}

		for(int i = 1; i < 8; i++) {
			if(valid(x+i, y-i)) {
				if(b.getTile(x+i, y-i).isOccupied()) {
					if(b.getTile(x+i, y-i).getPiece().color != color)
						moves.add(new Move(x,y,x+i,y-i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x+i,y-i));	
			}
		}

		for(int i = 1; i < 8; i++) {
			if(valid(x-i, y-i)) {
				if(b.getTile(x-i, y-i).isOccupied()) {
					if(b.getTile(x-i, y-i).getPiece().color != color)
						moves.add(new Move(x,y,x-i,y-i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x-i,y-i));	
			}
		}
		
		return moves;
	}

	@Override
	public ArrayList<Move> getOpenFire(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();


		for(int i = 1; i < 8; i++) {
			if(valid(x+i, y+i)) {
				if(b.getTile(x+i, y+i).isOccupied()) {
					moves.add(new Move(x,y,x+i,y+i));
					break;
				}
				else
					moves.add(new Move(x,y,x+i,y+i));
			}
		}

		for(int i = 1; i < 8; i++) {
			if(valid(x-i, y+i)) {
				if(b.getTile(x-i, y+i).isOccupied()) {
					moves.add(new Move(x,y,x-i,y+i));
					break;
				}
				else
					moves.add(new Move(x,y,x-i,y+i));
			}
		}

		for(int i = 1; i < 8; i++) {
			if(valid(x+i, y-i)) {
				if(b.getTile(x+i, y-i).isOccupied()) {
					moves.add(new Move(x,y,x+i,y-i));
					break;
				}
				else
					moves.add(new Move(x,y,x+i,y-i));
			}
		}

		for(int i = 1; i < 8; i++) {
			if(valid(x-i, y-i)) {
				if(b.getTile(x-i, y-i).isOccupied()) {
					moves.add(new Move(x,y,x-i,y-i));
					break;
				}
				else
					moves.add(new Move(x,y,x-i,y-i));
			}
		}

		return moves;
	}
}
