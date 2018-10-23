package tp.pr2.cm;


import tp.pr2.exceptions.ArrayException;
import tp.pr2.exceptions.BadFormatByteCodeException;

import tp.pr2.mv.Engine;

/**
 * Tiene como atributo la posicion donde quieres sustituir por  la nueva instruccion
 * @author salva
 *
 */
public class Replace implements Command {
	private int parametro;
	
	public Replace (int parametro){
		this.parametro = parametro;
	}
	@Override
	/**
	 * llama al executeReplace de engine
	 */
	public void execute(Engine engine) throws BadFormatByteCodeException,ArrayException{
		  engine.executeReplace(parametro);
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
		int auxiliar;
		if (s.length==2 && s[0].equalsIgnoreCase("REPLACE")){
			try{
			auxiliar = Integer.valueOf(s[1]);
			return new Replace(auxiliar);
			}
			catch (NumberFormatException e){
			//System.out.println(s[1] + " no es un número");
			throw new NumberFormatException (s[1] + " no es un número");
			}
			//return new Replace(auxiliar);
			}
			else return null;
	}

}
