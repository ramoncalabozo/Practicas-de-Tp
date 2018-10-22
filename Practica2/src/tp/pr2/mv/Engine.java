
package tp.pr2.mv;

import tp.pr2.bc.ByteCode;
import tp.pr2.bc.ByteCodeParser;
import tp.pr2.cm.Command;
import tp.pr2.cm.CommandParser;
import tp.pr2.cpu.CPU;

/**
 * Tiene como atributos la cpu, el booleano end y el escaner
 * @author salva
 *
 */
public class Engine {
	private CPU cpu; 
	private boolean end;
	private java.util.Scanner sc = new java.util.Scanner(System.in);
	
	/**
	 * Constructora Engine()
	 * Crea la CPU y pone el booleano end a false
	 */
	public Engine() {
		this.cpu = new CPU();
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
				
				if (comando.toUpperCase().equalsIgnoreCase("BYTECODE")) {
				
					this.cpu.InicializarPrograma();
					this.cpu.inicializa();
					System.out.println(toStringAux(comando));
					if (comandos.execute(this)) {
					
						System.out.println(this.cpu.gettoString());
					}
				} else if (comando.toUpperCase().equalsIgnoreCase("HELP")) {
				
					System.out.println(toStringAux(comando));
					comandos.execute(this);
				} else if (comando.toUpperCase().equalsIgnoreCase("RESET")) {
				
					System.out.println(toStringAux(comando));
					comandos.execute(this);
				} else if (comando.toUpperCase().equalsIgnoreCase("REPLACE")) {
				
					System.out.println(toStringAux(comando));
					if (!comandos.execute(this)) {
						System.out.println("Error : Comando incorrecto \n");
					}
					System.out.println(this.cpu.gettoString());
				} else {
					
					System.out.println(toStringAux(comando));
					comandos.execute(this);
					
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
	 */
	public boolean executeHelp() {
		CommandParser.showHelp(); 
		return true;

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
	 */
	public boolean executeReplace(int replace) {
		boolean executereplace = false;
		if (replace < this.cpu.getcontador()) {
			System.out.print("Nueva instrucción: ");
			String instruccion = sc.nextLine();
			ByteCode instrucciones = ByteCodeParser.parse(instruccion);
			if (instrucciones != null) {
				executereplace = this.cpu.añadirReplace(replace, instrucciones);
				System.out.println(this.cpu.gettoString());
			} else {
				executereplace = false;
			}
		} else {
			executereplace = false;
		}
		return executereplace;
	}

	/**
	 * Inicializa pila y memoria y llama al metodo run de cpu e inicializa halt una vez terminada la ejecucion a false
	 * @return ok
	 */
	public boolean executeRun() {
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
	public boolean readByteCodeProgram() {// Lee instrucciones hasta que se introduce la palabra end
		boolean read = true;
		String line;
		ByteCode instruccion;
		System.out.println("Introduce el bytecode. Una instruccion por línea: \n");
		do {
			line = sc.nextLine();
			instruccion = ByteCodeParser.parse(line);
			if (instruccion != null){
			
				this.cpu.añadirenpos(cpu.getcontador(),instruccion);
			}
			else {
				if (!line.toUpperCase().equalsIgnoreCase("END")){
				System.out.println("Error : Instruccion incorrecta \n");
				}
			}
		} while (!line.toUpperCase().equalsIgnoreCase("END"));
		return read;
	}
	
	
	private String toStringAux(String comando){
		String s;
		if(comando.toUpperCase().equalsIgnoreCase("RUN")){
			s = "Comienza la ejecucion de " +comando + System.lineSeparator();
			s = s + "El estado de la maquina tras ejecutar programa es:" + System.lineSeparator();
			return s;
		}
		else{
		s = "Comienza la ejecucion de " +comando + System.lineSeparator();
		return s;
		}
	}
	
}
