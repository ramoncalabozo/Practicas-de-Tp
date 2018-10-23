package tp.pr2.inst.assignments;

import tp.pr2.bc.ByteCode;




public interface Term {
	ByteCode compile (tp.pr2.elements.Compiler compiler);
	Term parse (String term);
}
