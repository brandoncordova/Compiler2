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
		currentToken.value+=this.line.charAt(xpos);
		xpos++;
	}
	private void addToken(){
		this.tokens.add(this.currentToken);
	}
	private void addWordToken(char type){
		currentToken = new Token(xpos,ypos,"",Type.WORD);
		addChar();
		try {
			while((Character.isAlphabetic((int) this.line.charAt(xpos))||Character.isDigit(this.line.charAt(xpos)))){
				addChar();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		addToken();
	}
	private void addStringToken(char type){
		boolean canEvaluate = true;
		currentToken = new Token(xpos,ypos,"",Type.STRING);
		xpos++;
		try {
			while(canEvaluate&(this.line.charAt(xpos)!=type)){
				addChar();
			}
		}
		catch(StringIndexOutOfBoundsException e){
			addToken();
			xpos++;
			return;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		addToken();
	}
	private void lex() {
		try {
			while(true){
				try{
					switch(this.line.charAt(xpos)){
						case '\n':
							xpos++;
							break;
						case '(':
						case ')':
							tokens.add(new Token(this.xpos, this.ypos, this.line.charAt(xpos)+"",Type.PARENTHESIS));
							xpos++;
							break;
						case '{':
						case '}':
							tokens.add(new Token(this.xpos,this.ypos,this.line.charAt(xpos)+"",Type.BRACKET));
							xpos++;
						case '\'':
						case '"':
							addStringToken(this.line.charAt(xpos));
							break;
						case '+':
						case '-':
						case '*':
							if(this.line.charAt(xpos+1)=='='){
									this.tokens.add(new Token(this.xpos,this.ypos,""+this.line.charAt(xpos)+this.line.charAt(xpos+1),Type.OPERATOR));
							}
							else if (this.line.charAt(xpos+1)==this.line.charAt(xpos)){
								this.tokens.add(new Token(this.xpos,this.ypos,""+this.line.charAt(xpos)+this.line.charAt(xpos),Type.OPERATOR));
							}
							else {
								this.tokens.add(new Token(this.xpos,this.ypos,""+this.line.charAt(xpos),Type.OPERATOR));
							}
							break;
						default:
							if(Character.isAlphabetic((int) this.line.charAt(xpos))){
								addWordToken(this.line.charAt(xpos));
								break;
							}
							System.out.println("Unknown char "+this.line.charAt(xpos));
							return;
					}
				}
				catch(StringIndexOutOfBoundsException e){
					try {
						this.line = this.reader.readLine();
						xpos = 0;
					}
					catch(IOException e1){
						return;
					}
				}
			}
		}
		catch(NullPointerException e){
			return;
		}
	}
	public void listTokens(){
		for(Token token : tokens){
			token.getInfo();
		}
	}
}
