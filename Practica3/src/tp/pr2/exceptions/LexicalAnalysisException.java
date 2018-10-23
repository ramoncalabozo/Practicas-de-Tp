package tp.pr2.exceptions;

@SuppressWarnings("serial")
public class LexicalAnalysisException extends Exception{
	/**
	 * 
	 * @param instr
	 */
	public LexicalAnalysisException(String instr){
		super(instr);
	}
	
	/**
	 * devuelve el nombre de la excepci�n
	 * 
	 */
	@Override
	public String toString() {
		return "LexicalAnalysisException";
	}
	
}
