package tp.pr2.cpu;

import tp.pr2.bc.ByteCode;

/**
 * Tiene como atributos el array de instrucciones, el contador y el tama�o del array
 * @author salva
 *
 */
public class ByteCodeProgram {
	private ByteCode[] program;
	private int contador;
	private final int SIZE;
	
	/**
	 * Constructora de ByteCodeProgram
	 * Inicializa el contador a 0, crea el array de bytecodes de tama�o 1000
	 */
	ByteCodeProgram() {
		this.contador = 0;
		this.SIZE = 1000;
		this.program = new ByteCode[SIZE];
	}
	/**
	 * Inicializa el contador a 0
	 */
	public void inicializar() {
		this.contador = 0;
	}
	
	/**
	 * Sustituye en la posicion pos, por la nueva instruccion
	 * @param pos
	 * @param instruccion
	 * @return
	 */
	public boolean a�adirReplace(int pos, ByteCode instruccion){
		boolean a�adirpos = false;
		if (pos <= this.contador && pos >=0) {
			this.program[pos] = instruccion;
			a�adirpos = true;
			
		}
		return a�adirpos;
		
	}
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
		
		return s;
	}

	/**
	 * Devuelve la instruccion del programa de la posicion pos
	 * @param pos
	 * @return
	 */
	public ByteCode getinstruccion(int pos) {
		ByteCode instruccion;
		instruccion = this.program[pos];
		return instruccion;
	}

	/**
	 * 	A�ade en la posicion pos una nueva instruccion
	 * @param pos
	 * @param instruccion
	 * @return
	 */
	public boolean a�adirenpos(int pos, ByteCode instruccion) {
		boolean a�adirpos = false;
		if (pos <= this.contador) {
			this.program[pos] = instruccion;
			a�adirpos = true;
			this.contador++; 
		}
		return a�adirpos;
	}
	
	
	/**
	 * devuelve el contador del programa
	 * @return
	 */
	public int getContador() {
		return this.contador;
	}
	
}
