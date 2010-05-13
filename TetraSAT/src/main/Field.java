package main;

import java.util.ArrayList;

public class Field {

	public ArrayList<Block> blocks;
	public Field(){
		blocks = new ArrayList<Block>();
	}
	
	public void addBlock(Block b){
		blocks.add(b);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return blocks.toString();
	}
	
	
}
