package compiler;

public class Token {
	public int xpos,ypos;
	public String value;
	public Type type;
	
	public Token(int xpos, int ypos,String value){
		this.xpos = xpos;
		this.ypos = ypos;
		this.value = value;
		this.type = Type.DEFAULT;
	}
	public Token(int xpos, int ypos,String value,Type type){
		this.xpos = xpos;
		this.ypos = ypos;
		this.value = value;
		this.type = type;
	}
	public Token(){
		this.xpos = -1;
		this.ypos = -1;
		this.type = Type.DEFAULT;
		this.value = "";
	}
}
