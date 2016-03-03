package main.java.hello;

public class Greeting {
	private final long id;
	private final String content;
	
	Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public long getID() { return this.id; }
	public String getContent() { return this.content; }
}