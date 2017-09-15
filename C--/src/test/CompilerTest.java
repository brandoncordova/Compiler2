package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import compiler.Lexar;

public class CompilerTest {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		System.out.println(new BufferedReader(new FileReader(new File(args[0]))).readLine());
		Lexar lexar = new Lexar(new File(args[0]));
		lexar.listTokens();
	}
}
