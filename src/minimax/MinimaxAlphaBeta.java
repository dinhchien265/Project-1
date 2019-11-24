package minimax;

import java.util.ArrayList;
import java.util.Random;

import game.Board;
import game.Move;
import game.Tile;
import pieces.Piece;


public class MinimaxAlphaBeta {

	boolean color;
	int maxDepth;
	Random rand;
	int count=0; // dem so nhanh cay
	
	public MinimaxAlphaBeta(boolean color, int maxDepth) {
		this.color = color;
		this.maxDepth = maxDepth;
		rand = new Random();
	}

	public int getCount() {
		return count;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	/**
	 *
	 * @param b
	 * @param state
	 * @param alpha
	 * @param beta
	 * @param depth
	 * @return alpha, tra ve truong hop gia tri ban co lon nhat
	 */
	private int maxValue(Board b, ArrayList<Move> state, int alpha, int beta, int depth) {
		if(depth > maxDepth) // dieu kien dung 1
			return eval(b, state, color);

		ArrayList<Move> moves = b.getMovesAfter(color, state);
		if(moves.size() == 0) // dieu kien dung 2
			return eval(b,moves,color);

		for(int i = 0; i < moves.size(); i++) {
			state.add(moves.get(i));
			int tmp = minValue(b, state, alpha, beta, depth + 1);
			state.remove(state.lastIndexOf(moves.get(i)));
			if(tmp > alpha) {
				alpha = tmp;
			}

			if(beta <= alpha)
				break; //cat tia
		}

		return alpha;
	}

	/**
	 *
	 * @param b ban co ban dau
	 * @param state danh sach cac nuoc da di
	 * @param alpha gia tri cua ban co
	 * @param beta gia tri cua ban co
	 * @param depth do sau tim kiem
	 * @return beta, tra ve truong hop co gia tri ban co nho nhat
	 */
	private int minValue(Board b, ArrayList<Move> state, int alpha, int beta, int depth) {
		if(depth > maxDepth) //dieu kien dung 1
			return eval(b, state, !color); //tra ve diem cua !color - diem cua color

		ArrayList<Move> moves = b.getMovesAfter(!color, state);
		if(moves.size() == 0) // dieu kien dung 2
			return eval(b,state,!color);

		for(int i = 0; i < moves.size(); i++) {
			state.add(moves.get(i));
			int tmp = maxValue(b, state, alpha, beta, depth + 1);
			state.remove(state.lastIndexOf(moves.get(i)));
			if(tmp < beta) {
				beta = tmp;
			}

			if(beta <= alpha)
				break; //cat tia
		}

		return beta;
	}



	public Move Decision(Board b) {
		count=0;

		ArrayList<Move> moves = b.getMoves(color);
		ArrayList<Move> state = new ArrayList<Move>();
		int[] costs = new int[moves.size()];
		for(int i = 0; i < moves.size(); i++) {
			state.add(moves.get(i));
			int tmp = minValue(b, state, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
			costs[i] = tmp;
			state.remove(state.lastIndexOf(moves.get(i)));
		}
		
		int maxi = -1;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < moves.size(); i++) {
			if(costs[i] >= max) {
				max = costs[i];
			}
		}
		ArrayList<Move> maxList=new ArrayList<Move>();
		for (int i = 0; i < moves.size(); i++) {
			if(costs[i]==max){
				maxList.add(moves.get(i));
			}
		}
		if(maxList.size()>0){
			maxi=rand.nextInt(maxList.size());
		}
		System.out.println("color = "+color);
		System.out.println("moves.size() = "+moves.size());
		System.out.println("maxlist.size() = "+maxList.size());
		System.out.println("max = "+max);
		System.out.println("count = "+count);
		System.out.println("maxDepth = "+maxDepth);

		if(maxi == -1)
			return null;
		else
			return maxList.get(maxi);
	}


	/**
	 * ham tra ve so diem cua ban co sau khi di cac nuoc moves
	 * @param b ban co ban dau
	 * @param moves danh sach cac nuoc di
	 * @param currentColor mau cua quan dang den luot di
	 * @return neu la quan trang se tra ve tong diem quan trang tru tong diem quan den
	 * 		   neu la quan den se tra ve tong diem quan den tru tong diem quan trang
	 */
	//danh gia
	private int eval(Board b, ArrayList<Move> moves, boolean currentColor) {
		count++;
		Tile[][] tiles = b.getTilesAfter(moves);

		if(b.getMovesAfter(currentColor,moves).size() == 0) { // kiem tra xem quan currentColor co con nuoc di khong
			if(b.isCheckAfter(currentColor, moves)) // kiem tra xem quan CurrentColor co dang bi chieu tuong khong
				return (currentColor == this.color) ? Integer.MIN_VALUE : Integer.MAX_VALUE; // quan currentColor dang bi chieu tuong thi tra ve am vo cung neu currenColor la White, tra ve duong vo cung neu currentColor la Black
			else
				return 0; //tra ve 0 neu roi vao the co hoa.
		}
		Board board=new Board(tiles);
		int whiteScore = 0;
		int blackScore = 0;

		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(tiles[i][j].isOccupied())
					if(tiles[i][j].getPiece().getColor() == Piece.WHITE)
						whiteScore += tiles[i][j].getPiece().getValue();
					else
						blackScore += tiles[i][j].getPiece().getValue();
			}


		if(color == Piece.WHITE)
			return whiteScore - blackScore;
		else
			return blackScore - whiteScore;
	}

}
