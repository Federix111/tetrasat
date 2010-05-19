package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import outputHandler.ConsoleFormatter;
import elements.Block;
import elements.Field;
import generator.TetravexGenerator;

public class Generator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TetravexGenerator generator = new TetravexGenerator(Integer.parseInt(args[1]));
		
		Block[] tetravex = generator.giveMeShuffledTetravex();
		
		Field field = new Field(tetravex);
		
		ConsoleFormatter.printStartingField(field);
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./input/" + args[0]));
			bufferedWriter.write(field.toFileDefinition());
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
