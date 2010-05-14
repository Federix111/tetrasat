package main;

import java.util.ArrayList;

public class Field {

	public ArrayList<Block> blocks;
	public ArrayList<Block> orderedBlocks;
	
	public Field(){
		blocks = new ArrayList<Block>();
		orderedBlocks = new ArrayList<Block>();
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
