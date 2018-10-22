package tp.pr1.mv;

public class ByteCodeParser {

	public static ByteCode parse(String line) {
		ByteCode instruction;
		String lines;
		lines = line.trim();// Hago que el String line le quito los espacios en blancos
					// iniciales
		String[] arrayLine = lines.split(" +");
		int param = 0;
		if (arrayLine.length < 3) {
			if (arrayLine.length == 2) {
				if (!isNumeric(arrayLine[1])){//si el segundo elemento del array no es un numero la instrucción es null
					instruction = null;
				}
				else{
				param = Integer.parseInt(arrayLine[1]);//combierto el segundo elemto del array de String a Integer
				if (arrayLine[0].equalsIgnoreCase("PUSH")) {
					instruction = new ByteCode(ENUM_BYTECODE.PUSH, param);
				} else if (arrayLine[0].equalsIgnoreCase("LOAD")) {
					instruction = new ByteCode(ENUM_BYTECODE.LOAD, param);
				} else if (arrayLine[0].equalsIgnoreCase("STORE")) {
					instruction = new ByteCode(ENUM_BYTECODE.STORE, param);
				} else {
					instruction = null;
				}
				}
			} else {//Son los que estan formados por un sólo elemento
				if (arrayLine[0].equalsIgnoreCase("ADD")) {
					instruction = new ByteCode(ENUM_BYTECODE.ADD);
				} else if (arrayLine[0].equalsIgnoreCase("SUB")) {
					instruction = new ByteCode(ENUM_BYTECODE.SUB);
				} else if (arrayLine[0].equalsIgnoreCase("MUL")) {
					instruction = new ByteCode(ENUM_BYTECODE.MUL);
				} else if (arrayLine[0].equalsIgnoreCase("DIV")) {
					instruction = new ByteCode(ENUM_BYTECODE.DIV);
				} else if (arrayLine[0].equalsIgnoreCase("OUT")) {
					instruction = new ByteCode(ENUM_BYTECODE.OUT);
				} else if (arrayLine[0].equalsIgnoreCase("HALT")) {
					instruction = new ByteCode(ENUM_BYTECODE.HALT);
				} else {
					instruction = null;
				}
			}
		} else {
			instruction = null;
		}
		return instruction;
	}
	
	// Creo la instrucción si los datos que me llegan son correctos
	
	private static boolean isNumeric(String cadena){
	    	try {
	    		Integer.parseInt(cadena);
	    		return true;
	    	} catch (NumberFormatException nfe){
	    		return false;
	    	}
	//Está funcion permite saber si lo que le llega es un número o no
	
	}
}