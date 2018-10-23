package tp.pr2.exceptions;

@SuppressWarnings("serial")
public class BadFormatByteCodeException extends Exception{
	/**
	 *  
	 * @param instr
	 */
	public BadFormatByteCodeException(String instr){
		super(instr);
	}
	
	/**
	 * devuelve el nombre de la excepción
	 */
	@Override
	public String toString() {
		return "BadFormatByteCodeException";
	}
	
}
