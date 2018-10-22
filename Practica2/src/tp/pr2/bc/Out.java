package tp.pr2.bc;
// Está clase ya está hecha
import tp.pr2.cpu.CPU;

public class Out extends ByteCode {

	@Override
	/**
	 * llama a out de cpu y a next
	 */
	public boolean execute(CPU cpu) {
		cpu.next();
		return cpu.out();
	}

	@Override
	/**
	 * parsea el string, si tiene longitud 1 y es out, crea el bytecode
	 */
	public ByteCode parse(String[] s) {
		if (s.length !=1){
		return null;
		}
		else{
			if (s[0].equalsIgnoreCase("OUT")){
				return new Out();
			}
			else{
				return null;
			}
		}
	}
public String toString (){
	return "out";
}
}
