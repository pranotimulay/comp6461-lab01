package src.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map.Entry;

import src.input.Command;
import src.input.RequestType;

public class MainTester {

	public static void main(String[] args) throws UnknownHostException, IOException {

//		testGet();

		testPost();
	}

	private static void testPost() throws IOException {
		Command command = new Command();
		command.setType(RequestType.GET);
		command.setUrl("httpbin.org");

		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("content", "application/json");

		command.setHeaders(headersMap);

		String paramStr = "";

		for (Entry<String, String> header : command.getHeaders().entrySet()) {

			String paramName = header.getKey();
			String paramValue = header.getValue();
			paramStr += "&" + URLEncoder.encode(paramName, "UTF-8") + "=" + URLEncoder.encode(paramValue, "UTF-8");

		}

		paramStr = paramStr.replaceFirst("&", "");

		System.out.println("Paramstr: " + paramStr);

		InetAddress addr = InetAddress.getByName(command.getUrl());
		Socket socket = new Socket(addr, 80);
		String path = command.getFilePath();

		// Send headers
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		wr.write("POST " + path + " HTTP/1.0rn");
		wr.write("Content-Length: " + command.getHeaders().size() + "rn");
		wr.write("Content-Type: application/x-www-form-urlencodedrn");
		wr.write("rn");

		// Send parameters
		wr.write(paramStr);
		wr.flush();

		// Get response
		BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line;

		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}

		wr.close();
		rd.close();
		socket.close();

	}

	private static void testGet() throws UnknownHostException, IOException {
		Command command = new Command();
		command.setType(RequestType.GET);
		command.setUrl("httpbin.org");

		Socket socket = new Socket(command.getUrl(), 80);

		// Instantiates a new PrintWriter passing in the sockets output stream
		PrintWriter wtr = new PrintWriter(socket.getOutputStream());

		// Prints the request string to the output stream
		wtr.println("GET  /status/418 HTTP/1.0");
		wtr.println("Host: httpbin.org");
		wtr.println("");
		wtr.flush();

		// Creates a BufferedReader that contains the server response
		BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String outStr;

		// Prints each line of the response
		while ((outStr = bufRead.readLine()) != null) {
			System.out.println(outStr);
		}

		// Closes out buffer and writer
		bufRead.close();
	}

}
