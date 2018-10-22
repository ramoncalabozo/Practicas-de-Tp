package tp.pr2.bc.Arithmetics;

import tp.pr2.bc.ByteCode;
import tp.pr2.cpu.CPU;

public class Div extends Arithmetics {

	@Override
	/**
	 * Realiza la operacion de division,si la cima es cero, no se opera, en caso contrario, opera
	 * añade el resultado a la pila y llama a next de cpu
	 */
	protected boolean operates(int cima, int subcima, CPU cpu) {
		int resultado;
		if (cima == 0){
		return false;
		}
		else {
			resultado = subcima / cima;
			cpu.next();
			return cpu.añadirElementos(resultado);
		}
	}
	
	public String toString (){
	return "Div ";
}
	@Override
	/**
	 * parsea el string s, si es DIV  crea el bytecode
	 */
	protected ByteCode parseOperations(String s) {
	if (s.equalsIgnoreCase("DIV"))
		return new Div();
		else return null;
}
}
