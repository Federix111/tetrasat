package testCase;

import java.lang.reflect.Field;

import main.Block;

public class BlockTestCase {
	public static void main(String[] args){
		String s1 = "0123";
		String s2 = "3142";
		String s3 = "5173";
		String s4 = "0193";
		Block b1 = new Block(s1);
		Block b2 = new Block(s2);
		Block b3 = new Block(s3);
		Block b4 = new Block(s4);
		
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		
		main.Field field = new main.Field();
		field.addBlock(b1);
		field.addBlock(b2);
		field.addBlock(b3);
		System.out.println(field);
	}

}
