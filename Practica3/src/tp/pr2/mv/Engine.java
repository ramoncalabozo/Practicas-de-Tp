
package tp.pr2.mv;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.ByteCodeParser;
import tp.pr2.cm.Command;
import tp.pr2.cm.CommandParser;
import tp.pr2.elements.CPU;
import tp.pr2.elements.LexicalParser;
import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.BadFormatByteCodeException;
import tp.pr2.exceptions.ExecutionErrorException;
import tp.pr2.exceptions.LexicalAnalysisException;
import tp.pr2.exceptions.StackException;
import tp.pr2.elements.Compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 


/**
 * Tiene como atributos la cpu, el booleano end y el escaner
 * @author salva
 *
 */
public class Engine {
	private SourceProgram sProgram; // Donde se almacena el programa fuente
	private ParsedProgram parsedProgram;// Donde se almacena el programa parseado
	private ByteCodeProgram bytecodeProgram;// Donde se almacena el programa de bytecodes
	private LexicalParser Lexparser; // Analizador lexico
	private Compiler compilador; // Compilador
	private boolean end; // indica el final de la máquina virtual
	private CPU cpu; // Cpu de la máquina virtual
	private java.util.Scanner sc = new java.util.Scanner(System.in);// Permite leer de teclado
	
	/**
	 * Constructora Engine() // Hay que cambiarla ya que está clase ya no tiene le atributo cpu
	 * Crea la CPU y pone el booleano end a false
	 */
	public Engine() {
		this.sProgram = new SourceProgram();
		this.parsedProgram = new ParsedProgram();
		this.bytecodeProgram = new ByteCodeProgram();
		this.Lexparser = new LexicalParser(this.sProgram);
		this.compilador = new Compiler(this.bytecodeProgram);
		this.cpu = new CPU(this.bytecodeProgram);
		this.end = false;
	}
	
	/**
	 * Comienzo de la maquina virtual
	 */
	public void start() {
		this.cpu.inicializa();
		String comando;
		Command comandos;
		do {
			System.out.print("> ");
			comando = sc.nextLine();
			comando = comando.trim();
			comandos = CommandParser.parse(comando);
			if (comandos != null) {
				try{
					
					System.out.println(toStringAux(comando));
					comandos.execute(this);
				
				} 
				catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					System.out.println(ex.getMessage());
					//System.out.println(""+ex.toString());
				}
				catch (ArrayException ex) {
					// TODO Auto-generated catch block
					System.out.println(ex.getMessage());
					System.out.println(""+ex.toString());
				}
				catch (BadFormatByteCodeException ex) {
					// TODO Auto-generated catch block
					System.out.println(ex.getMessage());
					System.out.println(""+ex.toString());
				}
				catch (LexicalAnalysisException ex) {
					// TODO Auto-generated catch block
					System.out.println(ex.getMessage());
					System.out.println(""+ex.toString());
				}
				catch (ExecutionErrorException ex) {
					// TODO Auto-generated catch block
					System.out.println(ex.getMessage());
					System.out.println(""+ex.toString());
				}
				catch (NumberFormatException ex){
					System.out.println(ex.getMessage());
					
				}
			} else {
				System.out.println("Error : Comando incorrecto \n");
			}
			
		} while (!this.end);
		
		System.out.println("Fin de la ejecucion....");
	}


	/**
	 * Muestra lo que hace cada comando llamando a las funciones help de cada comando
	 * @return true
	 * @throws FileNotFoundException,ArrayException
	 */
	public void load (String fichName) throws java.io.FileNotFoundException,ArrayException{
		this.sProgram.reset();
		this.cpu.InicializarPrograma();
		this.cpu.inicializa();
		String leer;
		try{
		Scanner sc = new Scanner(new File(fichName));/** Metodo que permite leer los archivos **/
		if (!sc.hasNextLine()){
		sc.close();
			return;
			}
		leer = sc.nextLine().trim();
		this.sProgram.addInsr(leer);
		while (sc.hasNextLine()){
			leer = sc.nextLine().trim();
			this.sProgram.addInsr(leer);
		}	
		sc.close();
		System.out.println(this.sProgram.toString());
			}catch (ArrayException ex){
				System.out.println (ex.getMessage());
				sc.close();
			}	
	}
		
	public boolean executeHelp() {
		CommandParser.showHelp(); 
		return true;
	}
	
	public boolean executeCompile()throws LexicalAnalysisException,ArrayException{
			this.lexicalAnalysis();
			this.generateByteCode();
			System.out.println(this.bytecodeProgram.toString());
			return true;
	}
	private void generateByteCode() throws LexicalAnalysisException,ArrayException {
		
		this.bytecodeProgram.inicializar();
		Compiler compiler = new Compiler(this.bytecodeProgram);
		compiler.compile(this.parsedProgram);
	}

	private void lexicalAnalysis() throws LexicalAnalysisException,ArrayException { 
		this.parsedProgram.reset();
		LexicalParser lexicalParser = new LexicalParser (this.sProgram);
		lexicalParser.lexicalParser(this.parsedProgram, "end");
	}

	/**
	 * Pone el booleano end = true 
	 * @return true
	 */
	public boolean executeQuit() {
		this.end = true;						
		return true;
	}
	
	/**
	 * Inicializa el programa y la cpu
	 * @return true
	 */
	
	public boolean executeReset() { 
		this.cpu.reset();
		return true;
	}

	/**
	 * Reemplaza un bytecode del programa por uno nuevo en la posicion replace
	 * solo si replace es menor que el contador de CPU y se cumplen las condiciones
	 * @param replace
	 * @return
	 * @throws StackException 
	 */
	public void executeReplace(int replace)throws BadFormatByteCodeException, ArrayException{
		if (replace < this.cpu.getcontador() && replace >= 0) {
			System.out.print("Nueva instrucción: ");
			String instruccion = sc.nextLine();
			ByteCode instrucciones = ByteCodeParser.parse(instruccion);
			if (instrucciones != null) {
				this.bytecodeProgram.añadirReplace(replace, instrucciones);
				System.out.println(this.cpu.gettoString());
			} else {
				throw new BadFormatByteCodeException("Bytecode no válido");
			}
		} else {
			throw new ArrayException("Posición en la que se intenta sustituir el nuevo bytecode no es válida");
		}
	}

	/**
	 * Inicializa pila y memoria y llama al metodo run de cpu e inicializa halt una vez terminada la ejecucion a false
	 * @return ok
	 */
	public boolean executeRun()throws ExecutionErrorException,ArrayException {
		boolean ok;
		cpu.inicializa();
		ok = this.cpu.run();
		this.cpu.inicializarHalt();
		return ok;
		
	}
	/**
	 * Lee las instrucciones hatsa que el usuario escriba la palabra end
	 * @return
	 */

	private String toStringAux(String comando){
		String s;
		if(comando.toUpperCase().equalsIgnoreCase("RUN")){
			s = "Comienza la ejecucion de " + comando + System.lineSeparator();
			s = s + "El estado de la maquina tras ejecutar programa es:" + System.lineSeparator();
			return s;
		}
		else{
		s = "Comienza la ejecucion de " + comando + System.lineSeparator();
		return s;
		}
	}
}
