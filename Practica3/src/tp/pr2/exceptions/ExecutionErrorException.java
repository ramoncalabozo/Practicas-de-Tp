package tp.pr2.exceptions;

@SuppressWarnings("serial")
public class ExecutionErrorException extends Exception{
	/**
	 * 
	 * @param instr
	 */
	public ExecutionErrorException(String instr){
		super(instr);
	}
	
	/**
	 * devuelve el nombre de la excepción
	 */
	@Override
	public String toString() {
		return "ExecutionErrorException";
	}
	
}
