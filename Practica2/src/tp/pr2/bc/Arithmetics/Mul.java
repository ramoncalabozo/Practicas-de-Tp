package tp.pr2.bc.Arithmetics;

import tp.pr2.bc.ByteCode;
import tp.pr2.cpu.CPU;

public class Mul extends Arithmetics {

	@Override
	/**
	 * realiza la operacion de multiplicar la cima y la subcima, lo añade el resultado a la pila y llama a next de cpu
	 */
	protected boolean operates(int cima, int subcima, CPU cpu) {
	int resultado;
	resultado = subcima * cima;
	cpu.next();
	return cpu.añadirElementos(resultado);
	}

	@Override
	/**
	 * parsea el string, si es MUL crea el bytecode
	 */
	public ByteCode parseOperations(String s) {
		if (s.equalsIgnoreCase("MUL"))
			return new Mul();
			else return null;
	}
public String toString(){
	return "Mul ";
}
}
