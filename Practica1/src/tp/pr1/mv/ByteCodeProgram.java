package tp.pr1.mv;

public class ByteCodeProgram {
	private ByteCode[] program;// arrays de instrucciones
	private int contador;
	private final int SIZE;

	ByteCodeProgram() {
		this.contador = 0;// contador del array de instrucciones
		this.SIZE = 1000;// tama�o del array
		this.program = new ByteCode[SIZE];// array de instrucciones
	}

	public void inicializar() {
		this.contador = 0;
	}

	// inicializa el programa
	
	public boolean a�adir(ByteCode instruction) {
		boolean a�adir = false;
		if (this.contador < this.SIZE) {
			this.program[this.contador] = instruction;
			this.contador++;
			a�adir = true;
		}
		return a�adir;
	}
	/*
	 Si el contador del programa es menor que el tama�o del array
	 a�ado la instrucci�n al programa en la posici�n del contador e 
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
		 Devuelve la instrucci�n que se encuentra en la posici�n pos del array
		 */
	
	public boolean a�adirenpos(int pos, ByteCode instruccion) {// Funci�n que ha de usar el replace
		boolean a�adirpos = false;
		if (pos < this.contador) {
			this.program[pos] = instruccion;
			a�adirpos = true;
		}
		return a�adirpos;
	}
	
	/*
	a�ade una instrucc�n al programa en la posici�n pos   
	*/
	
	public int getContador() {
		return this.contador;
	}
	// Devuelve el contador del program
}
