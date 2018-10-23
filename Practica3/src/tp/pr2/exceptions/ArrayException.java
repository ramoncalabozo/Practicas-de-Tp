package tp.pr2.exceptions;

@SuppressWarnings("serial")
public class ArrayException extends Exception{
	
	/**
	 * Constructora de ArrayException que recibe un string
	 * @param instr
	 */
	public ArrayException(String instr){
		super(instr);
	}
	
	/**
	 * devuelve el nombre de la excepción
	 */
	@Override
	public String toString() {
		return "ArrayException";
	}
}
