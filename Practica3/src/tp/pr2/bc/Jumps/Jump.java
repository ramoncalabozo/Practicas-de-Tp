package tp.pr2.bc.Jumps;




import tp.pr2.bc.ByteCode;
/**
 * Clase abstracta que tiene como atributo la posicion donde va a saltar
 * @author salva
 *
 */
abstract public class Jump implements ByteCode {
	protected int n;
	/**
	 * Constructora de saltos con el parametro n de la posicion hacia la que salta
	 * @param n
	 */
	public Jump (int n){
		this.n = n;
	}
	/**
	 * metodo abstracto del parseador de saltos
	 * @param s
	 * @param n
	 * @return
	 */
	protected abstract ByteCode parseJump(String s, int n);
	
	/**
	 * Parsea lo comun de los saltos, si su longitud es 2 entonces el devuelve el Bytecode con la parte restante parseada
	 */
	public ByteCode parse(String[] s){
		if (s.length == 2){
			n = Integer.parseInt(s[1]);
			return parseJump(s[0],n);
		}
		else{
			return null;
		}
	}
}
