package tp.pr2.cm;

import tp.pr2.mv.Engine;


public class Help extends Command {

	@Override
	/**
	 * llama al executeHelp de engine
	 */
	public boolean execute(Engine engine) {
		return engine.executeHelp();
	}

	@Override
	/**
	 * muestra lo que hace el comando
	 */
	public String textHelp() {
		return "HELP: Muestra esta ayuda" + System.lineSeparator();
	}

	@Override
	/**
	 * parsea el array de string, si su longitud es 1 y es Help, lo crea y lo devuelve
	 */
	public Command parse(String[] s) {
			if (s.length==1 && s[0].equalsIgnoreCase("HELP"))
			return new Help();
			else return null;
	}

}
