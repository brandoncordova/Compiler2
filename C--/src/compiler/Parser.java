package compiler;

import compiler.Type;

import java.util.ArrayList;

public class Parser {
	private ArrayList<Token> tokens;
	private Token currentToken;
	private LibrariesAndVars libraries = new LibrariesAndVars();
	private int xpos = 0;
	private int xPos;
	private int yPos;
	public Parser(ArrayList<Token> tokens,int xpos, int ypos){
		this.tokens = tokens;
		this.xPos = xpos;
		this.yPos = ypos;
		this.parse();
	}
	private void parseFunctionCall(){
		this.xpos++;
		if(this.tokens.get(xpos).type!=Type.PARENTHESIS){
			System.out.println("["+this.xPos+","+this.yPos+"] Syntax error: Missing Parenthesis '('");
			return;
		}
		
	}
	private void parse(){
		this.currentToken = this.tokens.get(xpos);
		switch(this.currentToken.type){
			/*case RESERVED_WORD:
				switch(this.currentToken.value){
					
				}*/
			case WORD:
				switch(this.currentToken.value){
					default:
						if(libraries.isFunction(this.currentToken.value)){
							this.parseFunctionCall();
						}
						this.xpos++;
						break;
				}
			default:
				break;
		}
	}
}
