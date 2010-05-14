package testCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.BlockInputParser;

import encoder.SATEncoder;
import main.Field;

public class SATEncoderTestCase {

	private static String output = "";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String freddCommand = " /sw/bin/miniwrapper -m /Users/Fredd/Documents/workspace/TetraSAT/rules.txt";
		String hCommand = " /sw/bin/miniwrapper -m /Users/LoL/Documents/workspace/TetraSAT/rules.txt";
		
		
		BlockInputParser parser = new BlockInputParser("input/realTetra");
		
		Field field = parser.getField();
		
		SATEncoder encoder = new SATEncoder(field);
		
		long start = System.currentTimeMillis();
		encoder.encode();
		
		long end = System.currentTimeMillis();
		
		System.out.println((end-start) + "ms");
		
		while(output == "") {
			try
			{
				Process proc = Runtime.getRuntime().exec(freddCommand);
				BufferedReader read=new BufferedReader(new InputStreamReader(proc.getInputStream()));

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
				System.out.println("UNSATISFIABLE");
			} else {
				processOuput();
			}
		}

	}

	private static void processOuput() {
		
		System.out.println("SATISFIABLE");
		
		Pattern timePattern = Pattern.compile("Solving.*([0-9]+\\.[0-9]+[s])");
		
		Matcher timeMatcher = timePattern.matcher(output);
		
		
		Pattern blocksPattern = Pattern.compile("block_([0-9]+)_in_square_([0-9]+)");
		Matcher blocksMatcher = blocksPattern.matcher(output);
		
		while(blocksMatcher.find()) {
			System.out.println("Block: " + blocksMatcher.group(1) + "\tSquare: " + blocksMatcher.group(2));
		}
		
		while(timeMatcher.find()) {
			System.out.println("COMPUTING TERMINATED\n" + timeMatcher.group(1));
		}
		
		
	}

}
