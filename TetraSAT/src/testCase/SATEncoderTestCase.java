package testCase;

import java.util.ArrayList;

import parser.BlockInputParser;

import encoder.SATEncoder;
import main.Block;
import main.Field;

public class SATEncoderTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Block> blocks = new ArrayList<Block>();
		
//		blocks.add(new Block("0895"));
//		blocks.add(new Block("7683"));
//		blocks.add(new Block("5845"));
//		blocks.add(new Block("2279"));
//		blocks.add(new Block("9580"));
//		blocks.add(new Block("8020"));
//		blocks.add(new Block("7008"));
//		blocks.add(new Block("1308"));
//		blocks.add(new Block("2800"));
//		blocks.add(new Block("9728"));
//		blocks.add(new Block("3866"));
//		blocks.add(new Block("4590"));
//		blocks.add(new Block("9738"));
//		blocks.add(new Block("9917"));
//		blocks.add(new Block("0697"));
//		blocks.add(new Block("0390"));
		
		BlockInputParser parser = new BlockInputParser("input/testBlocks");
		
		Field field = parser.getField();
		
//		for (Block block : blocks) {
//			field.addBlock(block);
//		}
		
		SATEncoder encoder = new SATEncoder(field);
		
		long start = System.currentTimeMillis();
		encoder.encode();
		
		long end = System.currentTimeMillis();
		
		System.out.println((end-start) + "ms");
		
		
	}

}
