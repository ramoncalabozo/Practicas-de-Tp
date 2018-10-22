package tp.pr2.cm;

import tp.pr2.mv.Engine;

public class Run extends Command {

	@Override
	/**
	 * Llama al executeRun de engine
	 */
	public boolean execute(Engine engine) {
		return engine.executeRun();
	}

	@Override
	/**
	 * Muestra por pantalla lo que hace el comando
	 */
	public String textHelp() {
		return "RUN: Ejecuta el programa" +
				System.getProperty("line.separator");
	}

	@Override
	/**
	 * Parsea el comando, si la longitud es de 1 y es RUN lo crea y lo devuelve
	 */
	public Command parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("RUN"))
			return new Run();
			else return null;
	}

}
