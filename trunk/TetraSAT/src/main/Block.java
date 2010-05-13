package main;

public class Block {
	
	private int top;
	private int right;
	private int down;
	private int left;
	
	public Block(int top, int right, int down, int left) {
		super();
		this.top = top;
		this.right = right;
		this.down = down;
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}
	
}
