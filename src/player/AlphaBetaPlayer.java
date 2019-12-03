package player;


import game.GameController;
import game.Move;
import game.Board;
import minimax.*;


public class AlphaBetaPlayer extends Player implements Runnable {
    MinimaxAlphaBeta minimax;
    GameController gc;


    public AlphaBetaPlayer(boolean color, GameController gc) {
        super(color);
        this.gc = gc;
        minimax = new MinimaxAlphaBeta(color, 2);
    }


    public Move getNextMove(Board board) {
        minimax = new MinimaxAlphaBeta(color, 2);
        Move move = minimax.Decision(board);
//		if(minimax.getCount()<15000)
//			minimax.setMaxDepth(minimax.getMaxDepth()+1);
//		if (minimax.getCount()>20000)
//			minimax.setMaxDepth(minimax.getMaxDepth()-1);
        return move;
    }
    public void makeMove(Board board){
        Move move=getNextMove(board);
        board.makeMove(move);
    }

    @Override
    public void run() {
        Move move = getNextMove(gc.chessBoard.board);
        if (gc.stopThread == false) {
        	gc.chessBoard.board.makeMove(move);
            while (gc.boardsList.size()>gc.count+1) {
                gc.boardsList.remove(gc.count+1);
            }
        	gc.boardsList.add(gc.chessBoard.board.clone());
        	gc.count++;
            gc.chessBoard.paintBoard();
            gc.turn = !gc.turn;
			System.out.println("chien dep trai");
            System.out.println(gc.chessBoard.board.toString());
        }


    }
}
