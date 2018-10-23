package tp.pr2.bc.Jumps;

import tp.pr2.elements.CPU;
import tp.pr2.exceptions.StackException;

public abstract class ConditionalJump extends Jump {
	
	/**
	 * llama a la constructora padre con la posicion position, posicion hacia la que salta
	 * @param position
	 */
	public ConditionalJump (int position){ 
		super(position);
	}
	/**
	 * metodo abstracto
	 * @param c
	 * @param sc
	 * @param cpu
	 * @return
	 */
	protected abstract boolean compares(int c,int sc,CPU cpu);
	
	/**
	 * Saca la cima y la subcima de la pila,(si se puede) y llama al compares de los saltos para ejecutarlos
	 * si se ha realizado todo correctamente devuelve true
	 */
	public boolean execute(CPU cpu)throws StackException{// En el doc pone que tiene que ser void
		boolean execute = true;
		int cima,
		subcima;
		if (cpu.estavacia()) {
			execute = false;
		} else {
			cima = cpu.getcima();
			if (cpu.estavacia()) {
				execute = false;
								
				cpu.añadirElementos(cima);
			} else {
				subcima = cpu.getcima();	
				execute = this.compares (cima ,subcima ,cpu);
			}
		}	
		return execute;
	}
public	void setN (int n){
		this.n = n;
	}
}
