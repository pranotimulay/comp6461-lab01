package src.input;

import java.util.HashMap;

public class Command {

	RequestType type;
	boolean verboseOption;
	HashMap<String, String> headers;
	String inlineData;
	String filePath;
	String url;

	public Command(RequestType type, boolean verboseOption, HashMap<String, String> headers, String inlineData,
			String filePath, String url) {
		super();
		this.type = type;
		this.verboseOption = verboseOption;
		this.headers = headers;
		this.inlineData = inlineData;
		this.filePath = filePath;
		this.url = url;
	}

	public RequestType getType() {
		return type;
	}

	public void setType(RequestType type) {
		this.type = type;
	}

	public boolean isVerboseOption() {
		return verboseOption;
	}

	public void setVerboseOption(boolean verboseOption) {
		this.verboseOption = verboseOption;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public String getInlineData() {
		return inlineData;
	}

	public void setInlineData(String inlineData) {
		this.inlineData = inlineData;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
