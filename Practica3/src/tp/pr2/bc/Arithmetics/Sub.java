package tp.pr2.bc.Arithmetics;




import tp.pr2.bc.ByteCode;
import tp.pr2.elements.CPU;
import tp.pr2.exceptions.StackException;

public class Sub extends Arithmetics {

	@Override
	/**
	 * Realiza la operacion de resta de subcima y cima, añade el resultado a la pila y llama a next de cpu
	 */
	protected boolean operates(int cima, int subcima, CPU cpu)throws StackException{
		int resultado;
		resultado = subcima - cima;
		cpu.next();
		return cpu.añadirElementos(resultado);
	}

	@Override
	/**
	 * parsea el string, si es SUB, crea el bytecode
	 */
	public ByteCode parseOperations(String s) {
		if (s.equalsIgnoreCase("SUB"))
			return new Sub();
			else return null;
	}
public String toString (){
	return "Sub ";
}
}