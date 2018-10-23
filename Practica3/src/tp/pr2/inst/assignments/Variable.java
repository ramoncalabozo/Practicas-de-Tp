package tp.pr2.inst.assignments;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Load;

public class Variable implements Term {
	private String varName;
		
	public Variable (String term){
			this.varName = term;
		}
	
	public ByteCode compile(tp.pr2.elements.Compiler compile) {
        	if (compile.indexOf(varName)==-1){
        		compile.addVariable(varName);
        	}
		return new Load (compile.indexOf (varName));
        
	}

	@Override
	public Term parse(String term) {
		if (term.length()!=1) return null;
		else{
		char name = term.charAt(0);
		if ('a' <= name && name <='z') return new Variable(term);
		else return null;
		}
	}

}
