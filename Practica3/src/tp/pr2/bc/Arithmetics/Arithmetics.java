package tp.pr2.bc.Arithmetics;

import tp.pr2.bc.ByteCode;
import tp.pr2.elements.CPU;
import tp.pr2.exceptions.DivByZeroException;
import tp.pr2.exceptions.StackException;




abstract public class Arithmetics implements ByteCode {
	/**
	 * Saca la cima y la subcima(si se puede) y realiza la operacion pedida
	 * @return 
	 */
	public boolean execute (CPU cpu)throws StackException ,DivByZeroException{ // En la interfaz de Bytecode pone que es void pero por si acaso sólo lo comento
		boolean execute = true;
		int cima,
		subcima;
		if (cpu.estavacia()) {
			execute = false;
		} else {
			cima = cpu.getcima();
			if (cpu.estavacia()) {
				execute = false;// Si no se puede realizar la operacion se
								// mete en pila otravez el elmento que se
								// acaba de sacar
				cpu.añadirElementos(cima);
			//throw new StackException ("Error : pila vacía"); Lo he añadido yo pero no sé si está bien
			} else {
				subcima = cpu.getcima();	
				execute = this.operates (cima ,subcima ,cpu);
			}
		}	
		if (execute == false){
			throw new StackException ("Error : pila vacía");
		}	
	return execute;
	}
		/**
}	 * metodo abstracto de operaciones
	 * @param cima
	 * @param subcima
	 * @param cpu
	 * @return
	 */
	protected abstract boolean operates(int cima, int subcima ,CPU cpu)throws StackException,DivByZeroException;
	/**
	 * metodo abstracto de parseador
	 * @param s
	 * @return
	 */
	protected abstract ByteCode parseOperations(String s);
	
	/**
	 * parsea la parte comun de la operaciones, si tiene longitud n llama a parseOperations
	 *  que parsea la parte no comun
	 */
	public ByteCode parse (String[] s){
		if (s.length == 1)
			return parseOperations(s[0]);
			else return null;
	}

}
