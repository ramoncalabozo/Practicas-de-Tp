package tp.pr2.bc.Jumps.conditionalJumps;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.Jumps.ConditionalJump;
import tp.pr2.cpu.CPU;

public class IfLeq extends ConditionalJump {

	/**
	 * llama a la constructora padre con el parametro position, posicion hacia la que salta
	 * @param position
	 */
	public IfLeq(int position) {
		super(position);
	}

	@Override
	/**
	 * si la subcima es menor o igual que la cima, siguiente instruccion, si no, hace el salto llamando ajump de cpu
	 */
	protected boolean compares(int c, int sc, CPU cpu) {
		if (sc <= c)return cpu.next();
		else return cpu.jump(n);
	}

	@Override
	/**
	 * Parsea el string s, si es ifleq, lo crea
	 */
	protected ByteCode parseJump(String s, int n) {
		if (s.equalsIgnoreCase("IFLEQ")){
			return new IfLeq(n);
		}
		else {
			return null;
		}
	}
	public String toString(){
		String s;
		s = "ifLeq " + this.n; // mirar por que la n siempre esta a 0
		return s; 
	}
}
