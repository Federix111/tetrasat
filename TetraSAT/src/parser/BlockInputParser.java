package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.Block;
import main.Field;

public class BlockInputParser {

	public Field field;
	public static final String INPUTFILEPATH="blocks.txt";
	
	public BlockInputParser(){
		field = new Field();
		FileReader fr;
		try {
			File file = new File(INPUTFILEPATH);
			if(file.exists()){
			fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String s ="";
			while((s = bf.readLine()) != null){
				field.addBlock(new Block(s));
			}
			bf.close();
			System.out.println(field);
			} else {
				System.out.println("File not found");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
