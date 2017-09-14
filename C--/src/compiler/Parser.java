package compiler;

import compiler.Type;

import java.util.ArrayList;

public class Parser {
	private ArrayList<Statement> statements;
	private ArrayList<Token> tokens;
	private Token currentToken;
	private LibrariesAndVars libraries = new LibrariesAndVars();
	private int xpos = 0;
	public Parser(ArrayList<Token> tokens,int xpos, int ypos){
		this.tokens = tokens;
		this.parse();
	}
	private void parseFunctionCall(){
		this.xpos++;
		if(this.tokens.get(this.xpos).value!="("){
			System.out.println("["+this.tokens.get(this.xpos).xpos+","+this.tokens.get(this.xpos).ypos+"] Syntax error: Missing Parenthesis '('");
			return;
		}
		this.xpos++;
		while(this.tokens.get(this.xpos).value!=")"){
			//TODO
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
