package tp.pr2.inst;


// CREO QUE ESTA CLASE YA ESTÁ HECHA

import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;

public interface Instruction {
		void compile (tp.pr2.elements.Compiler compiler)throws ArrayException;
		Instruction lexParse (String[] words , LexicalParser lexParser);
}
