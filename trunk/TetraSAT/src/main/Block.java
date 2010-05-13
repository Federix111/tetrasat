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
	
	public Block(String blockString){
		char[] test = blockString.toCharArray();
		this.top = Integer.parseInt(Character.toString(test[0]));
		this.right = Integer.parseInt(Character.toString(test[1]));
		this.down = Integer.parseInt(Character.toString(test[2]));
		this.left = Integer.parseInt(Character.toString(test[3]));
		
		
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+top+" "+right+" "+ down + " "+ left+", ";
	}
	
	
}
