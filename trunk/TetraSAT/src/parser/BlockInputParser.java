package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import elements.Block;
import elements.Field;


public class BlockInputParser {

	private Field field;
	
	
	public BlockInputParser(String fileInputPath){
		field = new Field();
		FileReader fr;
		try {
//			File file = new File(fileInputPath);
//			if(file.exists()){
			fr = new FileReader(fileInputPath);
			BufferedReader bf = new BufferedReader(fr);
			String s ="";
			while((s = bf.readLine()) != null){
				field.addBlock(new Block(s));
			}
			bf.close();
//			System.out.println(field);
//			} else {
//				System.out.println("File not found");
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not Found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		field.finalizeField();
	}
	
	public Field getField() {
		return field;
	}
}
