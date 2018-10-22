//IMPORTANTE FALTA CAMBIAR LOS ERRORES
//HAY DOS TIPOS
//A) Error: Ejecucion incorrecta del comando
//b) Error: Comando incorrecto

package tp.pr1.mv;

public class Engine {
	private ByteCodeProgram program;// representa el programa actual
	private boolean end;// terminacion de la aplicacion
	private java.util.Scanner sc = new java.util.Scanner(System.in);//para leer de teclado

	public Engine() {
		this.program = new ByteCodeProgram();
		this.end = false;
	}

	
	public void start() {
		this.program.inicializar();
		String comando;
		Command comandos;
		ENUM_COMMANDO comparar;
		do {
			System.out.print("> ");
			comando = sc.nextLine();
			comando.trim();
			comandos = CommandParser.parse(comando);
			if (comandos != null) {
				comparar = comandos.getcomando();
				if (comparar.equals(ENUM_COMMANDO.NEWINST)) {
					System.out.println(comandos.toString());
					//comandos.execute(this);
					if (!comandos.execute(this)) {
						System.out.println("Error : Ejecución incorrecta del comando \n");
					}
					//System.out.println(comandos.toString());
				} else if (comparar.equals(ENUM_COMMANDO.HELP)) {
					System.out.println(comandos.toString());
					comandos.execute(this);
				} else if (comparar.equals(ENUM_COMMANDO.RESET)) {
					System.out.println(comandos.toString());
					comandos.execute(this);
				} else if (comparar.equals(ENUM_COMMANDO.REPLACE)) {
					System.out.println(comandos.toString());
					if (!comandos.execute(this)) {
						System.out.println("Error : Ejecución incorrecta del comando \n");
					}
				} else {
					System.out.println(comandos.toString());
					comandos.execute(this);
				}
			} else {
				System.out.println("Error : Comando incorrecto \n");
			}
			this.program.toString();
		} while (!end);
		/*
		 * Pide que se le introduzca un comando se parsea y si es correcto 
		 * se ejecuta dicho comando
		 *  Después se lleva a cabo la ejecución del comando,invocando el
		 * método public boolean execute(Engine engine)de la clase Command.Si se
		 * produce un error en la ejecucución del comando debe maostrarse un
		 * mensaje de ERROR ()
		 */
		System.out.println("Fin de la ejecucion....");//cuando se ejecuta el comando quit
	}

	
	
	public boolean executeHelp() {
		String s = "HELP: Muestra esta ayuda" + System.lineSeparator() + "QUIT: Cierra la aplicacion"
				+ System.lineSeparator() + "RUN: Ejecuta el programa" + System.lineSeparator()
				+ "NEWINST BYTECODE: Introduce una nueva instrucción al programa" + System.lineSeparator()
				+ "RESET: Vacia el programa actual" + System.lineSeparator()
				+ "REPLACE N: Reemplaza la instruccion N por la solicitada al usuario" + System.lineSeparator();
		System.out.println(s);
		return true;

	}

	
	public boolean executeQuit() { // si quit es true, entonces sale del bucle y
									// se apaga el programa
		this.end = true;
		return true;
	}

	public boolean executeNewinst(ByteCode instruction) {
		if (instruction.getname() == null){
			return false;
		}
		else{
		return program.añadir(instruction);
		}
	}
	/*
	 se añade una instrucción al programa
	 */
	
	
	public boolean executeReset() { // Pone a 0 el contador del programa
		this.program.inicializar();
		return true;
	}

	
	
	public boolean executeReplace(int replace) {
		boolean executereplace = false;
		if (replace < this.program.getContador()) {
			System.out.print("Nueva instrucción: ");
			String instruccion = sc.nextLine();
			ByteCode instrucciones = ByteCodeParser.parse(instruccion);
			if (instrucciones != null) {
				executereplace = this.program.añadirenpos(replace, instrucciones);
			} else {
				executereplace = false;
			}
		} else {
			executereplace = false;
		}
		return executereplace;
	}
	/*
	 Si el valor que te llega es < que el contador del prgrama pide una nueva instruccion
	 se parsea y si es correcta se añade en la posicion replace del programa
	 si alguna de las condiciones falla se devuelbe false
	 */
	
	
	public boolean executeRun() {
		int contador = 0;
		boolean executeRun = true;
		boolean halt = false;
		ByteCode instruccion;
		CPU cpu = new CPU();
		while (!halt && contador < this.program.getContador() && executeRun == true) {
			instruccion = this.program.getinstruccion(contador);
			executeRun = cpu.execute(instruccion);
			if (executeRun) {
				System.out.println(toString(contador));
				System.out.println(cpu.toString());
			}
			contador++;
			halt = cpu.getHalt();
		}
		if (!executeRun) {
			System.out.println("Error : Ejecución incorrecta del comando " + System.lineSeparator());
		}
		return executeRun;
	}

	/*
	 Crea una cpu y mientras que no sea halt el contador de instrucciones ejecutadas sea
	 < que el contador del programa y la ultima instrucción en ejecutarse halla sido correcta 
	 ejecuta la instrucción de la cpu,se incrementa el contador y se comprueba que la ultima instrucción no es halt
	 */
	
	
	public String toString(int contador) {
		String s = null;
		s = "El estado de la máquina tras ejecutar el bytecode " + this.program.getinstruccion(contador) + " es:";
		return s;
	}
}
