package game;

import pieces.*;
import java.util.ArrayList;

public class Board {
	public static final int a = 0, b = 1, c = 2, d = 3, e = 4, f = 5, g = 6, h = 7;

	public Tile[][] tiles;

	/**
	 * 8	r n b q k b n r
	 * 7	p p p p p p p p
	 * 6	. . . . . . . .
	 * 5	. . . . . . . .
	 * 4	. . . . . . . .
	 * 3	. . . . . . . .
	 * 2	P P P P P P P P
	 * 1    R N B Q K B N R
     *
	 *      a b c d e f g h
	 *
	 * P=pawn, K=king, Q=queen, R=rook, N=knight, B=Bishop
	 */

	public Board(Tile[][] tiles) {
		this.tiles = tiles;
	}


	public Board() {
		boolean co = Piece.WHITE;
		tiles = new Tile[8][8];
		tiles[a][1 - 1] = new Tile(new Rook(co));
		tiles[b][1 - 1] = new Tile(new Knight(co));
		tiles[c][1 - 1] = new Tile(new Bishop(co));
		tiles[d][1 - 1] = new Tile(new Queen(co));
		tiles[e][1 - 1] = new Tile(new King(co));
		tiles[f][1 - 1] = new Tile(new Bishop(co));
		tiles[g][1 - 1] = new Tile(new Knight(co));
		tiles[h][1 - 1] = new Tile(new Rook(co));

		for (int i = 0; i < 8; i++) {
			tiles[i][2 - 1] = new Tile(new Pawn(co));
		}

		for (int i = 2; i < 7; i++) {
			for (int j = 0; j < 8; j++) {
				tiles[j][i] = new Tile();
			}
		}

		co = Piece.BLACK;
		tiles[a][8 - 1] = new Tile(new Rook(co));
		tiles[b][8 - 1] = new Tile(new Knight(co));
		tiles[c][8 - 1] = new Tile(new Bishop(co));
		tiles[d][8 - 1] = new Tile(new Queen(co));
		tiles[e][8 - 1] = new Tile(new King(co));
		tiles[f][8 - 1] = new Tile(new Bishop(co));
		tiles[g][8 - 1] = new Tile(new Knight(co));
		tiles[h][8 - 1] = new Tile(new Rook(co));

		for (int i = 0; i < 8; i++) {
			tiles[i][7 - 1] = new Tile(new Pawn(co));
		}
	}
	public Board clone(){
		Tile[][] tempTiles=new Tile[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tempTiles[i][j]=tiles[i][j].clone();
			}
		}
		return new Board(tempTiles);
	}



	public String toString() {
		String str = "";
		for (int i = 7; i >= 0; i--) {
			str += (i + 1) + "  ";
			for (int j = 0; j < 8; j++) {
				str += tiles[j][i] + " ";
			}
			str += "\n";
		}

		str += "\n   a b c d e f g h";

		return str;
	}

	public ArrayList<Move> getMoves(boolean color) {
		return getMoves(color, true);
	}


	/**
	 * kiem tra xem king co dang bi chieu hay khong
	 *
	 * @param color mau cua quan muon kiem tra
	 * @return
	 */
	public boolean isCheck(boolean color) {
		int x = -1, y = -1;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (tiles[i][j].isOccupied() &&
						tiles[i][j].getPiece().getColor() == color &&
						tiles[i][j].getPiece().toString().equalsIgnoreCase("K")) {
					x = i;
					y = j;
				}
			}

		ArrayList<Move> opponentMoves = getMoves(!color, false);

		for (int j = 0; j < opponentMoves.size(); j++) {
			if (opponentMoves.get(j).getX2() == x && opponentMoves.get(j).getY2() == y)
				return true;
		}

		return false;
	}


	/**
	 * Kiem tra sau khi thuc hien mot danh sach cac nuoc di(moves) quan vua
	 * co bi chieu hay khong
	 *
	 * @param color mau quan muon kiem tra
	 * @param moves danh sach cac nuoc di
	 * @return true neu khong danh sach cac nuoc di moves khong hop le
	 * false neu danh sach cac nuoc di hop le
	 */
	public boolean isCheckAfter(boolean color, ArrayList<Move> moves) {

		Tile[][] newTiles = getTilesAfter(moves);
		Board b=new Board(newTiles);
		return b.isCheck(color);
	}

	// lay danh sach tat ca cac nuoc di hop le ma quan co mau color co the di
	public ArrayList<Move> getMoves(boolean color, boolean checkCheck) {
		ArrayList<Move> moves = new ArrayList<Move>();

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (tiles[i][j].isOccupied() &&
						tiles[i][j].getPiece().getColor() == color) {
					moves.addAll(tiles[i][j].getPiece().getMoves(this, i, j));
				}
			}

		if (checkCheck) {
			for (int i = 0; i < moves.size(); i++) {
				ArrayList<Move> checkThis = new ArrayList<Move>(moves.subList(i, i + 1));
				if(isCheckAfter(color,checkThis)==true){
					moves.remove(checkThis.get(0));
					i--;
				}

			}
		}
		return moves;
	}
	public ArrayList<Move> getMovesWithoutCastling(boolean color){
        ArrayList<Move> moves = new ArrayList<Move>();

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (tiles[i][j].isOccupied() &&
                        !tiles[i][j].getPiece().toString().equalsIgnoreCase("K")&&
                        tiles[i][j].getPiece().getColor() == color) {
                    moves.addAll(tiles[i][j].getPiece().getMoves(this, i, j));
                }
            }
        return moves;
    }

	// lay danh sach cac nuoc di hop le cua quan color sau cac nuoc moves
	public ArrayList<Move> getMovesAfter(boolean color, ArrayList<Move> moves) {
		return getMovesAfter(color, moves, true);
	}

	public ArrayList<Move> getMovesAfter(boolean color, ArrayList<Move> moves, boolean checkCheck) {

		Tile[][] temp = new Tile[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp[x][y] = new Tile(this.tiles[x][y]);
			}
		}

		Board b = new Board(temp);

		for (int i = 0; i < moves.size(); i++)
			b.makeMove(moves.get(i));

		ArrayList<Move> futureMoves = b.getMoves(color, checkCheck);

		return futureMoves;
	}

	public Tile[][] getTilesAfter(ArrayList<Move> moves) {

		Tile[][] temp = new Tile[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp[x][y] = tiles[x][y].clone();
			}
		}

		Board b = new Board(temp);

		for(int i = 0; i < moves.size(); i++)
			b.makeMove(moves.get(i));


		return temp;
	}


	public void makeMove(Move m) {
		Tile oldTile = tiles[m.getX1()][m.getY1()];

		tiles[m.getX2()][m.getY2()] = tiles[m.getX1()][m.getY1()];
		tiles[m.getX1()][m.getY1()] = new Tile();

		// nhap thanh
		if (oldTile.getPiece().toString().equalsIgnoreCase("K") && (Math.abs(m.getX2() - m.getX1()) == 2)) {
			if (m.getX2() == g && m.getY2() == 1 - 1) {
				tiles[f][1 - 1] = tiles[h][1 - 1];
				tiles[h][1 - 1] = new Tile();
			}
			if (m.getX2() == c && m.getY2() == 1 - 1) {
				tiles[d][1 - 1] = tiles[a][1 - 1];
				tiles[a][1 - 1] = new Tile();
			}
			if (m.getX2() == g && m.getY2() == 8 - 1) {
				tiles[f][8 - 1] = tiles[h][8 - 1];
				tiles[h][8 - 1] = new Tile();
			}
			if (m.getX2() == c && m.getY2() == 8 - 1) {
				tiles[d][8 - 1] = tiles[a][8 - 1];
				tiles[a][8 - 1] = new Tile();
			}
		}

		// phong hau
		if (oldTile.isOccupied()&&
				oldTile.getPiece().toString().equals("P") && m.getY2() == 8 - 1)
			tiles[m.getX2()][m.getY2()] = new Tile(new Queen(Piece.WHITE));

		if (oldTile.isOccupied()&&
				oldTile.getPiece().toString().equals("p") && m.getY2() == 1 - 1)
			tiles[m.getX2()][m.getY2()] = new Tile(new Queen(Piece.BLACK));

		//cap nhat gia tri hasMoved
		if (oldTile.isOccupied()&&
				oldTile.getPiece().toString().equalsIgnoreCase("K")) {
			((King) oldTile.getPiece()).setHasMoved(true);
		}
		if (oldTile.isOccupied()&&
				oldTile.getPiece().toString().equalsIgnoreCase("R")) {
			((Rook) oldTile.getPiece()).setHasMoved(true);
		}

	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	public Tile getTile(Position position) {
		return tiles[position.getX()][position.getY()];
	}

}
