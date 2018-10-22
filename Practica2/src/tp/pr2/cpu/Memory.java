package tp.pr2.cpu;
/**
 * Tiene 2 atributos, el array de integers y el tamaño del array
 * @author salva
 *
 */
public class Memory {
	private Integer[] memory;
	private int SIZE;
	
	/**
	 * Constructora de Memory
	 * Crea el array de memoria de tamaño SIZE
	 * @param SIZE
	 */
	public Memory(int SIZE) {
		this.SIZE = SIZE;
		this.memory = new Integer[SIZE];
	}

	/**
	 * Inicializa toda la memoria a null
	 */
	public void inicializa(){
		
		for(int i = 0; i < SIZE; i++){
			memory[i] = null;
		}
	}
	/**
	 * Escribe en la posicion pos de memoria, el valor value
	 * @param pos
	 * @param value
	 * @return
	 */
	public boolean write(int pos, int value) {
		boolean escrito = false;
		if (pos >= 0) {
			if (pos > this.SIZE) {
				 redimensionar(pos);
			}
			escrito = true;
			memory[pos] = value;
		
		}
		return escrito;
	}
	/**
	 * Lee en la posicion pos de memorya y devuelve su valor
	 * @param pos
	 * @return
	 */
	public Integer read(int pos) {
		Integer valor = 0; 
		if (pos >= 0) {
			valor = this.memory[pos];
		}
		return valor;
	}
	
	/**
	 * Redimensiona el tamaño de la memoria a SIZE+pos
	 * @param pos
	 */
	void redimensionar(int pos) {
		Integer[] redimension =new Integer[SIZE+pos];
		for(int i = 0; i<SIZE; i++){
			redimension[i] = memory[i];
		}
		this.memory = redimension;
		this.SIZE = this.SIZE + pos;
	}
	
	
	/**
	 * Recorre el array, si hay alguna posicion distinto de null devuelve false, true en caso contrario
	 * @return
	 */
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
	
	/**
	 * Coge el valor del tamaño de array y lo devuelve
	 * @return
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
				s = s + "[" + i + "]" + ":" + this.memory[i] + " ";
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