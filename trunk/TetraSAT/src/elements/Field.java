package elements;

import java.util.ArrayList;
import java.util.Arrays;


public class Field {

	public ArrayList<Block> blocks;
	public Block[] orderedBlocks;
	
	public Field(){
		blocks = new ArrayList<Block>();
	}
	
	public Field(Block[] blocks){
		this.blocks = new ArrayList<Block>(Arrays.asList(blocks));
	}
	
	public void finalizeField(){
		orderedBlocks = new Block[blocks.size()];
	}
	
	public void addBlock(Block b){
		blocks.add(b);
		b.addedToList();
//		System.out.println(b.addedToList());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return blocks.toString();
	}
	
	public String toFileDefinition() {
		
		String definition = "";
		
		for (Block block : blocks) {
			definition += (block.toString() + "\n");
		}
		
		return definition;
		
	}
	
	
}
