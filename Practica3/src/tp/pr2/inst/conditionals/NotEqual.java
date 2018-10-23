package tp.pr2.inst.conditionals;

import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.bc.Jumps.conditionalJumps.ifNeq;
import tp.pr2.elements.Compiler;
import tp.pr2.elements.LexicalParser;
import tp.pr2.inst.assignments.Term;

public class NotEqual extends Condition {
	
	public NotEqual(Term term1,Term term2){
		super(term1,term2);
}
	public NotEqual() {
	}
	@Override
	protected Condition parseOP(Term term1, String op,Term term2, LexicalParser parser) {
		if (!op.equalsIgnoreCase("!=") ){
			return null;
			}
			else {
				//parser.increaseProgramCounter();
				return new NotEqual (term1,term2); 
			}
	}

	@Override
	protected ConditionalJump compileop(Compiler compiler) {
		this.cj = new ifNeq(0); // cambiar el parametro
		return cj;
	}


}
