package tp.pr2.exceptions;

@SuppressWarnings("serial")
public class DivByZeroException extends ExecutionErrorException {
		
	/**
	 * 
	 * @param instr
	 */
	public DivByZeroException(String instr){
		super(instr);
	}
	
	/**
	 * devuelve el nombre de la excepción
	 * 
	 */
	@Override
	public String toString() {
		return "DivByZeroException";
	}
	
}
