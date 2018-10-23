package tp.pr2.inst.conditionals;

import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.LexicalAnalysisException;
import tp.pr2.inst.Instruction;
import tp.pr2.mv.ParsedProgram;

public class IfThen implements Instruction {
	
	private Condition condicion;
	private ParsedProgram body;

	public IfThen(Condition condition, ParsedProgram body){// Creo que necesitaría algo más aquí
		this.condicion = condition;
		this.body = body;
		
	}
	public IfThen() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser)/*throws ArrayException, LexicalAnalysisException*/{
		//if i < 5
		if(words.length != 4){
		return null;}
		if(words[0].equalsIgnoreCase("if")){
			Condition condicion = ConditionalParser.parse(words[1], words[2], words[3], lexParser);
			
			if(condicion == null){return null;}
			ParsedProgram wb = new ParsedProgram();
			try{
			lexParser.increaseProgramCounter();
			lexParser.lexicalParser(wb,"ENDIF");}
			catch(LexicalAnalysisException ex){
				return null;
			}
			
			return new IfThen(condicion ,wb);}
		return null;
		}
	

	public void compile(tp.pr2.elements.Compiler compiler)throws ArrayException {
		this.condicion.compile(compiler);
		compiler.compile(this.body);
		int j = compiler.getSizeBcProgram();
		this.condicion.set(j);
	}


}
