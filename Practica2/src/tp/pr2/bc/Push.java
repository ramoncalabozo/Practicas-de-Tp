package tp.pr2.bc;

import tp.pr2.cpu.CPU;
/**
 * Tiene como Atributo el numero que introduces en la pila
 * @author salva
 *
 */
public class Push extends ByteCode {

	private int parametre;
	
	/**
	 * constructora
	 * @param parametre
	 */
	public Push(int parametre) {
		this.parametre = parametre;
	}

	@Override
	/**
	 * añade un elemento en la pila, y llama a next de cpu
	 */
	public boolean execute(CPU cpu) {
		cpu.next();
		return cpu.añadirElementos(this.parametre);
	}

	@Override
	/**
	 * parsea el string, si tiene longitud 2 y es push crea el bytecode
	 */
	public ByteCode parse(String[] s) {
		if (s.length != 2) {
			return null;
		} else {
			if (s[0].equalsIgnoreCase("PUSH")) {
				int numero = Integer.parseInt(s[1]);
				return new Push(numero);
			}
			else{
				return null;
			}
		}
	}
public String toString (){
	String s;
	s = "Push " + this.parametre;
	return s;
}
}
