package tp.pr2.inst.assignments;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Store;
import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.inst.Instruction;

public class SimpleAssignament implements Instruction {
	private String varName;
	private Term rhs;

	public SimpleAssignament(String varName, Term rhs) {
		this.varName = varName;
		this.rhs = rhs;
	}

	public SimpleAssignament() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if (words.length != 3) {
			return null;
		} // x = 0
		
		if (!words[1].equalsIgnoreCase("=")) {
			return null;
		}
	
		Term rhs = TermParser.parse(words[2]);
		if (rhs == null ) {
			return null;
		}
		return new SimpleAssignament(words[0], rhs);
	}

	@Override
	public void compile(tp.pr2.elements.Compiler compiler)throws ArrayException {
		
		ByteCode termino = this.rhs.compile (compiler); 
			if (compiler.indexOf(varName)== -1){
				compiler.addVariable(varName);
			}
		ByteCode guardar = new Store (compiler.indexOf(varName));
		compiler.addByteCode(termino);
		compiler.addByteCode(guardar);
	}
}
