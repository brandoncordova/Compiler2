package compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lexar {
	
	private BufferedReader reader;
	private String line = "\n";
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private int xpos = 0;
	private int ypos = -1;
	private Token currentToken;
	
	public Lexar(BufferedReader reader){
		this.reader = reader;
		this.lex();
	}
	public Lexar(File reader){
		try {
			this.reader = new BufferedReader(new FileReader(reader));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		this.lex();
	}
	private void addChar(){
		this.currentToken.value+=this.line.charAt(xpos);
		this.xpos++;
	}
	private void addToken(){
		this.tokens.add(this.currentToken);
	}
	private void addWordToken(char type){
		currentToken = new Token(xpos,ypos,"",Type.WORD);
		addChar();
		try {
			while((Character.isAlphabetic(this.line.charAt(this.xpos))||Character.isDigit(this.line.charAt(this.xpos)))){
				this.addChar();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.addToken();
	}
	private void addStringToken(char type){
		boolean canEvaluate = true;
		this.currentToken = new Token(this.xpos,this.ypos,"",Type.STRING);
		this.xpos++;
		try {
			while(canEvaluate&(this.line.charAt(this.xpos)!=type)){
				this.addChar();
			}
		}
		catch(StringIndexOutOfBoundsException e){
			this.addToken();
			this.xpos++;
			return;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.xpos++;
		this.addToken();
	}
	private void addNumberToken(){
	    int c = 0;
	    this.currentToken = new Token(this.xpos,this.ypos,"",Type.NUMBER);
	    try {
	    	while(Character.isDigit(this.line.charAt(this.xpos))||(c==0&&this.line.charAt(this.xpos+1)=='.')){
	    		if(this.line.charAt(this.xpos)=='.'){
	    			c++;
	    		}
	    		this.currentToken.value+=this.line.charAt(this.xpos);
	    		//System.out.println("Value of digit: "+this.line.charAt(this.xpos));
	    		this.xpos++;
	    	}
	    }catch(StringIndexOutOfBoundsException e){
	    	this.xpos = 0;
	    	try {
	    		this.line = this.reader.readLine();
	    	}catch(IOException e1){
	    		e.printStackTrace();
	    	}
	    }
	    this.addToken();
	}
	private void lex() {
		try {
			while(true){
				try{
					//System.out.println(this.line.charAt(xpos));
					switch(this.line.charAt(this.xpos)){
						case '\n':
							this.xpos++;
							break;
						case '/':
							if(this.line.charAt(xpos+1)=='/'){
								this.xpos = 0;
								this.line = this.reader.readLine();
							}
							else if(this.line.charAt(this.xpos+1)=='='){
								this.tokens.add(new Token(this.xpos,this.ypos,"/=",Type.OPERATOR));
							}
							else {
								this.tokens.add(new Token(this.xpos,this.ypos,"/",Type.OPERATOR));
							}
							break;
						case '(':
						case ')':
							this.tokens.add(new Token(this.xpos, this.ypos, this.line.charAt(xpos)+"",Type.PARENTHESIS));
							this.xpos++;
							break;
						case '{':
						case '}':
							this.tokens.add(new Token(this.xpos,this.ypos,this.line.charAt(xpos)+"",Type.BRACKET));
							xpos++;
						case '\'':
						case '"':
							this.addStringToken(this.line.charAt(this.xpos));
							break;
						case '+':
						case '-':
						case '*':
							if(this.line.charAt(this.xpos+1)=='='){
									this.tokens.add(new Token(this.xpos,this.ypos,""+this.line.charAt(xpos)+this.line.charAt(xpos+1),Type.OPERATOR));
									this.xpos+=2;
							}
							else if (this.line.charAt(this.xpos+1)==this.line.charAt(this.xpos)){
								this.tokens.add(new Token(this.xpos,this.ypos,""+this.line.charAt(this.xpos)+this.line.charAt(this.xpos),Type.OPERATOR));
								this.xpos+=2;
							}
							else {
								this.tokens.add(new Token(this.xpos,this.ypos,""+this.line.charAt(xpos),Type.OPERATOR));
							   	this.xpos++;
							}
							break;
						case ' ':
							this.xpos++;
							break;
						case '=':
							if(this.line.charAt(this.xpos+1)=='='){
								this.tokens.add(new Token(this.xpos,this.ypos,"==",Type.OPERATOR));
								xpos+=2;
							}
							else {
								tokens.add(new Token(this.xpos,this.ypos,"=",Type.OPERATOR));
								xpos++;
							}
							break;
						default:
							if(Character.isAlphabetic(this.line.charAt(this.xpos))){
								this.addWordToken(this.line.charAt(this.xpos));
								break;
							}
							else if(Character.isDigit(this.line.charAt(xpos))){
								this.addNumberToken();
								break;
							}
							System.out.println("Unknown char "+this.line.charAt(xpos));
							return;
					}
				}
				catch(StringIndexOutOfBoundsException e){
					try {
						this.line = this.reader.readLine();
						this.xpos = 0;
					}
					catch(IOException e1){
						return;
					}
				}
				catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
		catch(NullPointerException e){
			return;
		}
	}
	public void listTokens(){
		for(Token token : this.tokens){
			token.getInfo();
		}
	}
	public ArrayList<Token> getTokens(){
		return this.tokens;
	}
}
