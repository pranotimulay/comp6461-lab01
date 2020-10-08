package src.test;

import java.util.Scanner;

import src.input.Parser;

public class CommandTester {

	public static void main(String[] args) {

//		Scanner scanner = new Scanner(System.in);
//		String command = scanner.nextLine();
//		
		String commandStr = "httpc get -v 'http://httpbin.org/get?course=networking&assignment=1'";
		Parser parser = new Parser();
		parser.parseInput(commandStr);

	}

}
