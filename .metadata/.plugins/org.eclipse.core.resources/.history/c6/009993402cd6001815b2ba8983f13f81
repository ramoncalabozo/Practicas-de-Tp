package tp.pr1.mv;

public class Command {
	private ENUM_COMMANDO command;
	private ByteCode instruction;// Sólo para NEWINST 
	private int replace; // Sólo para el REPLACE

	Command(ENUM_COMMANDO command) {
		this.command = command;
	}

	Command(ENUM_COMMANDO command, ENUM_BYTECODE inst, int param) {
		this.command = command;
		this.instruction = new ByteCode(inst, param);
	}

	Command(ENUM_COMMANDO command, int replace) {
		this.command = command;
		this.replace = replace;
	}

	public ByteCode getinstruccion() {
		return this.instruction;
	}
	// Devevbe la instruccion del comando
	
	
	public ENUM_COMMANDO getcomando() {
		return this.command;
	}
	
	// Devuelve el enumerado del comando
	
	public int getreplace() {
		return this.replace;
	}
	public String toString() {
		String s;
		s = "Comienza la ejecucion de ";
		s = s + this.command;
		if(this.instruction/*.getname()*/ != null){
			if (this.instruction.getname() != null){
			s = s + " " + this.instruction.toString();
			}
		}
		if(this.replace != 0){
			s = s + " " + this.replace;
		}
		return s;
	}
	
	public boolean execute(Engine engine) {
		boolean result = false;
		switch(getcomando())
		{
		case HELP:
			result = engine.executeHelp();
			break;
		case QUIT:
			result = engine.executeQuit();
			break;
		case NEWINST:
			result = engine.executeNewinst(instruction);
			break;
		case RUN:
			result = engine.executeRun();
			break;
		case RESET:
			result = engine.executeReset();
			break;
		case REPLACE:
			result = engine.executeReplace(this.replace);
			break;
		}
		return result;
	}
	
	/*
	 Coge el comando del programa y ejecuta el comando 
	 */
}
