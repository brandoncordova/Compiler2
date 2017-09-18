package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import compiler.Lexar;
import compiler.Parser;

public class CompilerTest {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		Lexar lexar = new Lexar(new File(args[0]));
		lexar.listTokens();
		Parser parser = new Parser(lexar);
	}
}
