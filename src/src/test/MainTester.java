package src.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map.Entry;

import src.input.Command;
import src.input.RequestType;

public class MainTester {

	public static void main(String[] args) throws UnknownHostException, IOException {

//		testGet();

		// testPostWithSocket();

		testPostWithUrlConnection();

	}

	private static void testPostWithUrlConnection() throws IOException {

		// create command object with basic info
		Command command = new Command();
		command.setType(RequestType.GET);
		command.setUrl("http://httpbin.org/post");
		command.setInlineData("{\"Assignment\": 1}");

		// create request parameters
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");

		// add parameters to request command
		command.setHeaders(headersMap);

		// Create a URL Object
		URL url = new URL(command.getUrl());

		// open a connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// Set the Request Method
		con.setRequestMethod("POST");

		// Set the Request Content-Type Header Parameter
		for (Entry<String, String> header : command.getHeaders().entrySet()) {
			con.setRequestProperty(header.getKey(), header.getValue() + "; utf-8");
		}

		// Set Response Format Type
		con.setRequestProperty("Accept", "application/json");

		// Ensure the Connection Will Be Used to Send Content
		con.setDoOutput(true);

		// create request body with inline data
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = command.getInlineData().getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		// get response
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}

	}

	private static void testPostWithSocket() throws IOException {

		// create command object with basic info
		Command command = new Command();
		command.setType(RequestType.GET);
		command.setUrl("httpbin.org/post");
		command.setInlineData("{\"Assignment\": 1}");

		// create request parameters
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");

		// add parameters to request command
		command.setHeaders(headersMap);

		// create parameter string to append in request
		String paramStr = "";
		for (Entry<String, String> header : command.getHeaders().entrySet()) {

			String paramName = header.getKey();
			String paramValue = header.getValue();
			paramStr += "&" + URLEncoder.encode(paramName, "UTF-8") + "=" + URLEncoder.encode(paramValue, "UTF-8");

		}
		paramStr = paramStr.replaceFirst("&", "");
		System.out.println("Paramstr: " + paramStr);

		// create inet for url
		InetAddress addr = InetAddress.getByName(command.getUrl());

		// create socket
		Socket socket = new Socket(addr, 80);

		// take the file path from command object
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

		// create command object with basic info
		Command command = new Command();
		command.setType(RequestType.GET);
		command.setUrl("httpbin.org");

		// create sockdt for url
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
