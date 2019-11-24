package game;

public class Move {
    private int x1, y1, x2, y2;
    private Position p1,p2;
    public Move(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public Move(Position p1, Position p2){
        this.x1=p1.getX();
        this.y1=p1.getY();
        this.x2=p2.getX();
        this.y2=p2.getY();
    }

    public Position getP1() {
        return p1;
    }

    public Position getP2() {
        return p2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

	@Override
	public boolean equals(Object obj) {
    	Move move=(Move)obj;
		if (x1 == move.x1 && y1 == move.y1 && x2 == move.x2 && y2 == move.y2)
			return true;
		else
			return false;
	}
}
