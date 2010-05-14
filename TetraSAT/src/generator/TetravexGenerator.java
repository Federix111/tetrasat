package generator;

import java.util.Random;

import main.Block;

public class TetravexGenerator {
	
	private Random generator;
	private int[] tetravexInLine;
	private int side;
	
	public TetravexGenerator(int side) {
		
		generator = new Random();
		tetravexInLine = new int[side*side*4];
		this.side = side;
		
	}
	
	public Block[] giveMeTetravex() {
		
		int counter = 0;
		for(int i=0; i<side; i++) {
			tetravexInLine[counter++] = generator.nextInt(10);
		}
		for(int i=0; i<side-1; i++) {
			tetravexInLine[counter++] = generator.nextInt(10);
			for(int j=0; j<(side-1); j++) {
				int temp = generator.nextInt(10);
				tetravexInLine[counter++] = temp;
				tetravexInLine[counter++] = temp;
			}
			tetravexInLine[counter++] = generator.nextInt(10);
			for(int k=0; k<side; k++) {
				int temp = generator.nextInt(10);
				tetravexInLine[counter] = temp;
				tetravexInLine[(counter+side)] = temp;
				counter++;
			}
			counter+=side;
		}
		tetravexInLine[counter++] = generator.nextInt(10);
		for(int j=0; j<(side-1); j++) {
			int temp = generator.nextInt(10);
			tetravexInLine[counter++] = temp;
			tetravexInLine[counter++] = temp;
		}
		tetravexInLine[counter++] = generator.nextInt(10);
		for(int i=0; i<side; i++) {
			tetravexInLine[counter++] = generator.nextInt(10);
		}
		
		return blocksDescription();
	}

	private Block[] blocksDescription() {
		
		Block[] blocksList = new Block[side*side];
		
		for(int i=0; i<blocksList.length; i++) {
			blocksList[i] = new Block("0000");
		}
		
		int counter=0;
		for(int i=0; i<side; i++) {
			for(int j=0; j<side; j++) {
				blocksList[j+side*i].setTop(tetravexInLine[counter++]);
			}
			for(int j=0; j<side; j++) {
				blocksList[j+side*i].setLeft(tetravexInLine[counter++]);
				blocksList[j+side*i].setRight(tetravexInLine[counter++]);
			}
			for(int j=0; j<side; j++) {
				blocksList[j+side*i].setDown(tetravexInLine[counter++]);
			}
		}
		
		return blocksList;
	}
	
}
