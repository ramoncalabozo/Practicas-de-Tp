package tp.pr1.mv;

public class ByteCodeProgram {
	private ByteCode[] program;// arrays de instrucciones
	private int contador;
	private final int SIZE;

	ByteCodeProgram() {
		this.contador = 0;// contador del array de instrucciones
		this.SIZE = 1000;// tamaño del array
		this.program = new ByteCode[SIZE];// array de instrucciones
	}

	public void inicializar() {
		this.contador = 0;
	}

	// inicializa el programa
	
	public boolean añadir(ByteCode instruction) {
		boolean añadir = false;
		if (this.contador < this.SIZE) {
			this.program[this.contador] = instruction;
			this.contador++;
			añadir = true;
		}
		return añadir;
	}
	/*
	 Si el contador del programa es menor que el tamaño del array
	 añado la instrucción al programa en la posición del contador e 
	 incremento el contador
	 */	
	
	public String toString() {
		String s;
		s = "";
		if (this.contador > 0){
		s = "Programa almacenado:"+System.lineSeparator();;
		for (int i = 0; i < this.contador; i++) {
			s = s + i + ": ";
			s = s + this.program[i].toString();
			s = s + System.lineSeparator();
		}
		}
		System.out.println(s);
		return s;
	}

	public ByteCode getinstruccion(int pos) {
		ByteCode instruccion;
		instruccion = this.program[pos];
		return instruccion;
	}

		/*
		 Devuelve la instrucción que se encuentra en la posición pos del array
		 */
	
	public boolean añadirenpos(int pos, ByteCode instruccion) {// Función que ha de usar el replace
		boolean añadirpos = false;
		if (pos < this.contador) {
			this.program[pos] = instruccion;
			añadirpos = true;
		}
		return añadirpos;
	}
	
	/*
	añade una instruccón al programa en la posición pos   
	*/
	
	public int getContador() {
		return this.contador;
	}
	// Devuelve el contador del program
}
