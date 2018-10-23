package tp.pr2.inst.conditionals;

import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.inst.assignments.Term;
import tp.pr2.inst.assignments.TermParser;

public abstract class Condition {
	private Term term1;
	private Term term2;
	protected ConditionalJump cj;

		public Condition (Term term1,Term term2){
			this.term1 = term1;
			this.term2 = term2;
		}
	
	public Condition() {
		}

	public Condition parse(String t1, String op, String t2, LexicalParser parser) {
		this.term1 = TermParser.parse(t1);
		this.term2 = TermParser.parse(t2);
		
		return parseOP(term1, op, term2, parser);
	}

	protected abstract Condition parseOP(Term term1, String op, Term term2, LexicalParser parser);// este método irá parseando en cada clase
		
	public void compile(tp.pr2.elements.Compiler compiler) throws ArrayException{
		compiler.addByteCode(this.term1.compile(compiler));
		compiler.addByteCode(this.term2.compile(compiler));
		this.cj = compileop(compiler);
		compiler.addByteCode(this.cj);
	
}

	protected abstract ConditionalJump compileop(tp.pr2.elements.Compiler compiler);

	public void set(int i){
		this.cj.setN(i);// en cada clase
	}
}
