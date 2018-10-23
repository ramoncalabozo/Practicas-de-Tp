package tp.pr2.bc.Jumps;




import tp.pr2.bc.ByteCode;
import tp.pr2.elements.CPU;

public class Goto extends Jump{

	/**
	 * llama a la constructora padre con el parametro n, posicion hacia la que salta
	 * @param n
	 */
	public Goto(int n) {
		 super(n);
	}

	@Override
	/**
	 * Parsea el string, si es goto entonces lo crea
	 */
	protected ByteCode parseJump(String s, int n) {
		if (s.equalsIgnoreCase("GOTO")){
		return new Goto(n);
		}
		else {	
		return null;
		}
	}

	@Override
	/**
	 * devuelve true, con el salto realizado y manda al contador de la siguiente instruccion de cpu que aumente
	 */
	public boolean execute(CPU cpu) {
		cpu.next();
		return cpu.jump(n);
	}
	public String toString(){
		String s;
		s = "Goto " + n; // mirar por que la n siempre esta a 0
		return s;
	}
}
