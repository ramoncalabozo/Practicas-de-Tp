package tp.pr2.elements;

import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.LexicalAnalysisException;
import tp.pr2.inst.Instruction;
import tp.pr2.inst.InstructionParser;

// FALTA ESTE MÉTODO QUE MUY IMPORTANTE PARA LA PRÁCTICA

import tp.pr2.mv.ParsedProgram;
import tp.pr2.mv.SourceProgram;

public class LexicalParser {
	private SourceProgram sProgram;
	private int programCounter;
		
	public LexicalParser (SourceProgram sProgram){
		this.programCounter = 0;
		this.sProgram = sProgram;
	}
		
	public void increaseProgramCounter() {
		this.programCounter++;
	}

	public void initialize(SourceProgram sProgram) {
		sProgram.reset();
	}

	public void lexicalParser(ParsedProgram pProgram,String stopKey)throws LexicalAnalysisException{;
		boolean stop = false;
		try{
			while (this.programCounter < this.sProgram.getSize()&& !stop){
		String line = sProgram.getInstruction(this.programCounter);
		if (line.equalsIgnoreCase(stopKey)){
			stop = true;
			}
		else{
			Instruction instruction	= InstructionParser.parse(line,this);
			if (instruction != null){
				pProgram.addInsr(instruction);
				increaseProgramCounter();	
				}
			else{
				throw new LexicalAnalysisException(line + " es una instrucción no válida" + ", linea " +this.programCounter);
			}
			}
		}
	if (!stop){
		throw new LexicalAnalysisException("No se ha encontrado la StopKey");
				}
		}catch(ArrayException ex){
			throw new LexicalAnalysisException(ex.getMessage());
		}
		
	}
}
