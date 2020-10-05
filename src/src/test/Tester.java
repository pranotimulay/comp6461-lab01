package src.test;

import src.input.Parser;

public class Tester {

	
	
	public static void main(String[] args) {
		
		
		String commandStr = "httpc help";
		Parser parser = new Parser();
		parser.parseInput(commandStr);
		
	}
	
	
}
