package tp.pr2.elements;


import tp.pr2.bc.ByteCode;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.ExecutionErrorException;
import tp.pr2.exceptions.StackException;
import tp.pr2.mv.ByteCodeProgram;
/**
 * Tiene como atributos la memoria, la pila, el contador de la siguiente instruccion, el boleano de halt y el programa
 * @author salva
 *
 */

public class CPU {
	private Memory memoria;
	private OperandStack pila;
	private boolean halt;
	private int programCounter = 0; 
	private ByteCodeProgram bcProgram;
	
	/**
	 * Constructora de CPU
	 * Crea la memoria, la pila y pone el boleano halt a false
	 */
	 public CPU(ByteCodeProgram program) {
		this.halt = false;
		this.memoria = new Memory(1000);
		this.pila = new OperandStack();
		this.bcProgram = program;
	 }
	
	 /**
	  * Añade el elemento elementoa la pila
	  * @param elemento
	  * @return
	  */
	public boolean añadirElementos (int elemento)throws StackException{
	
		return this.pila.añadirElementos(elemento);
	}
	
	/**
	 * Ejecuta los bytecodes almacenados en el programa, si hay algun error de ejecucion,
	 * halt es true o se acaban las instrucciones se termina
	 * @return
	 */
	public boolean run() throws ArrayException,ExecutionErrorException{ 
		this.programCounter = 0;
		boolean error = false;
		try{
			while (this.programCounter < bcProgram.getContador() && !error && this.halt == false) { 
				ByteCode bc = bcProgram.getinstruccion(this.programCounter);
				
				bc.execute(this);
			}
			System.out.println(this.toString());
			System.out.println(this.bcProgram.toString());
			return error; 
			
		}
		catch(ExecutionErrorException ex){
			throw new ExecutionErrorException(ex.getMessage()+ ", linea " + this.programCounter);
		}
	}
	
	
	public String toString() {
		String s;
		s = "Estado de la CPU " + System.lineSeparator();
		s = s + this.memoria.toString();
		s = s + this.pila.toString();
		return s;
	}
	/**
	 * Inicializa el programa y el contador de siguiente instruccion
	 */
	public void reset(){
		this.bcProgram.inicializar();
		this.programCounter = 0;
	}
	/**
	 * Inicializa el programa
	 */
	public void InicializarPrograma(){ 
		this.bcProgram.inicializar();
	}
	
	/**
	 * pregunta si la pila esta vacia 
	 * @return
	 */
	public boolean estavacia (){
		return this.pila.estavaciapila();
	}
	public boolean stackOverflow(){
		return this.pila.stackOverflow();
	}


	/**
	 * Inicializa la pila y la memoria
	 */
	public void inicializa (){ 
		this.pila.Inicializa();
		this.memoria.inicializa();
	} 


	/**
	 * Llama al escribir de memoria para escribir el valor value en la posicion pos
	 * @param pos
	 * @param value
	 * @return
	 */
	public boolean write (int pos , int value) {
		return this.memoria.write(pos,value);
	}


	/**
	 * Llama al leer de memoria para coger el valor que hay en la posicion pos de memoria
	 * @param pos
	 * @return
	 */
	public Integer read(int pos){
		return this.memoria.read(pos);
	}


	/**
	 * Llama al añadirpos del programa para añadir una instruccion en la posicion pos
	 * @param pos
	 * @param instruccion
	 * @return
	 */
	public boolean añadirenpos (int pos,ByteCode instruccion)throws ArrayException{
		return this.bcProgram.añadirenpos(pos,instruccion);
	}
	
	/**
	 * Llama al añadirReplace del programa para sustituir una instruccion en la posicion pos
	 * @param pos
	 * @param instruccion
	 * @return
	 */
	public boolean añadirReplace(int pos,ByteCode instruccion) throws ArrayException{
		return this.bcProgram.añadirReplace(pos, instruccion);
	}


	/**
	 * Si no esta vacia coge, el numero que hay en la cima de la pila y lo muestra como caracter
	 * @return
	 */
	public boolean out()throws StackException{
		//if(this.estavacia()){
		//	throw new StackException("No hay elementos en la pila");
	//	}
	//	else {//char c = (char) this.getcima();
		System.out.println( "consola: " + this.getcima());
		return true ;
	//	}
	}
	/**
	 * Pone el contador de la siguiente instruccion al valor n
	 * @param n
	 * @return
	 */
	public boolean jump(int n){
		this.programCounter = n;
		return true;
	}
	/**
	 * Aumenta en 1 el contador de la siguiente instruccion
	 * @return
	 */
	public boolean next (){
		boolean next = true;
		this.programCounter++; 
		return next;
	}
	/**
	 * Llama al getcima de pila para obtener la cima de la pila
	 * @return
	 */
	public int getcima()throws StackException{
	//	if(this.estavacia()){
	//		throw new StackException("No hay elementos en la pila");
	//	}
	//	else
			return this.pila.getcima(); 
	}
	/**
	 * Pone el booleano halt a true
	 * @return
	 */
	public boolean finish (){
		this.halt = true;
		return true;
	}
	
	/**
	 * Pone el booleano halt a false
	 */
	public void inicializarHalt(){
		this.halt = false;
	}

		/**
		 * Devuelve el contador del programa y lo devuelve
		 * @return
		 */
	public int getcontador(){
		return bcProgram.getContador();
	}
	/**
	 * Devuelve el contador de la siguiente instruccion
	 * @return
	 */
	public int getProgramCounter(){
		return this.programCounter;
	}
	/**
	 * Devuelve el toString del programa
	 * @return
	 */
	public String gettoString(){
		return this.bcProgram.toString();
	}
}
