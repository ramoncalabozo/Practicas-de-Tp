package tp.pr2.inst.conditionals;

import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.bc.Jumps.conditionalJumps.IfLe;
import tp.pr2.elements.Compiler;
import tp.pr2.elements.LexicalParser;
import tp.pr2.inst.assignments.Term;

public class Less extends Condition {
	
	public Less(Term term1,Term term2){
		super(term1,term2);
}
	public Less() {
		super();
	}
	@Override
	protected Condition parseOP(Term term1, String op,Term term2, LexicalParser parser) {
		if (!op.equalsIgnoreCase("<")){
		return null;
		}
		else {
			//parser.increaseProgramCounter();
			return new Less (term1,term2);
		}
	}

	@Override
	protected ConditionalJump compileop(Compiler compiler) {
		this.cj = new IfLe(0);
		return cj;
	}

}
