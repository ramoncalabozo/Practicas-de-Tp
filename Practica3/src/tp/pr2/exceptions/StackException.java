package tp.pr2.exceptions;

@SuppressWarnings("serial")
public class StackException extends ExecutionErrorException {
	/**
	 * 
	 * @param instr
	 */
	public StackException(String instr){
		super(instr);
	}
	
	/**
	 * devuelve el nombre de la excepci�n
	 */
	@Override
	public String toString() {
		return "StackException";
	}
	
}
