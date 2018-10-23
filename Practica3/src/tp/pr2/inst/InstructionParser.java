package tp.pr2.inst;

import tp.pr2.elements.LexicalParser;
import tp.pr2.inst.assignments.CompoundAssignment;
import tp.pr2.inst.assignments.SimpleAssignament;
import tp.pr2.inst.conditionals.IfThen;
import tp.pr2.inst.conditionals.While;

public class InstructionParser {
	
	private final static Instruction[] instructions = {
			new SimpleAssignament(), new CompoundAssignment(),
			new Write(), new Return(), new While(), new IfThen()};

public static Instruction parse (String line, LexicalParser parser){
		line = line.trim();
		String[] arrayLine = line.split(" +");
		 Instruction inst = null;
		 for (Instruction ins:instructions) {
		inst= ins.lexParse(arrayLine,parser); 
		if (inst != null) {
			return inst;
			} 
		} 
		return inst;
	}
}
