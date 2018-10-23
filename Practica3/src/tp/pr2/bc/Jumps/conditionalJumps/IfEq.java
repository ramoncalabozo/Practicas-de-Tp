package tp.pr2.bc.Jumps.conditionalJumps;




import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.elements.CPU;

public class IfEq extends ConditionalJump {

	/**
	 * llama a la constructora padre con el parametro position ,posicion hacia la que salta
	 * @param position
	 */
	public IfEq(int position) {
		super(position);
	}

	@Override
	/**
	 * si la subcima es igual a la cima, siguiente instruccion, si no hace el salto llamando a jump de cpu
	 */
	protected boolean compares(int c, int sc, CPU cpu) {
		if (sc == c)return cpu.next();
		else return cpu.jump(n);
	}
	
	/**
	 * parsea el string, si es ifeq entonces lo crea
	 */
	protected ByteCode parseJump(String s, int n) {
		if (s.equalsIgnoreCase("IFEQ")){
			return new IfEq(n);
		}
		else {
			return null;
		}
	}
public String toString(){
	String s;
	s = "ifEq " + this.n; // mirar por que la n siempre esta a 0
	return s;
}
}
