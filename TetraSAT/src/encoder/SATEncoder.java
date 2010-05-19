package encoder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import elements.Block;
import elements.Field;


public class SATEncoder {

	private Field field;
	private int numberOfBlocks;
	private int sideLength;
	private String rulesList = "";

	public SATEncoder(Field field) {
		this.field = field;
		numberOfBlocks = field.blocks.size();
		sideLength = (int) Math.sqrt(numberOfBlocks);

	}

	public void encode() {

		encodeRules();
		System.out.println("ENCODING TERMINATED");

	}

	private void encodeRules() {
		staticRules();
//		dynamicRules();
//		dynamicRulesFast();
		dynamicRulesTurbo();
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./rules.txt"));
			bufferedWriter.write(rulesList);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void staticRules() {

		//one block per square
		rulesList += "# one block per square\n\n";
		for (int i = 0; i < numberOfBlocks; i++) {
			for (int j = 0; j < numberOfBlocks; j++) {
				for (int k = j+1; k < numberOfBlocks; k++) {
					rulesList += "~block_" + i + "_in_square_" + j + " ~block_" + i + "_in_square_" + k + " $\n";
				}
			}
			rulesList += "\n";
		}

		//each square contains only one block
		rulesList += "\n# each square contains only one block\n\n";
		for (int i = 0; i < numberOfBlocks; i++) {
			for (int j = 0; j < numberOfBlocks; j++) {
				for (int k = j+1; k < numberOfBlocks; k++) {
					rulesList += "~block_" + j + "_in_square_" + i + " ~block_" + k + "_in_square_" + i + " $\n";
				}
			}
			rulesList += "\n";
		}

		//each block is in a square
		rulesList += "\n# each block is in a square\n\n";
		for (int i = 0; i < numberOfBlocks; i++) {
			for (int j = 0; j < numberOfBlocks; j++) {
				rulesList += "block_" + i + "_in_square_" + j + " ";
			}
			rulesList += "$\n";
		}

		rulesList += "\n\n";

	}
	
	private void dynamicRulesTurbo() {

		ArrayList<Block> blocksList = field.blocks;

		ArrayList<String> blockAcantStayLeftOfBlockB = new ArrayList<String>();
		ArrayList<String> blockAcantStayAboveOfBlockB = new ArrayList<String>();
		
		for (int i = 0; i < numberOfBlocks; i++) {
			Block temp1 = blocksList.get(i);
			for (int j = 0; j < numberOfBlocks; j++) {
				Block temp2 = blocksList.get(j);
				if(i!=j) {
					if(temp1.getDown() != temp2.getTop()) {
						blockAcantStayAboveOfBlockB.add(i + "," + j);
					}
					if(temp1.getRight() != temp2.getLeft()) {
						blockAcantStayLeftOfBlockB.add(i + "," + j);
					}
				}
			}
		}
		
		Collections.sort(blockAcantStayAboveOfBlockB);
		Collections.sort(blockAcantStayLeftOfBlockB);
		
		rulesList += "# impossible neighbors\n\n";
		
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength-1; j++) {
				if((j+i*sideLength) != (j+1)+i*sideLength) {
					for (String string : blockAcantStayLeftOfBlockB) {
						rulesList += "~block_" + string.split(",")[0] + "_in_square_" + (j+i*sideLength) + " ~block_" + string.split(",")[1] + "_in_square_" + ((j+1)+i*sideLength) + " $\n";
					}
				}
				if((i+j*sideLength) != (i+1)+j*sideLength) {
					for (String string : blockAcantStayAboveOfBlockB) {
						rulesList += "~block_" + string.split(",")[0] + "_in_square_" + (i+j*sideLength) + " ~block_" + string.split(",")[1] + "_in_square_" + ((i+sideLength)+j*sideLength) + " $\n";
					}
				}
			}		
		}
	}
	
	private void dynamicRulesFast() {

		ArrayList<Block> blocksList = field.blocks;

		for (int i = 0; i < numberOfBlocks-1; i++) {
			rulesList += "# block " + i + " impossible neighbors\n\n";
			Block temp1 = blocksList.get(i);
			for (int j = 0; j < numberOfBlocks; j++) {
				for (int k = 0; k < numberOfBlocks; k++) {
					if(k!=i) {
						Block temp2 = blocksList.get(k);
						//check if i.top == k.bottom, if different => set rule
						if((j-sideLength) >= 0 && (temp1.getTop() != temp2.getDown())) {
							String rule = "~block_" + i + "_in_square_" + j + " ~block_" + k + "_in_square_" + (j-sideLength) + " $\n";
							String reverseRule = "~block_" + k + "_in_square_" + (j-sideLength) + " ~block_" + i + "_in_square_" + j + " $\n";
							if(!rulesList.contains(reverseRule)) {
								rulesList += rule;
							}
						}
						//check if i.bottom == k.top, if different => set rule
						if((j+sideLength) < numberOfBlocks && (temp1.getDown() != temp2.getTop())) {
							String rule = "~block_" + i + "_in_square_" + j + " ~block_" + k + "_in_square_" + (j+sideLength) + " $\n";
							String reverseRule = "~block_" + k + "_in_square_" + (j+sideLength) + " ~block_" + i + "_in_square_" + j + " $\n";
							if(!rulesList.contains(reverseRule)) {
								rulesList += rule;
							}
						}
						//check if i.left == k.right, if different => set rule
						if((j-1) >= 0 && (j % sideLength != 0) && (temp1.getLeft() != temp2.getRight())) {
							String rule = "~block_" + i + "_in_square_" + j + " ~block_" + k + "_in_square_" + (j-1) + " $\n";
							String reverseRule = "~block_" + k + "_in_square_" + (j-1) + " ~block_" + i + "_in_square_" + j + " $\n";
							if(!rulesList.contains(reverseRule)) {
								rulesList += rule;
							}
						}
						//check if i.right == k.left, if different => set rule
						if((j+1) < numberOfBlocks && (j % sideLength != sideLength-1) && (temp1.getRight() != temp2.getLeft())) {
							String rule = "~block_" + i + "_in_square_" + j + " ~block_" + k + "_in_square_" + (j+1) + " $\n";
							String reverseRule = "~block_" + k + "_in_square_" + (j+1) + " ~block_" + i + "_in_square_" + j + " $\n";
							if(!rulesList.contains(reverseRule)) {
								rulesList += rule;
							}
						}
					}
				}
				rulesList += "\n";
			}
			rulesList += "\n";
		}

	}

	private void dynamicRules() {

		Boolean incompatible = false;
		ArrayList<Block> blocksList = field.blocks;

		for (int i = 0; i < numberOfBlocks-1; i++) {
			rulesList += "# block " + i + " impossible neighbors\n\n";
			Block temp1 = blocksList.get(i);
			for (int j = 0; j < numberOfBlocks; j++) {
				for (int k = 0; k < numberOfBlocks; k++) {
					if(k!=i) {
						Block temp2 = blocksList.get(k);
						for (int l = 0; l < numberOfBlocks; l++) {
							if(l!=j){
								if((Math.abs(j-l) % sideLength == 0) || Math.abs(j-l) == 1) {
									if(j-sideLength == l) {
										//check if i.top == k.bottom, if different => set rule
										if(temp1.getTop() != temp2.getDown()) {
											incompatible = true;
										}
									} else if(j+sideLength == l) {
										//check if i.bottom == k.top, if different => set rule
										if(temp1.getDown() != temp2.getTop()) {
											incompatible = true;
										}
									} else if(j-1 == l && (j % sideLength > l % sideLength)) {
										//check if i.left == k.right, if different => set rule
										if(temp1.getLeft() != temp2.getRight()) {
											incompatible = true;
										}
									} else if(j+1 == l && (j % sideLength < l % sideLength)) {
										//check if i.right == k.left, if different => set rule
										if(temp1.getRight() != temp2.getLeft()) {
											incompatible = true;
										}
									}
									if(incompatible) {
										String rule = "~block_" + i + "_in_square_" + j + " ~block_" + k + "_in_square_" + l + " $\n";
										String reverseRule = "~block_" + k + "_in_square_" + l + " ~block_" + i + "_in_square_" + j + " $\n";
										if(!rulesList.contains(reverseRule)) {
											rulesList += rule;
										}
										incompatible = false;
									}
								}
							}
						}
					}
				}
				rulesList += "\n";
			}
			rulesList += "\n";
		}

	}

}
