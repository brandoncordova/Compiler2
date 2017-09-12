package compiler;

import java.util.ArrayList;

public class LibrariesAndVars {
	private ArrayList<String> functions = new ArrayList<String>();
	public boolean isFunction(String name){
		for(String function : functions){
			if(name==function){
				return true;
			}
		}
		return false;
	}
}
