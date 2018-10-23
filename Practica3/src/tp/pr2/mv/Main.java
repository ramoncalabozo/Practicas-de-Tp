package tp.pr2.mv;

import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.LexicalAnalysisException;

public class Main {
	
	/**
	 * Crea el Engine y lo ejecuta
	 * @param args
	 */
	public static void main(String[] args)throws LexicalAnalysisException,java.io.FileNotFoundException,ArrayException {
		Engine engine = new Engine();
		engine.start();
	}

}
