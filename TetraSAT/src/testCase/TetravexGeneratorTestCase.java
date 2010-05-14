package testCase;

import java.util.Arrays;
import java.util.Collections;

import outputHandler.ConsoleFormatter;
import main.Block;
import main.Field;
import generator.TetravexGenerator;

public class TetravexGeneratorTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TetravexGenerator generator = new TetravexGenerator(3);
		
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
