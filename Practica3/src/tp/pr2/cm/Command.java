package tp.pr2.cm;

import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.BadFormatByteCodeException;
import tp.pr2.exceptions.DivByZeroException;
import tp.pr2.exceptions.ExecutionErrorException;
import tp.pr2.exceptions.LexicalAnalysisException;
import tp.pr2.exceptions.StackException;

//CREO QUE ESTA CLASE YA ESTA


import tp.pr2.mv.Engine;
/** 	
 * Clase abstracta con 3 metodos abstractos
 * @author salva
 *
 */
   public interface Command {
    void execute(Engine engine)  throws java.io.FileNotFoundException,
    LexicalAnalysisException,
    ArrayException,
    BadFormatByteCodeException,
    StackException,
    DivByZeroException,
    ExecutionErrorException;
    String textHelp();
    Command parse(String[ ] s);
}
