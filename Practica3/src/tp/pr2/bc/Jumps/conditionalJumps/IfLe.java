package tp.pr2.bc.Jumps.conditionalJumps;




import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.elements.CPU;

public class IfLe extends ConditionalJump {

	/**
	 * llama a la constructora padre con el parametro position, posicion hacia la que salta
	 * @param position
	 */
	public IfLe(int position) {
		super(position);
	}

	@Override
	/**
	 * si la subcima es menor que la cima, siguiente instruccion, si no, hace el salto llamando a jump de cpu
	 */
	protected boolean compares(int c, int sc, CPU cpu) {
		if (sc < c)return cpu.next();
		else return cpu.jump(n);
	}

	@Override
	/**
	 * se parsea el string, si es ifle, lo crea
	 */
	protected ByteCode parseJump(String s, int n) {
		if (s.equalsIgnoreCase("IFLE")){
			return new IfLe(n);
		}
		else {
			return null;
		}
	}
	public String toString(){
		String s;
		s = "ifLe " + this.n; // mirar por que la n siempre esta a 0
		return s;
	}
}
