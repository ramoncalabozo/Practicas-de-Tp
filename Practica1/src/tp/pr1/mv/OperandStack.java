package tp.pr1.mv;

public class OperandStack {
	private int[] stack;//array de int
	private int contador;//contador del array
	private final int SIZESTACK = 1000;//tamaño del array

	OperandStack() {
		this.contador = 0;
		this.stack = new int[SIZESTACK];
	}

	public  boolean añadirElementos(int elementos) {
		boolean añadir = false;
		if (contador < this.SIZESTACK) {
			this.stack[this.contador] = elementos;
			this.contador++;
			añadir = true;
		}
		return añadir;
	}
	/*
	 si el contador es menor que el tamaño 
	 mete en el array en la posicion del contador el elmento e incrementa el contador
	 */
	public int getcima() {
		int sacar;
		sacar = stack[this.contador -1];
		this.contador--;
		return sacar;
	}
	/*
	 saca el elemnto que es más reciente en la pila y decrementa el contador en 1
	 */
	public boolean estavaciapila() {
		boolean vacia = true;
		if (this.contador > 0) {
			vacia = false;
		}
		return vacia;
	}
	/*
	 comprueba si la pila está vacía
	 */
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