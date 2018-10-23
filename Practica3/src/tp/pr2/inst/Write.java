package tp.pr2.inst;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Out;
import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.inst.assignments.Term;
import tp.pr2.inst.assignments.TermParser;

public class Write implements Instruction{
	private Term varName;
		public Write (Term varName){
			this.varName = varName;
		}

	public Write() {
			// TODO Auto-generated constructor stub
		}

	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if (!words[0].equalsIgnoreCase("WRITE")){
			return null;
		}
		else {
			Term escribe = TermParser.parse(words[1]);
			//lexParser.increaseProgramCounter();
			return new Write (escribe);
		}
		
	}

	@Override
	public void compile(tp.pr2.elements.Compiler compiler)throws ArrayException {
		ByteCode bc = this.varName.compile(compiler);// Compila lo que va escribir
		ByteCode bytecode = new Out();
		compiler.addByteCode(bc);
		compiler.addByteCode(bytecode);
	}

}
