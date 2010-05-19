package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import outputHandler.ConsoleFormatter;
import parser.BlockInputParser;
import elements.Field;
import encoder.SATEncoder;

public class Solver {

	private static String output;
	private static Field field;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String freddCommand = " /sw/bin/miniwrapper -m /Users/Fredd/Documents/workspace/TetraSAT/rules.txt";
		String hCommand = " /Users/adnan/Documents/Toc/miniwrapper/miniwrapper/miniwrapper -m /Users/adnan/Documents/Toc/workspace/TetraSAT/rules.txt";
		
		String command = freddCommand;
		
		output = "";
		
		BlockInputParser parser = new BlockInputParser("./input/" + args[0]);
		
		field = parser.getField();
		
		SATEncoder encoder = new SATEncoder(field);
		
		ConsoleFormatter.printStartingField(field);
		
		long start = System.currentTimeMillis();
		encoder.encode();
		long end = System.currentTimeMillis();
		
		float duration = (end-start)/1000;
		
		System.out.println(duration + "s");
		
		while(output == "") {
			try
			{
				Process proc = Runtime.getRuntime().exec(command);
				BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));

				while(read.ready())
				{
					output += read.readLine();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

			if(output.contains("unsatisfiable")) {
				System.out.println("\nUNSATISFIABLE");
			} else {
				processOutput();
			}
		}
		
	}
	
	private static void processOutput() {
		
		System.out.println("\nSATISFIABLE");
		
		Pattern timePattern = Pattern.compile("Solving.* ([0-9]+\\.[0-9]+[s])");
		
		Matcher timeMatcher = timePattern.matcher(output);
		
		
		Pattern blocksPattern = Pattern.compile("block_([0-9]+)_in_square_([0-9]+)");
		Matcher blocksMatcher = blocksPattern.matcher(output);
		
		while(blocksMatcher.find()) {
			field.orderedBlocks[Integer.parseInt(blocksMatcher.group(2))] = field.blocks.get(Integer.parseInt(blocksMatcher.group(1)));
		}
		
		ConsoleFormatter.printSolvedField(field);
		
		while(timeMatcher.find()) {
			System.out.println("COMPUTING TERMINATED\n" + timeMatcher.group(1));
		}
		
	}

}
