package tp.pr2.cm;

import tp.pr2.mv.Engine;

/**
 * Tiene como atributo la posicion donde quieres sustituir por  la nueva instruccion
 * @author salva
 *
 */
public class Replace extends Command {
	private int parametro;
	public Replace (int parametro){
		this.parametro = parametro;
	}
	@Override
	/**
	 * llama al executeReplace de engine
	 */
	public boolean execute(Engine engine) {
		return engine.executeReplace(parametro);
	}

	@Override
	/**
	 * Muestra lo que hace el programa
	 */
	public String textHelp() {
		return "REPLACE N: Reemplaza la instruccion N por la solicitada al usuario " +
				System.getProperty("line.separator");
	}

	@Override
	/**
	 * parsea el array de string, si es dos la longitud y es Replace, entonces lo crea y lo devuelve
	 */
	public Command parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("REPLACE"))
			return new Replace(Integer.valueOf(s[1]));
			else return null;
	}

}
