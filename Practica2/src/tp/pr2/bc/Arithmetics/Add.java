package tp.pr2.bc.Arithmetics;

import tp.pr2.bc.ByteCode;
import tp.pr2.cpu.CPU;

public class Add extends Arithmetics {

	@Override
	/**
	 * realiza la operacion de suma, llama a next y pone el resultado en la pila
	 */
	protected boolean operates(int cima, int subcima, CPU cpu) {
		int resultado = cima + subcima;
		cpu.next();
		return cpu.añadirElementos(resultado);
	}
	@Override
	/**
	 * parsea el string s, si es add crea el bytecode
	 */
	public ByteCode parseOperations(String s) {
		if (s.equalsIgnoreCase("ADD"))
			return new Add();
			else return null;
	}
	public String toString (){
	return "Add ";
}
}