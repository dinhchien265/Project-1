package player;

import game.Move;

public abstract class Player {

	protected boolean color;

	public Player(boolean color) {
		this.color = color;
	}

	public Move getNextMove() {
		return null;
	}

	public boolean getColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}
}
