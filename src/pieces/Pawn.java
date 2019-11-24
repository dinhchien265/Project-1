package pieces;

import game.Board;
import game.Move;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(boolean color) {
        super(color);
        value = 1;
    }

    public Pawn clone() {
        return new Pawn(color);
    }

    public String toString() {
        if (color == Piece.WHITE)
            return "P";
        else
            return "p";
    }

    /**
     * @param b game.Board
     * @param x toa do x
     * @param y toa do y
     * @return
     */
    public ArrayList<Move> getMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<Move>();

        if (color == Piece.WHITE) {
            if (valid(x, y + 1) && !b.getTile(x, y + 1).isOccupied())
                moves.add(new Move(x, y, x, y + 1));
            if (y == 2 - 1 && !b.getTile(x, 4 - 1).isOccupied() && !b.getTile(x, 3 - 1).isOccupied())
                moves.add(new Move(x, y, x, y + 2));

            if (valid(x + 1, y + 1) && b.getTile(x + 1, y + 1).isOccupied() && b.getTile(x + 1, y + 1).getPiece().getColor() != color)
                moves.add(new Move(x, y, x + 1, y + 1));

            if (valid(x - 1, y + 1) && b.getTile(x - 1, y + 1).isOccupied() && b.getTile(x - 1, y + 1).getPiece().getColor() != color)
                moves.add(new Move(x, y, x - 1, y + 1));
        } else {
            if (valid(x, y - 1) && !b.getTile(x, y - 1).isOccupied())
                moves.add(new Move(x, y, x, y - 1));
            if (y == 7 - 1 && !b.getTile(x, 5 - 1).isOccupied() && !b.getTile(x, 6 - 1).isOccupied())
                moves.add(new Move(x, y, x, y - 2));

            if (valid(x + 1, y - 1) && b.getTile(x + 1, y - 1).isOccupied() && b.getTile(x + 1, y - 1).getPiece().getColor() != color)
                moves.add(new Move(x, y, x + 1, y - 1));

            if (valid(x - 1, y - 1) && b.getTile(x - 1, y - 1).isOccupied() && b.getTile(x - 1, y - 1).getPiece().getColor() != color)
                moves.add(new Move(x, y, x - 1, y - 1));
        }

        return moves;
    }

    public ArrayList<Move> getOpenFire(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<Move>();

        if (color == Piece.WHITE) {
            if (valid(x + 1, y + 1))
                moves.add(new Move(x, y, x + 1, y + 1));

            if (valid(x - 1, y + 1))
                moves.add(new Move(x, y, x - 1, y + 1));
        } else {

            if (valid(x + 1, y - 1))
                moves.add(new Move(x, y, x + 1, y - 1));

            if (valid(x - 1, y - 1))
                moves.add(new Move(x, y, x - 1, y - 1));
        }

        return moves;
    }
}
