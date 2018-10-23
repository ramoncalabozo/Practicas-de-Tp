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
	 * devuelve el nombre de la excepci�n
	 */
	@Override
	public String toString() {
		return "ExecutionErrorException";
	}
	
}
