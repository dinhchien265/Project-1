package game;

import pieces.*;
import player.AlphaBetaPlayer;

import java.awt.*;
import java.util.ArrayList;

public class GameController {
    public ChessBoard chessBoard;
    Position firstPosition;
    public boolean turn = Piece.WHITE;
    public boolean humanColor = Piece.WHITE;
    boolean PvC = true; // false la che do nguoi choi voi nguoi
    AlphaBetaPlayer alphaBetaPlayer = new AlphaBetaPlayer(Piece.BLACK, this);
    public ArrayList<Board> boardsList = new ArrayList<>();
    public boolean stopThread = false;
    public int count = 0;//dem so nuoc da di


    public static void main(String[] args) {
        GameController gc = new GameController();
    }

    public GameController() {
        chessBoard = new ChessBoard(this);
        boardsList.add(chessBoard.board.clone());
    }

    public void newGame() {
        stopThread = true;
        count=0;
        boardsList=new ArrayList<>();
        turn = true;
        chessBoard.board = new Board();
        chessBoard.setImageIcon(chessBoard.board.tiles);
        chessBoard.paintBoard();
        boardsList.add(chessBoard.board.clone());
        if (alphaBetaPlayer.getColor() && PvC) {
            alphaBetaPlayer.makeMove(chessBoard.board);
            count++;
            turn = !turn;
            chessBoard.paintBoard();
            boardsList.add(chessBoard.board.clone());
        }

    }

    public void click(Position position) {
        if (PvC && turn != humanColor)
            return;
        Tile tile = chessBoard.board.getTile(position);
        if (tile.isOccupied() && tile.getPiece().getColor() == turn){
            if(firstPosition!=null) restoreBackground();
            firstPosition = position;
            setBackground();
        }
        else if (firstPosition != null) {
            Move move = new Move(firstPosition, position);

            ArrayList<Move> moves = chessBoard.board.getMoves(turn);
            for (int i = 0; i < moves.size(); ++i) {
                if ((moves.get(i)).equals(move)) {
                    chessBoard.board.makeMove(move);
                    while (boardsList.size()>count+1) {
                        boardsList.remove(count+1);
                    }
                    boardsList.add(chessBoard.board.clone());
                    stopThread = false;
                    count++;
                    turn = !turn;
                    firstPosition = null;
                    chessBoard.paintBoard();
                    restoreBackground();

                    System.out.println(chessBoard.board.toString());

                    if (PvC) {
                        new Thread(alphaBetaPlayer).start();
                    }
                    break;
                }
            }
        }
    }

    public void undo() {
        if(PvC&&!humanColor&&count<2)
            return;
        if(count<1)
            return;
        if(!PvC) count--;
        else if(turn!=humanColor){
            stopThread=true;
            count-=1;
            turn=!turn;
        }
        else count-=2;

        chessBoard.board = boardsList.get(count).clone();
        chessBoard.setImageIcon(chessBoard.board.tiles);
        chessBoard.paintBoard();
    }

    public void redo() {
        if(boardsList.size()<count+2)
            return;
        if(PvC && boardsList.size()<count+3)
            return;
        if(PvC)
            count+=2;
        else count++;
        chessBoard.board = boardsList.get(count).clone();
        chessBoard.setImageIcon(chessBoard.board.tiles);
        chessBoard.paintBoard();
    }
    //to mau nhung o quan co duoc chon co the di
    private void setBackground(){
        chessBoard.jButton[firstPosition.getX()][firstPosition.getY()].setBackground(Color.BLUE);

        Piece piece=chessBoard.board.tiles[firstPosition.getX()][firstPosition.getY()].getPiece();
        ArrayList<Move> moves=piece.getMoves(chessBoard.board,firstPosition.getX(),firstPosition.getY());
        for (int i = 0; i < moves.size(); i++) {
            ArrayList<Move> checkThis = new ArrayList<Move>(moves.subList(i, i + 1));
            if(chessBoard.board.isCheckAfter(turn,checkThis)){
                moves.remove(checkThis.get(0));
                i--;
            }
        }
        for (int i = 0; i < moves.size(); i++) {
            chessBoard.jButton[moves.get(i).getX2()][moves.get(i).getY2()].setBackground(Color.BLUE);
        }
    }
    // bo to mau
    private void restoreBackground(){
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
                    chessBoard.jButton[x][y].setBackground(Color.YELLOW);
                }
                else{
                    chessBoard.jButton[x][y].setBackground(Color.WHITE);
                }
            }
        }
    }

}
