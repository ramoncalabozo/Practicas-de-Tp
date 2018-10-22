package tp.pr2.cm;

import tp.pr2.mv.Engine;


public class Reset extends Command {

	@Override
	/**
	 * llama al executeReset de engine
	 */
	public boolean execute(Engine engine) {
		return engine.executeReset();
	}

	@Override
	/**
	 * Muestra por pantalla lo que hace el comando
	 */
	public String textHelp() {
		return "RESET: Vacia el programa actual " +
				System.getProperty("line.separator");
	}

	@Override
	/**
	 * Parsea el array de String, si su longitud es 1 y es reset, lo crea y lo devuelve
	 */
	public Command parse(String[] s) {
			if (s.length==1 && s[0].equalsIgnoreCase("RESET"))
			return new Reset();
			else return null;
		}
}
