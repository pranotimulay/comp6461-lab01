package src.test;

public class StringSplitTester {

	public static void main(String[] args) {
		String url = "httpc get -v 'http://httpbin.org/get?course=networking&assignment=1'";

		String[] tokens = url.split("-v");

		for (int i = 0; i < tokens.length; i++) {
			System.out.println(tokens[i]);

		}
	}

}
