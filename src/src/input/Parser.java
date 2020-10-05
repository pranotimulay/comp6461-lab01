package src.input;

public class Parser {

	public Command parseInput(String commandStr) {

		if (!commandStr.startsWith("httpc")) {
			return null;
		}
		
		commandStr = commandStr.replace("httpc ", "");
		System.out.println("remaining command: "+commandStr);
		
		String[] tokens = commandStr.split(" ");
		System.out.println(tokens[0]);

		if (tokens.length == 0) {
			return null;
		}

		if (tokens[0].trim().equals("help")) {
			System.out.println(
					"httpc is a curl-like application but supports HTTP protocol only. \nUsage:\n\t httpc command [arguments]");
			System.out.println(
					"The commands are:\n\tget\texecutes a HTTP GET request and prints the response.\n\tpost\texecutes a HTTP POST request and prints the response\n\thelp\tprints this screen");

			System.out.println("Use \"httpc help [command]\" for more information about a command.");
		}

		return null;

	}

}
