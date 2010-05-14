package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Field {

	public ArrayList<Block> blocks;
	public Block[] orderedBlocks;
	
	public Field(){
		blocks = new ArrayList<Block>();
	}
	
	public Field(Block[] blocks){
		this.blocks = (ArrayList<Block>) Arrays.asList(blocks);
	}
	
	public void finalizeField(){
		orderedBlocks = new Block[blocks.size()];
	}
	
	public void addBlock(Block b){
		blocks.add(b);
		System.out.println(b.addedToList());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return blocks.toString();
	}
	
	
}
