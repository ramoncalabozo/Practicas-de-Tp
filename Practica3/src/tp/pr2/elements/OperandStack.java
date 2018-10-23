package tp.pr2.elements;

import tp.pr2.exceptions.StackException;



/**
 * Tiene tres atributos, el array de enteros, el contador y el tama�o del array
 * @author salva
 *
 */
public class OperandStack {
	private int[] stack;//array de int
	private int contador;//contador del array
	private final int SIZESTACK = 1000;//tama�o del array
	
	/**
	 * Constructora de OperandStack
	 * Inicializa el contador a 0 y crea el array de enteros
	 */
	OperandStack() {
		this.contador = 0;
		this.stack = new int[SIZESTACK];
	}

	/**
	 * A�ade el elemento elementos a la pila
	 * @param elementos
	 * @return 
	 */
	public  boolean a�adirElementos(int elementos) throws StackException{
		boolean a�adir = false;
		if  (!stackOverflow()){
			this.stack[this.contador] = elementos;
			this.contador++;
			a�adir = true;
		}
		else throw new StackException("Pila llena de elementos");
		return a�adir;
	}
	/*
	 si el contador es menor que el tama�o 
	 mete en el array en la posicion del contador el elmento e incrementa el contador
	 */
	/**
	 * Coge el elemento de la cima de la pila y lo borra de �sta
	 * @return
	 */
	public int getcima() throws StackException {
		int sacar;
		if(!estavaciapila()){
		sacar = stack[this.contador -1];
		this.contador--;
		return sacar;
		}
		else {
			throw new StackException("No hay elementos en la pila");
			}
		
	}
	/*
	 saca el elemnto que es m�s reciente en la pila y decrementa el contador en 1
	 */
	/**
	 * Si la pila esta vacia devuelve true, false en caso contrario
	 * @return
	 */
	public boolean estavaciapila() {
		boolean vacia = true;
		if (this.contador > 0) {
			vacia = false;
		}
		return vacia;
	}
	public boolean stackOverflow(){
		if (contador < this.SIZESTACK){
			return false;
		}
		else return true;
	}
	/*
	 comprueba si la pila est� vac�a
	 */
	/**
	 * pone el contador a 0
	 */
	public void Inicializa(){ 
		this.contador = 0;
	}
	
	public String toString() {
		String s;
		s = " Pila : ";
		if (this.contador == 0) {
			s = s + " < vacia > ";
		}
		for (int i = 0; i < this.contador; i++) {
			s = s + this.stack[i] + " ";
		}
		s = s + System.lineSeparator();
		return s;
	}
}