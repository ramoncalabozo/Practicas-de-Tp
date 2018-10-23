package tp.pr2.inst.assignments;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Store;
import tp.pr2.bc.Arithmetics.Add;
import tp.pr2.bc.Arithmetics.Div;
import tp.pr2.bc.Arithmetics.Mul;
import tp.pr2.bc.Arithmetics.Sub;
import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.inst.Instruction;

public class CompoundAssignment implements Instruction{

	private String varName;
	private String operator;
	private Term term1;
	private Term term2;
		
		public CompoundAssignment (String varName,String operator,Term term1,Term term2){
			this.varName = varName;
			this.operator = operator;
			this.term1 = term1;
			this.term2 = term2;
		}

	public CompoundAssignment() {
			// TODO Auto-generated constructor stub
		}
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if (words.length != 5) {
			return null;
		}
		if (!words[1].equalsIgnoreCase("=") ||(!words[3].equalsIgnoreCase("+") && !words[3].equalsIgnoreCase("-") && !words[3].equalsIgnoreCase( "/") && !words[3].equalsIgnoreCase( "*") ) ) {
			return null;
		}
		Term variable = TermParser.parse(words[0]);
		Term rhs = TermParser.parse(words[2]);
		Term rhs2 = TermParser.parse(words[4]);// LO he añadido yo pero no sé si está bien
		if (variable == null ||rhs == null || rhs2 == null) {
			return null;
		}
		//lexParser.increaseProgramCounter();// No sé si esto es está bien
		return new CompoundAssignment (words[0],words[3],rhs,rhs2);
	}

	@Override
	public void compile(tp.pr2.elements.Compiler compiler)throws ArrayException {
		ByteCode termino1 =  this.term1.compile(compiler);
		ByteCode termino2 =  this.term2.compile (compiler);
		ByteCode operacion = null;
		ByteCode guardar ;
		// Ahora con este switch vamos a ver que operación es
		switch (operator){
		case "/":operacion = new Div();break;
		case "*":operacion = new Mul();break;
		case "-":operacion = new Sub();break;
		case "+":operacion = new Add();break;
		}
		if (compiler.indexOf(this.varName) == -1){ // Si no existe esa variable	
		compiler.addVariable(this.varName);
		}
		int indice = compiler.indexOf(varName);
		guardar = new Store (indice);
		compiler.addByteCode(termino1);
		compiler.addByteCode(termino2);
		compiler.addByteCode(operacion);
		compiler.addByteCode(guardar);
	}

}
