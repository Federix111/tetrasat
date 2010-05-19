package testCase;

import java.util.Arrays;
import java.util.Collections;

import elements.Block;
import elements.Field;

import outputHandler.ConsoleFormatter;
import generator.TetravexGenerator;

public class TetravexGeneratorTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TetravexGenerator generator = new TetravexGenerator(5);
		
		Block[] tetravex = generator.giveMeTetravex();
		
//		ConsoleFormatter.printBlocksArray(tetravex);

		Collections.shuffle(Arrays.asList(tetravex));
		Collections.shuffle(Arrays.asList(tetravex));
		Collections.shuffle(Arrays.asList(tetravex));
		Collections.shuffle(Arrays.asList(tetravex));
		Collections.shuffle(Arrays.asList(tetravex));
		
//		ConsoleFormatter.printBlocksArray(tetravex);
		
		Field field = new Field(tetravex);
		
		ConsoleFormatter.printStartingField(field);
		
		
		
	}

}
