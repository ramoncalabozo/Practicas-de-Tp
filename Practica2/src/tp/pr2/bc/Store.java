package tp.pr2.bc;

import tp.pr2.cpu.CPU;
/**
 * Tiene como atributo la posicion de memoria
 * @author salva
 *
 */
public class Store extends ByteCode {
	private int parametre;

	/**
	 * Constructora
	 * @param parametre
	 */
	public Store(int parametre) {
		this.parametre = parametre;
	}

	@Override
	/**
	 * si no esta vacia la pila,escribe en la posicion de memoria el valor de la cima de la pila y llama  next de cpu
	 */
	public boolean execute(CPU cpu) {
		
		if(!cpu.estavacia()){
		int cima = cpu.getcima();
		cpu.next();
		return cpu.write(parametre, cima);
	}
		else return false;
	}

	@Override
	/**
	 * Parsea el string, si tiene longitud 2 y es STORE entonces, crea el bytecode
	 */
	public ByteCode parse(String[] s) {
		if (s.length != 2) {
			return null;
		} else {
			if (s[0].equalsIgnoreCase("STORE")) {
				int numero = Integer.parseInt(s[1]);// Paso el string a int
				return new Store(numero);
			} else {
				return null;
			}
		}
	}
public String toString (){
	String s;
	s = "Store " + parametre;
	return s;
}
}
