package tp.pr2.cm;


import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.LexicalAnalysisException;



import tp.pr2.mv.Engine;

public class Compile implements Command{

	@Override
	public void execute(Engine engine) throws LexicalAnalysisException,ArrayException {
		engine.executeCompile(); 
	}

	@Override
	public String textHelp() {
		return " Compile :Realiza el análisis léxico del programa fuente " + System.lineSeparator();
	}

	@Override
	public Command parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("COMPILE"))
			return new Compile();
			else return null;
	}
	
}
