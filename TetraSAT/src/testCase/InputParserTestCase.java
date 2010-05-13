package testCase;

import parser.BlockInputParser;

public class InputParserTestCase {
	public static final String INPUTFILEPATH="input/blocks.txt";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockInputParser inputParser = new BlockInputParser(INPUTFILEPATH);
		System.out.println(inputParser.getField());
	}

}
