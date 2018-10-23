package tp.pr2.inst.conditionals;

import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.bc.Jumps.conditionalJumps.IfLeq;
import tp.pr2.elements.Compiler;
import tp.pr2.elements.LexicalParser;
import tp.pr2.inst.assignments.Term;

public class LessEq extends Condition {
	
	public LessEq (Term term1,Term term2){
		super(term1,term2);
}
	public LessEq() {
	}
	@Override
	protected Condition parseOP(Term term1, String op,Term term2, LexicalParser parser) {
		if (!op.equalsIgnoreCase("<=") ){
			return null;
			}
			else {
				//parser.increaseProgramCounter();
				return new LessEq (term1,term2); // No sé si esto está así bien porque no uso los dos terminos
			}
	}

	@Override
	protected ConditionalJump compileop(Compiler compiler) {
		this.cj = new IfLeq(0);
		return cj;
	}

}
