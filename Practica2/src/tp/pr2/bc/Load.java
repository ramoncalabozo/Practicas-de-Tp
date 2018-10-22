package tp.pr2.bc;

import tp.pr2.cpu.CPU;

/**
 * Tiene comoa tributo la posicion de memoria
 * @author salva
 *
 */
public class Load extends ByteCode {
	private int parametre;
	/**
	 * Constructora
	 * @param parametre
	 */
	public Load(int parametre){
		this.parametre = parametre;
	}
	@Override
	/**
	 * coge el valor en la posicion de memoria, si no es null, la añade llama a cpu y devuelve true, si no, 
	 * devuelve false
	 */
	public boolean execute(CPU cpu) {
		Integer mem;
		boolean ok = false;
		
		mem = cpu.read(parametre);
		if(mem != null) {
			ok = true;
			cpu.añadirElementos(mem);
			cpu.next();
		}
		return ok;
	}

	@Override
	/**
	 * parsea el string, si tiene  longitud 2 y e sload, crea el bytecode
	 */
	public ByteCode parse(String[] s) {
		if (s.length != 2) {
			return null;
		} else {
			if (s[0].equalsIgnoreCase("LOAD")) {
				int numero = Integer.parseInt(s[1]);//Paso el string a int 
				return new Load(numero);
			}
			else{
				return null;
			}
		}
	}
public String toString (){
	String s;
	s = "Load " + parametre;
	return s;
}
}
