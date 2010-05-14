package outputHandler;

import main.Block;
import main.Field;

public class ConsoleFormatter {
	
	public static void printStartingField(Field field) {
		
		Block[] blocks = new Block[field.blocks.size()];
		
		int counter=0;
		for (Block block : field.blocks) {
			blocks[counter++] = block;;
		}
		
		printBlocksArray(blocks);
		
	}
	
	public static void printSolvedField(Field field) {
		
		printBlocksArray(field.orderedBlocks);
		
	}
	
	private static void printBlocksArray(Block[] blocksList) {
		
		int side = (int)Math.sqrt(blocksList.length);
		
		int[] data = new int[blocksList.length*4];
		
		int counter = 0;
		
		for(int j=0; j<side; j++) {
			for(int i=0; i<side; i++) {
				data[counter++] = blocksList[i+side*j].getTop();
			}
			for(int i=0; i<side; i++) {
				data[counter++] = blocksList[i+side*j].getLeft();
				data[counter++] = blocksList[i+side*j].getRight();
			}
			for(int i=0; i<side; i++) {
				data[counter++] = blocksList[i+side*j].getDown();
			}
		}
		
		counter = 0;
		
		for(int j=0; j<side; j++) {
			for(int i=0; i<side; i++) {
				System.out.print(" ___________  ");
			}
			System.out.println();
			for(int i=0; i<side; i++) {
				System.out.print("|           | ");
			}
			System.out.println();
			for(int i=0; i<side; i++) {
				System.out.print("|     " + data[counter++] + "     | ");
			}
			System.out.println();
			for(int i=0; i<side; i++) {
				System.out.print("|    \\ /    | ");
			}
			System.out.println();
			for(int i=0; i<side; i++) {
				System.out.print("|  " + data[counter++] + "  X  " + data[counter++] + "  | ");
			}
			System.out.println();
			for(int i=0; i<side; i++) {
				System.out.print("|    / \\    | ");
			}
			System.out.println();
			for(int i=0; i<side; i++) {
				System.out.print("|     " + data[counter++] + "     | ");
			}
			System.out.println();
			for(int i=0; i<side; i++) {
				System.out.print("|___________| ");
			}
			System.out.println();
		}
		
	}
	
}
