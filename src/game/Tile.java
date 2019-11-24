package game;

import pieces.*;

public class Tile {
	private boolean occupied;
	private Piece piece;

	public Tile() {
		occupied = false;
	}
	
	public Tile(Tile tile) {
		this.occupied = tile.isOccupied();
		this.piece = tile.isOccupied() ? tile.getPiece().clone() : null;
	}
	
	public Tile(Piece piece) {
		occupied = true;
		this.piece = piece;
	}
	
	public String toString() {
		if(occupied)
			return piece.toString();
		else
			return ".";
	}

	public Piece getPiece() {
		return piece;
	}
	
	public boolean isOccupied() {
		return occupied;
	}

	protected Tile clone() {
		return new Tile(this);
	}

	public String getImage(){
		if(toString().equals("b")) return "img\\BlackBishop.png";
		else if(toString().equals("k")) return "img\\BlackKing.png";
		else if(toString().equals("n")) return "img\\BlackKnight.png";
		else if(toString().equals("p")) return "img\\BlackPawn.png";
		else if(toString().equals("r")) return "img\\BlackRook.png";
		else if(toString().equals("q")) return "img\\BlackQueen.png";
		else if(toString().equals("B")) return "img\\WhiteBishop.png";
		else if(toString().equals("K")) return "img\\WhiteKing.png";
		else if(toString().equals("N")) return "img\\WhiteKnight.png";
		else if(toString().equals("P")) return "img\\WhitePawn.png";
		else if(toString().equals("R")) return "img\\WhiteRook.png";
		else if(toString().equals("Q")) return "img\\WhiteQueen.png";
		else return "";
	}
}
