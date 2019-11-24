package pieces;
import game.*;
import java.util.ArrayList;




public class Rook extends Piece {
	private boolean hasMoved=false;

	public Rook(boolean color) {
		super(color);
		value = 5;
	}

	public String toString() {
		if(color == Piece.WHITE)
			return "R";
		else
			return "r";
	}

	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public Rook clone() {
		return new Rook(color);
	}
	
	
	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();
		

		for(int i = 1; i < 8; i++) {
			if(valid(x, y+i)) {
				if(b.getTile(x, y+i).isOccupied()) {
					if(b.getTile(x, y+i).getPiece().color != color)
						moves.add(new Move(x,y,x,y+i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x,y+i));	
			}
		}
		

		for(int i = 1; i < 8; i++) {
			if(valid(x, y-i)) {
				if(b.getTile(x, y-i).isOccupied()) {
					if(b.getTile(x, y-i).getPiece().color != color)
						moves.add(new Move(x,y,x,y-i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x,y-i));	
			}
		}
		

		for(int i = 1; i < 8; i++) {
			if(valid(x-i, y)) {
				if(b.getTile(x-i, y).isOccupied()) {
					if(b.getTile(x-i, y).getPiece().color != color)
						moves.add(new Move(x,y,x-i,y));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x-i,y));	
			}
		}
		

		for(int i = 1; i < 8; i++) {
			if(valid(x+i, y)) {
				if(b.getTile(x+i, y).isOccupied()) {
					if(b.getTile(x+i, y).getPiece().color != color)
						moves.add(new Move(x,y,x+i,y));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x+i,y));	
			}
		}
		
		
		return moves;
	}

	@Override
	public ArrayList<Move> getOpenFire(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();


		for(int i = 1; i < 8; i++) {
			if(valid(x, y+i)) {
				if(b.getTile(x, y+i).isOccupied()) {
					moves.add(new Move(x,y,x,y+i));
					break;
				}
				else
					moves.add(new Move(x,y,x,y+i));
			}
		}


		for(int i = 1; i < 8; i++) {
			if(valid(x, y-i)) {
				if(b.getTile(x, y-i).isOccupied()) {
					moves.add(new Move(x,y,x,y-i));
					break;
				}
				else
					moves.add(new Move(x,y,x,y-i));
			}
		}

		for(int i = 1; i < 8; i++) {
			if(valid(x-i, y)) {
				if(b.getTile(x-i, y).isOccupied()) {
					moves.add(new Move(x,y,x-i,y));
					break;
				}
				else
					moves.add(new Move(x,y,x-i,y));
			}
		}


		for(int i = 1; i < 8; i++) {
			if(valid(x+i, y)) {
				if(b.getTile(x+i, y).isOccupied()) {
					moves.add(new Move(x,y,x+i,y));
					break;
				}
				else
					moves.add(new Move(x,y,x+i,y));
			}
		}


		return moves;
	}
}
