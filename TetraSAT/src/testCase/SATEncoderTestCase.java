package testCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import parser.BlockInputParser;

import encoder.SATEncoder;
import main.Field;

public class SATEncoderTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String freddCommand = " /sw/bin/miniwrapper -m /Users/Fredd/Documents/workspace/TetraSAT/rules.txt";
		String hCommand = " /sw/bin/miniwrapper -m /Users/LoL/Documents/workspace/TetraSAT/rules.txt";
		
		BlockInputParser parser = new BlockInputParser("input/testBlocks");
		
		Field field = parser.getField();
		
		SATEncoder encoder = new SATEncoder(field);
		
		long start = System.currentTimeMillis();
		encoder.encode();
		
		long end = System.currentTimeMillis();
		
		System.out.println((end-start) + "ms");
		
		try
		{
			Process proc = Runtime.getRuntime().exec(freddCommand);
			BufferedReader read=new BufferedReader(new InputStreamReader(proc.getInputStream()));

			while(read.ready())
			{
				System.out.println(read.readLine());
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}

}
