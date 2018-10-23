package tp.pr2.inst;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Halt;
import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;


// CREO QUE ESTA CLASE YA ESTA HECHA

public class Return implements Instruction {

	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if (!words[0].equalsIgnoreCase("RETURN")){
		return null;
	}
		else {
			//lexParser.increaseProgramCounter();
			return new Return ();
		}
}
	@Override
	public void compile(tp.pr2.elements.Compiler compiler)throws ArrayException {
		ByteCode halt = new Halt ();
		compiler.addByteCode(halt);
	}

}
