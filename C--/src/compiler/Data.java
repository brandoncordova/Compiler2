package compiler;

import java.util.ArrayList;

public class Data {
	private static ArrayList<String> variables;
	private static ArrayList<String> functions;
	
	public static boolean isVar(String name){
		for(String s : variables){
			if(name==s){
				return true;
			}
		}
		return false;
	}
	public static boolean isFunc(String name){
		for(String s : functions){
			if(s==name){
				return true;
			}
		}
		return false;
	}
}
