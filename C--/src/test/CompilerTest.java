package test;
import java.io.File;

import compiler.Lexar;

public class CompilerTest {
	public static void main(String[] args){
		Lexar lexar = new Lexar(new File(args[0]));
		lexar.listTokens();
	}
}
