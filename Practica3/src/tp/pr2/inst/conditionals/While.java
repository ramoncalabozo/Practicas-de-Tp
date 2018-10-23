package tp.pr2.inst.conditionals;

import tp.pr2.bc.Jumps.Goto;
import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.LexicalAnalysisException;
import tp.pr2.inst.Instruction;
import tp.pr2.mv.ParsedProgram;

public class While implements Instruction {
	private ParsedProgram wbody;
	private Condition condition;
	
	public While (Condition cd,ParsedProgram pP){
		this.condition = cd;
		this.wbody = pP;
	}
	public While() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if(words.length != 4){
			return null;
		}
		if(words[0].equalsIgnoreCase("while")){		
		Condition condicion = ConditionalParser.parse(words[1], words[2], words[3], lexParser);// Peta aquí
		if(condicion == null){return null;}
		ParsedProgram wb = new ParsedProgram();
		try{
		lexParser.increaseProgramCounter();// Lo he puesto aquí pero puede ser una locura
		lexParser.lexicalParser(wb,"ENDWHILE");
		}
		catch(LexicalAnalysisException ex){
			return null;
		}
		return new While (condicion ,wb);}
		else{
	return null;}
	}
	
	@Override
	public void compile(tp.pr2.elements.Compiler compiler)throws ArrayException {
		int i = compiler.getSizeBcProgram();
		this.condition.compile(compiler);
		compiler.compile(this.wbody);
		compiler.addByteCode(new Goto(i));// Lo he cambiado una posición más abajo
		int j = compiler.getSizeBcProgram();
		this.condition.set(j);
	}

}
