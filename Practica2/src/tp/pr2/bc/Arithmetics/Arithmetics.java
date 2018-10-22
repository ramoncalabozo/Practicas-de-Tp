package tp.pr2.bc.Arithmetics;

import tp.pr2.bc.ByteCode;
import tp.pr2.cpu.CPU;

abstract public class Arithmetics extends ByteCode {
	/**
	 * Saca la cima y la subcima(si se puede) y realiza la operacion pedida
	 */
	public boolean execute (CPU cpu){
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
			} else {
				subcima = cpu.getcima();	
				execute = this.operates (cima ,subcima ,cpu);
			}
		}	
		return execute;
}	
	/**
	 * metodo abstracto de operaciones
	 * @param cima
	 * @param subcima
	 * @param cpu
	 * @return
	 */
	protected abstract boolean operates(int cima, int subcima ,CPU cpu);
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
