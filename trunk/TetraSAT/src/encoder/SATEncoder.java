package encoder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.Field;

public class SATEncoder {

	private Field field;
	private int numberOfBlocks;
	private int sideLength;
	private String rulesList = "";

	public SATEncoder(Field field) {
		this.field = field;
		numberOfBlocks = field.blocks.size();
		sideLength = (int) Math.sqrt(numberOfBlocks);

		encodeRules();
		System.out.println("ENCODING TERMINATED");

	}

	private void encodeRules() {
		staticRules();
		dynamicRules();
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

	private void dynamicRules() {

		Boolean incompatible = false;

		for (int i = 0; i < numberOfBlocks-1; i++) {
			rulesList += "# block " + i + " impossible neighbors\n\n";
			for (int j = 0; j < numberOfBlocks; j++) {
				for (int k = 0; k < numberOfBlocks; k++) {
					if(k!=i) {
						for (int l = 0; l < numberOfBlocks; l++) {
							if(j-sideLength == l) {
								//check if i.top == k.bottom, if different => set rule
								if(field.blocks.get(i).getTop() != field.blocks.get(k).getDown()) {
									incompatible = true;
								}
							} else if(j+sideLength == l) {
								//check if i.bottom == k.top, if different => set rule
								if(field.blocks.get(i).getDown() != field.blocks.get(k).getTop()) {
									incompatible = true;
								}
							} else if(j-1 == l && (j % sideLength > l % sideLength)) {
								//check if i.left == k.right, if different => set rule
								if(field.blocks.get(i).getLeft() != field.blocks.get(k).getRight()) {
									incompatible = true;
								}
							} else if(j+1 == l && (j % sideLength < l % sideLength)) {
								//check if i.right == k.left, if different => set rule
								if(field.blocks.get(i).getRight() != field.blocks.get(k).getLeft()) {
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
				rulesList += "\n";
			}
			rulesList += "\n";
		}

	}

}
