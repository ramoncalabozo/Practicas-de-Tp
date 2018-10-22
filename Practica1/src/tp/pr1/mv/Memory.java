package tp.pr1.mv;

public class Memory {
	private Integer[] memory;//array de Integers
	private int SIZE;//tamaño del array

	public Memory(int SIZE) {
		this.SIZE = SIZE;
		this.memory = new Integer[SIZE];
	}

	
	public boolean write(int pos, int value) {
		boolean escrito = false;
		if (pos >= 0 /*&& memory[pos] == null*/) {
			if (pos > this.SIZE) {
				 redimensionar(pos);
			}
			if(memory[pos] == null){
			escrito = true;
			memory[pos] = value;
			}
		}
		return escrito;
	}
	/*
	 si el contador es mayor que cero y no hay escrito nada en 
	 esa posción del array escribe en esa poscición el valor
	 si la posición que me dan es mayor redimensiono el array y 
	 escribo en esa posición
	 */
	
	
	public int read(int pos) {
		int valor = -1; 
		if (pos >= 0 && this.memory[pos]!=null) {
			valor = this.memory[pos];
		}
		return valor;
	}
	/*
	 si las posición es mayor que cero leo el valor 
	 que se encuentra en la posición del array y si el valor es null 
	 devuelvo -1
	 */
	
	
	void redimensionar(int pos) {
		Integer[] redimension =new Integer[SIZE+pos];
		for(int i = 0; i<SIZE; i++){
			redimension[i] = memory[i];
		}
		this.memory = redimension;
		this.SIZE = this.SIZE + pos;
	}
	// redimensiona el array
	
	
	
	boolean estavacia() {
		boolean vacia = true;
		int contador = 0;
		while (vacia == true && contador < this.SIZE) {
			if (memory[contador] != null) {
				vacia = false;
			}
		contador++;
		}
		return vacia;
	}
	/*
	 recorre el array para si hay escrita en alguna posición del array
	 */
	public  int  getSize(){
		return this.SIZE;
	}
	
	public String toString() {
		String s;
		int contador = 0;
		s = "Memoria : ";
		for (int i = 0; i < this.SIZE; i++) {
			if (this.memory[i] != null) {
				s = s + "[" + i + "]" + ":" + this.memory[i];
				contador++;
			}
		}
		if (contador == 0) {
			s = s + " < vacia > ";
		}
		s = s + System.lineSeparator();
		return s;
	}
}