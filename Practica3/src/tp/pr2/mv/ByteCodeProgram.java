package tp.pr2.mv;

import tp.pr2.bc.ByteCode;
import tp.pr2.exceptions.ArrayException;

// CREO QUE ESTÁ CLASE YA ESTÁ

/**
 * Tiene como atributos el array de instrucciones, el contador y el tamaño del array
 * @author salva
 *
 */
public class ByteCodeProgram {
	private ByteCode[] program;
	private int contador;
	private final int SIZE;
	
	/**
	 * Constructora de ByteCodeProgram
	 * Inicializa el contador a 0, crea el array de bytecodes de tamaño 1000
	 */
	public ByteCodeProgram() {
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
	public boolean añadirReplace(int pos, ByteCode instruccion)throws ArrayException{
		boolean añadirpos = false;
		if (pos <= this.contador && pos >=0) {
			this.program[pos] = instruccion;
			añadirpos = true;
			
		}
		else {
			throw new ArrayException("Posición en la que se intenta sustituir el nuevo bytecode no es válida");
			}
		return añadirpos;
		
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
	public ByteCode getinstruccion(int pos)throws ArrayException {
		ByteCode instruccion;
		instruccion = this.program[pos];
		return instruccion;
	}

	/**
	 * 	Añade en la posicion pos una nueva instruccion
	 * @param pos
	 * @param instruccion
	 * @return
	 */
	
	public boolean añadirenpos(int pos, ByteCode instruccion)throws ArrayException {
		boolean añadirpos = false;
		if ( this.contador < this.SIZE) {
			this.program[this.contador] = instruccion;
			añadirpos = true;
			this.contador++; 
		}
		else {
			throw new ArrayException("Posición en la que se intenta sustituir el nuevo bytecode no es válida");
		}
		return añadirpos;
	}
	
	
	/**
	 * devuelve el contador del programa
	 * @return
	 */
	
	public int getContador() {
		return this.contador;
	}
	
}
