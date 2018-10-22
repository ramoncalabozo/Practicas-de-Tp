package tp.pr2.cm;


import tp.pr2.mv.Engine;


public class AddByteCodeProgram extends Command {

	@Override
	/**
	 * llama al readByteCodeProgram de engine
	 */
	public boolean execute(Engine engine) {
		return engine.readByteCodeProgram();
	}

	@Override
	/***
	 * muestra lo que hace el comando
	 */
	public String textHelp() {
		return "BYTECODE : Introduce instrucciones hasta que se introduce la palabra end " +
				System.getProperty("line.separator") ;
	}

	@Override
	/**
	 * parsea el comando,si tiene longitud = 1 y es bytecode, entonces lo crea
	 */
	public Command parse(String[] s) {
		if (s.length ==1 && s[0].equalsIgnoreCase("BYTECODE")){
			return new AddByteCodeProgram();
			}
			else{
				return null;
			}
		}
	}
