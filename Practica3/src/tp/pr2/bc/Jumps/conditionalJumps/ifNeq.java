package tp.pr2.bc.Jumps.conditionalJumps;




import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.elements.CPU;

public class ifNeq extends ConditionalJump {

	/**
	 * llama a la constructora padre con el parametro posicion, posicion hacia la que salta
	 * @param position
	 */
	public ifNeq(int position) {
		super(position);
	}

	@Override
	/**
	 * Si la subcima es distinto de la cima, siguiente instruccion, si no, hace el salto llamando a jump de CPU
	 */
	protected boolean compares(int c, int sc, CPU cpu) {
		if (sc != c)return cpu.next();
		else return cpu.jump(n);
	}

	@Override
	/**
	 * parsea el String, si es ifneq entonces lo crea
	 */
	protected ByteCode parseJump(String s, int n) {
		if (s.equalsIgnoreCase("IFNEQ")){
			return new ifNeq(n);
		}
		else {
			return null;
		}
	}
	public String toString(){
		String s;
		s = "ifNeq " + this.n; // mirar por que la n siempre esta a 0
		return s;
	}
}
