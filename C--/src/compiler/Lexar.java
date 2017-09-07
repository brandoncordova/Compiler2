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
	private void addWordToken(){
		
	}
	private void lex(){
		try {
			while(this.line.charAt(xpos)!='\0'){
				//System.out.print(xpos+" & "+this.line.length());
				try {
					switch(this.line.charAt(xpos)){
						case '(':
						case ')':
							tokens.add(new Token(this.xpos, this.ypos, this.line.charAt(xpos)+"",Type.PARENTHESIS));
							break;
						case '{':
						case '}':
							tokens.add(new Token(this.xpos,this.ypos,this.line.charAt(xpos)+"",Type.BRACKET));
						default:
							if(Character.isAlphabetic((int) this.line.charAt(xpos))){
								addWordToken();
							}
							System.out.println(this.line.charAt(xpos));
							xpos++;
							break;
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		catch(StringIndexOutOfBoundsException e){
			try {
				this.line = this.reader.readLine();
				System.out.println("Newline");
				xpos = 0;
				this.lex();
			}
			catch(IOException e1){
				System.out.println("EOF");
			}
		}
		catch(NullPointerException e){
			return;
		}
	}
	
}
