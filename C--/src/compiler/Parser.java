package compiler;

import java.util.ArrayList;

public class Parser {
	private Lexar lexar;
	private int i;
	private ArrayList<Token> tokens;
	public Parser(Lexar lexar){
		this.lexar = lexar;
		this.tokens = this.lexar.getTokens();
		this.i = 0;
		this.run();
	}
	private void parseAssign(){
		
	}
	private void parseLine(){
		if(this.tokens.get(i).type==Type.WORD){
			switch(this.tokens.get(i).value){
				case "var":
					this.parseAssign();
					break;
				default:
					
					break;
			}
		}
	}
	private void run(){
		while(true){
			this.parseLine();
			if(!(this.tokens.get(this.i).value=="\n")){
				break;
			}
		}
	}
}
